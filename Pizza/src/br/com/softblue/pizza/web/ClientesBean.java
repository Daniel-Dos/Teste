package br.com.softblue.pizza.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.softblue.pizza.domain.Cliente;
import br.com.softblue.pizza.service.ClienteService;

@Named
@SessionScoped
public class ClientesBean implements Serializable {
	
	@EJB
	private ClienteService clienteService;	
	private Cliente cliente = new Cliente();
	
	public List<Cliente> getClientes() {
		return clienteService.listAll();
	}
	
	public String excluir(Integer clienteId) {
		clienteService.delete(clienteId);
		cliente = new Cliente();
		return "clientes?faces-redirect=true";
	}
	
	public String editar(Cliente cliente) {
		this.cliente = cliente;
		return null;
	}

	public String gravar() {
		clienteService.createOrUpdate(cliente);
		cliente = new Cliente();
		return "clientes?faces-redirect=true";
	}
	
	public String cancelar() {
		cliente = new Cliente();
		return "clientes?faces-redirect=true";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
