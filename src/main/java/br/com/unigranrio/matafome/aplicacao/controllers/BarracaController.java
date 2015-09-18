package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;
import br.com.unigranrio.matafome.infra.repositorios.BarracaRepositorioImpl;

@RestController()
public class BarracaController {
	private BarracaRepositorio negocioRepositorio;
	
	public BarracaController() {
		negocioRepositorio = new BarracaRepositorioImpl();
	}
	
	@RequestMapping("/negocio/obter-todos-dentro-raio")
	public List<Barraca> obterPorEmail(
			@RequestParam(value = "raio")
			double raio,
			@RequestParam(value = "lat")
			double lat,
			@RequestParam(value = "lng")
			double lng){
		
		return negocioRepositorio.obterTodosDentroDoRaio(raio, lat, lng);
	}
}
