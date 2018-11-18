package br.com.softblue.pizza.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.softblue.pizza.domain.Cliente;

@Stateless
public class ClienteService {
	
	@PersistenceContext
	private EntityManager em;

	public List<Cliente> listAll() {
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class);
		return q.getResultList();
	}
	
	public Cliente findById(Integer clienteId) {
		return em.find(Cliente.class, clienteId);
	}
	
	public Cliente findByTelefone(String telefone) {
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente c WHERE c.telefone = ?1", Cliente.class);
		q.setParameter(1, telefone);
		
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void delete(Integer clienteId) {
		Cliente cliente = findById(clienteId);
		em.remove(cliente);
	}
	
	public void createOrUpdate(Cliente cliente) {
		if (cliente.getId() != null) {
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}
	}
}
