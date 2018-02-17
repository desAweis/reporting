package de.monticore.lang.embeddedmontiarc.reporting.testCocos.helper;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class TestResult {
    private File filePath;
    private File projectPath;

    private String modelPath = "";
    private String qualifiedName = "";
    private String modelName = "";
    private String path = "";
    private String project = "";
    private String type = "";
    private String svgPath = "";
    private String zipName = "";
    private List<String> errorMessage = new LinkedList<>();
    private int parsed = 0;
    private int resolve = 0;
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




    public TestResult(String path) {
        this.setPath(path);
    }

    public boolean isValid() {
        return this.getComponentCapitalized() != -1 && this.getComponentInstanceNamesUnique() != -1 && this.getComponentInstanceNamesUnique() != -1 &&
                this.getConnectorEndPointCorrectlyQualified() != -1 && this.getDefaultParametersHaveCorrectOrder() != -1 &&
                this.getInPortUniqueSender() != -1 && this.getPackageLowerCase() != -1 &&
                this.getParameterNamesUnique() != -1 && this.getPortTypeOnlyBooleanOrSIUnit() != -1 && this.getPortUsage() != -1 &&
                this.getReferencedSubComponentExists() != -1 && this.getSimpleConnectorSourceExists() != -1 && this.getSourceTargetNumberMatch() != -1 &&
                this.getSubComponentsConnected() != -1 && this.getTopLevelComponentHasNoInstanceName() != -1 &&
                this.getTypeParameterNamesUnique() != -1 && this.getUniquePorts() != -1 && this.getParsed() != -1 &&
                this.getResolve() != -1;
    }

    public String getVFSLink() {
        String zipName_ = zipName;

        String urlToZip = "https://raw.githubusercontent.com/EmbeddedMontiArc/reporting/gh-pages/" + zipName_;
        zipName_ = zipName_.substring(0, zipName_.lastIndexOf("."));
        String name = getProject() +
                getModelPath().substring(getModelPath().indexOf(getProject())) +
                getModelName().replace(".", "/") +
                "." + getType().toLowerCase();
        String displayName = getModelName();
        if (modelPath.contains("MontiSim"))
            displayName = "MontiSim/" + displayName;
        return "<a target='_blank' href='onlineIDE/api/load.html?mountPoint=EmbeddedMontiArc/reporting/" + zipName_ + "&url="
                + urlToZip + "&openFile=/" + name + "'>" + displayName + "</a>";
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParsed() {
        return parsed;
    }

    public void setParsed(int parsed) {
        this.parsed = parsed;
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

    public void setPath(String path) {
        this.path = path;
    }

    public int getResolve() {
        return resolve;
    }

    public void setResolve(int resolve) {
        this.resolve = resolve;
    }

    public void addErrorMessage(String msg) {
        this.errorMessage.add(msg);
    }

    public List<String> getErrorMessages() {
        return this.errorMessage;
    }

    public String getErrorMessage() {
        if (this.getModelName().contains("Block1")) {
            int i = 0;
        }
        String msg = "";


        for (String m : errorMessage) {
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        String res = modelPath.replace("\\", "/");
        if (res.charAt(res.length() - 1) != '/')
            res = res + "/";
        this.modelPath = res;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        String res = project.replace("\\", "/");
        if (res.charAt(res.length() - 1) != '/')
            res = res + "/";
        this.project = res;
    }

    public String getSvgPath() {
        if (svgPath.equals(""))
            return "";
        else
            return svgPath.substring("report/".length());
    }

    public void setSvgPath(String svgPath) {
        this.svgPath = svgPath.replace("\\", "/");
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    public File getFilePath() {
        return filePath;
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

    public File getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(File projectPath) {
        this.projectPath = projectPath;
    }
}
