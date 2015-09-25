package br.com.unigranrio.matafome.infra.repositorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;

public class BarracaRepositorioImpl extends RepositorioAbstrato implements BarracaRepositorio {

	@Override
	public void salvar(Barraca barraca) {
		String query = "INSERT INTO barraca(iddono, nome, descricao, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
		
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
		Barraca barraca = null;
		
		String query = "SELECT * FROM barraca WHERE id = ?";
		
		try {
			openConnection();
			
			prepareStatement(query, id);
			
			while (readResults()) {
				barraca = resultSetParaObjeto();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return barraca;
	}

	@Override
	public List<Barraca> obterTodosDoUsuario(long idDono) {
		List<Barraca> barracas = new ArrayList<Barraca>();
		
		String query = "SELECT * FROM barraca WHERE iddono = " + idDono;
		
		try {
			openConnection();
			
			prepareStatement(query);
			
			while (readResults()) {
				barracas.add(resultSetParaObjeto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return barracas;
	}

	@Override
	public List<Barraca> obterTodosDentroDoRaio(double raio, double lat, double lng) {
		List<Barraca> barracas = new ArrayList<Barraca>();
		
		String query = "SELECT * FROM barraca WHERE " + (double)(raio/1000.00) + " >= public.geodistance(" + lat + ", " + lng + ", latitude, longitude)";
		
		try {
			openConnection();
			
			prepareStatement(query);
			
			while (readResults()) {
				barracas.add(resultSetParaObjeto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return barracas;
	}
	
	private Barraca resultSetParaObjeto() throws SQLException{
		Barraca barraca = new Barraca();
		
		barraca.setNome(resultSet.getString("nome"));
		barraca.setId(resultSet.getLong("id"));
		barraca.setDescricao(resultSet.getString("descricao"));
		barraca.setIdDono(resultSet.getLong("iddono"));
		barraca.setLatitude(resultSet.getFloat("latitude"));
		barraca.setLongitude(resultSet.getFloat("longitude"));
		
		return barraca;
	}

	@Override
	public Barraca obterPorLatLng(double lat, double lng) {
		Barraca barraca = null;
		
		String query = "SELECT * FROM barraca WHERE latitude = ? AND longitude = ?";
		
		try {
			openConnection();
			
			prepareStatement(query, lat, lng);
			
			while (readResults()) {
				barraca = resultSetParaObjeto();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return barraca;
	}
}
