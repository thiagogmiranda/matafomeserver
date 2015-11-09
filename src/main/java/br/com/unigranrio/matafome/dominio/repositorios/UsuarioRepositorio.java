package br.com.unigranrio.matafome.dominio.repositorios;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;

public interface UsuarioRepositorio {
	void salvar(Usuario usuario);
	Usuario obterPorEmail(String email);
	boolean existeUsuarioComEmail(String email);
	void atualizar(Usuario usuario);
}
