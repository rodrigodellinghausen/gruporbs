package com.rbs.speedygonzales.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
public class Configuration {

    private String templateBaseDir;
    private String outputBaseDir;
    
    private Map<String,ConfigInput> mapInputs = new HashMap<String, ConfigInput>();
    private List<ConfigOutput> outputs = new ArrayList<ConfigOutput>();

    public String getTemplateBaseDir() {
        return templateBaseDir;
    }

    public void setTemplateBaseDir(String templateBaseDir) {
        this.templateBaseDir = templateBaseDir;
    }

    public String getOutputBaseDir() {
        return outputBaseDir;
    }

    public void setOutputBaseDir(String outputBaseDir) {
        this.outputBaseDir = outputBaseDir;
    }

    public Map<String, ConfigInput> getMapInputs() {
        return mapInputs;
    }

    public void setMapInputs(Map<String, ConfigInput> mapInputs) {
        this.mapInputs = mapInputs;
    }

    
    public List<ConfigOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<ConfigOutput> outputs) {
        this.outputs = outputs;
    }
    
    
}
