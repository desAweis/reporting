package de.monticore.reporting.order;

import de.monticore.reporting.testCocos.helper.CheckCoCoResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ModelWriter {

    public static void writeModels(List<CheckCoCoResult> models, String oldRoot, String newRoot) throws IOException {
        File out = new File(newRoot);
        if(!out.exists())
            out.mkdirs();
        else {
            FileUtils.deleteDirectory(out);
            out.mkdirs();
        }

        for(CheckCoCoResult model: models) {
            String modelPath = model.getPathToFile().substring(model.getRootFile().
                    getAbsolutePath().length() + 1).replace("emam", "ema");
            File modelFile = new File(newRoot + "/" + modelPath);
            if(!modelFile.getParentFile().exists())
                modelFile.getParentFile().mkdirs();
            if(model.isAtomic())
                FileUtils.write(modelFile, model.getEmaToPrint());
            else
                FileUtils.copyFile(new File(model.getPathToFile()), modelFile);
            model.setEmaModelPath(model.getModelPath().replace("\\","/").replace(oldRoot, newRoot));

        }
    }

}