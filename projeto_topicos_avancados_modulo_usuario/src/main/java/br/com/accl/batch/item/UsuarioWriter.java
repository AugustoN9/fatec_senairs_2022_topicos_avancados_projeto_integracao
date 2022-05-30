package br.com.accl.batch.item;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import br.com.accl.entity.ProcessaUsuario;

public class UsuarioWriter implements ItemWriter<ProcessaUsuario>{

	String fileName = "datas.txt";
	@Override
	public void write(List<? extends ProcessaUsuario> items) throws Exception {
		System.out.println("Write");
		
		for(ProcessaUsuario user : items) {
			String conteudo = user.getNome() + " - " + user.getDataProcessamento() + " - " + user.getStatus() ;
			FileWriter fw = new FileWriter(fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(conteudo);
		    bw.newLine();
		    bw.close();
		}
		
	}

}
