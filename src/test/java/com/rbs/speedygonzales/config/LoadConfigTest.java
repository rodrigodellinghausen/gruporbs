package com.rbs.speedygonzales.config;

import java.io.File;
import java.net.URL;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 * 
 * @author Rodrigo Dellinghausen, rodrigo@gruporbs.com.br, dellinghausen@gmail.com
 * @since 27/05/2013
 */
public class LoadConfigTest extends TestCase {
    
    public LoadConfigTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLoadWithOutputs() throws Exception {
        
        File file = getFileConfig();
        
        LoadConfig loadConfig = new LoadConfig();
        Configuration config = loadConfig.load(file);
        
        assertNotNull("Não carregou", config);
        assertEquals("Tem que ter 2 outputs", 2, config.getOutputs().size());
        
        ConfigOutput output = config.getOutputs().get(0);
        assertEquals("", "saida{sequence}.json", output.getFilenamePattern());
    }
    
    
    
    private File getFileConfig() {
        URL url = this.getClass().getClassLoader().getResource("modelo-config.xml");
        
        assertNotNull("Deve encontrar resources/modelo-config.xml", url);
        
        File file = new File( url.getPath() );
        
        assertTrue("Não encontrou o arquivo", file.exists());
        
        return file;
    }
}
