package br.com.unigranrio.matafome.aplicacao.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.infra.repositorios.NegocioRepositorioImpl;

@RestController()
public class NegocioController {
	private NegocioRepositorio negocioRepositorio;
	
	public NegocioController() {
		negocioRepositorio = new NegocioRepositorioImpl();
	}
	
	@RequestMapping("/usuario/obter-por-email")
	public List<Negocio> obterPorEmail(
			@RequestParam(value = "raio")
			double raio,
			@RequestParam(value = "lat")
			double lat,
			@RequestParam(value = "lng")
			double lng){
		
		return negocioRepositorio.obterTodosDentroDoRaio(raio, new LatLng(lat, lng));
	}
}
