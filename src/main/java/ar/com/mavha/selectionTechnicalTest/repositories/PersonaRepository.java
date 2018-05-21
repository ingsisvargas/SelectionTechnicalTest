package ar.com.mavha.selectionTechnicalTest.repositories;

import java.util.List;

import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.util.RepositoryException;

public interface PersonaRepository {

	
	public String agregarPersona (Persona persona) throws RepositoryException;
	public List<Persona> listarPersonas()throws RepositoryException;
	
	
}
