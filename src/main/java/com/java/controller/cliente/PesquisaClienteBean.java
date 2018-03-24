package com.java.controller.cliente;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Cliente;
import com.java.service.ClienteService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;

	private List<Cliente> clientes = new ArrayList<>();

	private Cliente cliente;

	private Cliente clienteSelecionada;

	@PostConstruct
	public void inicializar() {

		try {
			
			carregarLista();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			clienteService.excluir(clienteSelecionada);
			this.clientes.remove(clienteSelecionada);
			FacesUtil.addSuccessMessage("Cliente " + clienteSelecionada.getNome() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void carregarLista() throws ClassNotFoundException, SQLException {

		clientes = clienteService.listarTodos();

	}

	public void filtrar() throws SQLException {
		clientes = clienteService.listarTodos();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionada() {
		return clienteSelecionada;
	}

	public void setClienteSelecionada(Cliente clienteSelecionada) {
		this.clienteSelecionada = clienteSelecionada;
	}

}
