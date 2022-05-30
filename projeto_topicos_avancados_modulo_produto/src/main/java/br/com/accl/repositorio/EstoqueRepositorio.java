package br.com.accl.repositorio;

import org.springframework.cloud.openfeign.FeignClient;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.accl.entidade.Produto;


//url: endereco de onde esta o servico.

@FeignClient(value = "modulo-estoque", 
url = "http://localhost:8087/estoque/")

//,fallback = EstoqueRepositorioFalha.class)

public interface EstoqueRepositorio {

	@RequestMapping(value = "/verificarSeExisteNoEstoque/", method = RequestMethod.GET)
	public Boolean existeEstoque(
			  @PathVariable("Produto") Integer codProduto
			  ) throws Exception;
	
	@RequestMapping(value = "/verificarQuantidadeNoEstoque/{id}", method = RequestMethod.GET)
	public Integer quantidadeEstoqueID(
			  @PathVariable("id") Integer codProduto
			  ) throws Exception;
	
	@RequestMapping(value = "/verificarEstoque/", method = RequestMethod.GET)
	public Produto verificarEstoque(
			 @PathVariable("codProduto") Integer codProduto,
			 @PathVariable("quantProduto") Integer quantProduto
			  ) throws Exception;
	 
	 @RequestMapping(value = "/atualizarEstoque/", method = RequestMethod.GET)	 
	  public ResponseEntity<Integer> atualizarEstoque(
			  @PathVariable("Produto") Integer codProduto,
			  @PathVariable("Quantidade") Integer quantProduto
			  ) throws Exception;
	 
	
}
