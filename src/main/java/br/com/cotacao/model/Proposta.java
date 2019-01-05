package br.com.cotacao.model;

import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.cotacao.exception.CotacaoServiceException;
import br.com.cotacao.util.Constantes;
/***
 * Entidade que representa o resultado da cotacao do seguro, a proposta e um 
 * Map contendo uma ou mais seguradoras
 * @author vandialvesdeliraneto
 *
 */
@JsonInclude (value = JsonInclude.Include.NON_EMPTY)
public class Proposta {

	private static final long serialVersionUID = 1L;

	private String codigoProposta = null;
	private String dtInicio = null;
	private String dtFim = null;
	private String codigoStatus = null;
	private String mensagem = null;
	private HashMap<String, Object> proposta = null;
	
	public Proposta(){
		super();
	}
	
	public Proposta(String codigo) {
		super();
		this.codigoProposta = codigo;
	}
	
	public Proposta(String codigoProposta, String dtInicio, String dtFim, String codigoStatus, String mensagem,
			HashMap<String, Object> proposta) {
		super();
		this.codigoProposta = codigoProposta;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.codigoStatus = codigoStatus;
		this.mensagem = mensagem;
		this.proposta = proposta;
	}

	public String getCodigoProposta() {
		return codigoProposta;
	}

	public void setCodigoProposta(String codigoProposta) {
		this.codigoProposta = codigoProposta;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getDtFim() {
		return dtFim;
	}

	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public String getCodigoStatus() {
		return codigoStatus;
	}

	public void setCodigoStatus(String codigoStatus) {
		this.codigoStatus = codigoStatus;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public HashMap<String, Object> getProposta() {
		return proposta;
	}

	public void setProposta(HashMap<String, Object> proposta) {
		this.proposta = proposta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static Proposta getResponseError(Exception e) {
		Proposta response = new Proposta(UUID.randomUUID().toString());
		CotacaoServiceException ex = (CotacaoServiceException) e; 
		response.setCodigoStatus(ex.getErrorCode());
		response.setMensagem(ex.getClientMessage());
		return response;
	}
	
	public static Proposta getResponseError() {
		Proposta response = new Proposta(UUID.randomUUID().toString());
		response.setCodigoStatus(Constantes._0199);
		response.setMensagem(Constantes.ERRO_AO_PROCESSAR_OPERACAO);
		return response;
	}

}