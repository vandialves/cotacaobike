package br.com.cotacao.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.com.cotacao.exception.InvalidInputException;
import br.com.cotacao.model.Pedido;
import br.com.cotacao.model.Proposta;

/***
 * Classe para metodos uteis
 * @author vandialvesdeliraneto
 *
 */
public class Utilitarios {

	public static Pedido validarInputs(Map<String, Object> requestArguments) throws InvalidInputException {
		Pedido entrada = new Pedido();
		if(null == requestArguments) {
			throw new InvalidInputException("Requisicao com body vazio");
		}

		if(null == requestArguments.get("valor")) {
			throw new InvalidInputException("Parametro obrigatorio: 'valor'");
		}
		else {
			String obj = String.valueOf(requestArguments.get("valor"));
			try {
				double valor = Double.parseDouble(obj);
				entrada.setPrice(valor);
			}
			catch(NumberFormatException e) {
				throw new InvalidInputException("Valor deve estar no formato de um Double, exemplo: 5025.30");
			}
		}

		return entrada;
	}

	public static Proposta montarResposta(String startTime, String endTime, double premioAlfa, double premioBeta, double premioSigma) {
		Proposta response = new Proposta(UUID.randomUUID().toString());
		response.setDtInicio(startTime);
		response.setDtFim(endTime);
		response.setCodigoStatus(Constantes._0100);
		response.setMensagem(Constantes.OPERACAO_REALIZADA_COM_SUCESSO);

		HashMap<String,Object> resultado = new HashMap<String,Object>();
		if(premioAlfa == 0.0f) {
			resultado.put("Seguradora ALFA", "O valor informado esta abaixo do valor minimo para cotar um seguro.");
		}
		else {
			resultado.put("Premio Seguradora ALFA", "R$"+String.format("%.2f",premioAlfa));			
		}
		
		if(premioBeta == 0.0f) {
			resultado.put("Seguradora BETA", "O valor informado esta abaixo do valor minimo para cotar um seguro.");
		}
		else {
			resultado.put("Premio Seguradora BETA", "R$"+String.format("%.2f",premioBeta));
		}
		
		if(premioSigma == 0.0f) {
			resultado.put("Seguradora SIGMA", "O valor informado esta abaixo do valor minimo para cotar um seguro.");
		}
		else {
			resultado.put("Premio Seguradora SIGMA", "R$"+String.format("%.2f",premioSigma));
		}

		response.setProposta(resultado);
		return response;
	}
}
