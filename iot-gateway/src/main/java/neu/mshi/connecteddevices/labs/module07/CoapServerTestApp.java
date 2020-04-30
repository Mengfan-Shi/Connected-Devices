package neu.mshi.connecteddevices.labs.module07;

public class CoapServerTestApp 
{	
	private static CoapServerTestApp _App;
	public static void main(String[] args)
	{
		_App = new CoapServerTestApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	// private var's
	private CoapServerConnector _coapServer;
	// constructors
	public CoapServerTestApp()
	{
		super();
	}
	// public methods
	/**
	 * start the CoapServerTestApp.
	 */
	public void start()
	{
		_coapServer = new CoapServerConnector();
		_coapServer.start();
		}
	}
