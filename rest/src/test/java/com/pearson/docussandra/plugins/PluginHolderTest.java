package com.pearson.docussandra.plugins;

import com.pearson.docussandra.plugininterfaces.NotifierPlugin;
import com.pearson.docussandra.plugininterfaces.SecurityPlugin;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class PluginHolderTest
{

    /**
     * Plugin holder for this test. Global and static for better reuse.
     */
    private static PluginHolder ph;

    public PluginHolderTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        //builds/creates the plugin holder for this test.
        File pluginJar = new File("./src/test/resources", "plugin-test-notify.jar");
        File[] jars = new File[1];
        jars[0] = pluginJar;
        try
        {
            ph = PluginHolder.build(jars);
        } catch (IllegalStateException e)
        {
            ph = PluginHolder.getInstance();
        }
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of build method, of class PluginHolder.
     */
    @Test
    public void testBuild() throws Exception
    {
        System.out.println("build");
        assertNotNull(ph);//make sure it got built        
        //make sure we can't build it twice
        boolean expectedExceptionThrown = false;
        try
        {
            PluginHolder.build(null);
        } catch (IllegalStateException e)
        {
            expectedExceptionThrown = true;
        }
        assertTrue(expectedExceptionThrown);
    }

    /**
     * Test of getInstance method, of class PluginHolder.
     */
    @Test
    public void testGetInstance()
    {
        System.out.println("getInstance");
        PluginHolder result = PluginHolder.getInstance();
        assertNotNull(result);
        //call it again; make sure we can call it twice for the heck of it
        result = PluginHolder.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getNotifierPlugins method, of class PluginHolder.
     */
    @Test
    public void testGetNotifierPlugins()
    {
        System.out.println("getNotifierPlugins");
        ArrayList<NotifierPlugin> result = ph.getNotifierPlugins();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        //make sure the plugin is able to be used
        NotifierPlugin plugin = result.get(0);
        assertNotNull(plugin);
        plugin.doNotify(NotifierPlugin.MutateType.DELETE, null);
        //make sure the plugin is able to be used
        NotifierPlugin plugin2 = result.get(1);
        assertNotNull(plugin2);
        plugin2.doNotify(NotifierPlugin.MutateType.DELETE, null);
    }

    /**
     * Test of getSecurityPlugins method, of class PluginHolder.
     */
    @Test
    public void testGetSecurityPlugins()
    {
        System.out.println("getSecurityPlugins");
        ArrayList<SecurityPlugin> result = ph.getSecurityPlugins();
        assertNotNull(result);
        //TODO: Add more
    }

}
