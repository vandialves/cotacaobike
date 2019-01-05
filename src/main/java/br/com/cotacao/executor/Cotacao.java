package br.com.cotacao.executor;

/***
 * Essa classe pode se tornar generica com a utilizacao do DynamoDB para carregar 
 * dinamicamente as informacoes das seguradoras
 * @author vandialvesdeliraneto
 *
 */
public class Cotacao {

	public static double cotarBike_ALFA(double valor) {
		double premio = 0.0f;

		if(valor < 2000.00f) {
			premio = 0.0f;
		}
		else if(valor >= 2000.00f && valor <= 4999.99f) {
			premio = valor*0.02;
		}
		else if(valor > 4999.99f && valor <= 8999.99f) {
			premio = valor*0.05;
		}
		else if(valor > 8999.99f && valor <= 11999.99f) {
			premio = valor*0.10;
		}
		else if(valor > 11999.99f) {
			premio = valor*0.18;
		}

		return premio;
	}

	public static double cotarBike_BETA(double valor) {
		double premio = 0.0f;

		if(valor < 5000.00f) {
			premio = 0.0f;
		}
		else if(valor >= 5000.00f && valor <= 7999.99f) {
			premio = valor*0.05;
		}
		else if(valor > 7999.99f && valor <= 12999.99f) {
			premio = valor*0.09;
		}
		else if(valor > 12999.99f && valor <= 15999.99f) {
			premio = valor*0.11;
		}
		else if(valor > 15999.99f) {
			premio = valor*0.22;
		}

		return premio;
	}

	public static double cotarBike_SIGMA(double valor) {
		double premio = 0.0f;

		if(valor < 1800.00f) {
			premio = 0.0f;
		}
		else if(valor >= 1800.00f && valor <= 3999.99f) {
			premio = valor*0.12;
		}
		else if(valor > 3999.99f && valor <= 6999.99f) {
			premio = valor*0.15;
		}
		else if(valor > 6999.99f && valor <= 8999.99f) {
			premio = valor*0.18;
		}
		else if(valor > 8999.99f) {
			premio = valor*0.25;
		}

		return premio;
	}

}
