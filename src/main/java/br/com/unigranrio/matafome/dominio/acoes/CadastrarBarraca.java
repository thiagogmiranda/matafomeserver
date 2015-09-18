package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroBarraca;

public class CadastrarBarraca {

	private BarracaRepositorio barracaRepositorio;
	private ValidadorCadastroBarraca validadorCadastro;
	
	public CadastrarBarraca(BarracaRepositorio barracaRepositorio, ValidadorCadastroBarraca validadorCadastro){
		this.barracaRepositorio = barracaRepositorio;
		this.validadorCadastro = validadorCadastro;
	}
	
	public ResultadoAcao executar(Barraca barraca) {
		ResultadoAcao resultado = new ResultadoAcao();
		resultado.adicionarMensagens(validadorCadastro.validar(barraca));
		
		if(resultado.deuCerto()){
			barracaRepositorio.salvar(barraca);
		}
		
		return resultado;
	}

}
