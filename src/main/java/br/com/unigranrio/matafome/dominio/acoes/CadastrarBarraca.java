package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroNegocio;

public class CadastrarBarraca {

	private NegocioRepositorio barracaRepositorio;
	private ValidadorCadastroNegocio validadorCadastro;
	
	public CadastrarBarraca(NegocioRepositorio barracaRepositorio, ValidadorCadastroNegocio validadorCadastro){
		this.barracaRepositorio = barracaRepositorio;
		this.validadorCadastro = validadorCadastro;
	}
	
	public ResultadoAcao executar(Negocio barraca) {
		ResultadoAcao resultado = new ResultadoAcao();
		resultado.adicionarMensagens(validadorCadastro.validar(barraca));
		
		if(resultado.deuCerto()){
			barracaRepositorio.salvar(barraca);
		}
		
		return resultado;
	}

}
