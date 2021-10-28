package uy.org.curso.backingbeans;

import java.util.Date;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import uy.org.curso.model.UsuarioLogeado;
import uy.org.curso.model.UsuarioModel;

@Model
public class LoginController {
	
	private String username;
	private String password;
	
	@Inject
	private UsuarioModel usuarioModel;
	
	@Inject
	private UsuarioLogeado usuarioLogeado;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String autenticar() {		
		if (usuarioModel.autenticar(username, password)) {
			FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO,
	                		"Bienvenido usuario", null));
			
			usuarioLogeado.setUsername(username);
			usuarioLogeado.setDate(new Date());
			
			return "home.xhtml?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                		"El usuario o la contrasenia no son correctos", null));
			return "";		
		}
	}
	
	public String logout() {
		usuarioLogeado.setUsername(null);
		usuarioLogeado.setDate(null);
		
		return "login.xhtml?faces-redirect=true";
		
	}

}
