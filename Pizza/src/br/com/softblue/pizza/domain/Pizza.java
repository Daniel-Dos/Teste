package br.com.softblue.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Pizza implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Tamanho tamanho;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pizza_sabor",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "sabor_id"))
	private List<Sabor> sabores = new ArrayList<>();

	@Column(nullable = false)
	private Boolean bordaRecheada;

	@Column(nullable = false)
	private Double preco = 0.0;
	
	@ManyToOne
	private Pedido pedido;
	
	@Transient
	private List<Double> precos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Boolean getBordaRecheada() {
		return bordaRecheada;
	}

	public void setBordaRecheada(Boolean bordaRecheada) {
		this.bordaRecheada = bordaRecheada;
	}

	public Double getPreco() {
		return preco;
	}

	public List<Sabor> getSabores() {
		return sabores;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public void adicionarSabor(Sabor sabor, Double preco) {
		sabores.add(sabor);
		precos.add(preco);
	}
	
	public void calcularPreco() {
		for (Double preco : precos) {
			this.preco += preco;
		}
		
		this.preco /= sabores.size();
	}
}
