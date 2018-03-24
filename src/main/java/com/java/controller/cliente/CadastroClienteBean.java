package com.java.controller.cliente;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.dao.CidadeDAO;
import com.java.dao.UsuarioDAO;
import com.java.modelo.Cidade;
import com.java.modelo.Cliente;
import com.java.modelo.Estado;
import com.java.service.ClienteService;
import com.java.service.EstadoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;

	private Estado estado;

	@Inject
	private EstadoService estadoService;

	/*@Inject
	private CidadeService cidadeService;*/

	@Inject
	UsuarioDAO usuarioDao;

	private Map<String, Estado> listarEstados = new HashMap<String, Estado>();

	private List<Cidade> listarCidades = new ArrayList<>();

	private List<Cliente> clientes = new ArrayList<>();

	private Cliente cliente;

	@PostConstruct
	public void init() {

		try {
			preencheListasSelect();
			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void salvar() {

		try {

			if (cliente.getId() == null) {

				clienteService.incluir(cliente);
			} else {
				clienteService.alterar(cliente);

			}

			FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.cliente = new Cliente();
	}

	public void popular() throws ClassNotFoundException, SQLException {

		if (estado != null) {
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			List<Cidade> resultado = cidadeDAO.listarTodos();

			for (Cidade cidade : resultado) {
				System.out.println("Código da Cidade: " + cidade.getDescricao());
				System.out.println("Nome da Cidade: " + cidade.getId());
				System.out.println("Nome da Cidade: " + cidade.getEstado().getId());
				System.out.println("Nome do Estado: " + cidade.getEstado().getDescricao());
				System.out.println();
			}
		}
		}

	

	private void preencheListasSelect() throws SQLException, ClassNotFoundException {

		listarEstados = new TreeMap<String, Estado>();
		for (Estado estado : estadoService.listarTodos()) {
			listarEstados.put(estado.getDescricao(), estado);
		}

	}
    

	public void carregarLista() throws ClassNotFoundException, SQLException {

		clientes = clienteService.listarTodos();

	}

	public Map<String, Estado> getListarEstados() {
		return listarEstados;
	}

	public void setListarEstados(Map<String, Estado> listarEstados) {
		this.listarEstados = listarEstados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Cidade> getListarCidades() {
		return listarCidades;
	}

	public void setListarCidades(List<Cidade> listarCidades) {
		this.listarCidades = listarCidades;
	}

}
