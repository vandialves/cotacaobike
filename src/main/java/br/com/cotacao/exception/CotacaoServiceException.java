package br.com.cotacao.exception;

import br.com.cotacao.util.Constantes;

/***
 * Excessao para erro generico
 * @author vandialvesdeliraneto
 *
 */
public abstract class CotacaoServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public CotacaoServiceException() {
	}

	public CotacaoServiceException(Throwable th) {
		super(th);
	}

	public String getErrorCode() {
		return Constantes._0199;
	}

	public String getClientMessage() {
		return Constantes.ERRO_AO_PROCESSAR_OPERACAO;
	}

}
