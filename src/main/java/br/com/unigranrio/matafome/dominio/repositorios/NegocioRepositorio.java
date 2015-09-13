package br.com.unigranrio.matafome.dominio.repositorios;

import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;

public interface NegocioRepositorio {
	void salvar(Negocio negocio);
	Negocio obterPorId(long id);
	List<Negocio> obterTodosDoUsuario(long idDono);
}
