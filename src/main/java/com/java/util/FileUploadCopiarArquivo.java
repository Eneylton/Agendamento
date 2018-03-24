package com.java.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

public class FileUploadCopiarArquivo {

	// static String caminho =
	// FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
	static String diretorioBase = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

	public static void iniciarCopia(UploadedFile arquivo) throws IOException {

		// Endereço a ser salvo SINDAUMA - E nos futuros projetos utilizar esta
		// página como padrão
		// String diretorioSite = diretorioBase.replace("GerenciadorSite",
		// "ROOT");
		// File diretorio = new File(diretorioSite + "/estilo/upload/");

		// Endereço a ser salvo STEFEM - não utilizar esse em projetos futuros
		String diretorioSite = diretorioBase.replace("GerenciadorSite", "ROOT");
		File diretorio = new File(diretorioSite + "/resources/upload/");

		// if (diretorio.exists()) {
		// throw new NegocioException("Arquivo já existe.");
		// }

		if (diretorio.exists() == false) {
			new File(diretorio.toString()).mkdirs();
		}

		copiar(arquivo, diretorio);

	}

	public static void copiar(UploadedFile arquivo, File diretorio) throws IOException {

		InputStream inputStream = arquivo.getInputstream();
		OutputStream out = new FileOutputStream(new File(diretorio, arquivo.getFileName()));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		inputStream.close();
		out.flush();
		out.close();

	}

}

