package ar.com.mavha.selectionTechnicalTest.jaxrs;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.services.PersonaService;
import ar.com.mavha.selectionTechnicalTest.services.impl.PersonaServiceImpl;
import ar.com.mavha.selectionTechnicalTest.util.ServiceException;

/**
 * Root resource (exposed at "personas" path)
 */
@Path("personas")
public class PersonaResource {

    /**
     */
    @SuppressWarnings("unchecked")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getPersonas() {
    	PersonaService  service = PersonaServiceImpl.getInstance();
    	JSONArray personas = new JSONArray();
    	JSONObject resultadoJ = new JSONObject();
    	try {
    		List<Persona> resultado = service.listarPersonas();
    		resultado.stream().forEach( persona -> {
    			JSONObject personaJ = new JSONObject(); 
    			personaJ.put("Dni",persona.getDni());
    			personaJ.put("nombre", persona.getNombre());
    			personaJ.put("apellido", persona.getApellido());
    			personaJ.put("edad", persona.getEdad());
    			personas.add(personaJ);
    		});

			resultadoJ.put("resultado", true);
    		resultadoJ.put("Mensaje", personas.toJSONString());
    		
    	} catch (ServiceException exception ) {
    		resultadoJ.put("resultado", false);
    		resultadoJ.put("Mensaje",exception.getMessage());
    		return Response.status(500).entity(resultadoJ.toJSONString()).build();
    	}
    	
    	return Response.status(200).entity(resultadoJ.toJSONString()).build();
    }
    
    
    @SuppressWarnings("unchecked")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarPersona( Persona persona) {
    
    	PersonaService service = PersonaServiceImpl.getInstance();
    	String result =  "";
    	JSONObject resultJ = new JSONObject();
    	try{
    		result = service.agregarPersona(persona);
    		resultJ.put("resultado", true);
    		resultJ.put("Mensaje", result);
    	} catch (ServiceException exception) {
    		resultJ.put("resultado", false);
    		resultJ.put("Mensaje", exception.getMessage());
    		return Response.status(500).entity( resultJ.toJSONString() ).build();
    		
    	}
    	
    	return Response.status(200).entity( resultJ.toJSONString()).build();
    	
    }
    	
    
    
    
    
}
