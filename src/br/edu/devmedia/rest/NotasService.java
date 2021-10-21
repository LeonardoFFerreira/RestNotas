package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.devmedia.dao.NotaDAO;
import br.edu.devmedia.entidade.Nota;

@Path("/notas")
public class NotasService {

	private NotaDAO notaDAO;

	@PostConstruct
	private void init() {
		notaDAO = new NotaDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nota> listarNotas() {
		List<Nota> lista = null;

		try {
			lista = notaDAO.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Nota buscaPorId(@PathParam("id") int idNota) {
		Nota nota = null;

		try {
			nota = notaDAO.buscarNotaporId(idNota);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNota(Nota nota) {
		String msg = "";
		System.out.println(nota.getTitulo());

		try {
			notaDAO.addNota(nota);
			msg = "Nota adicionada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao adicionar a nota!";
			e.printStackTrace();
		}
		return msg;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarNota(Nota nota, @PathParam("id") int idNota) {
		String msg = null;
		System.out.println(nota.getTitulo());

		try {
			notaDAO.editarNota(nota, idNota);
			msg = "Nota editada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar nota!";
			e.printStackTrace();
		}
		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerNota(@PathParam("id") int idNota) {
		String msg = "";

		try {
			notaDAO.removerNota(idNota);
			msg = "Nota deletada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao excluir a nota!";
			e.printStackTrace();
		}
		return msg;
	}
}
