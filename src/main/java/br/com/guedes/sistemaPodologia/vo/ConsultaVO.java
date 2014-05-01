package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ConsultaVO implements Serializable {

	private static final long serialVersionUID = 5973747976774655467L;

	private Integer cosCodigo;
	private Date cosDtConsulta;
	private String cosFlgTipo;
	private String cosFlgPrimeiro;
	private ProfissionalVO profissionalVO;
	private ClienteVO clienteVO;
	private TratamentoVO tratamentoVO;
	private List<ProfissionalVO> listaProfissionalVO;
	private List<ClienteVO> listaClienteVO;
	private List<TratamentoVO> listaTratamentoVO;
	
	public ConsultaVO() {
		setListaTratamentoVO(new ArrayList<TratamentoVO>());
		setListaProfissionalVO(new ArrayList<ProfissionalVO>());
		setListaClienteVO(new ArrayList<ClienteVO>());
	}
	
	public Integer getCosCodigo() {
		return cosCodigo;
	}
	
	public void setCosCodigo(Integer cosCodigo) {
		this.cosCodigo = cosCodigo;
	}
	
	public Date getCosDtConsulta() {
		return cosDtConsulta;
	}
	
	public void setCosDtConsulta(Date cosDtConsulta) {
		this.cosDtConsulta = cosDtConsulta;
	}
	
	public String getCosFlgTipo() {
		return cosFlgTipo;
	}
	
	public void setCosFlgTipo(String cosFlgTipo) {
		this.cosFlgTipo = cosFlgTipo;
	}
	
	public String getCosFlgPrimeiro() {
		return cosFlgPrimeiro;
	}
	
	public void setCosFlgPrimeiro(String cosFlgPrimeiro) {
		this.cosFlgPrimeiro = cosFlgPrimeiro;
	}
	
	public ProfissionalVO getProfissionalVO() {
		return profissionalVO;
	}
	
	public void setProfissionalVO(ProfissionalVO profissionalVO) {
		this.profissionalVO = profissionalVO;
	}
	
	public ClienteVO getClienteVO() {
		return clienteVO;
	}
	
	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}
	
	public TratamentoVO getTratamentoVO() {
		return tratamentoVO;
	}
	
	public void setTratamentoVO(TratamentoVO tratamentoVO) {
		this.tratamentoVO = tratamentoVO;
	}
	
	public List<ProfissionalVO> getListaProfissionalVO() {
		return listaProfissionalVO;
	}
	
	public void setListaProfissionalVO(List<ProfissionalVO> listaProfissionalVO) {
		this.listaProfissionalVO = listaProfissionalVO;
	}
	
	public List<ClienteVO> getListaClienteVO() {
		return listaClienteVO;
	}
	
	public void setListaClienteVO(List<ClienteVO> listaClienteVO) {
		this.listaClienteVO = listaClienteVO;
	}
	
	public List<TratamentoVO> getListaTratamentoVO() {
		return listaTratamentoVO;
	}
	
	public void setListaTratamentoVO(List<TratamentoVO> listaTratamentoVO) {
		this.listaTratamentoVO = listaTratamentoVO;
	}
}
