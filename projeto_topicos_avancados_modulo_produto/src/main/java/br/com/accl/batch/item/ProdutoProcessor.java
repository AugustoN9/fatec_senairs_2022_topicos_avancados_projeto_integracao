package br.com.accl.batch.item;

import org.springframework.batch.item.ItemProcessor;
import br.com.accl.entidade.ProcessaProduto;
import br.com.accl.entidade.Produto;

public class ProdutoProcessor implements ItemProcessor<Produto, ProcessaProduto> {

	@Override
	public ProcessaProduto process(Produto item) throws Exception {

		System.out.println("Processor " + item);
		
		ProcessaProduto procProd = null;
		
		
		if(item.preco > 10.00) {
			procProd = new ProcessaProduto();
			procProd.setId(item.id);
			procProd.setNome(item.nome);
			procProd.setPreco(item.preco);
		}
		
		
		/*
		System.out.println("Processor " + item);
		
		ProcessaUsuario pu = new ProcessaUsuario();
		pu.setNome(item.nome); // de-para
		if(item.idade >= 18) {
			pu.setStatus("e maior de idade");
		}else {
			pu.setStatus("e menor de idade");
		}
		
		return pu;
		
	
		*/
		
		return procProd;
	}
	
	
	

}
