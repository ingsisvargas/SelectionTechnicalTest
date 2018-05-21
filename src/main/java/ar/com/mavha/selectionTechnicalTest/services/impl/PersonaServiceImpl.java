package ar.com.mavha.selectionTechnicalTest.services.impl;

import java.util.List;

import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.repositories.PersonaRepository;
import ar.com.mavha.selectionTechnicalTest.repositories.impl.PersonaRepositoryImpl;
import ar.com.mavha.selectionTechnicalTest.services.PersonaService;
import ar.com.mavha.selectionTechnicalTest.util.RepositoryException;
import ar.com.mavha.selectionTechnicalTest.util.ServiceException;

public class PersonaServiceImpl implements PersonaService {

	PersonaRepository repositorioPersona;
	private  static PersonaServiceImpl instance ;
	
	public static PersonaServiceImpl getInstance () {
		if (instance == null) {
			instance =  new PersonaServiceImpl();
		}
		return instance;
	}

	public PersonaServiceImpl() {
		this.repositorioPersona = new PersonaRepositoryImpl();
	}

	
	@Override
	public String agregarPersona(Persona persona) throws ServiceException{
		
		String resultado = "";
		try{
		resultado =	repositorioPersona.agregarPersona(persona);
		} catch (RepositoryException exception) {
			throw new ServiceException(exception.getMessage());
		}
		return resultado;
	}

	@Override
	public List<Persona> listarPersonas()  throws ServiceException {
		List<Persona> resultado = null;
		try{
		resultado = repositorioPersona.listarPersonas();
		} catch (RepositoryException exception) {
			throw new ServiceException(exception.getMessage());
		}
		return resultado ;
	}
	

}
