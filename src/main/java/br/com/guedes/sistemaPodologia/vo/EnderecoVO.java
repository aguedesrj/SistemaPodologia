package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnderecoVO implements Serializable {

	private static final long serialVersionUID = -8863516968047497337L;

	private Integer endCodigo;
	private String endLogadouro;
	private String endNumero;
	private String endBairro;
	private String endCidade;
	private String endCep;
	private EstadoVO estadoVO;
	private List<EstadoVO> listaEstadosVO;
	
	public EnderecoVO() {
		this.estadoVO = new EstadoVO();
		this.listaEstadosVO = new ArrayList<EstadoVO>();
	}
	
	public Integer getEndCodigo() {
		return endCodigo;
	}
	
	public void setEndCodigo(Integer endCodigo) {
		this.endCodigo = endCodigo;
	}
	
	public String getEndLogadouro() {
		return endLogadouro;
	}
	
	public void setEndLogadouro(String endLogadouro) {
		this.endLogadouro = endLogadouro;
	}
	
	public String getEndNumero() {
		return endNumero;
	}
	
	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}
	
	public String getEndBairro() {
		return endBairro;
	}
	
	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}
	
	public String getEndCidade() {
		return endCidade;
	}
	
	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}
	
	public String getEndCep() {
		return endCep;
	}
	
	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}

	public EstadoVO getEstadoVO() {
		return estadoVO;
	}

	public void setEstadoVO(EstadoVO estadoVO) {
		this.estadoVO = estadoVO;
	}

	public List<EstadoVO> getListaEstadosVO() {
		return listaEstadosVO;
	}

	public void setListaEstadosVO(List<EstadoVO> listaEstadosVO) {
		this.listaEstadosVO = listaEstadosVO;
	}
}
