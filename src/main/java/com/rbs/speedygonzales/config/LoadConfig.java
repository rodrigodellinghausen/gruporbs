package com.rbs.speedygonzales.config;

import com.rbs.speedygonzales.util.XmlUtil;
import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
public class LoadConfig {

    public Configuration load(File file) throws Exception {
        
        if (file == null || !file.exists()) {
            return null;
        }
        
        Configuration configuration = new Configuration();
        
        XmlUtil xmlUtil = new XmlUtil();
        Document document = xmlUtil.getDocument(file);
        CachedXPathAPI cachedXPathAPI = new CachedXPathAPI();
        
        loadBaseDirs(document, cachedXPathAPI, configuration);
        loadInputs(document, cachedXPathAPI, configuration);
        loadOutputs(document, cachedXPathAPI, configuration);
        
        return configuration;
    }

    private void loadBaseDirs(final Document document, 
            final CachedXPathAPI cachedXPathAPI, 
            final Configuration configuration) throws Exception {
        
        Element element = 
                (Element) cachedXPathAPI.selectSingleNode(document.getFirstChild(), "basedirs");
        
        configuration.setOutputBaseDir( element.getAttribute("output-dir") );
        configuration.setTemplateBaseDir( element.getAttribute("template-dir") );
    }
    private void loadInputs( final Document document, 
            final CachedXPathAPI cachedXPathAPI, 
            final Configuration configuration) throws Exception {
        
        NodeList inputList = 
                cachedXPathAPI.selectNodeList(document.getFirstChild(), "inputs/input");
        
        for (int i = 0; i < inputList.getLength(); i++) {
            Element element = (Element) inputList.item(i);
            
            String typeValue = element.getAttribute("type");
            
            ConfigInput configInput = new ConfigInput();
            configInput.setId( element.getAttribute("id") );
            configInput.setType(Class.forName(typeValue));
            configInput.setSource( element.getAttribute("source") );
            configInput.setCondition( element.getAttribute("condition") );
            configInput.setUseInContext( "true".equals(element.getAttribute("use-in-context")) );
            
            configuration.getMapInputs().put( configInput.getId(), configInput);
        }
    }
    
    private void loadOutputs( final Document document, 
            final CachedXPathAPI cachedXPathAPI, 
            final Configuration configuration) throws Exception {
        
        NodeList outputList = 
                cachedXPathAPI.selectNodeList(document.getFirstChild(), "outputs/output");
        
        for (int i = 0; i < outputList.getLength(); i++) {
            Element element = (Element) outputList.item(i);
            
            ConfigOutput output = new ConfigOutput();
            output.setTemplate( element.getAttribute("template") );
            output.setCondition( element.getAttribute("condition") );
            output.setFilenamePattern( element.getAttribute("filename-pattern") );
            
            String inputs = element.getAttribute("inputs");
            if ( inputs != null) {
                String[] inputsArray = inputs.split("\\s*,\\s*");
                for(String idInput : inputsArray) {
                    ConfigInput configInput = configuration.getMapInputs().get(idInput);
                    if (configInput != null) {
                        output.getInputs().add(configInput);
                    }
                }
            }
            
            configuration.getOutputs().add(output);
        }
    }
    
}
