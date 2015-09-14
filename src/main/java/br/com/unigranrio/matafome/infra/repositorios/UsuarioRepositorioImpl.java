package br.com.unigranrio.matafome.infra.repositorios;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class UsuarioRepositorioImpl extends RepositorioAbstrato implements UsuarioRepositorio {

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		
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
			}
		} catch (Exception e) {
			
		} finally {
			closeConnection();
		}
		
		return usuario;
	}	
}
