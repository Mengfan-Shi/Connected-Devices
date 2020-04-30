/**
 * 
 */
package com.labbenchstudios.edu.connecteddevices.common;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * @author aking
 *
 */
public class ConfigUtilTest
{
	// static
	
	public static final String DIR_PREFIX = "./src/test/java/com/labbenchstudios/edu/connecteddevices/common/";
	public static final String TEST_VALID_CFG_FILE = DIR_PREFIX + "ValidTestConfig.props";
	public static final String TEST_EMPTY_CFG_FILE = DIR_PREFIX + "EmptyTestConfig.props";
	public static final String TEST_INVALID_CFG_FILE = DIR_PREFIX + "InvalidTestConfig.props";
	public static final String TEST_MISSING_CFG_FILE = DIR_PREFIX + "MissingTestConfig.props";
	
	public static final String HOST_VAL = "things.ubidots.com";
	public static final String NOT_SET_VAL = "Not Set";
	public static final int PORT_VAL = 8883;
	
	// private
	
	private File _validTestFile = new File(TEST_VALID_CFG_FILE);
	
	
	// test methods
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		// make sure test files exist
		assertTrue(_validTestFile.exists());
		assertTrue(ConfigUtil.getInstance().loadConfig(TEST_VALID_CFG_FILE));
	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#getBooleanProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetBooleanProperty()
	{
		String useWebAccessStr =
			ConfigUtil.getInstance().getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.USE_WEB_ACCESS_KEY);
		
		assertTrue(Boolean.parseBoolean(useWebAccessStr));
	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#getIntegerProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetIntegerProperty()
	{
		String portStr =
			ConfigUtil.getInstance().getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.PORT_KEY);
		
		assertTrue((Integer.parseInt(portStr) == PORT_VAL));

	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#loadConfig(java.lang.String)}.
	 */
	@Test
	public void testGetProperty()
	{
		String host =
			ConfigUtil.getInstance().getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.HOST_KEY);
		
		String apiKey =
			ConfigUtil.getInstance().getProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.API_KEY);
		
		assertTrue(host.equals(String.valueOf(HOST_VAL)));
		assertTrue(apiKey.equals(NOT_SET_VAL));
	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#hasProperty(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testHasProperty()
	{
		assertTrue(ConfigUtil.getInstance().hasProperty(ConfigConst.UBIDOTS_CLOUD_SECTION, ConfigConst.USE_WEB_ACCESS_KEY));
	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#hasSection(java.lang.String)}.
	 */
	@Test
	public void testHasSection()
	{
		assertTrue(ConfigUtil.getInstance().hasSection(ConfigConst.UBIDOTS_CLOUD_SECTION));
	}
	
	/**
	 * Test method for {@link com.labbenchstudios.edu.connecteddevices.common.ConfigUtil#isConfigDataLoaded()}.
	 */
	@Test
	public void testIsConfigDataLoaded()
	{
		assertTrue(ConfigUtil.getInstance().isConfigDataLoaded());
	}
	
}
