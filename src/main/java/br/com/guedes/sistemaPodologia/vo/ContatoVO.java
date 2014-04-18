package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContatoVO implements Serializable {

	private static final long serialVersionUID = -1074993420183706486L;

	private Integer conCodigo;
	private String conDescricao;
	private String conResponsavel;
	private TipoContatoVO tipoContatoVO;
	private List<TipoContatoVO> listaTipoContatosVO;
	
	public ContatoVO() {
		this.tipoContatoVO = new TipoContatoVO();
		this.listaTipoContatosVO = new ArrayList<TipoContatoVO>();
	}
	
	public Integer getConCodigo() {
		return conCodigo;
	}
	
	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}
	
	public String getConDescricao() {
		return conDescricao;
	}
	
	public void setConDescricao(String conDescricao) {
		this.conDescricao = conDescricao;
	}
	
	public String getConResponsavel() {
		return conResponsavel;
	}
	
	public void setConResponsavel(String conResponsavel) {
		this.conResponsavel = conResponsavel;
	}

	public TipoContatoVO getTipoContatoVO() {
		return tipoContatoVO;
	}

	public void setTipoContatoVO(TipoContatoVO tipoContatoVO) {
		this.tipoContatoVO = tipoContatoVO;
	}

	public List<TipoContatoVO> getListaTipoContatosVO() {
		return listaTipoContatosVO;
	}

	public void setListaTipoContatosVO(List<TipoContatoVO> listaTipoContatosVO) {
		this.listaTipoContatosVO = listaTipoContatosVO;
	}
}
