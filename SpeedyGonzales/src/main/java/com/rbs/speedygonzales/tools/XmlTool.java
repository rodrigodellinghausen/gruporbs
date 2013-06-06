package com.rbs.speedygonzales.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.sun.org.apache.xpath.internal.CachedXPathAPI;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 28/05/2013
 */
public class XmlTool {

    private CachedXPathAPI cachedXPathAPI =  new CachedXPathAPI();
    
    private final List<Node> nodes;
    
    public XmlTool(final Node node) {
        this(Collections.singletonList(node));
    }
    
    public XmlTool(final List<Node> nodes) {
        this.nodes = nodes;
    }
    
    /**
     * Retorna:
     * 1) o valor de um atributo do nodo corrente; ou
     * 2) um ou mais elementos filhos do nodo corrente;
     * 3) null se nada for encontrado
     * 
     * @param name nome do atributo ou elemento
     * @return 
     */
    public Object get(final String name) {
       if (name == null || name.trim().length() == 0) {
           return null;
       }
       final String value = attr(name);
       if ( value != null) {
           return value;
       }
       Integer integer = null;
       try {
           integer = Integer.parseInt(name);
       } catch (Exception e){}
       if (integer != null) {
               return get(integer);
       }
       
       String xpath = "";
       if (!name.trim().startsWith("/")) {
           xpath = "./";
       }
       xpath += name;
       return find(xpath);
    }
    
    public XmlTool get(int i) {
            if (isEmpty() || i >= nodes.size() || i < 0) {
                    return null;
            }
            return new XmlTool( nodes.get(i) );
    }
    /**
     * Retorna o valo de um atributo.
     * 
     * @param name
     * @return 
     */
    public String attr(final String name) {
        if (name == null || name.trim().length() == 0) {
           return null;
       }
        Node node = node();
        if (node != null && node instanceof Element) {
            Attr att = ((Element) node).getAttributeNode(name);
            if (att != null) {
                return att.getValue();
            }
        }
        return null;
    }
   
    /**
     * Busca por uma xpath.
     * 
     * @param xpath
     * @return 
     */
    public XmlTool find(final String xpath) {
        if (xpath == null || xpath.trim().length() == 0) {
            return null;
        }
        List<Node> result = new ArrayList<Node>();
        for (Node node : nodes) {
            try {
                NodeIterator nodeIt = cachedXPathAPI.selectNodeIterator(node, xpath);
                if (nodeIt != null) {
                    Node resNode;
                    while ( (resNode = nodeIt.nextNode() ) != null) {
                        result.add( resNode );
                    }
                }
            } catch (Exception e) {}
        }
        
        return new XmlTool(result);
    }
    
    /**
     * Retorna a lista de nodos encapsulado neste objeto.
     * Útil em foreach.
     * @return 
     */
    public List<XmlTool> list() {
         List<XmlTool> list = new ArrayList<XmlTool>();
        if (nodes != null) {
                for (Node node : nodes) {
                        list.add( new XmlTool(node));
                }
        }
        return list;
    }
    
    /**
     * Retorna o primeiro nodo.
     * 
     * @return 
     */
    public Node node() {
        if (isEmpty()) {
            return null;
        }
        return nodes.get(0);
    }
    
    public boolean isEmpty() {
        return (nodes == null || nodes.isEmpty());
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return super.toString();
        }
        
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append( node.getTextContent() );
        }
        return sb.toString();
    }
    
    
}
