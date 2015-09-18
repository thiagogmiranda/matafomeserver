package br.com.unigranrio.matafome.dominio.repositorios;

import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;

public interface BarracaRepositorio {
	void salvar(Barraca negocio);
	Barraca obterPorId(long id);
	List<Barraca> obterTodosDoUsuario(long idDono);
	List<Barraca> obterTodosDentroDoRaio(double raio, double lat, double lng);
	Barraca obterPorLatLng(double lat, double lng);
}
