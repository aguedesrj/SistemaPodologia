package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DiagnosticoDoencaPk implements Serializable {

	private static final long serialVersionUID = -7329423951651670406L;

	@ManyToOne
	@JoinColumn(name = "DIA_CODIGO", referencedColumnName = "DIA_CODIGO")
	private Diagnostico diagnostico;	
	
	@ManyToOne
	@JoinColumn(name = "DOE_CODIGO", referencedColumnName = "DOE_CODIGO")
	private Doenca doenca;

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
}
