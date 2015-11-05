package br.com.unigranrio.matafome.infra.repositorios;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class UsuarioRepositorioImpl extends RepositorioAbstrato implements UsuarioRepositorio {

	@Override
	public void salvar(Usuario usuario) {
		String query = "INSERT INTO Usuario(email, senha) VALUES (?, ?, ?)";

		try {
			openConnection();

			prepareStatement(query, usuario.getEmail(), usuario.getSenha());

		} catch (Exception e) {

		} finally {
			closeConnection();
		}
	}

	@Override
	public Usuario obterPorEmail(String email) {
		Usuario usuario = null;

		String query = "SELECT * FROM Usuario WHERE email = ?";

		try {
			openConnection();

			prepareStatement(query, email);

			while (readResults()) {
				usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setSenha(resultSet.getString("senha"));

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

		String query = "SELECT COUNT(1) as Total FROM Usuario WHERE email = ?";

		try {
			openConnection();

			prepareStatement(query, email);

			int count = 0;

			while (readResults()) {
				count = resultSet.getInt("Total");
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
}
