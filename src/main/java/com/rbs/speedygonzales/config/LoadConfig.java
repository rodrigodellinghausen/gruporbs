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
        
        NodeList outputList = cachedXPathAPI.selectNodeList(document.getFirstChild(), "outputs/output");
        
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
        
        return configuration;
    }
    
}
