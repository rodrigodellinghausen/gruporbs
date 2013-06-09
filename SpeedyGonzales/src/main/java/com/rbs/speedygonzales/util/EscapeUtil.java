package com.rbs.speedygonzales.util;

import java.lang.reflect.Method;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 09/06/2013
 */
public class EscapeUtil {

    private static final StringEscapeUtils stringEscapeUtils = new StringEscapeUtils();
    
    public String escape(final Object obj, final String type) {
        if (obj == null) {
            return null;
        }
        return escape(String.valueOf(obj), type);
    }
    public String escape(final String str, final String type) {
        if ( str == null || type == null) {
            return str;
        }
        final String methodName = "escape"+ type;
        try {
            Method method =
                    StringEscapeUtils.class.getDeclaredMethod(methodName, String.class);
            return (String) method.invoke(stringEscapeUtils, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
}
