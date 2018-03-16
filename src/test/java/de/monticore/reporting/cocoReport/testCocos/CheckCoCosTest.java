package de.monticore.reporting.cocoReport.testCocos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckCoCosTest {


//    @Ignore
//    @Test
//    public void testCoCosTester() throws IOException {
//        int modelNumber = 126;
//        int validNumber = 78;
//        int notParsedNumber = 24;
//        int notResolvedNumber = 11;
//        String root = "src/test/";
//        String zipDummy = "modelsDummy.zip";
//        String comparisonResultsPath = "src/test/resources/comparisonResults/comparisonResults.json";
//        String comparisonResultsMergedPath = "src/test/resources/comparisonResults/comparisonResultsMerged.json";
//        String resultPath = "target/generatedTestResults/results.json";
//
//        File   rootFile = new File(root);
//        CheckCoCos tcc = new CheckCoCos();
//        List<CheckCoCoResult> testResults = tcc.testAllCocos(rootFile, zipDummy, "emam", "ema");
//        int size = testResults.size();
//        assertTrue("Models found: " + size + " ; Models expected: " + modelNumber, size == modelNumber);
//
//        int valid = 0;
//        int parse = 0;
//        int resolve = 0;
//
//        for(CheckCoCoResult testResult: testResults) {
//            if(testResult.isValid()) valid ++;
//            if(testResult.getParsed() == -1) parse++;
//            if(testResult.getResolved() == -1) resolve++;
//        }
//
//        assertTrue("Valid models: " + valid + " ; expected: " + validNumber, valid == validNumber);
//        assertTrue("Models not correctly parsed: " + parse + " ; expected: " + notParsedNumber, parse == notParsedNumber);
//        assertTrue("Models not correctly resolved: " + resolve + " ; expected: " + notResolvedNumber, resolve == notResolvedNumber);
//
//        TestResultPrinter.printTestResults(testResults, resultPath, false);
//        String generatedTestResults = FileUtils.readFileToString(new File(resultPath));
//        TestResultPrinter.printTestResults(testResults, resultPath, true);
//        String generatedTestResultsMerged = FileUtils.readFileToString(new File(resultPath));
//        String comparisonResults = FileUtils.readFileToString(new File(comparisonResultsPath));
//        String comparisonResultsMerged = FileUtils.readFileToString(new File(comparisonResultsMergedPath));
//
//        assertTrue("Did not find comparison file", !comparisonResults.equals(""));
//        assertTrue("Did not find comparison_merged file", !comparisonResultsMerged.equals(""));
//        assertTrue("Did not find generated files", !generatedTestResults.equals(""));
//        assertTrue("Did not find generated_merged files", !generatedTestResultsMerged.equals(""));
//        boolean notMerged = defaulting(comparisonResults).equals(defaulting(generatedTestResults));
//        boolean merged = defaulting(comparisonResultsMerged).equals(defaulting(generatedTestResultsMerged));
//        assertTrue("Generated test results are not correct", notMerged);
//        assertTrue("Generated merged test results are not correct", merged);
//    }
//
//    private String defaulting(String str){
//        return new String(str.getBytes(Charset.defaultCharset()));
//    }
}