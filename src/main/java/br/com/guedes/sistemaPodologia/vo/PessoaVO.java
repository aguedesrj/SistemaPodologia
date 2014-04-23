package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;

public class PessoaVO implements Serializable {

	private static final long serialVersionUID = 1478785122548694710L;

	private Integer pesCodigo;
	private String pesNome;	
	private String pesCpf;
	private String pesSexo;	
	private String pesDtNascimento;
	private String pesObs;
	
	public Integer getPesCodigo() {
		return pesCodigo;
	}
	
	public void setPesCodigo(Integer pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
	
	public String getPesNome() {
		return pesNome;
	}
	
	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}
	
	public String getPesSexo() {
		return pesSexo;
	}
	
	public String getPesSexoFormat() {
		if (this.pesSexo != null) {
			if (this.pesSexo.equals("F")) {
				return "Feminino";
			}
			return "Masculino";
		}
		return pesSexo;
	}	
	
	public void setPesSexo(String pesSexo) {
		this.pesSexo = pesSexo;
	}
	
	public String getPesDtNascimento() {
		return pesDtNascimento;
	}
	
	public void setPesDtNascimento(String pesDtNascimento) {
		this.pesDtNascimento = pesDtNascimento;
	}
	
	public String getPesObs() {
		return pesObs;
	}
	
	public void setPesObs(String pesObs) {
		this.pesObs = pesObs;
	}

	public String getPesCpf() {
		return pesCpf;
	}

	public void setPesCpf(String pesCpf) {
		this.pesCpf = pesCpf;
	}
}
