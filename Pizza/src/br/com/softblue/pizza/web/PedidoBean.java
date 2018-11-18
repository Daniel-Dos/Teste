package br.com.softblue.pizza.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.softblue.pizza.domain.Cliente;
import br.com.softblue.pizza.domain.Pedido;
import br.com.softblue.pizza.domain.Pizza;
import br.com.softblue.pizza.domain.Sabor;
import br.com.softblue.pizza.domain.Tamanho;
import br.com.softblue.pizza.service.ClienteService;
import br.com.softblue.pizza.service.PedidoService;

@Named
@SessionScoped
public class PedidoBean implements Serializable {

	@EJB
	private PedidoService pedidoService;
	
	@EJB
	private ClienteService clienteService;
	
	private String telefone;
	
	private Cliente cliente;
	
	private Tamanho tamanho;
	
	private Boolean bordaRecheada;
	
	private List<Integer> selectedSabores = new ArrayList<>();
	
	private Pedido pedido;
	
	public String buscarCliente() {
		Cliente cliente = clienteService.findByTelefone(telefone);
		
		if (cliente == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente não encontrado"));
		}

		this.cliente = cliente;
		pedido = pedidoService.createPedido(cliente);
		return null;
	}
	
	public String adicionarPizza() {
		pedidoService.createPizza(tamanho, selectedSabores, bordaRecheada, pedido);
		selectedSabores = new ArrayList<>();
		bordaRecheada = null;
		tamanho = null;
		return null;
	}
	
	public String excluir(Integer pizzaId) {
		pedidoService.deletePizza(pizzaId);
		return null;
	}
	
	public String confirmarPedido() {
		pedidoService.updatePedido(pedido.getId());
		pedido = null;
		selectedSabores = new ArrayList<>();
		bordaRecheada = null;
		telefone = null;
		tamanho = null;
		cliente = null;
		return "pedidos";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public List<Sabor> getSabores() {
		return pedidoService.listSabores();
	}
	
	public List<Tamanho> getTamanhos() {
		return pedidoService.listTamanhos();
	}
	
	public List<Pizza> getPizzas() {
		return pedidoService.listPizzasByPedidoId(pedido.getId());
	}
	
	public List<Pedido> getPedidos() {
		return pedidoService.listPedidos();
	}
	
	public List<Integer> getSelectedSabores() {
		return selectedSabores;
	}
	
	public void setSelectedSabores(List<Integer> selectedSabores) {
		this.selectedSabores = selectedSabores;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
	
}
