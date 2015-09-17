package br.com.unigranrio.matafome.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.LatLng;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;

public class NegocioRepositorioImpl extends RepositorioAbstrato implements NegocioRepositorio {

	@Override
	public void salvar(Negocio negocio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Negocio obterPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Negocio> obterTodosDoUsuario(long idDono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Negocio> obterTodosDentroDoRaio(double raio, LatLng coordenadas) {
		List<Negocio> barracas = new ArrayList<Negocio>();
		
		String query = "SELECT * FROM barraca";
		
		try {
			openConnection();
			
			prepareStatement(query);
			
			while (readResults()) {
				Negocio negocio = new Negocio();
				negocio.setNome(resultSet.getString("nome"));
				negocio.setId(resultSet.getLong("id"));
				negocio.setDescricao(resultSet.getString("descricao"));
				negocio.setIdDono(resultSet.getLong("iddono"));
				negocio.setLatitude(resultSet.getFloat("latitude"));
				negocio.setLongitude(resultSet.getFloat("longitude"));
				
				LatLng localNegocio = new LatLng(negocio.getLatitude(), negocio.getLongitude());
				
				double d = distancia(coordenadas, localNegocio);
				
				if(d <= (raio/1000)){
					barracas.add(negocio);
				}
			}
		} catch (Exception e) {
			
		} finally {
			closeConnection();
		}
		
		return barracas;
	}
	
	private double distancia(LatLng pontoA, LatLng pontoB){		
		// Conversão de graus pra radianos das latitudes
        double firstLatToRad = Math.toRadians(pontoA.lat);
        double secondLatToRad = Math.toRadians(pontoB.lat);

        // Diferença das longitudes
        double deltaLongitudeInRad = Math.toRadians(pontoB.lng - pontoA.lng);

        // Cálcula da distância entre os pontos
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
		        * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
		        * Math.sin(secondLatToRad))
		        * 6371;
	}

}
