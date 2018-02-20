(function(f){if(typeof exports==="object"&&typeof module!=="undefined"){module.exports=f()}else if(typeof define==="function"&&define.amd){define([],f)}else{var g;if(typeof window!=="undefined"){g=window}else if(typeof global!=="undefined"){g=global}else if(typeof self!=="undefined"){g=self}else{g=this}g.jsonm = f()}})(function(){var define,module,exports;return (function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
module.exports.Packer = require("./lib/packer").Packer;

module.exports.Unpacker = require("./lib/unpacker").Unpacker;

},{"./lib/packer":2,"./lib/unpacker":3}],2:[function(require,module,exports){
var TYPE_ARRAY = 0;
var TYPE_VALUE = 1;
var TYPE_STRING = 2;
var MIN_DICT_INDEX = 3;

exports.Packer = function(json) {
    
    var dict = [];
    var dictMap = {};
    var dictIndex = MIN_DICT_INDEX;
    var sequenceId = -1;
    var maxDictSize = 2000;
    
    return {
        /**
         * Pack an object.
         * 
         * @param {Object} object
         * @param {Object} [options]
         * @param {Number} [options.packStringDepth]
         *                         Object depth at which to apply packString().
         * @returns {Object}
         */
        pack: pack,
        /**
         * Pack a string. Efficiently packs multi-line strings and JSON strings.
         * When unpacked, a string is always returned again.
         * 
         * @param {String} string
         * @returns {Object}
         */
        packString: packString,
        /**
         * Set the maximum dictionary size. Must match the dictionary size
         * used by the unpacker.
         * 
         * @param value  New dictionary size, default 2000.
         */
        setMaxDictSize: function(value) {
            maxDictSize = value;
        },
        /**
         * @ignore
         */
        $getDict: function() {
            return dict;
        }
    };
    
    function packString(string, options) {
        if (typeof string !== "string")
            return pack(string, options);
        var json;
        try {
            json = JSON.parse(string);
        }
        catch (e) {}
        if (!json) {
            var result = pack(string.split("\n"), options);
            result[0] = TYPE_STRING;
            return result;
        }
        return pack(json, options);
    }
    
    function pack(object, options) {
        if (object == null)
            return object;
        var packStringDepth = options && options.packStringDepth;
        
        var result = packObjectOrValue(object, packStringDepth);
        if (options && options.noSequenceId)
            return result;
        return Array.isArray(result)
            ? result.concat([++sequenceId])
            : [TYPE_VALUE, result, ++sequenceId];

        function packObjectOrValue(object, packStringDepth) {
            if (Array.isArray(object))
                return [TYPE_ARRAY].concat(object.map(function(o) {
                    return packObjectOrValue(o, packStringDepth - 1);
                }));
            if (typeof object === "string" && packStringDepth >= 0)
                return packString(object, { noSequenceId: true});
            if (typeof object !== "object" || object == null)
                return packValue(object);
                
            var results = [];
            
            // Keys
            for (var key in object) {
                if (!object.hasOwnProperty(key)) continue;
                
                results.push(packValue(key));
            }
            
            var isError = packErrorKeys(object, results);
            
            // Values
            for (var key in object) {
                if (!object.hasOwnProperty(key)) continue;
                
                var value = object[key];
                if (typeof value === "object") {
                    results.push(packObjectOrValue(value, packStringDepth - 1));
                }
                else if (typeof value === "string") {
                    results.push(packStringDepth > 0
                        ? packString(value, { noSequenceId: true})
                        : packValue(value));
                }
                else {
                    results.push(packValue(value));
                }
            }
            if (isError)
                results.push(packObjectOrValue(object.message), packObjectOrValue(object.stack));
            
            return results;
        }
        
        function packErrorKeys(object, results) {
            if (object instanceof Error) {
                results.push(packValue("message"), packValue("stack"));
                return true;
            }
        }
                
        function packValue(value) {
            var mapKey = typeof value === "string" ? "_" + value : value;
            var result = dictMap[mapKey];
            if (result == null) {
                addToDict(mapKey, value);
                
                if (typeof value === "number")
                    return String(value);
                if (/^[0-9\.]|^~/.test(value))
                    return "~" + value;
                return value;
            }
            return result;
        }
        
        function addToDict(mapKey, value) {
            if (dict[dictIndex] != null) {
                var deleteKey = typeof dict[dictIndex] === "string" ? "_" + dict[dictIndex] : dict[dictIndex];
                delete dictMap[deleteKey];
            }
            dictMap[mapKey] = dictIndex;
            dict[dictIndex] = value;
            
            dictIndex++;
            if (dictIndex >= maxDictSize + MIN_DICT_INDEX)
                dictIndex = MIN_DICT_INDEX;
        }
    }
};
},{}],3:[function(require,module,exports){
var OLD_MESSAGE = -1;
var TYPE_ARRAY = 0;
var TYPE_VALUE = 1;
var TYPE_STRING = 2;
var MIN_DICT_INDEX = 3;

exports.Unpacker = function() {
    var dict = [];
    var dictIndex = MIN_DICT_INDEX;
    var maxDictSize = 2000;
    var sequenceId = -1;
    var pendingUnpacks = [];
    
    return {
        /**
         * Unpack an packed object to its original input.
         * (If this was a string, a string is returned.)
         * 
         * @param {Object} packed
         * @param {Function} [callback]
         * @param {Error} callback.err
         * @param {Object} callback.result
         * @returns {Object}
         */
        unpack: unpack,
        /**
         * Unpack an object to a string.
         * 
         * @ignore
         * 
         * @param {Object} packed
         * @returns {String}
         */
        unpackString: unpackString,
        /**
         * @ignore
         */
        $getDict: function() {
            return dict;
        },
        /**
         * Set the maximum dictionary size. Must match the dictionary size
         * used by the packer.
         * 
         * @param value  New dictionary size, default 2000.
         */
        setMaxDictSize: function(value) {
            maxDictSize = value;
        }
    };
    
    function unpack(packed, callback) {
        if (typeof packed === "string")
            return unpack(JSON.parse(packed));
        if (!packed)
            return callback ? callback(null, packed) : packed;
        
        // Prepare input
        var remoteSequenceId = packed.pop();
        if (remoteSequenceId === 0) {
            dictIndex = MIN_DICT_INDEX;
        }
        else if (remoteSequenceId !== sequenceId + 1) {
            if (callback && remoteSequenceId > sequenceId + 1) {
                // Try after receiving earlier messages
                packed.push(remoteSequenceId);
                return pendingUnpacks.push(unpack.bind(null, packed, callback));
            }
            
            var error = new Error("Message unpacked out of sequence");
            error.code = "EOLD";
            if (!callback)
                throw error;
            return callback(error);
        }
        sequenceId = remoteSequenceId;
        
        // Unpack
        var result = unpackObject(packed);
        packed.push(OLD_MESSAGE);
        
        // Return results
        if (callback)
            callback(null, result);
        
        if (pendingUnpacks.length) {
            var pending = pendingUnpacks.slice();
            pendingUnpacks = [];
            pending.forEach(function(f) { f(); });
        }
        
        if (!callback)
            return result;
    }
    
    function unpackString(packed) {
        return packed[0] === TYPE_STRING
            ? unpack(packed)
            : JSON.stringify(unpack(packed));
    }
    
    function unpackObject(object) {
        if (typeof object != "object" || object == null)
            return unpackValue(object);
        if (!object)
            return object;
        
        switch (object[0]) {
            case TYPE_ARRAY:
                object.shift();
                return object.map(unpackObject);
            case TYPE_STRING:
                object.shift();
                return object.map(unpackObject).join("\n");
            case TYPE_VALUE:
                return unpackValue(object[1]);
            default:
        }
        
        var result = {};
        for (var i = 0; i < object.length; i++) {
            object[i] = typeof object[i] === "object"
                ? unpackObject(object[i])
                : unpackValue(object[i]);
        }
        var keys = object.length / 2;
        for (var i = 0; i < keys; i++) {
            result[object[i]] = object[i + keys];
        }
        return result;
    }
    
    function unpackValue(value) {
        if (typeof value === "number") {
            return dict[value];
        }
        if (typeof value === "string") {
            if (/^[0-9\.]/.test(value)) {
                var parsed = parseFloat(value);
                if (!isNaN(parsed)) {
                    addToDict(parsed);
                    return parsed;
                }
            }
        }
        if (value && value[0] === "~")
            value = value.substr(1);
        addToDict(value);
        return value;
    }
        
    function addToDict(value) {
        dict[dictIndex] = value;
        dictIndex++;
        if (dictIndex >= maxDictSize + MIN_DICT_INDEX)
            dictIndex = MIN_DICT_INDEX;
        
    }
};
},{}]},{},[1])(1)
});