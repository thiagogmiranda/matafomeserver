package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CadastrarNegocio;
import br.com.unigranrio.matafome.dominio.acoes.EditarNegocio;
import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.DetalheNegocio;
import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroNegocio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorEdicaoNegocio;
import br.com.unigranrio.matafome.infra.repositorios.AvaliacaoRepositorioImpl;
import br.com.unigranrio.matafome.infra.repositorios.NegocioRepositorioImpl;

@RestController()
public class NegocioController {
	private NegocioRepositorio negocioRepositorio;
	private AvaliacaoRepositorio avaliacaoRepositorio;
	
	private CadastrarNegocio cadastrarNegocio;
	private EditarNegocio editarNegocio;
	
	public NegocioController() {
		negocioRepositorio = new NegocioRepositorioImpl();
		avaliacaoRepositorio = new AvaliacaoRepositorioImpl();
		
		cadastrarNegocio = new CadastrarNegocio(negocioRepositorio, new ValidadorCadastroNegocio(negocioRepositorio));
		editarNegocio = new EditarNegocio(negocioRepositorio, new ValidadorEdicaoNegocio(negocioRepositorio));
	}
	
	@RequestMapping("/lanches/todos-no-raio")
	public List<Negocio> obterTodasDentroDoRaio(
			@RequestParam(value = "raio")
			double raio,
			@RequestParam(value = "lat")
			double lat,
			@RequestParam(value = "lng")
			double lng){
		
		return negocioRepositorio.obterTodosDentroDoRaio(raio, lat, lng);
	}
	
	@RequestMapping("/negocio")
	public Negocio obterPorId(@RequestParam(value = "idDono") long idDono){
		return negocioRepositorio.obterNegocioDoDono(idDono);
	}
	
	@RequestMapping(value = "/negocio/cadastrar", method = RequestMethod.POST)
	public ResultadoAcao<Negocio> cadastrar(@RequestBody Negocio negocio){
		return cadastrarNegocio.executar(negocio);
	}
	
	@RequestMapping(value = "/negocio/editar", method = RequestMethod.POST)
	public ResultadoAcao<Negocio> editar(@RequestBody Negocio negocio){
		return editarNegocio.executar(negocio);
	}
	
	@RequestMapping(value = "/negocio/detalhes")
	public DetalheNegocio detalheNegocio(
			@RequestParam(value = "idNegocio") long idNegocio, 
			@RequestParam(value = "idUsuario") long idUsuario){
		DetalheNegocio detalhe = new DetalheNegocio();
		
		detalhe.setNegocio(negocioRepositorio.obterPorId(idNegocio));
		detalhe.setAvaliacaoUsuario(avaliacaoRepositorio.obterAvaliacaoDoUsuario(idUsuario, detalhe.getNegocio().getId()));
		
		return detalhe;
	}
}
