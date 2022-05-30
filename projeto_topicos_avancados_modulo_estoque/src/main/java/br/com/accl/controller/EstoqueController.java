package br.com.accl.controller;

import java.util.HashMap;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.accl.entidades.Estoque;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController(value = "API para manipulacao de estoque")
@RequestMapping("estoque")
@Api(description = "Api de estoque")
public class EstoqueController {

	Map<Integer, Integer> mapaEstoque = new HashMap<Integer, Integer>();

	public EstoqueController() {
		
		mapaEstoque.put(001, 20);	
		mapaEstoque.put(002, 30);
		mapaEstoque.put(003, 50);
		mapaEstoque.put(004, 15);
		mapaEstoque.put(005, 10);

	}

	@RequestMapping(value = "/teste/", method = RequestMethod.GET)
	public ResponseEntity<Void> teste() throws Exception {
		System.out.println("Processando teste");
		return null;
	}
	
	@ApiOperation(value = "Verificar se existe produto no Estoque via requestParam")
	@RequestMapping(value = "/verificarSeExisteNoEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificarSeExisteEstoque(

			@RequestParam("produto") Integer codProduto
			
			)
					throws Exception {

		System.out.println("Processando Se Existe Produto no Estoque");
		
		Boolean verificarSeExisteEstoque = mapaEstoque.containsKey(codProduto);
		
		if(verificarSeExisteEstoque){			
			
			System.out.println("VERIFICACAO CONCLUIDA, ESTE PRODUTO FOI ENCONTRADO NO CADASTRO DO ESTOQUE");
			return ResponseEntity.ok(true);	

		}else {
			System.out.println("VERIFICACAO NAO ENCONTROU CADASTRO DESTE PRODUTO EM ESTOQUE");
			return ResponseEntity.ok(false);
			
		}

	}

	@ApiOperation(value = "Verificar Estoque via requestParam")
	@RequestMapping(value = "/verificarEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificarEstoque(

			@RequestParam("produto") Integer codProduto, 
			@RequestParam("quantidade") Integer quantProduto 
			
			)

			throws Exception {

		System.out.println("Processando Verificação de Estoque");
		
		Boolean verificarEstoque = mapaEstoque.containsKey(codProduto);
		
		if(verificarEstoque){
			
			//Integer estoqueAtual = mapaEstoque.get(quantProduto);
			Integer estoqueAtual = mapaEstoque.get(codProduto);
			Integer estoqueAtualizado =  estoqueAtual - quantProduto;
			if(estoqueAtual >= quantProduto) {
				System.out.println("Estoque Atual [ "+estoqueAtual+" ] ABASTECE a solicitacao [ "+ quantProduto + " ] estoque Atual [ "+ estoqueAtualizado  + " ]");
				return ResponseEntity.ok(true);
				
			}else {
				System.out.println("Estoque Atual NAO ABASTECE esta demanda");
				return ResponseEntity.ok(false);	
				
			}		

		}else {
			System.out.println("VERIFICACAO NAO ENCONTROU ESTOQUE DESTE PRODUTO");
			return ResponseEntity.ok(false);
			
		}

	}
	
	
	
	@ApiOperation(value = "Atualizar Estoque via requestParam")
	@RequestMapping(value = "/atualizarEstoque/", method = RequestMethod.GET)
	public ResponseEntity<Integer> atualizarEstoque(

			@RequestParam("produto") Integer codProduto, 
			@RequestParam("quantidade") Integer quantProduto 
			
			)

			throws Exception {
		
		System.out.println("Processando Atualização de Estoque");
		
		Boolean atualizarEstoque = mapaEstoque.containsKey(codProduto);
		
		if(atualizarEstoque){
			
			Integer estoqueAtual = mapaEstoque.get(codProduto);
			if(estoqueAtual >= quantProduto) {
				estoqueAtual = estoqueAtual - quantProduto;
				mapaEstoque.put(codProduto, estoqueAtual);
				System.out.println("Estoque Atualizado com Sucesso");
				return ResponseEntity.ok(estoqueAtual);

			}else {
				System.out.println("Estoque insuficiente para esta Atualização");
				System.out.println("Estoque Atual =>"+estoqueAtual);
				System.out.println("Quantidade para retirar do estoque =>"+quantProduto);

				return ResponseEntity.ok(0);
			}
		}else {
			mapaEstoque.put(codProduto, quantProduto);
			System.out.println("Produto Novo adicionado ao Estoque, Atualização realizada com Sucesso");
	
			return ResponseEntity.ok(quantProduto);

		}
		
	}

		

}
