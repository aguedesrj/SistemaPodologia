package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioPerfilPk implements Serializable {

	private static final long serialVersionUID = 3920292378011019801L;

	@ManyToOne
	@JoinColumn(name = "USU_CODIGO", referencedColumnName = "USU_CODIGO")
	private Usuario usuario;	
	
	@ManyToOne
	@JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
	private Perfil perfil;		

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
