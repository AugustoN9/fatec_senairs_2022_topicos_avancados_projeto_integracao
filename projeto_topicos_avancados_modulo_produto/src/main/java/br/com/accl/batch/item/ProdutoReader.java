package br.com.accl.batch.item;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.accl.entidade.Produto;
import br.com.accl.servico.ProdutoServico;

public class ProdutoReader implements ItemReader<Produto>{

	@Autowired	
	private ProdutoServico produtoServico;
	
	private Iterator<Produto> it ;
	
	private Produto produtoRetorno;
	
	@PostConstruct
	public void postConstruct() {				       
	}
	
	
	@Override
	public Produto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Reader");
		
		if(produtoRetorno == null) {				
			it = produtoServico.listarProdutos().iterator();
		}
			
		while(it.hasNext()) {
			return produtoRetorno = it.next();			
		}	
		produtoRetorno = null;
		return produtoRetorno;
		
	}

}
