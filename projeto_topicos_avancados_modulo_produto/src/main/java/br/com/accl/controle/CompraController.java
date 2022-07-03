package br.com.accl.controle;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.accl.entidade.Compra;
import br.com.accl.entidade.Produto;
import br.com.accl.repositorio.EstoqueRepositorio;
import br.com.accl.servico.CompraServico;
import br.com.accl.servico.ProdutoServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API para manipulacao de compras")
@RequestMapping("compras")
@Api(description="Api de compras")
public class CompraController {

	@Autowired
	private CompraServico compraservice;
	
	@Autowired
	private ProdutoServico produtoservice;
	
	@Autowired
	private EstoqueRepositorio estoquerepositorio;
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Autowired
    private Job processJob;
	
	
	
	/*----------------------------- [ API - salvar compra - comprarProduto (COM INTEGRA��O) ] ------------------------------------*/

	 
	@ApiOperation(value = "Salvar uma compra via RequestParam COM INTEGRA��O" )
	 @ApiResponses(value = {
		@ApiResponse(code = 200, message = "Compra salva com sucesso", response = String.class),
		@ApiResponse(code = 405, message = "Problema de valida��o da compra",response = String.class),
		@ApiResponse(code = 500, message = "Error no servidor.")	 
	 })
	 @RequestMapping(value = "/salvarComIntegracao/", method = RequestMethod.POST)	 
	  public ResponseEntity<Integer> salvarCompraIntegrada(
			  
			  @RequestParam("Produto") Integer idProduto,
			  @RequestParam("quantidade") Integer quantidade
			  )
	  
			   throws Exception {
		 
		 System.out.println("Processando metodo salvarCompraIntegrada ...");
		 
	
		 ResponseEntity<Boolean> estoqueSuficiente = estoquerepositorio.verificarEstoque(idProduto, quantidade);
		 Boolean estoqueSuficienteOk = estoqueSuficiente.getBody();
		 
		 
		 
		 	 
		 Compra compra = new Compra();
		 Produto prod = new Produto();
		 
		 compra.setProduto(prod);
		 
		 compra.produto.id = idProduto;
		 compra.quantidade = quantidade;
		  
		 Produto prod_ = produtoservice.buscarPorId(idProduto);
		 compra.setProduto(prod_);
		
		if(estoqueSuficienteOk != false) {
			compraservice.salvarCompra(compra);
			
			System.out.println(compra.id);
			System.out.println("Quantidade solicitada "+quantidade+" status do estoque ");
		 
			estoquerepositorio.atualizarEstoque(idProduto, quantidade);
		
			
			return ResponseEntity.ok(compra.id);
 
		}else {
			
			System.out.println("Quantidade solicitada "+quantidade+" Insuficiente no estoque ");
			return ResponseEntity.badRequest().build();			
		}
	  }
	
	
	
	
	/*----------------------------- [ API - salvar compra - comprarProduto (sem integra��o) ] ------------------------------------*/

	 
	@ApiOperation(value = "Salvar uma compra via RequestParam" )
	 @ApiResponses(value = {
		@ApiResponse(code = 200, message = "Compra salva com sucesso", response = String.class),
		@ApiResponse(code = 405, message = "Problema de valida��o da compra",response = String.class),
		@ApiResponse(code = 500, message = "Error no servidor.")	 
	 })
	 @RequestMapping(value = "/salvar/", method = RequestMethod.POST)	 
	  public ResponseEntity<Integer> salvarCompra(
			  
			  @RequestParam("idProduto") Integer idProduto,
			  @RequestParam("quantidade") Integer quantidade
			  )
	  
			   throws Exception {
		 
		 System.out.println("Processando metodo salvarCompra ...");
		 
		 Compra compra = new Compra();
		 Produto prod_ = new Produto();
		 
		 compra.setProduto(prod_);
		 
		 compra.produto.id = idProduto;
		 compra.quantidade = quantidade;	
		 
		Produto prod = produtoservice.buscarPorId(idProduto);
		compra.setProduto(prod);
		
		if(prod != (null)) {
			compraservice.salvarCompra(compra);
			
			System.out.println(compra.id);

		 
			return ResponseEntity.ok(compra.id);
 
		}else {
			return ResponseEntity.badRequest().build();
		}
	  }
	
	/*----------------------------- [ API - listar todas as compras ] ------------------------------------*/
	
	 
	 @ApiOperation(value = "Retorna todos as compras realizadas" )
	 @ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorno da lista de compras realizadas com sucesso", response = Compra.class)		
			 
	 })
	 @RequestMapping(value = "/listar/", method = RequestMethod.GET)	 
	  public ResponseEntity<List<Compra>> listar()			  
			  throws Exception {		 
		 
		 System.out.println("Processando listar Compras ");
		 		 
		 return ResponseEntity.ok(compraservice.listarCompras());
	  }

	 
/*----------------------------- [ API - executaLoteComprasAcima10 ] ------------------------------------*/
	
 
	 @ApiOperation(value = "Retorna Lote compras realizadas Acima 10" )
	 @ApiResponses(value = {
		@ApiResponse(code = 200, message = "retorno da lote de comprasacima de 10 realizadas com sucesso", response = Compra.class)		
			 
	 })
	 @RequestMapping(value = "/loteacimade10/", method = RequestMethod.GET)	 
	  public ResponseEntity<String> Lote()  
			  throws Exception {		 
		 
		 System.out.println("Processando lote de Compras Acima de 10 ");
		 		 
		 return ResponseEntity.ok("Lote de 10");
	  }
	 
	 
/*----------------------------- [ API - executaProcessamentoEmLote - ComprasAcima10 ] ------------------------------------*/

	 
	 @RequestMapping(value = "/inicia-processamento-loteacimade10/", method = RequestMethod.GET)	 
	  public void iniciarProcessamentoLote() throws JobExecutionAlreadyRunningException, 
	  JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		 JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
	                .toJobParameters();
	        jobLauncher.run(processJob, jobParameters);
		 
	 }

}
