package com.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.web.model.Fornecedor;
import com.web.util.jpa.Transactional;

public class Fornecedores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@Transactional
	public void adicionar(Fornecedor fornecedor) {
		manager.persist(fornecedor);
	}

	public List<Fornecedor> todosComCidadeEstado() {
		return manager.createQuery("from Fornecedor f inner join fetch f.cidade c inner join fetch c.estado"
				, Fornecedor.class).getResultList();
	}

}
