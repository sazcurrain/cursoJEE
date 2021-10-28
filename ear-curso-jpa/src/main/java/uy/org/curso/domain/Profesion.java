package uy.org.curso.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Profesion extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descripcion;
	
	
	public Profesion() {
		
	}
	
	public Profesion(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
