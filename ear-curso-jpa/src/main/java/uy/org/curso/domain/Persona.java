package uy.org.curso.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Persona extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String apellido;
	private String nroDocumento;
	private Date fechaNacimiento;
	private Sexo sexo;
	private Profesion profesion;
	
	
	public Persona() {
		super();
	}
	
	public Persona(Long id, String nombre, String apellido, String nroDocumento, Date fechaNacimiento, Sexo sexo, Profesion profesion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroDocumento = nroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.profesion = profesion;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@NotNull
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Size(min = 9, max = 9, message = "Documento debe tener 9 caracteres")
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Enumerated
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}
}
