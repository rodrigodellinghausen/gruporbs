package com.rbs.speedygonzales.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 30/05/2013
 */
public class Engine {
    
    /**
     * Gera os recursos a partir do contexto e do template velocity.
     * @return retorna o conteúdo gerado.
     */
    public String generate(final Context context, final File templateFile) throws FileNotFoundException {
        
        if ( !templateFile.exists() ) {
            throw new FileNotFoundException("Arquivo não encontrado ("+ templateFile.getAbsolutePath() +").");
        }
        
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        
        StringWriter writer = new StringWriter();
        engine.evaluate(context, writer, "Gerando template", new FileReader(templateFile));
        
        return writer.toString();
    }
}
