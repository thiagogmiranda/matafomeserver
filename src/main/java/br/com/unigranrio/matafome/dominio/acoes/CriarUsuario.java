package br.com.unigranrio.matafome.dominio.acoes;

import java.util.Date;

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
	
	public ResultadoAcao<Usuario> executar(Usuario usuario){
		ResultadoAcao<Usuario> resultado = new ResultadoAcao<Usuario>();
		resultado.adicionarMensagens(validadorCadastro.validar(usuario));
		
		if(resultado.estaValido()){
			usuario.setDataCadastro(new Date());
			usuario.setTipo("UN"); // Não definido ainda, usuário precisa escolher
			
			usuarioRepositorio.salvar(usuario);
			
			usuario = usuarioRepositorio.obterPorEmail(usuario.getEmail());
			
			resultado.setData(usuario);
		}
		
		return resultado;
	}
}
