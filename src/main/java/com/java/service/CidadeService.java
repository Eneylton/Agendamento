package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.CidadeDAO;
import com.java.modelo.Cidade;

public class CidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeDAO cidadeDAO;

	public Cidade retornarCidadePorID(Long id) throws ClassNotFoundException, SQLException {
		return cidadeDAO.retornarCidadePorID(id);
	}
	
	public List<Cidade> buscarPorEstado(Long idestado) throws ClassNotFoundException, SQLException {
		return cidadeDAO.buscarPorEstado(idestado);
		
	}

	public List<Cidade> listarTodos() throws SQLException {
		return cidadeDAO.listarTodos();
	}

	public void incluir(Cidade cidade) throws SQLException {
		cidadeDAO.incluir(cidade);
	}

	public void alterar(Cidade cidade) throws SQLException {
		cidadeDAO.alterar(cidade);
	}

	public void excluir(Cidade cidade) throws SQLException {
		cidadeDAO.excluir(cidade);
	}

}
