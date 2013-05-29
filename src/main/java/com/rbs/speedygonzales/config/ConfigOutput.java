package com.rbs.speedygonzales.config;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
class ConfigOutput {
    
    private String template;
    private List<ConfigInput> inputs = new ArrayList<ConfigInput>();
    private String filenamePattern;
    private String condition;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<ConfigInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<ConfigInput> inputs) {
        this.inputs = inputs;
    }

    public String getFilenamePattern() {
        return filenamePattern;
    }

    public void setFilenamePattern(String filenamePattern) {
        this.filenamePattern = filenamePattern;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
}
