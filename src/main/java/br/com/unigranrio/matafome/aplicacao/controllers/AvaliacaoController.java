package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;
import br.com.unigranrio.matafome.infra.repositorios.AvaliacaoRepositorioImpl;

@RestController()
public class AvaliacaoController {
	private AvaliacaoRepositorio avaliacaoRepositorio;
	
	public AvaliacaoController(){
		this.avaliacaoRepositorio = new AvaliacaoRepositorioImpl();
	}
	
	@RequestMapping("avaliacao/cadastrar")
	public ResultadoAcao<Avaliacao> cadastrar(Avaliacao avaliacao){
		return null;
	}
	
	@RequestMapping("avaliacao/obter-todas")
	public List<Avaliacao> obterAvaliacoesPara(@RequestParam(value = "idNegocio") long idNegocio){
		return avaliacaoRepositorio.obterAvaliacoesPara(idNegocio);
	}
}
