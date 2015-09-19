package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CadastrarBarraca;
import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroBarraca;
import br.com.unigranrio.matafome.infra.repositorios.BarracaRepositorioImpl;

@RestController()
public class BarracaController {
	private BarracaRepositorio barracaRepositorio;
	private CadastrarBarraca cadastrarBarraca;
	
	public BarracaController() {
		barracaRepositorio = new BarracaRepositorioImpl();
		cadastrarBarraca = new CadastrarBarraca(barracaRepositorio, new ValidadorCadastroBarraca(barracaRepositorio));
	}
	
	@RequestMapping("/barraca/obter-todas-dentro-raio")
	public List<Barraca> obterTodasDentroDoRaio(
			@RequestParam(value = "raio")
			double raio,
			@RequestParam(value = "lat")
			double lat,
			@RequestParam(value = "lng")
			double lng){
		
		return barracaRepositorio.obterTodosDentroDoRaio(raio, lat, lng);
	}
	
	@RequestMapping("/barraca/obter-todas-usuario")
	public List<Barraca> obterTodasDoUsuario(@RequestParam(value = "iddono") long iddono){
		return barracaRepositorio.obterTodosDoUsuario(iddono);
	}
	
	@RequestMapping("/barraca/obter")
	public Barraca obterPorId(@RequestParam(value = "id") long id){
		return barracaRepositorio.obterPorId(id);
	}
	
	@RequestMapping(value = "/barraca/cadastrar", method = RequestMethod.POST)
	public ResultadoAcao cadastrar(@RequestBody Barraca barraca){
		return cadastrarBarraca.executar(barraca);
	}
	
	@RequestMapping("/barraca/criar-teste")
	public ResultadoAcao criarTeste(){
		Barraca b = new Barraca();
		b.setIdDono(1);
		b.setLatitude(-22.789493);
		b.setLongitude(-43.306523);
		b.setNome("Podrão da Esquina");
		
		return cadastrarBarraca.executar(b);
	}
}
