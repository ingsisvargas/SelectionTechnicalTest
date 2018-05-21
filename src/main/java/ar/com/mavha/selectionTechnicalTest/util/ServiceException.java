package ar.com.mavha.selectionTechnicalTest.util;

public class ServiceException extends Exception {
	
	static final long serialVersionUID = 1126425689;

	public ServiceException (String mensajeError) {
		super(mensajeError);
	}

}
