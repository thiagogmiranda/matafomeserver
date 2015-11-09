package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CadastrarNegocio;
import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroNegocio;
import br.com.unigranrio.matafome.infra.repositorios.NegocioRepositorioImpl;

@RestController()
public class NegocioController {
	private NegocioRepositorio negocioRepositorio;
	private CadastrarNegocio cadastrarNegocio;
	
	public NegocioController() {
		negocioRepositorio = new NegocioRepositorioImpl();
		cadastrarNegocio = new CadastrarNegocio(negocioRepositorio, new ValidadorCadastroNegocio(negocioRepositorio));
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
	public ResultadoAcao cadastrar(@RequestBody Negocio barraca){
		return cadastrarNegocio.executar(barraca);
	}
}
