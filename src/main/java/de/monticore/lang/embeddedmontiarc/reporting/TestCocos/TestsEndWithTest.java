package de.monticore.lang.embeddedmontiarc.reporting.testCocos;

import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.GitHubHelper;
import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.SearchFiles;
import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.TestResultPrinter;
import de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper.TestsEndWithTestResult;
import de.se_rwth.commons.logging.Log;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class TestsEndWithTest {

    public static void main(String[] args){
        if(args.length == 0)
            Log.error("Please enter a path.");
        String path = args[0];

        File root = new File(path);
        if(!root.exists())
            Log.error("Path does not exist");

        TestsEndWithTest tewt = new TestsEndWithTest();
        List<TestsEndWithTestResult> testResults = tewt.testTestsEndWithTest(root);

        TestResultPrinter.printTestsEndWithTestResults(testResults, "C:/Praktikum/report/dataEWT.json");
    }

    public List<TestsEndWithTestResult> testTestsEndWithTest(File projectRoot){
        List<TestsEndWithTestResult> testResults = new LinkedList<>();

        GitHubHelper ghh = new GitHubHelper();

        for(File project: projectRoot.listFiles()) {
            File testDirectory = new File(project.getAbsoluteFile() + "/src/test/java");
            if(testDirectory.exists()){
                String gitHubRoot = ghh.getGitHubRoot(project);
                List<File> files = SearchFiles.searchFiles(testDirectory, "java");
                for(File file: files) {
                    String htmlTag = ghh.getHTMLTagOf(project, file, gitHubRoot);
                    if(file.getName().substring(0, file.getName().length() - ".java".length()).endsWith("Test"))
                        testResults.add(new TestsEndWithTestResult(htmlTag, true));
                    else
                        testResults.add(new TestsEndWithTestResult(htmlTag, false));
                }
            }
        }

        return testResults;
    }
}
