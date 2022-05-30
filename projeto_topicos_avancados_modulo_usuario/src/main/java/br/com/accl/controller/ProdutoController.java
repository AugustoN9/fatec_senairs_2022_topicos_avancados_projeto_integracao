package br.com.accl.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.accl.entity.Produto;
import br.com.accl.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value = "API para manipulacao de produtos")
@RequestMapping("produtos")
@Api(description = "Api de produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoservice;
	
	
	/*----------------------------- [ API - cadastro do produto ] ------------------------------------*/


	@ApiOperation(value = "Cadastrar um produto via RequestParam")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto cadastrado com sucesso", response = String.class),
			@ApiResponse(code = 405, message = "Problema de validação do produto", response = String.class),
			@ApiResponse(code = 500, message = "Error no servidor.") })
	@RequestMapping(value = "/cadastrar/", method = RequestMethod.POST)
	public ResponseEntity<Integer> cadastrarProduto(

			@RequestParam("nome") String nome, 
			@RequestParam("marca") String marca,
			@RequestParam("preco") Float preco,
			@RequestParam("validade") String validade
			)

			throws Exception {

		System.out.println("Processando metodo cadastrarProduto ...");

		Produto produto = new Produto();
		produto.nome = nome;
		produto.marca = marca;
		produto.preco = preco;
		produto.validade = validade;

		produtoservice.cadastrarProduto(produto);

		return ResponseEntity.ok(produto.id);
	}

	/*----------------------------- [ API - buscar produto por id ] ------------------------------------*/

	@ApiOperation(value = "Retorna produto pelo ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retorno com sucesso", response = Produto.class)

	})
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscar(

			@PathVariable("id") Integer id)

			throws Exception {

		System.out.println("Processando metodo buscarPorId ... ");

		
		return ResponseEntity.ok(produtoservice.buscarPorId(id));
	}

	/*----------------------------- [ API - listar todos os produtos ] ------------------------------------*/

	@ApiOperation(value = "Retorna todos os produtos" )
	 @ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorno da lista de produtos com sucesso", response = Produto.class)		
			 
	 })
	 @RequestMapping(value = "/listar/", method = RequestMethod.GET)	 
	  public ResponseEntity<List<Produto>> listar()			  
			  throws Exception {		 
		 
		 System.out.println("Processando metodo listarProduto ... ");
		 
		 
		 		 
		 return ResponseEntity.ok(produtoservice.listarProdutos());
	  }

}
