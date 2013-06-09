package com.rbs.speedygonzales.engine;

import com.rbs.speedygonzales.config.ConfigInput;
import com.rbs.speedygonzales.config.ConfigOutput;
import com.rbs.speedygonzales.config.Configuration;
import com.rbs.speedygonzales.tools.XmlTool;
import com.rbs.speedygonzales.util.EscapeUtil;
import com.rbs.speedygonzales.util.IOUtil;
import com.rbs.speedygonzales.util.XmlUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.w3c.dom.Document;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 30/05/2013
 */
public class Engine {
    
    /**
     * 
     * @param configuration
     * @throws FileNotFoundException 
     */
    public void generate(final Configuration configuration) {
        
        
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        
        IOUtil ioUtil = new IOUtil();
        
        for (ConfigOutput configOutput : configuration.getOutputs() ) {
            StringWriter writer = new StringWriter();
            
            final File templateFile = new File(configuration.getTemplateBaseDir() + configOutput.getTemplate());
            if ( !templateFile.exists() ) {
                System.err.println("Erro: template "+ templateFile.getAbsolutePath() +" não existe.");
                continue;
            }
            
            VelocityContext context = new VelocityContext();
            
            bindContext(context, configOutput.getInputs());

            //escapeUtil
            context.put("escape", new EscapeUtil());
            
            try {
                velocityEngine.evaluate(context, writer, "Gerando template", new FileReader(templateFile));        
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            
            //TODO tratar o getFilenamePattern quando é "pattern"
            File outputFile = new File( configuration.getOutputBaseDir() + configOutput.getFilenamePattern() );
            //grava no file system 
            try {
                ioUtil.writeTo(outputFile, new StringReader( writer.toString() ) , "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private void bindContext(Context context, List<ConfigInput> inputs) {
        final XmlUtil xmlUtil = new XmlUtil();
        Document document = null;
        for (ConfigInput input : inputs) {
            try {
                if ( input.getSource() != null) {
                    if (input.getSource().startsWith("file:")) {
                        File file = new File( input.getSource().replaceFirst("file:", "") );
                        document = xmlUtil.getDocument(file);
                    } else if (input.getSource().startsWith("http://")) {
                        
                    } else if (input.getSource().startsWith("classpath:")) {
                        URL url = 
                                this.getClass().getClassLoader()
                                    .getResource(input.getSource().replaceFirst("classpath:", ""));
                        File file = new File( url.getPath() );
                        document = xmlUtil.getDocument(file);
                    }
                }
                
                if (document != null) {
                    context.put(input.getId(), new XmlTool(document.getFirstChild()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
