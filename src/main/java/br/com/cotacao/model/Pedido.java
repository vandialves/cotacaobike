package br.com.cotacao.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/***
 * Entidade que representa a intencao de cotacao do seguro do cliente
 * @author vandialvesdeliraneto
 *
 */
@JsonInclude (value = JsonInclude.Include.NON_EMPTY)
public class Pedido {

	private String corretor = null;
	private double valor;
	private String modeloBike = null;

	public Pedido() {
		super();
	}

	public Pedido(String corretor, double valor, String modeloBike) {
		super();
		this.corretor = corretor;
		this.valor = valor;
		this.modeloBike = modeloBike;
	}

	public String getClient() {
		return corretor;
	}
	public void setClient(String client) {
		this.corretor = client;
	}
	public double getPrice() {
		return valor;
	}
	public void setPrice(double price) {
		this.valor = price;
	}
	public String getProduct() {
		return modeloBike;
	}
	public void setProduct(String product) {
		this.modeloBike = product;
	}

}