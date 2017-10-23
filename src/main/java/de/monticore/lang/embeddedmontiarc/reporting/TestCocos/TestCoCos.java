package de.monticore.lang.embeddedmontiarc.reporting.TestCocos;

import de.se_rwth.commons.logging.Log;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class TestCoCos {

    public static void main(String[] args){
        if(args.length == 0)
            Log.error("Program needs projects root as argument.");
        File root = new File(args[0]);
        if(!root.exists())
            Log.error("Cannot find dir: " + root.getAbsolutePath());

        Log.enableFailQuick(false);

        List<TestResult> testResults = new LinkedList<>();

        TestCoCos testCoCos = new TestCoCos();
        for(File project: root.listFiles()){
            if(project.isDirectory()){
                testResults.addAll(testCoCos.testAllCocos(project, "emam", "m"));
            }
        }


        TestResultPrinter.printTestResults(testResults);
    }

    public List<TestResult> testAllCocos(String projectDir, String... fileType){
        return testAllCocos(new File(projectDir), fileType);
    }

    public List<TestResult> testAllCocos(File projectDir, String... fileType){
        GitHubHelper ghh = new GitHubHelper();
        String gitHubRoot = ghh.getGitHubRoot(projectDir);
        List<File> files = SearchFiles.searchFiles(projectDir, fileType);

        List<TestResult> testResults = new LinkedList<>();
        Log.enableFailQuick(false);
        for(File file: files){
            CoCoTester ccT = new CoCoTester();
            TestResult testResult = null;

            testResult = ccT.testCoCos(file.getAbsolutePath());
            testResult.setPath(ghh.getHTMLTagOf(projectDir, file, gitHubRoot));

            testResults.add(testResult);
        }

        return testResults;
    }
}
