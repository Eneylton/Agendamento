package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.ClienteDAO;
import com.java.modelo.Cliente;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;

	public Cliente retornarClientePorID(Long id) throws ClassNotFoundException, SQLException {
		return clienteDAO.retornarClientePorID(id);
	}

	public List<Cliente> listarTodos() throws SQLException {
		return clienteDAO.listarTodos();
	}

	public void incluir(Cliente cliente) throws SQLException {
		clienteDAO.incluir(cliente);
	}

	public void alterar(Cliente cliente) throws SQLException {
		clienteDAO.alterar(cliente);
	}

	public void excluir(Cliente cliente) throws SQLException {
		clienteDAO.excluir(cliente);
	}

}
