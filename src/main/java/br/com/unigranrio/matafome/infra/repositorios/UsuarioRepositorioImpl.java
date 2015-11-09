package br.com.unigranrio.matafome.infra.repositorios;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class UsuarioRepositorioImpl extends RepositorioAbstrato implements UsuarioRepositorio {

	@Override
	public void salvar(Usuario usuario) {
		String query = "insert into Usuario(nome, email, senha, tipo, datacadastro) values (?, ?, ?, ?, ?)";

		try {
			openConnection();

			prepareStatement(query, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTipo(), usuario.getDataCadastro());

		} catch (Exception e) {

		} finally {
			closeConnection();
		}
	}

	@Override
	public Usuario obterPorEmail(String email) {
		Usuario usuario = null;

		String query = "select * from Usuario where email = ?";

		try {
			openConnection();

			prepareStatement(query, email);

			while (readResults()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setTipo(resultSet.getString("tipo"));
				usuario.setDataCadastro(resultSet.getDate("datacadastro"));

				break;
			}
		} catch (Exception e) {

		} finally {
			closeConnection();
		}

		return usuario;
	}

	@Override
	public boolean existeUsuarioComEmail(String email) {
		boolean existe = false;

		String query = "select count(1) as total from Usuario where email = ?";

		try {
			openConnection();

			prepareStatement(query, email);

			int count = 0;

			while (readResults()) {
				count = resultSet.getInt("total");
				break;
			}

			existe = count > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return existe;
	}

	@Override
	public void atualizar(Usuario usuario) {
		String query = "update Usuario set tipo = ? where id = ?";

		try {
			openConnection();

			prepareStatement(query, usuario.getTipo(), usuario.getId());

		} catch (Exception e) {

		} finally {
			closeConnection();
		}
	}
}
