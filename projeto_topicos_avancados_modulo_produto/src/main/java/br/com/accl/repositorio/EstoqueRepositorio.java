package br.com.accl.repositorio;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//url: endereco de onde esta o servico.

@FeignClient(value = "modulo-estoque", 
url = "http://localhost:8087/estoque/")

//ip do docker

//,fallback = EstoqueRepositorioFalha.class)

public interface EstoqueRepositorio {
	
	@RequestMapping(value = "/verificarSeExisteNoEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificarSeExisteEstoque(

			@RequestParam("produto") Integer codProduto
			
			)throws Exception;
	
	
	@RequestMapping(value = "/verificarEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificarEstoque(

			@RequestParam("produto") Integer codProduto, 
			@RequestParam("quantidade") Integer quantProduto 
			
			)

			throws Exception;
	
	@RequestMapping(value = "/atualizarEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Integer> atualizarEstoque(

			@RequestParam("produto") Integer codProduto, 
			@RequestParam("quantidade") Integer quantProduto 
			
			)

			throws Exception;
	 
	
}
