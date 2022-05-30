package br.com.mbs.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("EnviaEmailParaDiretorDelegate")
public class EnviaEmailParaDiretorDelegate 
	implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("ENVIANDO EMAIL PARA O DIRETOR");
		
	}

}
