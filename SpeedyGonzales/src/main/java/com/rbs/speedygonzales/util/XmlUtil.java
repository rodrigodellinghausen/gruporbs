package com.rbs.speedygonzales.util;

import java.io.File;
import java.io.InputStream;
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
     * Faz o parser de um InputStream e retorna um <code>org.w3c.dom.Document</code>.
     * @param inputStream
     * @return 
     */
    public Document getDocument(final InputStream inputStream) throws Exception {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
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
