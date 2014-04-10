package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ModuloPerfilPk implements Serializable {

	private static final long serialVersionUID = 5105399405931744361L;

	@ManyToOne
	@JoinColumn(name = "MOD_CODIGO", referencedColumnName = "MOD_CODIGO")
	private Modulo modulo;	
	
	@ManyToOne
	@JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
	private Perfil perfil;

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}		
}
