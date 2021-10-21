package br.edu.devmedia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.devmedia.config.BDconfig;
import br.edu.devmedia.entidade.Nota;

public class NotaDAO {

	public List<Nota> listarNotas() throws Exception {
		List<Nota> lista = new ArrayList<>();

		Connection connection = BDconfig.getConnection();

		String sql = "select * from tb_nota";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			Nota nota = new Nota();
			nota.setId(rs.getInt("id_note"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));

			lista.add(nota);
		}
		return lista;
	}

	public Nota buscarNotaporId(int idNota) throws Exception {
		Nota nota = null;

		Connection connection = BDconfig.getConnection();

		String sql = "select * from tb_nota where id_note = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idNota);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("id_note"));
			nota.setTitulo(rs.getString("titulo"));
			nota.setDescricao(rs.getString("descricao"));
		}
		return nota;
	}

	public void addNota(Nota nota) throws Exception {
		Connection connection = BDconfig.getConnection();

		String sql = "insert into tb_nota (titulo, descricao) values(?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();

	}

	public void editarNota(Nota nota, int idNota) throws Exception {
		Connection connection = BDconfig.getConnection();

		String sql = "update tb_nota set titulo = ?, descricao = ? where id_note = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, idNota);
		statement.execute();

	}

	public void removerNota(int idNota) throws Exception {
		Connection connection = BDconfig.getConnection();

		String sql = "delete from tb_nota where id_note = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idNota);
		statement.execute();

	}

}
