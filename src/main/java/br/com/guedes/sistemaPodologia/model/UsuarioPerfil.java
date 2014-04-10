package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(UsuarioPerfilPk.class)
@Table(name="TB_USUARIO_PERFIL")
public class UsuarioPerfil implements Serializable {

	private static final long serialVersionUID = 8078133931236749103L;

	@Id
    private Usuario usuario;

	@Id
    private Perfil perfil;

	@Column(name="TMP_DATAALTERACAO")
	private Date tmpDataAlteracao;

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

	public Date getTmpDataAlteracao() {
		return tmpDataAlteracao;
	}

	public void setTmpDataAlteracao(Date tmpDataAlteracao) {
		this.tmpDataAlteracao = tmpDataAlteracao;
	}
}
