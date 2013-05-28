package com.rbs.speedygonzales.config;

import java.util.List;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
class ConfigInput<T> {
    
    private String id;
    private String source;
    private Class<T> type;
    
    private boolean useInContext = true;
    private String condition;
    
    private List<ConfigInput> inputs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<ConfigInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<ConfigInput> inputs) {
        this.inputs = inputs;
    }

    public boolean isUseInContext() {
        return useInContext;
    }

    public void setUseInContext(boolean useInContext) {
        this.useInContext = useInContext;
    }
    
}
