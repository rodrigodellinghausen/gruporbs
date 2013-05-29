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

    public void testLoad() throws Exception {
        
        File file = getFileConfig();
        
        LoadConfig loadConfig = new LoadConfig();
        Configuration config = loadConfig.load(file);
        
        assertNotNull("Não carregou", config);
        
        //base dir
        assertEquals("Template base dir", "/shared/templates", config.getTemplateBaseDir());
        assertEquals("Output base dir", "/shared/outputs", config.getOutputBaseDir());
        
        //inputs
        assertEquals("Número de inputs", 2, config.getMapInputs().size());
        
        //outputs
        assertEquals("Tem que ter 2 outputs", 2, config.getOutputs().size());
        
        ConfigOutput output = config.getOutputs().get(0);
        assertEquals("Valor do filename-pattern", "saida{sequence}.json", output.getFilenamePattern());
        assertEquals("Número de inputs do primeiro output", 2, output.getInputs().size());
        
    }
    
    
    
    private File getFileConfig() {
        URL url = this.getClass().getClassLoader().getResource("modelo-config.xml");
        
        assertNotNull("Deve encontrar resources/modelo-config.xml", url);
        
        File file = new File( url.getPath() );
        
        assertTrue("Não encontrou o arquivo", file.exists());
        
        return file;
    }
}
