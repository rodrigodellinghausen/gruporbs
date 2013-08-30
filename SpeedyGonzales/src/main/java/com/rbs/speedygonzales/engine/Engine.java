package com.rbs.speedygonzales.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.w3c.dom.Document;

import com.rbs.speedygonzales.config.ConfigInput;
import com.rbs.speedygonzales.config.ConfigOutput;
import com.rbs.speedygonzales.config.Configuration;
import com.rbs.speedygonzales.tools.XmlTool;
import com.rbs.speedygonzales.util.EscapeUtil;
import com.rbs.speedygonzales.util.IOUtil;
import com.rbs.speedygonzales.util.XmlUtil;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 30/05/2013
 */
public class Engine {
	
	final VelocityEngine velocityEngine;
	
	public Engine() {
		velocityEngine = new VelocityEngine();
		velocityEngine.init();
	}
    
    /**
     * 
     * @param configuration
     * @throws FileNotFoundException 
     */
    public void generate(final Configuration configuration) {
        
        IOUtil ioUtil = new IOUtil();
        
        for (ConfigOutput configOutput : configuration.getOutputs() ) {
           
        	Writer writer = null;
        	try {
        		writer = this.generate(configOutput, configuration);
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
    
    public Writer generate(ConfigOutput configOutput, final Configuration configuration) throws Exception{
    	StringWriter writer = new StringWriter();
        
        final File templateFile = new File(configuration.getTemplateBaseDir() + configOutput.getTemplate());
        if ( !templateFile.exists() ) {
            System.err.println("Erro: template "+ templateFile.getAbsolutePath() +" não existe.");
            throw new FileNotFoundException();
        }
        
        VelocityContext context = new VelocityContext();
        
        bindContext(context, configOutput.getInputs());

        //escapeUtil
        context.put("escape", new EscapeUtil());
        
        velocityEngine.evaluate(context, writer, "Gerando template", new FileReader(templateFile));        
        
        return writer;
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
                        IOUtil ioUtil = new IOUtil();
                        InputStream inputStream = ioUtil.getContent( input.getSource() );
                        document = xmlUtil.getDocument(inputStream);
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
