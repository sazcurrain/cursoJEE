package uy.org.curso.backingbeans;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import uy.org.curso.domain.Persona;
import uy.org.curso.domain.Profesion;
import uy.org.curso.domain.Sexo;
import uy.org.curso.ejbs.PersonaService;
import uy.org.curso.ejbs.ProfesionService;

/**
 * Backing Bean de las pantallas de Persona
 * @author juan
 *
 */
//TODO: Falta incorporar el manejo de excepciones y mostrarlas correctamente en JSF
@Named("personaController")
@ViewScoped
public class PersonaController implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Persona persona = new Persona();

	
	@EJB
	PersonaService personaService;
	
	@EJB
	ProfesionService profesionService;
	
	
	public PersonaController() {
		
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	/**
	 * Retorno la lista de personas cargadas en la BD
	 * @return List<Persona>
	 */
	public List<Persona> getListaPersonas(){
		return personaService.getPersonas();
		
	}
	
	/**
	 * Retorno la lista de profesiones cargadas en la BD
	 * @return List<Profesion> 
	 */
	public List<Profesion> getProfesiones(){
		return profesionService.getProfesiones();
	}
	
	/**
	 * Agrega una nueva persona ala BD
	 * @return String con la regla de navegacion
	 */
	public String crearPersona() {
		personaService.update(persona);					
		return "personas.xhtml?faces-redirect=true";
	}
	
	/**
	 * Retorna areglo con los valores del ENUM
	 * @return
	 */
	public Sexo[] getSexoValues() {
	   return  Sexo.values();
	}

	/**
	 * Busca a una persona por el ID
	 * Si la persona existe en la BD retorna esa persona
	 * En caso contrario crea una nueva
	 */
	public void findPersonaById() {
		if (persona.getId() != null) {
			persona = personaService.find(persona.getId());
			if (persona == null) {
				persona = new Persona();
			}
		}
	}
	
	/**
	 * Elimina una persona de la BD
	 * @param p
	 */
	public void eliminar(Persona p) {
		personaService.delete(p);
	}
	
	public boolean isManaged(Long id) {
		return id != null;
	}
}
