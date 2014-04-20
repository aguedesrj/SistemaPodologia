package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteVO implements Serializable {

	private static final long serialVersionUID = -4443973515051363781L;

	private PessoaVO pessoaVO;
	private EnderecoVO enderecoVO;
	private String cliDataUltimaConsulta;
	private Integer pacCodigo;
	private String pacLabora;
	private String pacAndaDescalco;	
	private String pacUnhaEngravada;
	private String pacCirurgiaPes;
	private String pacCirurgiaMotivo;
	private String pacCalcadoUtiliza;
	private String pacVisitaPedicuro;
	private String pacNumeroCalcado;
	private String pacAlergicoMedicamentos;
	private String pacAlergicoQuais;
	private String pacDiabetes;
	private String pacHipertensao;
	private String pacTabagismo;
	private Integer pacPeso;
	private String pacAltura;
	private ContatoVO contatoVO;
	private List<ContatoVO> listaContatos;
	
	public ClienteVO() {
		this.pessoaVO = new PessoaVO();
		this.enderecoVO = new EnderecoVO();
		this.contatoVO = new ContatoVO();
		this.listaContatos = new ArrayList<ContatoVO>();
	}
	
	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}
	
	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}
	
	public String getCliDataUltimaConsulta() {
		if (cliDataUltimaConsulta == null || this.cliDataUltimaConsulta.equals("")) {
			return "Primeira visita.";
		}
		return cliDataUltimaConsulta;
	}

	public void setCliDataUltimaConsulta(String cliDataUltimaConsulta) {
		this.cliDataUltimaConsulta = cliDataUltimaConsulta;
	}

	public EnderecoVO getEnderecoVO() {
		return enderecoVO;
	}
	
	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}
	
	public Integer getPacCodigo() {
		return pacCodigo;
	}
	
	public void setPacCodigo(Integer pacCodigo) {
		this.pacCodigo = pacCodigo;
	}
	
	public String getPacLabora() {
		return pacLabora;
	}
	
	public void setPacLabora(String pacLabora) {
		this.pacLabora = pacLabora;
	}
	
	public String getPacAndaDescalco() {
		return pacAndaDescalco;
	}
	
	public void setPacAndaDescalco(String pacAndaDescalco) {
		this.pacAndaDescalco = pacAndaDescalco;
	}
	
	public String getPacUnhaEngravada() {
		return pacUnhaEngravada;
	}
	
	public void setPacUnhaEngravada(String pacUnhaEngravada) {
		this.pacUnhaEngravada = pacUnhaEngravada;
	}
	
	public String getPacCirurgiaPes() {
		return pacCirurgiaPes;
	}
	
	public void setPacCirurgiaPes(String pacCirurgiaPes) {
		this.pacCirurgiaPes = pacCirurgiaPes;
	}
	
	public String getPacCirurgiaMotivo() {
		return pacCirurgiaMotivo;
	}
	
	public void setPacCirurgiaMotivo(String pacCirurgiaMotivo) {
		this.pacCirurgiaMotivo = pacCirurgiaMotivo;
	}
	
	public String getPacCalcadoUtiliza() {
		return pacCalcadoUtiliza;
	}
	
	public void setPacCalcadoUtiliza(String pacCalcadoUtiliza) {
		this.pacCalcadoUtiliza = pacCalcadoUtiliza;
	}
	
	public String getPacVisitaPedicuro() {
		return pacVisitaPedicuro;
	}
	
	public void setPacVisitaPedicuro(String pacVisitaPedicuro) {
		this.pacVisitaPedicuro = pacVisitaPedicuro;
	}
	
	public String getPacNumeroCalcado() {
		return pacNumeroCalcado;
	}
	
	public void setPacNumeroCalcado(String pacNumeroCalcado) {
		this.pacNumeroCalcado = pacNumeroCalcado;
	}
	
	public String getPacAlergicoMedicamentos() {
		return pacAlergicoMedicamentos;
	}
	
	public void setPacAlergicoMedicamentos(String pacAlergicoMedicamentos) {
		this.pacAlergicoMedicamentos = pacAlergicoMedicamentos;
	}
	
	public String getPacAlergicoQuais() {
		return pacAlergicoQuais;
	}
	
	public void setPacAlergicoQuais(String pacAlergicoQuais) {
		this.pacAlergicoQuais = pacAlergicoQuais;
	}
	
	public String getPacDiabetes() {
		return pacDiabetes;
	}
	
	public void setPacDiabetes(String pacDiabetes) {
		this.pacDiabetes = pacDiabetes;
	}
	
	public String getPacHipertensao() {
		return pacHipertensao;
	}
	
	public void setPacHipertensao(String pacHipertensao) {
		this.pacHipertensao = pacHipertensao;
	}
	
	public String getPacTabagismo() {
		return pacTabagismo;
	}
	
	public void setPacTabagismo(String pacTabagismo) {
		this.pacTabagismo = pacTabagismo;
	}
	
	public Integer getPacPeso() {
		return pacPeso;
	}
	
	public void setPacPeso(Integer pacPeso) {
		this.pacPeso = pacPeso;
	}
	
	public String getPacAltura() {
		return pacAltura;
	}
	
	public void setPacAltura(String pacAltura) {
		this.pacAltura = pacAltura;
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
