package de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper;

import de.monticore.lang.montiarc.helper.IndentPrinter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestResultPrinter {

    private static String[] names = {
            "\"Path\"",
            "\"FileType\"",
            "\"Valid\"",
            "\"Parse\"",
            "\"Resolve\"",
            "\"ComponentCapitalized\"",
            "\"ComponentInstanceNamesUnique\"",
            "\"ComponentWithTypeParametersHasInstance\"",
            "\"ConnectorEndPointCorrectlyQualified\"",
            "\"DefaultParametersHaveCorrectOrder\"",
            "\"InPortUniqueSender\"",
            "\"PackageLowerCase\"",
            "\"ParameterNamesUnique\"",
            "\"PortTypeOnlyBooleanOrSIUnit\"",
            "\"PortUsage\"",
            "\"ReferencedSubComponentExists\"",
            "\"SimpleConnectorSourceExists\"",
            "\"SourceTargetNumberMatch\"",
            "\"SubComponentsConnected\"",
            "\"TopLevelComponentHasNoInstanceName\"",
            "\"TypeParameterNamesUnique\"",
            "\"AtomicComponent\""
    };
    private static String tickTag = "\"<img src='images/tick.png'/>\"";
    private static String crossTag = "\"<img src='images/Red_cross_tick.png'/>\"";
    private static String noTag = "\"<img src='images/minus_318-140716.jpg'/>\"";

    private static String tagOf(int i){
        switch(i){
            case -1: return crossTag;
            case 0: return noTag;
            case 1: return tickTag;
            default: return noTag;
        }
    }

    public static void printTestResults(List<TestResult> testResults, String path){
        try {
            FileUtils.writeStringToFile(new File(path),
                    printTestResults(testResults));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String printTestResults(List<TestResult> testResults){
        IndentPrinter ip = new IndentPrinter();
        ip.println("[");
        ip.indent();
        
        boolean first = true;
        for (TestResult testResult: testResults) {
            if(testResult == null) continue;
            int i = 0;
            
            if(!first)
                ip.print(",\n");
            else
                first = false;

            ip.println("{");
            ip.indent();
            ip.println(names[i++] + ": \"" + testResult.getPath().replace("\\","/") + "\",");
            ip.println(names[i++] + ": \"" + testResult.getType() + "\",");
            ip.println(names[i++] + ": " + tagOf(testResult.isValid()?1:-1) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getParsed()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getResolve()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getComponentCapitalized()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getComponentInstanceNamesUnique()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getComponentWithTypeParametersHasInstance()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getConnectorEndPointCorrectlyQualified()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getDefaultParametersHaveCorrectOrder()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getInPortUniqueSender()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getPackageLowerCase()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getParameterNamesUnique()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getPortTypeOnlyBooleanOrSIUnit()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getPortUsage()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getReferencedSubComponentExists()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getSimpleConnectorSourceExists()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getSourceTargetNumberMatch()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getSubComponentsConnected()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getTopLevelComponentHasNoInstanceName()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getTypeParameterNamesUnique()) + ",");
            ip.println(names[i++] + ": " + tagOf(testResult.getAtomicComponent()));
            ip.unindent();
            ip.print("}");
        }
        ip.println();
        ip.unindent();
        ip.println("]");

        return ip.getContent();
    }

    public static void printTestsEndWithTestResults(List<TestsEndWithTestResult> results, String path){
        try {
            FileUtils.writeStringToFile(new File(path), printTestsEndWithTestResults(results));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String printTestsEndWithTestResults(List<TestsEndWithTestResult> results){
        IndentPrinter ip = new IndentPrinter();
        ip.println("[");
        ip.indent();

        boolean first = true;
        for (TestsEndWithTestResult testResult: results) {
            if(testResult == null /*testResult.isValid()*/) continue;

            if(!first)
                ip.print(",\n");
            else
                first = false;

            ip.println("{");
            ip.indent();
            ip.println("\"Path\": \"" + testResult.getPath().replace("\\","/") + "\",");
            ip.println("\"NameEndsWithTest\": " + tagOf(testResult.endsWithTest()?1:-1));
            ip.unindent();
            ip.print("}");
        }
        ip.println();
        ip.unindent();
        ip.println("]");

        return ip.getContent();
    }
}
