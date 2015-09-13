package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class CriarUsuario {
	private UsuarioRepositorio usuarioRepositorio;
	
	public CriarUsuario(UsuarioRepositorio usuarioRepositorio){
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	public void Executar(Usuario usuario){
		usuarioRepositorio.salvar(usuario);
	}
}
