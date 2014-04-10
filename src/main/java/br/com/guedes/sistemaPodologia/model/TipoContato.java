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
@Table(name="TB_TIPOCONTATO")
@SequenceGenerator(name="SEQUENCE_TIPOCONTATO", sequenceName = "GEN_TIPOCONTATO", allocationSize=1)
public class TipoContato implements Serializable {

	private static final long serialVersionUID = -4247525005569371343L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_TIPOCONTATO")
	@Column(name="TCO_CODIGO")
	private Integer tcoCodigo;
	
	@Column(name="TCO_DESCRICAO", length=20)
	private String tcoDescricao;

	public Integer getTcoCodigo() {
		return tcoCodigo;
	}

	public void setTcoCodigo(Integer tcoCodigo) {
		this.tcoCodigo = tcoCodigo;
	}

	public String getTcoDescricao() {
		return tcoDescricao;
	}

	public void setTcoDescricao(String tcoDescricao) {
		this.tcoDescricao = tcoDescricao;
	}
}
