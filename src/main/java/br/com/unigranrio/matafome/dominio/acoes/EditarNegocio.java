package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorEdicaoNegocio;

public class EditarNegocio {
	private NegocioRepositorio repositorio;
	private ValidadorEdicaoNegocio validador;
	
	public EditarNegocio(NegocioRepositorio repositorio, ValidadorEdicaoNegocio validador){
		this.repositorio = repositorio;
		this.validador = validador;
	}
	
	public ResultadoAcao<Negocio> executar(Negocio negocio){
		ResultadoAcao<Negocio> resultado = new ResultadoAcao<Negocio>();
		resultado.adicionarMensagens(validador.validar(negocio));
		
		if(resultado.estaValido()){
			Negocio salvo = repositorio.obterPorId(negocio.getId());
			
			salvo.setDescricao(negocio.getDescricao());
			salvo.setLatitude(negocio.getLatitude());
			salvo.setLongitude(negocio.getLongitude());
			
			repositorio.atualizar(salvo);
		}
		
		return resultado;
	}
}
