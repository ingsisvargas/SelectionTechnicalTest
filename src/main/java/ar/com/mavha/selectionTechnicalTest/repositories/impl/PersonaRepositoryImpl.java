package ar.com.mavha.selectionTechnicalTest.repositories.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.repositories.PersonaRepository;
import ar.com.mavha.selectionTechnicalTest.util.RepositoryException;

public class PersonaRepositoryImpl implements PersonaRepository {

	private EntityManager entityManager;

	// TODO Auto-generated constructor stub

	public PersonaRepositoryImpl() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("technicalTestPU");
		this.entityManager = managerFactory.createEntityManager();
	}

	@Override
	public String agregarPersona(Persona persona) throws RepositoryException {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(persona);
			entityManager.getTransaction().commit();

		} catch (EntityExistsException exception) {

			throw new RepositoryException("Ya existe una persona registrada con el DNI ingresado.");

		} catch (Exception exc) {
			throw new RepositoryException("Ocurrió un problema en BD. " + exc.getMessage());
		}

		return "Se agregó la persona con éxito.";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listarPersonas() throws RepositoryException {

		List<Persona> resultado = null;

		try {
		 	resultado = entityManager.createQuery("SELECT p FROM Persona p").getResultList();

		 	if(resultado.isEmpty()){
		 		throw new RepositoryException("No existen personas registradas en el sistema.");
		 	}
		 	
		} catch (Exception exc) {
			throw new RepositoryException("Ocurrió un problema en BD. " + exc.getMessage());
		}

		return resultado;
	}

}
