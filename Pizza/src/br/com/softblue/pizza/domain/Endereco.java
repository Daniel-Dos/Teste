package br.com.softblue.pizza.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {

	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = true)
	private Integer numero;
	
	@Column(nullable = true)
	private String complemento;
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String completo() {
		return String.format("%s, %d. %s", logradouro, numero, complemento);
	}
}
