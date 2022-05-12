package by.mytest.main.service;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1275893814867585247L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	

}
