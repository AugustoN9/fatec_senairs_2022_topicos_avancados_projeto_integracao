package br.com.accl.batch.item;

import org.springframework.batch.item.ItemProcessor;

import br.com.accl.entity.ProcessaUsuario;
import br.com.accl.entity.Usuario;

public class UsuarioProcessor 
	implements ItemProcessor<Usuario, ProcessaUsuario> {

	@Override
	public ProcessaUsuario process(Usuario item) throws Exception {

		System.out.println("Processor " + item);//DE-PARA
		
		ProcessaUsuario ProcessUser = new ProcessaUsuario();
		ProcessUser.setNome(item.nome);
		
		if(item.idade >= 18) {
			ProcessUser.setStatus("eh MAIOR de idade");
		}
		else {
			ProcessUser.setStatus("eh menor de idade");
		}
		return ProcessUser;
	}

}
