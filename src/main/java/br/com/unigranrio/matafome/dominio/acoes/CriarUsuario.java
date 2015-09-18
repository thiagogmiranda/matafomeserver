package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroUsuario;

public class CriarUsuario {
	private UsuarioRepositorio usuarioRepositorio;
	private ValidadorCadastroUsuario validadorCadastro;
	
	public CriarUsuario(UsuarioRepositorio usuarioRepositorio, ValidadorCadastroUsuario validadorCadastro){
		this.usuarioRepositorio = usuarioRepositorio;
		this.validadorCadastro = validadorCadastro;
	}
	
	public ResultadoAcao Executar(Usuario usuario){
		ResultadoAcao resultado = new ResultadoAcao();
		resultado.adicionarMensagens(validadorCadastro.validar(usuario));
		
		if(resultado.isSucesso()){
			usuarioRepositorio.salvar(usuario);
		}
		
		return resultado;
	}
}
