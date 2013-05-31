package com.rbs.speedygonzales.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 30/05/2013
 */
public class EngineTest {
    
    
    /**
     * Deve gerar uma exception quando o arquivo do template não existir.
     * 
     * @throws FileNotFoundException 
     */
    @Test(expected = FileNotFoundException.class )
    public void generateFileTemplateNotFound() throws FileNotFoundException {
        Engine engine = new Engine();
        
        engine.generate(null, new File("/fileNotExist.test") );
    }
    
    /**
     * Deve processar o template com sucesso.
     */
    @Test
    public void generateSuccessWithoutContext() throws FileNotFoundException  {
        
        URL url = this.getClass().getClassLoader().getResource("test-template.vm");
        assertNotNull("Deve encontrar resources/test-template.vm", url);        
        File templateFile = new File( url.getPath() );
        
        assertTrue("Não encontrou o arquivo", templateFile.exists());
        
        Engine engine = new Engine();
        String content = engine.generate(null, templateFile);
        
        assertNotNull("O conteúdo não pode ser nulo.", content);
        assertEquals("Conteúdo retornado.", "Test template", content);
    }
    
    
}
