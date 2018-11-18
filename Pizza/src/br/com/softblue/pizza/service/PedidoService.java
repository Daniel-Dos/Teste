package br.com.softblue.pizza.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.softblue.pizza.domain.Cliente;
import br.com.softblue.pizza.domain.Pedido;
import br.com.softblue.pizza.domain.Pedido.Status;
import br.com.softblue.pizza.domain.Pizza;
import br.com.softblue.pizza.domain.Sabor;
import br.com.softblue.pizza.domain.Tamanho;

@Stateless
public class PedidoService {
	@PersistenceContext
	private EntityManager em;
	
	public List<Sabor> listSabores() {
		TypedQuery<Sabor> q = em.createQuery("SELECT s FROM Sabor s ORDER BY s.nome", Sabor.class);
		return q.getResultList();
	}
	
	public List<Pizza> listPizzasByPedidoId(Integer pedidoId) {
		TypedQuery<Pizza> q = em.createQuery("SELECT p FROM Pizza p WHERE p.pedido.id = ?1", Pizza.class);
		q.setParameter(1, pedidoId);
		return q.getResultList();
	}
	
	public List<Tamanho> listTamanhos() {
		return Arrays.asList(Tamanho.values());
	}

	public void createPizza(Tamanho tamanho, List<Integer> saboresIds, boolean bordaRecheada, Pedido pedido) {
		Pizza pizza = new Pizza();
		pizza.setTamanho(tamanho);
		pizza.setBordaRecheada(bordaRecheada);
		pizza.setPedido(pedido);
		
		for (Integer saborId : saboresIds) {
			Double preco = findPrecoSaborByTamanhoAndSaborId(tamanho, saborId);
			Sabor sabor = findSaborById(saborId);
			pizza.adicionarSabor(sabor, preco);
		}
		
		pizza.calcularPreco();
		em.persist(pizza);
	}
	
	public void deletePizza(Integer pizzaId) {
		Pizza pizza = em.find(Pizza.class, pizzaId);
		em.remove(pizza);
	}
	
	public Pedido createPedido(Cliente cliente) {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		em.persist(pedido);
		return pedido;
	}
	
	private Double findPrecoSaborByTamanhoAndSaborId(Tamanho tamanho, Integer saborId) {
		TypedQuery<Double> q = em.createQuery("SELECT ps.preco FROM PrecoSabor ps WHERE ps.tamanho = ?1 AND ps.sabor.id = ?2", Double.class);
		q.setParameter(1, tamanho);
		q.setParameter(2, saborId);
		return q.getSingleResult();
	}
	
	private Sabor findSaborById(Integer saborId) {
		return em.find(Sabor.class, saborId);
	}
	
	private Pedido findPedidoById(Integer pedidoId) {
		return em.find(Pedido.class, pedidoId);
	}
	
	public List<Pedido> listPedidos() {
		TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p ORDER BY p.data DESC", Pedido.class);
		return q.getResultList();
	}
	
	public void updatePedido(Integer pedidoId) {
		Pedido pedido = findPedidoById(pedidoId);
		pedido.setData(LocalDateTime.now());
		pedido.setStatus(Status.Pendente);

		List<Pizza> pizzas = pedido.getPizzas();
		double preco = 0;
		for (Pizza pizza: pizzas) {
			preco += pizza.getPreco();
		}
		pedido.setPreco(preco);
	}
}
