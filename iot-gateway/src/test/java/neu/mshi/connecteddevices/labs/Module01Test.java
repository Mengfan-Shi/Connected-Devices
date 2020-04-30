/**
 * 
 */
package neu.mshi.connecteddevices.labs;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import neu.mshi.connecteddevices.labs.module01.SystemCpuUtilTask;
import neu.mshi.connecteddevices.labs.module01.SystemMemUtilTask;

/**
 * Test class for all requisite Module01 functionality.
 * 
 * Instructions:
 * 1) Rename 'testSomething()' method such that 'Something' is specific to your needs; add others as needed, beginning each method with 'test...()'.
 * 2) Add the '@Test' annotation to each new 'test...()' method you add.
 * 3) Import the relevant modules and classes to support your tests.
 * 4) Run this class as unit test app
 * 5) Include a screen shot of the report when you submit your assignment
 */
public class Module01Test
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
	
	@Test
	public void testValue()
	{	
		//The testValue()function is used to validate the return value of cpuUtil and memUtil if they are in the range(0.0,100.0)
		SystemCpuUtilTask cpu = new SystemCpuUtilTask();
		SystemMemUtilTask mem = new SystemMemUtilTask();
		double cpuUtil = cpu.getDataFromSensor();
		double memUtil = mem.getDataFromSensor();
		assertTrue(cpuUtil >= 0.0);
		assertTrue(cpuUtil <= 100.0);
		assertTrue(memUtil >= 0.0);
		assertTrue(memUtil <= 100.0);
		

	}
	
}
