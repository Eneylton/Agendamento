package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Agenda;


public class AgendaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Agenda retornarAgendaPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,titulo,inicio,fim,descricao,status from agenda where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Agenda agenda = null;

		while (rs.next()) {

			agenda = new Agenda();

			agenda.setId(rs.getLong("id"));
			agenda.setTitulo(rs.getString("titulo"));
			agenda.setInicio(rs.getDate("inicio"));
			agenda.setFim(rs.getDate("fim"));
			agenda.setDescricao(rs.getString("descricao"));
			agenda.setStatus(rs.getBoolean("status"));
		}

		stmt.close();
		con.close();

		return agenda;

	}

	public List<Agenda> listarTodos() throws SQLException {

		List<Agenda> lista = new ArrayList<Agenda>();

		String sql = "select id,titulo,inicio, "
				   + "fim,descricao,status from agenda order by id desc ";
	
		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Agenda agenda = null;

		while (rs.next()) {

			agenda = new Agenda();

			agenda.setId(rs.getLong("id"));
			agenda.setTitulo(rs.getString("titulo"));
			agenda.setMostrarDataInicio(rs.getString("inicio"));
			agenda.setInicio(rs.getTimestamp("inicio"));
			agenda.setMostrarDataFim(rs.getString("fim"));
			agenda.setFim(rs.getTimestamp("fim"));
			agenda.setDescricao(rs.getString("descricao"));
			agenda.setStatus(rs.getBoolean("status"));

			lista.add(agenda);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada = dt1.format(agenda.getInicio());

		String dataFormatada2 = dt1.format(agenda.getFim());

		String sql = "INSERT INTO agenda (titulo,inicio,fim,descricao,status) " + "VALUES (?,?,?,?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda.getTitulo());
		stmt.setString(2, dataFormatada);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda.getDescricao());
		stmt.setBoolean(5, agenda.isStatus());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dataFormatada = dt1.format(agenda.getInicio());

		String dataFormatada2 = dt1.format(agenda.getFim());

		String sql = "update agenda set " + "titulo = ?, inicio = ?,fim = ?, "
				+ "descricao = ?,status = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, agenda.getTitulo());
		stmt.setString(2, dataFormatada);
		stmt.setString(3, dataFormatada2);
		stmt.setString(4, agenda.getDescricao());
		stmt.setBoolean(5, agenda.isStatus());
		stmt.setLong(6, agenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Agenda agenda) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from agenda where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, agenda.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
