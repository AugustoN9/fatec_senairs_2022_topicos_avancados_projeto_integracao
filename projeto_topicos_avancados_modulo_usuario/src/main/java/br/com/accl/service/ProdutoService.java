package br.com.accl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.accl.entity.Produto;

@Service
public class ProdutoService {
	Map<Integer, Produto> mapaProduto = new HashMap<Integer, Produto>();
	Integer contador = 1;

	public void cadastrarProduto(Produto prod) {

		mapaProduto.put(contador, prod);
		prod.id = contador;
		contador++;
	}

	public Produto buscarPorId(Integer id) {
		
		return mapaProduto.get(id);
		
	}

	public ArrayList<Produto> listarProdutos() {
		
		ArrayList<Produto> lista = new ArrayList<>(mapaProduto.values());
		return lista;
	}

}
