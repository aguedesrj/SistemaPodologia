package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TB_CONFIGURACAO")
@SequenceGenerator(name="SEQUENCE_CONFIGURACAO", sequenceName = "GEN_CONFIGURACAO", allocationSize=1)
public class Configuracao implements Serializable {

	private static final long serialVersionUID = 8193570301918201525L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_CONFIGURACAO")
	@Column(name="COF_CODIGO")
	private Integer cofCodigo;
	
	@Column(name="COF_NOME", length=60, nullable=false)
	private String cofNome;	
	
	@Column(name="COF_ENDERECO", length=255)
	private String cofEndereco;
	
	@Column(name="COF_CONTATOS", length=255)
	private String cofContatos;
	
	@Column(name="COF_LOGO")
	private byte[] cofLogo;
	
	@Column(name="COF_OBS", length=255)
	private String cofObs;

	public Integer getCofCodigo() {
		return cofCodigo;
	}

	public void setCofCodigo(Integer cofCodigo) {
		this.cofCodigo = cofCodigo;
	}

	public String getCofNome() {
		return cofNome;
	}

	public void setCofNome(String cofNome) {
		this.cofNome = cofNome;
	}

	public String getCofEndereco() {
		return cofEndereco;
	}

	public void setCofEndereco(String cofEndereco) {
		this.cofEndereco = cofEndereco;
	}

	public String getCofContatos() {
		return cofContatos;
	}

	public void setCofContatos(String cofContatos) {
		this.cofContatos = cofContatos;
	}

	public byte[] getCofLogo() {
		return cofLogo;
	}

	public void setCofLogo(byte[] cofLogo) {
		this.cofLogo = cofLogo;
	}

	public String getCofObs() {
		return cofObs;
	}

	public void setCofObs(String cofObs) {
		this.cofObs = cofObs;
	}
}
