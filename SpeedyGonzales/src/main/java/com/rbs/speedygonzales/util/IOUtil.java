package com.rbs.speedygonzales.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        InputStream inputStream = null;
        try {
            final URL conUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) conUrl.openConnection();
            inputStream = connection.getInputStream();
            
            return getByteArrayInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { inputStream.close(); } catch(Exception e){}
        }
        return null;
    }
    
    private ByteArrayInputStream getByteArrayInputStream(final InputStream inputStream) throws IOException {
        
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        byte[] bytes = new byte[1024];
        int len = 0;
        while ( (len = inputStream.read(bytes) ) != -1 ) {
            out.write(bytes, 0, len);
        }
        
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream( out.toByteArray());
        
        return byteInputStream;
    }
}
