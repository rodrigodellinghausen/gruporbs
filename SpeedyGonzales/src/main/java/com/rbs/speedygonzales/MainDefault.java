package com.rbs.speedygonzales;

import com.rbs.speedygonzales.config.Configuration;
import com.rbs.speedygonzales.config.LoadConfig;
import com.rbs.speedygonzales.engine.Engine;
import java.io.File;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 02/06/2013
 */
public class MainDefault {
    
    public static void main(String[] args) throws Exception {
        
        if ( args.length == 0) {
            throw new IllegalArgumentException("Arquivo de configuração não foi informado.");
        }
        String configFileName = args[0];
        
        File configFile = new File( configFileName );
        
        Configuration config = new LoadConfig().load(configFile);
        
        Engine engine = new Engine();
        engine.generate(config);

        System.out.println("FIM ");
    }
}
