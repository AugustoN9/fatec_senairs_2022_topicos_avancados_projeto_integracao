package br.com.accl.batch.item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import br.com.accl.entidade.ProcessaProduto;

public class ProdutoWriter implements ItemWriter<ProcessaProduto>{

	String fileName = "itens_acima_de_10_comprados.txt";
	@Override
	public void write(List<? extends ProcessaProduto> items) throws Exception {
		System.out.println("Write");

		for(ProcessaProduto procProd : items) {
			String conteudo = procProd.getId() + " - " +
					procProd.getNome() + " - " +
					procProd.getMarca() + " - " +
					procProd.getPreco();
			FileWriter fw = new FileWriter(fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(conteudo);
		    bw.newLine();
		    bw.close();
		}
		
		
		
	}

}
