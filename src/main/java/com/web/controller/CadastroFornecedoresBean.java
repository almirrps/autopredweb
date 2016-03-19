package com.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.web.model.Cidade;
import com.web.model.Estado;
import com.web.model.Fornecedor;
import com.web.repository.Cidades;
import com.web.repository.Estados;
import com.web.repository.Fornecedores;

@Named
@ViewScoped
public class CadastroFornecedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fornecedores fornecedores;

	@Inject
	private Estados estados;

	@Inject
	private Cidades cidades;

	private Fornecedor fornecedor;

	private Estado estado;

	private List<Estado> todosEstados;

	private List<Cidade> cidadesPorEstado;

	private List<Fornecedor> todosFornecedores;

	public void inicializar() {
		fornecedor = new Fornecedor();
		estado = null;
		todosFornecedores = fornecedores.todosComCidadeEstado();

		if (!FacesContext.getCurrentInstance().isPostback()) {
			todosEstados = estados.todos();
		}
	}

	public void onEstadoChange() {
		cidadesPorEstado = null;
		if (estado != null) {
			cidadesPorEstado = cidades.porEstado(estado);
		}
	}

	public void cadastrar() {
		fornecedores.adicionar(fornecedor);
		inicializar();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Cidade> getCidadesPorEstado() {
		return cidadesPorEstado;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public List<Fornecedor> getTodosFornecedores() {
		return todosFornecedores;
	}

}
