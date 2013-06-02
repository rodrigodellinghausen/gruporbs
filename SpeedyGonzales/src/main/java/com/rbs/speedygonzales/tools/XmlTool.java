package com.rbs.speedygonzales.tools;

import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 28/05/2013
 */
public class XmlTool {

    private CachedXPathAPI cachedXPathAPI =  new CachedXPathAPI();
    
    private final Node node;
    
    public XmlTool(final Node node) {
        this.node = node;
    }
    
//    public XmlTool get(final String name) {
//        cachedXPathAPI.
//    }
}
