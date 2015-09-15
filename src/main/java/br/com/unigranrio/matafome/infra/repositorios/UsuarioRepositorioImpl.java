package br.com.unigranrio.matafome.infra.repositorios;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class UsuarioRepositorioImpl extends RepositorioAbstrato implements UsuarioRepositorio {

	@Override
	public void salvar(Usuario usuario) {
		String query = "INSERT INTO Usuario(nome, email, senha) VALUES (?, ?, ?)";
		
		try {
			openConnection();
			
			prepareStatement(query, usuario.getNome(), usuario.getEmail(), usuario.getSenha());
			
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
				usuario.setNome(resultSet.getString("nome"));
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
}
