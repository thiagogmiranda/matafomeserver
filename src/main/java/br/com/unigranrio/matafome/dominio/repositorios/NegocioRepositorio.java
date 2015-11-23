package br.com.unigranrio.matafome.dominio.repositorios;

import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;

public interface NegocioRepositorio {
	void salvar(Negocio negocio);
	void atualizar(Negocio negocio);
	Negocio obterPorId(long id);
	List<Negocio> obterTodosDoUsuario(String email);
	List<Negocio> obterTodosDentroDoRaio(double raio, double lat, double lng);
	Negocio obterPorLatLng(double lat, double lng);
	boolean existeNegocioNoLocal(double lat, double lng);
	Negocio obterNegocioDoDono(long idDono);
}
