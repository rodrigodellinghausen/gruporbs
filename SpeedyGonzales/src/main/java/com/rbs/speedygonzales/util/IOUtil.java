package com.rbs.speedygonzales.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 02/06/2013
 */
public class IOUtil {
    
    public boolean writeToTmpAndMove(final File file, final Reader reader, final String charsetName) 
            throws Exception {
        
        final File temp = new File(file.getAbsoluteFile() +".tmp");
        
        writeTo(temp, reader, charsetName);
        
        return temp.renameTo(file);
        
    }
    
    public void writeTo(final File file, final Reader reader, final String charsetName) throws Exception {
        OutputStreamWriter out = 
                new OutputStreamWriter(new FileOutputStream(file), charsetName);
        
        char[] chars = new char[1024];
        int length;
        while ( (length = reader.read(chars)) > 0 ) {
            out.write(chars, 0, length);
        }
        
        out.close(); 
    }
    
    public InputStream getContent(final String url) {
        //TODO implementar
        return null;
    }
}
