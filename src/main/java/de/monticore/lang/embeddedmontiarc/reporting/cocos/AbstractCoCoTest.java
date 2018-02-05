/**
 *
 *  ******************************************************************************
 *  MontiCAR Modeling Family, www.se-rwth.de
 *  Copyright (c) 2017, Software Engineering Group at RWTH Aachen,
 *  All rights reserved.
 *
 *  This project is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 3.0 of the License, or (at your option) any later version.
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * *******************************************************************************
 */
package de.monticore.lang.embeddedmontiarc.reporting.cocos;

import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._ast.ASTEmbeddedMontiArcNode;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._symboltable.ComponentSymbol;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath._cocos.EmbeddedMontiArcMathCoCoChecker;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath.cocos.EmbeddedMontiArcMathCoCos;
import de.monticore.lang.embeddedmontiarc.reporting.AbstractSymtabTest;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.logging.Finding;
import de.se_rwth.commons.logging.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author (last commit) Crispin Kirchner
 */
public class AbstractCoCoTest extends AbstractSymtabTest {
  
  private static final String MODEL_PATH = "src/test/resources/";
  
  protected static ASTEmbeddedMontiArcNode getAstNode(String modelPath, String model) {
    
    Scope symTab = createSymTab(MODEL_PATH + modelPath);
    ComponentSymbol comp = symTab.<ComponentSymbol> resolve(
        model, ComponentSymbol.KIND).orElse(null);
    
    return (ASTEmbeddedMontiArcNode) comp.getAstNode().get();
  }
  
  /**
   * Checks all cocos on the given model. Don't use for writing new test cases, use checkValid and
   * checkInvalid instead.
   */
  @Deprecated
  protected static void runCheckerWithSymTab(String modelPath, String model) {
    Log.getFindings().clear();
    
    ASTEmbeddedMontiArcNode node = getAstNode(modelPath, model);

    EmbeddedMontiArcMathCoCos.createChecker().checkAll(node);
  }
  
  /**
   * Checks all cocos on the given node, and checks for absence of errors. Use this for checking
   * valid models.
   */
  protected static void checkValid(String modelPath, String model) {
    Log.getFindings().clear();
    EmbeddedMontiArcMathCoCos.createChecker().checkAll(getAstNode(modelPath, model));
    new ExpectedErrorInfo().checkOnlyExpectedPresent(Log.getFindings());
  }
  
  /**
   * Runs coco checks on the model with two different coco sets: Once with all cocos, checking that
   * the expected errors are present; once only with the given cocos, checking that no addditional
   * errors are present.
   */
  protected static void checkInvalid(EmbeddedMontiArcMathCoCoChecker cocos, ASTEmbeddedMontiArcNode node,
                                     ExpectedErrorInfo expectedErrors) {
    
    // check whether all the expected errors are present when using all cocos
    Log.getFindings().clear();
    EmbeddedMontiArcMathCoCos.createChecker().checkAll(node);
    expectedErrors.checkExpectedPresent(Log.getFindings(), "Got no findings when checking all "
        + "cocos. Did you forget to add the new coco to MontiArcCocos?");
    
    // check whether only the expected errors are present when using only the given cocos
    Log.getFindings().clear();
    cocos.checkAll(node);
    expectedErrors.checkOnlyExpectedPresent(Log.getFindings(), "Got no findings when checking only "
        + "the given coco. Did you pass an empty coco checker?");
  }
  
  protected static class ExpectedErrorInfo {
    private static final Pattern ERROR_CODE_PATTERN = Pattern.compile("x[0-9A-F]{5}");
    
    private int numExpectedFindings = 0;
    
    private HashSet<String> expectedErrorCodes = new HashSet<>();
    
    private Predicate<String> containsExpectedErrorCode;
    
    /**
     * Raises an error if the given error codes don't match the convention for error codes in test
     * cases (no leading zero, capital hexadecimal digits)
     */
    protected static void checkExpectedErrorCodes(String[] errorCodes) {
      
      for (String errorCode : errorCodes) {
        if (!ERROR_CODE_PATTERN.matcher(errorCode).matches()) {
          Log.error(String.format(
              "The given expected error code \"%s\" is not a valid error code (pattern: \"%s\")",
              errorCode, ERROR_CODE_PATTERN.pattern()));
        }
      }
    }
    
    protected static Set<String> collectErrorCodes(String findings) {
      Matcher matcher = ERROR_CODE_PATTERN.matcher(findings);
      
      Set<String> errorCodes = new HashSet<>();
      while (matcher.find()) {
        errorCodes.add(matcher.group());
      }
      
      return errorCodes;
    }
    
    private void initContainsExpectedErrorCode() {
      containsExpectedErrorCode = new Predicate<String>() {
        
        @Override
        public boolean test(String s) {
          for (String errorCode : expectedErrorCodes) {
            if (s.contains(errorCode)) {
              return true;
            }
          }
          
          return false;
        }
      };
    }
    
    public ExpectedErrorInfo() {
      this(0);
    }
    
    public ExpectedErrorInfo(int numExpectedFindings, String... expectedErrorCodes) {
      checkExpectedErrorCodes(expectedErrorCodes);
      
      this.numExpectedFindings = numExpectedFindings;
      this.expectedErrorCodes.addAll(Arrays.asList(expectedErrorCodes));
      
      initContainsExpectedErrorCode();
    }
    
    private String concatenateFindings(List<Finding> findings) {
      return findings.stream().map(f -> f.buildMsg())
          .collect(Collectors.joining("\n"));
    }
    
    public void checkExpectedPresent(List<Finding> findings, String emptyFindingsHint) {
      String findingsString = concatenateFindings(findings);
      
      if (findingsString.isEmpty()) {
        findingsString = emptyFindingsHint;
      }
    }
    
    public void checkOnlyExpectedPresent(List<Finding> findings) {
      checkOnlyExpectedPresent(findings, "");
    }
    
    public void checkOnlyExpectedPresent(List<Finding> findings, String emptyFindingsHint) {
      checkExpectedPresent(findings, emptyFindingsHint);
      
      checkNoAdditionalErrorCodesPresent(concatenateFindings(findings));
    }
    
    private void checkNoAdditionalErrorCodesPresent(String findingsString) {
      Set<String> actualErrorCodes = collectErrorCodes(findingsString);
      
      // check whether there are unexpected error codes
      Set<String> unexpectedErrorCodes = new HashSet<>(actualErrorCodes);
      unexpectedErrorCodes.removeAll(expectedErrorCodes);

    }
  }
}
