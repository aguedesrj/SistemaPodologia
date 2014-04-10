package br.com.guedes.sistemaPodologia.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(DiagnosticoDoencaPk.class)
@Table(name="TB_DIAGNOSTICO_DOENCA")
public class DiagnosticoDoenca implements Serializable {

	private static final long serialVersionUID = 7487122772899520046L;

	@Id
    private Diagnostico diagnostico;

	@Id
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
