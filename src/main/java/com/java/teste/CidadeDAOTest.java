package com.java.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.java.dao.CidadeDAO;
import com.java.modelo.Cidade;

public class CidadeDAOTest {
	
	
	@Test
	public List<Cidade> buscarPorEstado() throws ClassNotFoundException, SQLException {
		Long idestado = 1L;
		

		List<Cidade> lista = new ArrayList<Cidade>();
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(idestado);

		for (Cidade cidade : resultado) {
			System.out.println("Código da Cidade: " + cidade.getDescricao());
			System.out.println("Nome da Cidade: " + cidade.getId());
			System.out.println("Nome da Cidade: " + cidade.getEstado().getId());
			System.out.println("Nome do Estado: " + cidade.getEstado().getDescricao());
			System.out.println();
		}
		
		return lista;
	}
}
