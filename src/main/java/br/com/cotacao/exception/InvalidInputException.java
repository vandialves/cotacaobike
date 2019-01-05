package br.com.cotacao.exception;

import br.com.cotacao.util.Constantes;

/***
 * Excessao com mensagem especifica
 * @author vandialvesdeliraneto
 *
 */
public class InvalidInputException extends CotacaoServiceException{

	private static final long serialVersionUID = 1L;
	private String input;

	public InvalidInputException(String input) {
		this.input = input;
	}

	@Override
	public String getClientMessage() {
		return input;
	}

	@Override
	public String getErrorCode() {
		return Constantes._0199;
	}

}
