package br.com.unigranrio.matafome.dominio.repositorios;

import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;

public interface AvaliacaoRepositorio {

	List<Avaliacao> obterAvaliacoesPara(long idNegocio);

}
