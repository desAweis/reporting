package de.monticore.lang.embeddedmontiarc.reporting.testCocos;

import de.monticore.ModelingLanguage;
import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.java.lang.JavaDSLLanguage;
import de.monticore.lang.embeddedmontiarc.LogConfig;
import de.monticore.lang.embeddedmontiarc.cocos.EmbeddedMontiArcCoCos;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._ast.ASTEmbeddedMontiArcNode;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._symboltable.ComponentSymbol;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._symboltable.EmbeddedMontiArcLanguage;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath._ast.ASTEMAMCompilationUnit;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath._parser.EmbeddedMontiArcMathParser;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath._symboltable.EmbeddedMontiArcMathLanguage;

import de.monticore.lang.embeddedmontiarc.reporting.cocos.AbstractCoCoTest;
import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.ResolveSymbolException;
import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.TestResult;
import de.monticore.lang.math.math._symboltable.MathLanguage;
import de.monticore.lang.monticar.stream._symboltable.StreamLanguage;
import de.monticore.symboltable.GlobalScope;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.Joiners;
import de.se_rwth.commons.logging.Log;

import java.nio.file.Paths;
import java.util.stream.Collectors;



public class CoCoTester extends AbstractCoCoTest {
    private static int z = 0;
    private static boolean containsErrorCode(String errorCode) {
        return Log.getFindings().stream().map(s -> s.getMsg()).collect(Collectors.joining(" ")).contains(errorCode);
    }

    public void testModel(String modelPath, String name, String fileName) throws ResolveSymbolException {
        Log.enableFailQuick(false);
        Log.getFindings().clear(); // clear log
        ASTEmbeddedMontiArcNode ast = getAstNode(modelPath, name, fileName);
        EmbeddedMontiArcCoCos.createChecker().checkAll(ast);
    }

    protected static ASTEmbeddedMontiArcNode getAstNode(String modelPath, String model, String fileName) throws ResolveSymbolException {
        ModelingLanguage language = null;
        if(fileName.endsWith(".emam"))
            language = new EmbeddedMontiArcMathLanguage();
        else if(fileName.endsWith(".ema"))
            language = new EmbeddedMontiArcLanguage();
        else if(fileName.endsWith("m"))
            language = new MathLanguage();
        else
            Log.error("Unknown file type: " + model);
        Scope symTab = createSymTab(language, modelPath);
        ComponentSymbol comp = symTab.<ComponentSymbol> resolve(
                model, ComponentSymbol.KIND).orElse(null);
        if(comp == null)
            throw new ResolveSymbolException("Could not resolve model " + model);

        return (ASTEmbeddedMontiArcNode) comp.getAstNode().get();
    }

    protected static Scope createSymTab(ModelingLanguage language, String... modelPath) {
        ModelingLanguageFamily fam = new ModelingLanguageFamily();
        fam.addModelingLanguage(language);

        // TODO should we use JavaDSLLanguage or add the resolvers in MALang?
        fam.addModelingLanguage(new JavaDSLLanguage());
        fam.addModelingLanguage(new StreamLanguage());
        // add default Types which are needed because monticore has integrated "java logic" deep inside
        final ModelPath mp = new ModelPath(Paths.get("src/main/resources/defaultTypes"));
        for (String m : modelPath) {
            mp.addEntry(Paths.get(m));
        }
        GlobalScope scope = new GlobalScope(mp, fam);

        LogConfig.init();
        return scope;
    }

    public TestResult testCoCos(String fileName){
        z++;
        fileName = fileName.replace("\\","/");
        EmbeddedMontiArcMathParser parser = new EmbeddedMontiArcMathParser();
        ASTEMAMCompilationUnit ast = null;
        try {
            ast = parser.parse(fileName).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean parse = ast != null;

        TestResult testResult = new TestResult(fileName);
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
        testResult.setType(fileType);
        testResult.setParsed(parse?1:-1);

        if(parse) {

            if(z == 248)
                System.out.println("FEHLER");

            String PackageName = Joiners.DOT.join(ast.getEMACompilationUnit().getPackage());
            String FileName = fileName.substring(fileName.replace("\\","/").lastIndexOf("/") + 1, fileName.length());
            String modelPath = fileName.substring(0, fileName.length() - (PackageName + "/" + FileName).length()); // package name + File name
            String modelName = PackageName + "." + FileName.replace(".emam", "").replace(".ema", "");
            try {
                System.out.println("Z: " + z);
                testModel(modelPath, modelName, fileName);
                testResult.setResolve(1);
                //} catch (FailedLoadingSymbol|ResolveSymbolException|NullPointerException e){
            } catch (Exception e) {
                e.printStackTrace();
                testResult.setResolve(-1);
                return testResult;
            }
            boolean componentCapitalized = containsErrorCode("0xAC004");
            boolean componentInstanceNamesUnique = containsErrorCode("0xAC010");
            boolean componentWithTypeParametersHasInstance = containsErrorCode("0x79C00");
            boolean connectorEndPointCorrectlyQualified = containsErrorCode("0xDB61C");
            boolean defaultParametersHaveCorrectOrder = containsErrorCode("0xAC005");
            boolean inPortUniqueSender = containsErrorCode("0x2BD7E");
            boolean packageLowerCase = containsErrorCode("0xAC003");
            boolean parameterNamesUnique = containsErrorCode("0xC4A61");
            boolean portTypeOnlyBooleanOrSIUnit = containsErrorCode("0xAE753");
            boolean portUsage = containsErrorCode("0xAC006") || containsErrorCode("0xAC007");
            boolean referencedSubComponentExists = containsErrorCode("0x069B7");
            boolean simpleConnectorSourceExists = containsErrorCode("0x9AF6C") || containsErrorCode("0xBEA8B") || containsErrorCode("0xF4D71");
            boolean sourceTargetNumberMatch = containsErrorCode("0xJK901");
            boolean subComponentsConnected = containsErrorCode("0xAC008") || containsErrorCode("0xAC009");
            boolean topLevelComponentHasNoInstanceName = containsErrorCode("0xE51E8") || containsErrorCode("0x3F207");
            boolean typeParameterNamesUnique = containsErrorCode("0x35F1A");
            boolean uniquePorts = containsErrorCode("0xAC002");
            boolean atomicComponent = containsErrorCode("0x00000AE1");

            if(fileType.equals("EMA") || fileType.equals("EMAM")){
                testResult.setComponentCapitalized(componentCapitalized?-1:1);
                testResult.setComponentInstanceNamesUnique(componentInstanceNamesUnique?-1:1);
                testResult.setComponentWithTypeParametersHasInstance(componentWithTypeParametersHasInstance?-1:1);
                testResult.setConnectorEndPointCorrectlyQualified(connectorEndPointCorrectlyQualified?-1:1);
                testResult.setDefaultParametersHaveCorrectOrder(defaultParametersHaveCorrectOrder?-1:1);
                testResult.setInPortUniqueSender(inPortUniqueSender?-1:1);
                testResult.setPackageLowerCase(packageLowerCase?-1:1);
                testResult.setParameterNamesUnique(parameterNamesUnique?-1:1);
                testResult.setPortTypeOnlyBooleanOrSIUnit(portTypeOnlyBooleanOrSIUnit?-1:1);
                testResult.setPortUsage(portUsage?-1:1);
                testResult.setReferencedSubComponentExists(referencedSubComponentExists?-1:1);
                testResult.setSimpleConnectorSourceExists(simpleConnectorSourceExists?-1:1);
                testResult.setSourceTargetNumberMatch(sourceTargetNumberMatch?-1:1);
                testResult.setSubComponentsConnected(subComponentsConnected?-1:1);
                testResult.setTopLevelComponentHasNoInstanceName(topLevelComponentHasNoInstanceName?-1:1);
                testResult.setTypeParameterNamesUnique(typeParameterNamesUnique?-1:1);
                testResult.setUniquePorts(uniquePorts?-1:1);
            }

            if(fileType.equals("EMAM"))
                testResult.setAtomicComponent(atomicComponent?-1:1);
        }
        return testResult;
    }
}
