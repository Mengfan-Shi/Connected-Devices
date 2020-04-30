/**
 * 
 */
package neu.mshi.connecteddevices.labs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import neu.mshi.connecteddevices.common.SensorData;

/**
 * Test class for all requisite Module02 functionality.
 * 
 * Instructions:
 * 1) Rename 'testSomething()' method such that 'Something' is specific to your needs; add others as needed, beginning each method with 'test...()'.
 * 2) Add the '@Test' annotation to each new 'test...()' method you add.
 * 3) Import the relevant modules and classes to support your tests.
 * 4) Run this class as unit test app
 * 5) Include a screen shot of the report when you submit your assignment
 */
public class Module02Test
{
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	/**
	 * Just in case, test the returned value averagevalue is in the range of [0,30]
	 */
	@Test
	public void testTemp()
	{
		SensorData d = new SensorData();
		float t = d.getAvgValue();
		assertTrue(t >= 0);
		assertTrue(t <= 0);
	}
	
}
