
package com.java.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.java.dao.EstadoDAO;
import com.java.modelo.Estado;


public class EstadoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstadoDAO estadoDAO;
	
	public Estado retornarEstadoPorID(Long id) throws ClassNotFoundException, SQLException {
		return estadoDAO.retornarEstadoPorID(id);
	}
	
	public List<Estado> listarTodos() throws SQLException {
		return estadoDAO.listarTodos();
	}
	
	public void incluir(Estado estado) throws SQLException {
		estadoDAO.incluir(estado);
	}
	
	public void alterar(Estado estado) throws SQLException {
		estadoDAO.alterar(estado);
	}
	
	public void excluir(Estado estado) throws SQLException {
		estadoDAO.excluir(estado);
	}
	
}
