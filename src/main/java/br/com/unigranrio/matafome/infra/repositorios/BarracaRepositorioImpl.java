package br.com.unigranrio.matafome.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;

public class BarracaRepositorioImpl extends RepositorioAbstrato implements BarracaRepositorio {

	@Override
	public void salvar(Barraca barraca) {
		String query = "INSERT INTO barraca(nome, email, senha) VALUES (?, ?, ?, ?, ?)";
		
		try {
			openConnection();
			
			prepareStatement(query, barraca.getIdDono(), barraca.getNome(), barraca.getDescricao(), barraca.getLatitude(), barraca.getLongitude());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public Barraca obterPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barraca> obterTodosDoUsuario(long idDono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Barraca> obterTodosDentroDoRaio(double raio, double lat, double lng) {
		List<Barraca> barracas = new ArrayList<Barraca>();
		
		String query = "SELECT * FROM barraca WHERE " + (double)(raio/1000.00) + " >= public.geodistance(" + lat + ", " + lng + ", latitude, longitude)";
		
		try {
			openConnection();
			
			prepareStatement(query);
			
			while (readResults()) {
				Barraca negocio = new Barraca();
				negocio.setNome(resultSet.getString("nome"));
				negocio.setId(resultSet.getLong("id"));
				negocio.setDescricao(resultSet.getString("descricao"));
				negocio.setIdDono(resultSet.getLong("iddono"));
				negocio.setLatitude(resultSet.getFloat("latitude"));
				negocio.setLongitude(resultSet.getFloat("longitude"));
				
				barracas.add(negocio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return barracas;
	}
}
