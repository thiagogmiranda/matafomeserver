package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CadastrarAvaliacao;
import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroAvaliacao;
import br.com.unigranrio.matafome.infra.repositorios.AvaliacaoRepositorioImpl;

@RestController()
public class AvaliacaoController {
	private AvaliacaoRepositorio avaliacaoRepositorio;
	
	private CadastrarAvaliacao cadastrarAvaliacao;
	
	public AvaliacaoController(){
		this.avaliacaoRepositorio = new AvaliacaoRepositorioImpl();
		this.cadastrarAvaliacao = new CadastrarAvaliacao(avaliacaoRepositorio, new ValidadorCadastroAvaliacao(avaliacaoRepositorio));
	}
	
	@RequestMapping("avaliacao/cadastrar")
	public ResultadoAcao<Avaliacao> cadastrar(@RequestBody Avaliacao avaliacao){
		return cadastrarAvaliacao.executar(avaliacao);
	}
	
	@RequestMapping("avaliacao/obter-todas")
	public List<Avaliacao> obterAvaliacoesPara(@RequestParam(value = "idVendedor") long idVendedor){
		return avaliacaoRepositorio.obterAvaliacoesPara(idVendedor);
	}
}
