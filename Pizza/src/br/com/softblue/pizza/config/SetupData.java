package br.com.softblue.pizza.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.softblue.pizza.domain.Cliente;
import br.com.softblue.pizza.domain.Endereco;
import br.com.softblue.pizza.domain.PrecoSabor;
import br.com.softblue.pizza.domain.Sabor;
import br.com.softblue.pizza.domain.Tamanho;

@Singleton
@Startup
public class SetupData {
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void setup() {
		long count = em.createQuery("SELECT COUNT(c) FROM Cliente c", Long.class).getSingleResult();
		if (count > 0) {
			return;
		}
		
		System.out.println("===> CONFIGURANDO BD <===");
		
		Cliente c = new Cliente();
		c.setNome("Luke");
		c.setTelefone("345-678-901");
		
		Endereco e = new Endereco();
		e.setLogradouro("Rua Jedi dos Carvalhos");
		e.setNumero(445);
		e.setComplemento("Nave 45B");
		c.setEndereco(e);
		
		em.persist(c);
		
		Sabor s = new Sabor();
		s.setNome("Mussarela Trooper");
		em.persist(s);
		PrecoSabor ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Pequena);
		ps.setPreco(22.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Media);
		ps.setPreco(35.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Grande);
		ps.setPreco(45.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Imperial);
		ps.setPreco(59.0);
		em.persist(ps);
		
		s = new Sabor();
		s.setNome("Napolitana Vader");
		em.persist(s);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Pequena);
		ps.setPreco(21.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Media);
		ps.setPreco(39.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Grande);
		ps.setPreco(43.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Imperial);
		ps.setPreco(54.0);
		em.persist(ps);
		
		s = new Sabor();
		s.setNome("800 Queijos");
		em.persist(s);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Pequena);
		ps.setPreco(19.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Media);
		ps.setPreco(31.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Grande);
		ps.setPreco(39.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Imperial);
		ps.setPreco(57.0);
		em.persist(ps);
		
		s = new Sabor();
		s.setNome("Calabresa Skywalker");
		em.persist(s);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Pequena);
		ps.setPreco(22.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Media);
		ps.setPreco(33.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Grande);
		ps.setPreco(44.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Imperial);
		ps.setPreco(55.0);
		em.persist(ps);
		
		s = new Sabor();
		s.setNome("Pepperoni de Luz");
		em.persist(s);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Pequena);
		ps.setPreco(26.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Media);
		ps.setPreco(34.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Grande);
		ps.setPreco(48.0);
		em.persist(ps);
		ps = new PrecoSabor();
		ps.setSabor(s);
		ps.setTamanho(Tamanho.Imperial);
		ps.setPreco(62.0);
		em.persist(ps);
		
	}
}
