package unitTest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ar.com.mavha.selectionTechnicalTest.entities.Persona;
import ar.com.mavha.selectionTechnicalTest.jaxrs.PersonaResource;


public class PersonaResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(PersonaResource.class);
	}

	@BeforeClass
	public static void init() throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		initDatabase();
	}

	@AfterClass
	public static void destroy() throws SQLException, ClassNotFoundException, IOException {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.executeUpdate("DROP TABLE Persona");
			connection.commit();
		}
	}

	/**
	 * Inicialización de base de datos par prueba.
	 * 
	 * 
	 * @throws SQLException
	 */
	private static void initDatabase() throws SQLException {

		try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
			statement.execute("CREATE TABLE Persona (dni VARCHAR(20) NOT NULL, nombre VARCHAR(50),"
					+ "apellido VARCHAR(50), edad INT, PRIMARY KEY (dni))");
			connection.commit();
			connection.close();
		}

	}

	/**
	 * Crear una conexion a base de datos.
	 * 
	 * @return connection object
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:hsqldb:mem:technicaltest", "sa", "");
	}

	/**
	 * Este caso de prueba valida que si no hay personas registradas el servicio
	 * responde como tal.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Response response = target("/personas").request().get();
		String responseData = response.readEntity(String.class);
		assertEquals(500, response.getStatus());
		assertTrue(responseData.contains("No existen personas registradas en el sistema."));
	}

	/**
	 * Este caso de prueba verifica que el servicio de ingresar una persona
	 * funciona correctamente.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Persona persona = new Persona("1126425648", "Ronald", "Vargas", 25);
		GenericType<String> returnType = new GenericType<String>() {
		};
		Response response = target("/personas").request().post(Entity.entity(persona, MediaType.APPLICATION_JSON));

		String responseData = response.readEntity(returnType);
		assertEquals(200, response.getStatus());
		assertTrue(responseData.contains("Se agregó la persona con éxito."));
	}

	/**
	 * Este caso de prueba permite verificar que el servicio de ingresar personas valida
	 * que la persona a ingresar no exista con el dni ingresado.
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {

		Persona persona = new Persona("1126425648", "Ronald", "Vargas", 25);

		GenericType<String> returnType = new GenericType<String>() {
		};
		
		Response response = target("/personas").request().post(Entity.entity(persona, MediaType.APPLICATION_JSON));

		String responseData = response.readEntity(returnType);
		assertEquals(500, response.getStatus());
		assertTrue(responseData.contains("Ya existe una persona registrada con el DNI ingresado."));

	}

	/**
	 * Este caso de prueba permite verificar que al listar las personas el servicio
	 * funciona correctamente si hay personas en registradas en el sistema.
	 */
	@Test
	public void test4() throws Exception {
		Response response2 = target("/personas").request().get();
		String responseData2 = response2.readEntity(String.class);
		System.out.println(responseData2);
		assertEquals(200, response2.getStatus());

	}

}
