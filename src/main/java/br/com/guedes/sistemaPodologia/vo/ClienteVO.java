package br.com.guedes.sistemaPodologia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteVO implements Serializable {

	private static final long serialVersionUID = -4443973515051363781L;

	private PessoaVO pessoaVO;
	private EnderecoVO enderecoVO;
	private String cliDataUltimaConsulta;
	private String cliUltimoTratamento;
	private Integer pacCodigo;
	private boolean pacLabora;
	private boolean pacAndaDescalco;	
	private boolean pacUnhaEngravada;
	private boolean pacCirurgiaPes;
	private String pacCirurgiaMotivo;
	private String pacCalcadoUtiliza;
	private boolean pacVisitaPedicuro;
	private String pacNumeroCalcado;
	private boolean pacAlergicoMedicamentos;
	private String pacAlergicoQuais;
	private boolean pacDiabetes;
	private boolean pacHipertensao;
	private boolean pacTabagismo;
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
	
	public String getPacNumeroCalcado() {
		return pacNumeroCalcado;
	}
	
	public void setPacNumeroCalcado(String pacNumeroCalcado) {
		this.pacNumeroCalcado = pacNumeroCalcado;
	}
	
	public String getPacAlergicoQuais() {
		return pacAlergicoQuais;
	}
	
	public void setPacAlergicoQuais(String pacAlergicoQuais) {
		this.pacAlergicoQuais = pacAlergicoQuais;
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

	public String getCliUltimoTratamento() {
		return cliUltimoTratamento;
	}

	public void setCliUltimoTratamento(String cliUltimoTratamento) {
		this.cliUltimoTratamento = cliUltimoTratamento;
	}

	public boolean isPacLabora() {
		return pacLabora;
	}

	public void setPacLabora(boolean pacLabora) {
		this.pacLabora = pacLabora;
	}

	public boolean isPacAndaDescalco() {
		return pacAndaDescalco;
	}

	public void setPacAndaDescalco(boolean pacAndaDescalco) {
		this.pacAndaDescalco = pacAndaDescalco;
	}

	public boolean isPacUnhaEngravada() {
		return pacUnhaEngravada;
	}

	public void setPacUnhaEngravada(boolean pacUnhaEngravada) {
		this.pacUnhaEngravada = pacUnhaEngravada;
	}

	public boolean isPacCirurgiaPes() {
		return pacCirurgiaPes;
	}

	public void setPacCirurgiaPes(boolean pacCirurgiaPes) {
		this.pacCirurgiaPes = pacCirurgiaPes;
	}

	public boolean isPacVisitaPedicuro() {
		return pacVisitaPedicuro;
	}

	public void setPacVisitaPedicuro(boolean pacVisitaPedicuro) {
		this.pacVisitaPedicuro = pacVisitaPedicuro;
	}

	public boolean isPacAlergicoMedicamentos() {
		return pacAlergicoMedicamentos;
	}

	public void setPacAlergicoMedicamentos(boolean pacAlergicoMedicamentos) {
		this.pacAlergicoMedicamentos = pacAlergicoMedicamentos;
	}

	public boolean isPacDiabetes() {
		return pacDiabetes;
	}

	public void setPacDiabetes(boolean pacDiabetes) {
		this.pacDiabetes = pacDiabetes;
	}

	public boolean isPacHipertensao() {
		return pacHipertensao;
	}

	public void setPacHipertensao(boolean pacHipertensao) {
		this.pacHipertensao = pacHipertensao;
	}

	public boolean isPacTabagismo() {
		return pacTabagismo;
	}

	public void setPacTabagismo(boolean pacTabagismo) {
		this.pacTabagismo = pacTabagismo;
	}
}
