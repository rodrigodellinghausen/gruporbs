package com.rbs.speedygonzales.util;

import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
public class XmlUtil {
 
    /**
     * Faz o parser de um file e retorna um <code>org.w3c.dom.Document</code>.
     * @param file
     * @return 
     */
    public Document getDocument(final File file) throws Exception {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }
    
    /**
     * 
     * @return um javax.xml.xpath.XPath.
     */
    public XPath getXPath() {
        XPathFactory factory = javax.xml.xpath.XPathFactory.newInstance();
        return factory.newXPath();
    }
}
