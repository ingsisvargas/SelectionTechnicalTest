package ar.com.mavha.selectionTechnicalTest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Entity
public class Persona{

	@Id
    @Column(name = "dni", nullable = false)
    private String dni;
 
    @Column(name = "nombre", nullable = true)
    private String nombre;
 
    @Null
    @Column(name = "apellido", nullable = true)
    private String apellido;
 
    @Null
    @Column(name = "edad", nullable = true)
    private int edad;

    public Persona(){}
    
    public Persona( String dni, String nombre, String apellido, int edad){
    	this.dni  = dni;
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.edad = edad;
    			
    }
    
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int  edad) {
		this.edad = edad;
	}
    
    	

}
