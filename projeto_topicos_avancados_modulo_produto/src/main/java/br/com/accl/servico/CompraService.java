package br.com.accl.servico;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.accl.entidade.Compra;


@Service
public class CompraService {
	Map<Integer, Compra> mapaCompra = new HashMap<Integer, Compra>();
	Integer contador = 1;

	public void salvarCompra(Compra compra) {
		
		mapaCompra.put(contador, compra);
		compra.id = contador;
		contador++;
	}

	public Compra buscarPorId(Integer id) {
		Compra obj = null;
		
		if(id != (null)) { 
			obj = mapaCompra.get(id);
		}
		
		return obj;
		
	}

	public ArrayList<Compra> listarCompras() {
		
		ArrayList<Compra> lista = new ArrayList<>(mapaCompra.values());
		return lista;
	}
	
	 
}