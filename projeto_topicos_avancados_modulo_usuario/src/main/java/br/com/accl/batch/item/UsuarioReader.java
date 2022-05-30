package br.com.accl.batch.item;

import java.util.Iterator;


import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.accl.entity.Usuario;



public class UsuarioReader implements ItemReader<Usuario>{

	@Autowired	
	private UsuarioServico usuarioServico;
	
	private Iterator<Usuario> it ;
	
	private Usuario usuarioRetorno;
	
	@PostConstruct
	public void postConstruct() {				       
	}
	
	
	@Override
	public Usuario read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Reader");
		
		if(usuarioRetorno == null) {				
			it = usuarioServico.listar().iterator();
		}
			
		while(it.hasNext()) {
			return usuarioRetorno = it.next();			
		}	
		usuarioRetorno = null;
		return usuarioRetorno;
		
	}

}
