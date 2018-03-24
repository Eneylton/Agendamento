package com.java.controller.estado;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.java.modelo.Estado;
import com.java.service.EstadoService;
import com.java.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoService estadoService;

	private Estado estado;

	@PostConstruct
	public void init() {

		try {

			this.limpar();

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesUtil.addErrorMessage("Erro no processamento - Erro: " + ex.getMessage());
		}

	}

	public void salvar() {

		try {

			if (estado.getId() == null) {

				estadoService.incluir(estado);
			} else {
				estadoService.alterar(estado);
			}

			FacesUtil.addSuccessMessage("Estado salvo com sucesso!");

			this.limpar();

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void limpar() {
		this.estado = new Estado();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado Estado) {
		this.estado = Estado;
	}

}
