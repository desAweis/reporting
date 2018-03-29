package de.monticore.reporting.cocoReport.helper;

import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._ast.ASTEmbeddedMontiArcNode;
import de.monticore.lang.embeddedmontiarc.embeddedmontiarcmath._ast.ASTEMAMCompilationUnit;
import de.monticore.reporting.order.ChildElement;
import de.monticore.reporting.svgTools.SVGInfo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CheckCoCoResult extends SVGInfo {

//    private File modelFile;
//    private File projectFile;
//    private File rootFile;
//
//    private String modelPath = "";
//    private String emaModelPath = "";
//    private boolean emaModelPathAvailable = false;
//    private String qualifiedName = "";
//    private String modelName = "";
//    private String pathToFile = "";
//    private String project = "";
//    private String fileType = "";
//    private String svgPath = "";
    private String onlineIDE = "";
    private String zipName = "";
//    private List<String> errorMessage = new LinkedList<>();
//    private int parsed = 0;
//    private int resolved = 0;
    private boolean atomic = false;
//    private String emaToPrint = "";

//    private ASTEMAMCompilationUnit unresolvedAST = null;
//    private ASTEmbeddedMontiArcNode resolvedAST = null;

//    private List<CheckCoCoResult> parents = new LinkedList<>();
//    private List<ChildElement> children = new LinkedList<>();

    private String githubBranch = "";

    private int componentCapitalized = 0;
    private int componentInstanceNamesUnique = 0;
    private int componentWithTypeParametersHasInstance = 0;
    private int connectorEndPointCorrectlyQualified = 0;
    private int defaultParametersHaveCorrectOrder = 0;
    private int inPortUniqueSender = 0;
    private int packageLowerCase = 0;
    private int parameterNamesUnique = 0;
    private int portTypeOnlyBooleanOrSIUnit = 0;
    private int portUsage = 0;
    private int referencedSubComponentExists = 0;
    private int simpleConnectorSourceExists = 0;
    private int sourceTargetNumberMatch = 0;
    private int subComponentsConnected = 0;
    private int topLevelComponentHasNoInstanceName = 0;
    private int typeParameterNamesUnique = 0;
    private int uniquePorts = 0;
    private int atomicComponent = 0;

//    private boolean errorResult = false;
//    private boolean mainPackage = false;


    public CheckCoCoResult(String pathToFile) {
        super(pathToFile);
    }

    public String toString() {
        return this.getModelName();
    }

    public boolean isValid() {
        return this.getComponentCapitalized() != -1 && this.getComponentInstanceNamesUnique() != -1 && this.getComponentInstanceNamesUnique() != -1 &&
                this.getConnectorEndPointCorrectlyQualified() != -1 && this.getDefaultParametersHaveCorrectOrder() != -1 &&
                this.getInPortUniqueSender() != -1 && this.getPackageLowerCase() != -1 &&
                this.getParameterNamesUnique() != -1 && this.getPortTypeOnlyBooleanOrSIUnit() != -1 && this.getPortUsage() != -1 &&
                this.getReferencedSubComponentExists() != -1 && this.getSimpleConnectorSourceExists() != -1 && this.getSourceTargetNumberMatch() != -1 &&
                this.getSubComponentsConnected() != -1 && this.getTopLevelComponentHasNoInstanceName() != -1 &&
                this.getTypeParameterNamesUnique() != -1 && this.getUniquePorts() != -1 && this.getParsed() != -1 &&
                this.getResolved() != -1;
    }

    public String getVFSLink() {
        String zipName_ = zipName;

        String urlToZip = "https://raw.githubusercontent.com/EmbeddedMontiArc/reporting/gh-pages/" + zipName_;
        zipName_ = zipName_.substring(0, zipName_.lastIndexOf("."));
        String name = getProject() +
                getModelPath().substring(getModelPath().indexOf(getProject())) +
                getModelName().replace(".", "/") +
                "." + getFileType().toLowerCase();
        String displayName = getModelName();
        return "<a target='_blank' href='onlineIDE/api/load.html?mountPoint=EmbeddedMontiArc/reporting/" + zipName_ + "&url="
                + urlToZip + "&openFile=/" + name + "'>" + displayName + "</a>";
    }


    public int getComponentCapitalized() {
        return componentCapitalized;
    }

    public void setComponentCapitalized(int componentCapitalized) {
        this.componentCapitalized = componentCapitalized;
    }

    public int getComponentInstanceNamesUnique() {
        return componentInstanceNamesUnique;
    }

    public void setComponentInstanceNamesUnique(int componentInstanceNamesUnique) {
        this.componentInstanceNamesUnique = componentInstanceNamesUnique;
    }

    public int getComponentWithTypeParametersHasInstance() {
        return componentWithTypeParametersHasInstance;
    }

    public void setComponentWithTypeParametersHasInstance(int componentWithTypeParametersHasInstance) {
        this.componentWithTypeParametersHasInstance = componentWithTypeParametersHasInstance;
    }

    public int getConnectorEndPointCorrectlyQualified() {
        return connectorEndPointCorrectlyQualified;
    }

    public void setConnectorEndPointCorrectlyQualified(int connectorEndPointCorrectlyQualified) {
        this.connectorEndPointCorrectlyQualified = connectorEndPointCorrectlyQualified;
    }

    public int getDefaultParametersHaveCorrectOrder() {
        return defaultParametersHaveCorrectOrder;
    }

    public void setDefaultParametersHaveCorrectOrder(int defaultParametersHaveCorrectOrder) {
        this.defaultParametersHaveCorrectOrder = defaultParametersHaveCorrectOrder;
    }

    public int getInPortUniqueSender() {
        return inPortUniqueSender;
    }

    public void setInPortUniqueSender(int inPortUniqueSender) {
        this.inPortUniqueSender = inPortUniqueSender;
    }

    public int getPackageLowerCase() {
        return packageLowerCase;
    }

    public void setPackageLowerCase(int packageLowerCase) {
        this.packageLowerCase = packageLowerCase;
    }

    public int getParameterNamesUnique() {
        return parameterNamesUnique;
    }

    public void setParameterNamesUnique(int parameterNamesUnique) {
        this.parameterNamesUnique = parameterNamesUnique;
    }

    public int getPortTypeOnlyBooleanOrSIUnit() {
        return portTypeOnlyBooleanOrSIUnit;
    }

    public void setPortTypeOnlyBooleanOrSIUnit(int portTypeOnlyBooleanOrSIUnit) {
        this.portTypeOnlyBooleanOrSIUnit = portTypeOnlyBooleanOrSIUnit;
    }

    public int getPortUsage() {
        return portUsage;
    }

    public void setPortUsage(int portUsage) {
        this.portUsage = portUsage;
    }

    public int getReferencedSubComponentExists() {
        return referencedSubComponentExists;
    }

    public void setReferencedSubComponentExists(int referencedSubComponentExists) {
        this.referencedSubComponentExists = referencedSubComponentExists;
    }

    public int getSimpleConnectorSourceExists() {
        return simpleConnectorSourceExists;
    }

    public void setSimpleConnectorSourceExists(int simpleConnectorSourceExists) {
        this.simpleConnectorSourceExists = simpleConnectorSourceExists;
    }

    public int getSourceTargetNumberMatch() {
        return sourceTargetNumberMatch;
    }

    public void setSourceTargetNumberMatch(int sourceTargetNumberMatch) {
        this.sourceTargetNumberMatch = sourceTargetNumberMatch;
    }

    public int getSubComponentsConnected() {
        return subComponentsConnected;
    }

    public void setSubComponentsConnected(int subComponentsConnected) {
        this.subComponentsConnected = subComponentsConnected;
    }

    public int getTopLevelComponentHasNoInstanceName() {
        return topLevelComponentHasNoInstanceName;
    }

    public void setTopLevelComponentHasNoInstanceName(int topLevelComponentHasNoInstanceName) {
        this.topLevelComponentHasNoInstanceName = topLevelComponentHasNoInstanceName;
    }

    public int getTypeParameterNamesUnique() {
        return typeParameterNamesUnique;
    }

    public void setTypeParameterNamesUnique(int typeParameterNamesUnique) {
        this.typeParameterNamesUnique = typeParameterNamesUnique;
    }

    public int getUniquePorts() {
        return uniquePorts;
    }

    public void setUniquePorts(int uniquePorts) {
        this.uniquePorts = uniquePorts;
    }

    public int getAtomicComponent() {
        return atomicComponent;
    }

    public void setAtomicComponent(int atomicComponent) {
        this.atomicComponent = atomicComponent;
    }

    @Override
    public String getErrorMessage() {
        if (isErrorResult() || isMainPackage()) return "";

        String msg = "";

        for (String m : getErrorMessages()) {
            if (m != null) {
                String ms = m.replace("\r\n", "<br>")
                        .replace("\n", "<br>")
                        .replace("\r", "<br>")
                        .replace("\t", "&#9;")
                        .replace("\"", "&quot;")
                        .replace("\'", "&prime;")
                        .replace("\\", "&bsol;")
                        .replace("\0", "")
                        + "<br>";
                if (!ms.contains("[ERROR]") && !ms.contains("[INFO]"))
                    ms = "[ERROR] " + ms;
                msg += ms;
            }
        }
        msg = msg.substring(0, msg.length() - 4);

        return msg;
    }

    @Override
    public void setModelPath(String modelPath) {
        String res = modelPath.replace("\\", "/");
        if (res.charAt(res.length() - 1) != '/')
            res = res + "/";
        super.setModelPath(res);
    }

    @Override
    public void setProject(String project) {
        String res = project.replace("\\", "/");
        if (res.charAt(res.length() - 1) != '/')
            res = res + "/";
        super.setProject(res);
    }

    @Override
    public String getSvgPath() {
        if (super.getSvgPath().equals(""))
            return "";

        String str = super.getSvgPath().contains("report/") ? super.getSvgPath().substring("report/".length()) : super.getSvgPath();
        if (str.contains("html"))
            return str.replace("\\", "/");
        else
            return str.replace("\\", "/") + ".html";
    }

    @Override
    public void setSvgPath(String svgPath) {
        super.setSvgPath(svgPath.replace("\\", "/"));
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    public boolean isAtomic() {
        return atomic;
    }

    public void setAtomic(boolean atomic) {
        this.atomic = atomic;
    }

    public String getOnlineIDE() {
        return onlineIDE;
    }

    public void setOnlineIDE(String onlineIDE) {
        this.onlineIDE = onlineIDE;
    }

    public void setChildInfo() {
        CheckCoCoResult mainPackage = this;
        for (ChildElement childElement: getChildren()) {
            CheckCoCoResult child = (CheckCoCoResult) childElement.getChild();

            if (child.getComponentCapitalized() != 0 && child.getComponentCapitalized() < mainPackage.getComponentCapitalized() || mainPackage.getComponentCapitalized() == 0) {
                mainPackage.setComponentCapitalized(child.getComponentCapitalized());
            }
            if (child.getComponentInstanceNamesUnique() != 0 && child.getComponentInstanceNamesUnique() < mainPackage.getComponentInstanceNamesUnique() || mainPackage.getComponentInstanceNamesUnique() == 0) {
                mainPackage.setComponentInstanceNamesUnique(child.getComponentInstanceNamesUnique());
            }
            if (child.getComponentWithTypeParametersHasInstance() != 0 && child.getComponentWithTypeParametersHasInstance() < mainPackage.getComponentWithTypeParametersHasInstance() || mainPackage.getComponentWithTypeParametersHasInstance() == 0) {
                mainPackage.setComponentWithTypeParametersHasInstance(child.getComponentWithTypeParametersHasInstance());
            }
            if (child.getConnectorEndPointCorrectlyQualified() != 0 && child.getConnectorEndPointCorrectlyQualified() < mainPackage.getConnectorEndPointCorrectlyQualified() || mainPackage.getConnectorEndPointCorrectlyQualified() == 0) {
                mainPackage.setConnectorEndPointCorrectlyQualified(child.getConnectorEndPointCorrectlyQualified());
            }
            if (child.getDefaultParametersHaveCorrectOrder() != 0 && child.getDefaultParametersHaveCorrectOrder() < mainPackage.getDefaultParametersHaveCorrectOrder() || mainPackage.getDefaultParametersHaveCorrectOrder() == 0) {
                mainPackage.setDefaultParametersHaveCorrectOrder(child.getDefaultParametersHaveCorrectOrder());
            }
            if (child.getInPortUniqueSender() != 0 && child.getInPortUniqueSender() < mainPackage.getInPortUniqueSender() || mainPackage.getInPortUniqueSender() == 0) {
                mainPackage.setInPortUniqueSender(child.getInPortUniqueSender());
            }
            if (child.getPackageLowerCase() != 0 && child.getPackageLowerCase() < mainPackage.getPackageLowerCase() || mainPackage.getPackageLowerCase() == 0) {
                mainPackage.setPackageLowerCase(child.getPackageLowerCase());
            }
            if (child.getParameterNamesUnique() != 0 && child.getParameterNamesUnique() < mainPackage.getParameterNamesUnique() || mainPackage.getParameterNamesUnique() == 0) {
                mainPackage.setParameterNamesUnique(child.getParameterNamesUnique());
            }
            if (child.getPortTypeOnlyBooleanOrSIUnit() != 0 && child.getPortTypeOnlyBooleanOrSIUnit() < mainPackage.getPortTypeOnlyBooleanOrSIUnit() || mainPackage.getPortTypeOnlyBooleanOrSIUnit() == 0) {
                mainPackage.setPortTypeOnlyBooleanOrSIUnit(child.getPortTypeOnlyBooleanOrSIUnit());
            }
            if (child.getPortUsage() != 0 && child.getPortUsage() < mainPackage.getPortUsage() || mainPackage.getPortUsage() == 0) {
                mainPackage.setPortUsage(child.getPortUsage());
            }
            if (child.getReferencedSubComponentExists() != 0 && child.getReferencedSubComponentExists() < mainPackage.getReferencedSubComponentExists() || mainPackage.getReferencedSubComponentExists() == 0) {
                mainPackage.setReferencedSubComponentExists(child.getReferencedSubComponentExists());
            }
            if (child.getSimpleConnectorSourceExists() != 0 && child.getSimpleConnectorSourceExists() < mainPackage.getSimpleConnectorSourceExists() || mainPackage.getSimpleConnectorSourceExists() == 0) {
                mainPackage.setSimpleConnectorSourceExists(child.getSimpleConnectorSourceExists());
            }
            if (child.getSourceTargetNumberMatch() != 0 && child.getSourceTargetNumberMatch() < mainPackage.getSourceTargetNumberMatch() || mainPackage.getSourceTargetNumberMatch() == 0) {
                mainPackage.setSourceTargetNumberMatch(child.getSourceTargetNumberMatch());
            }
            if (child.getSubComponentsConnected() != 0 && child.getSubComponentsConnected() < mainPackage.getSubComponentsConnected() || mainPackage.getSubComponentsConnected() == 0) {
                mainPackage.setSubComponentsConnected(child.getSubComponentsConnected());
            }
            if (child.getTopLevelComponentHasNoInstanceName() != 0 && child.getTopLevelComponentHasNoInstanceName() < mainPackage.getTopLevelComponentHasNoInstanceName() || mainPackage.getTopLevelComponentHasNoInstanceName() == 0) {
                mainPackage.setTopLevelComponentHasNoInstanceName(child.getTopLevelComponentHasNoInstanceName());
            }
            if (child.getTypeParameterNamesUnique() != 0 && child.getTypeParameterNamesUnique() < mainPackage.getTypeParameterNamesUnique() || mainPackage.getTypeParameterNamesUnique() == 0) {
                mainPackage.setTypeParameterNamesUnique(child.getTypeParameterNamesUnique());
            }
            if (child.getUniquePorts() != 0 && child.getUniquePorts() < mainPackage.getUniquePorts() || mainPackage.getUniquePorts() == 0) {
                mainPackage.setUniquePorts(child.getUniquePorts());
            }
            if (child.getAtomicComponent() != 0 && child.getAtomicComponent() < mainPackage.getAtomicComponent() || mainPackage.getAtomicComponent() == 0) {
                mainPackage.setAtomicComponent(child.getAtomicComponent());
            }
        }
    }

    public String getGithubBranch() {
        return githubBranch;
    }

    public void setGithubBranch(String githubBranch) {
        this.githubBranch = githubBranch;
    }
}
