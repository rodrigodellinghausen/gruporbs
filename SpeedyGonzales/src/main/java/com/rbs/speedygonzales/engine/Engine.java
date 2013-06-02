package com.rbs.speedygonzales.engine;

import com.rbs.speedygonzales.config.ConfigInput;
import com.rbs.speedygonzales.config.ConfigOutput;
import com.rbs.speedygonzales.config.Configuration;
import com.rbs.speedygonzales.util.IOUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;


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
                ioUtil.writeToTmpAndMove(outputFile, new StringReader( writer.toString() ) , "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
