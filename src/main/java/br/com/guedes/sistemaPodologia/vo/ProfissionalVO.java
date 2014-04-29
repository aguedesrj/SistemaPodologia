package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalVO implements Serializable {

	private static final long serialVersionUID = 2160665437884137506L;
	
	private Integer prfCodigo;
	private String prfDtFormacao;
	private String prfDescricaoFormacao;
	private PessoaVO pessoaVO;
	private EnderecoVO enderecoVO;
	private ContatoVO contatoVO;
	private List<ContatoVO> listaContatos;
	
	public ProfissionalVO() {
		this.pessoaVO = new PessoaVO();
		this.enderecoVO = new EnderecoVO();
		this.contatoVO = new ContatoVO();
		this.listaContatos = new ArrayList<ContatoVO>();
	}

	public Integer getPrfCodigo() {
		return prfCodigo;
	}

	public void setPrfCodigo(Integer prfCodigo) {
		this.prfCodigo = prfCodigo;
	}

	public String getPrfDtFormacao() {
		return prfDtFormacao;
	}

	public void setPrfDtFormacao(String prfDtFormacao) {
		this.prfDtFormacao = prfDtFormacao;
	}

	public String getPrfDescricaoFormacao() {
		return prfDescricaoFormacao;
	}

	public void setPrfDescricaoFormacao(String prfDescricaoFormacao) {
		this.prfDescricaoFormacao = prfDescricaoFormacao;
	}

	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}

	public EnderecoVO getEnderecoVO() {
		return enderecoVO;
	}

	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}

	public ContatoVO getContatoVO() {
		return contatoVO;
	}

	public void setContatoVO(ContatoVO contatoVO) {
		this.contatoVO = contatoVO;
	}

	public List<ContatoVO> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<ContatoVO> listaContatos) {
		this.listaContatos = listaContatos;
	}
}
