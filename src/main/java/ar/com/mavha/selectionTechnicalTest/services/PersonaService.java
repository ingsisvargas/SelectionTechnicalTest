package ar.com.mavha.selectionTechnicalTest.services;



import java.util.List;
import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.util.ServiceException;


public interface PersonaService{
		
	public String agregarPersona(Persona persona)  throws ServiceException;
	public List<Persona> listarPersonas()  throws ServiceException;
	
	
}
