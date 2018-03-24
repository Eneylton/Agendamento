package com.java.controller.estado;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Estado;
import com.java.service.EstadoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoService estadoService;

	private List<Estado> estados = new ArrayList<>();

	private Estado estado;

	private Estado estadoSelecionada;

	@PostConstruct
	public void inicializar() {

		try {
			estados = estadoService.listarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			estadoService.excluir(estadoSelecionada);
			this.estados.remove(estadoSelecionada);
			FacesUtil.addSuccessMessage("Estado " + estadoSelecionada.getDescricao() + " excluída com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage(ex.getMessage());
		}

	}

	public void filtrar() throws SQLException {
		estados = estadoService.listarTodos();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> Estados) {
		this.estados = Estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado Estado) {
		this.estado = Estado;
	}

	public Estado getEstadoSelecionada() {
		return estadoSelecionada;
	}

	public void setEstadoSelecionada(Estado EstadoSelecionada) {
		this.estadoSelecionada = EstadoSelecionada;
	}

}
