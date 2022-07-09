package br.com.accl.delegates;

import java.util.Map;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import br.com.accl.entity.Usuario;


@Component("CadastroUsuarioMemoriaDelegate")
public class CadastroUsuarioMemoriaDelegate
	implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("executando CadastroUsuarioMemoriaDelegate");
		
		Map<Integer,Usuario> mapaUsuario = 
				(Map<Integer, Usuario>) execution.
				getVariable("mapa-usuario");
		
		Usuario usuario = (Usuario) execution.getVariable("usuario");
		Integer contador = (Integer) execution.getVariable("id-usuario");
		
		if(usuario.nome.length() <= 1) {
			// tem problema
			execution.setVariable("email_diretor",false);
		}else {
			// tudo ok
			execution.setVariable("email_diretor", true);
			mapaUsuario.put(contador, usuario);			 
			contador++;
		}
		
		
		
	}

}
