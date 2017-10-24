<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>DynTable</title>
  <script src="jspkg-archive\jquery-3.2.1.js"></script>
  <script src="jspkg-archive\jquery.dynatable.js"></script>
  <link rel="stylesheet" media="all" href="css/bootstrap-2.3.2.min.css">
  <link rel="stylesheet" media="all" href="css/application.css">
  <link rel="stylesheet" media="all" href="css/dynatableORG.css">
  <link rel="stylesheet" media="all" href="css/dynatable.css">
</head>
<body>
  <div class="myTableContainer">
    <table id="my-table" class="table table-bordered responsive-table">
      <thead>
        <th data-dynatable-column="Path"><div><span>Path</span></div></th>
        <th data-dynatable-column="FileType"><div><span>FileType</span></div></th>
        <th data-dynatable-column="Valid"><div><span>Valid</span></div></th>
        <th data-dynatable-column="Parse"><div><span>Parse</span></div></th>
        <th data-dynatable-column="Resolve"><div><span>Resolve</span></div></th>
        <th data-dynatable-column="ComponentCapitalized"><div><span>Component Capitalized</span></div></th>
        <th data-dynatable-column="ComponentInstanceNamesUnique"><div><span>Component Instance Names Unique</span></div></th>
        <th data-dynatable-column="ComponentWithTypeParametersHasInstance"><div><span>Component With Type Parameters Has Instance</span></div></th>
        <th data-dynatable-column="ConnectorEndPointCorrectlyQualified"><div><span>Connector End Point Correctly Qualified</span></div></th>
        <th data-dynatable-column="DefaultParametersHaveCorrectOrder"><div><span>Default Parameters Have Correct Order</span></div></th>
        <th data-dynatable-column="InPortUniqueSender"><div><span>In Port Unique Sender</span></div></th>
        <th data-dynatable-column="PackageLowerCase"><div><span>Package Lower Case</span></div></th>
        <th data-dynatable-column="ParameterNamesUnique"><div><span>Parameter Names Unique</span></div></th>
        <th data-dynatable-column="PortTypeOnlyBooleanOrSIUnit"><div><span>Port Type Only Boolean Or SIUnit</span></div></th>
        <th data-dynatable-column="PortUsage"><div><span>Port Usage</span></div></th>
        <th data-dynatable-column="ReferencedSubComponentExists"><div><span>Referenced Sub Component Exists</span></div></th>
        <th data-dynatable-column="SimpleConnectorSourceExists"><div><span>Simple Connector Source Exists</span></div></th>
        <th data-dynatable-column="SourceTargetNumberMatch"><div><span>Source Target Number Match</span></div></th>
        <th data-dynatable-column="SubComponentsConnected"><div><span>Sub Components Connected</span></div></th>
        <th data-dynatable-column="TopLevelComponentHasNoInstanceName"><div><span>Top Level Component Has No Instance Name</span></div></th>
        <th data-dynatable-column="TypeParameterNamesUnique"><div><span>Type Parameter Names Unique</span></div></th>
        <th data-dynatable-column="AtomicComponent"><div><span>Atomic Component</span></div></th>
      </thead>
      <tbody>
      </tbody>
    </table>
  <div>
     
  <script>    
    function loadJSON(file,callback) {   
      var xobj = new XMLHttpRequest();
      xobj.overrideMimeType('application/json');
      xobj.open('GET', file, true); 
      xobj.onreadystatechange = function () {
        if (xobj.readyState == 4 && xobj.status == '200') {
        // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
          callback(xobj.responseText);
         }
      };
      xobj.send(null);  
    }
  
    loadJSON("report/data.json", createTable);
    
    function createTable(data) {
      data = JSON.parse(data);
      var table = $('#my-table').dynatable({
        features: {
          paginate: true,
          sort: true,
          pushState: true,
          search: true,
          recordCount: false,
          perPageSelect: true
        },
        dataset: {
          records:
            data
        },
        table: {
          copyHeaderClass: true
        }
      }).data('dynatable');
    };
         
  </script> 
   
</body>
</html>