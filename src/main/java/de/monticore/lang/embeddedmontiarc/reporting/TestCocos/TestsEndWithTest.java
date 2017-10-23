package de.monticore.lang.embeddedmontiarc.reporting.TestCocos;

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

        List<TestsEndWithTestResult> testResults = new LinkedList<>();

        GitHubHelper ghh = new GitHubHelper();

        for(File project: root.listFiles()) {
            File testDirectory = new File(project.getAbsoluteFile() + "/src/test/java");
            if(testDirectory.exists()){
                String gitHubRoot = ghh.getGitHubRoot(project);
                List<File> files = SearchFiles.searchFiles(testDirectory, "java");
                for(File file: files) {
//                    String name = file.getAbsolutePath().substring(project.getAbsolutePath().length()+1).replace("\\","/");
//                    String gitHubLink = gitHubRoot + name;
//                    String compName = file.getAbsolutePath().substring(path.length()+1);
                    String htmlTag = ghh.getHTMLTagOf(project, file, gitHubRoot);
                    if(file.getName().substring(0, file.getName().length() - ".java".length()).endsWith("Test"))
                        testResults.add(new TestsEndWithTestResult(htmlTag, true));
                    else
                        testResults.add(new TestsEndWithTestResult(htmlTag, false));
                }
            }
        }

        TestResultPrinter.printTestsEndWithTestResults(testResults);
    }
}
