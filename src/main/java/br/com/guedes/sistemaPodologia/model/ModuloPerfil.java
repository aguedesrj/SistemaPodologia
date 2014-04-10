package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(ModuloPerfilPk.class)
@Table(name="TB_MODULO_PERFIL")
public class ModuloPerfil implements Serializable {

	private static final long serialVersionUID = 5602528505756279557L;

	@Id
    private Modulo modulo;

	@Id
    private Perfil perfil;

	@Column(name="TMP_ALTERAR", length=1, nullable=false)
	private String tmpAlterar;
	
	@Column(name="TMP_APAGAR", length=1, nullable=false)
	private String tmpApagar;
	
	@Column(name="TMP_CONSULTAR", length=1, nullable=false)
	private String tmpConsultar;
	
	@Column(name="TMP_GRAVAR", length=1, nullable=false)
	private String tmpGravar;

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

	public String getTmpAlterar() {
		return tmpAlterar;
	}

	public void setTmpAlterar(String tmpAlterar) {
		this.tmpAlterar = tmpAlterar;
	}

	public String getTmpApagar() {
		return tmpApagar;
	}

	public void setTmpApagar(String tmpApagar) {
		this.tmpApagar = tmpApagar;
	}

	public String getTmpConsultar() {
		return tmpConsultar;
	}

	public void setTmpConsultar(String tmpConsultar) {
		this.tmpConsultar = tmpConsultar;
	}

	public String getTmpGravar() {
		return tmpGravar;
	}

	public void setTmpGravar(String tmpGravar) {
		this.tmpGravar = tmpGravar;
	}
}
