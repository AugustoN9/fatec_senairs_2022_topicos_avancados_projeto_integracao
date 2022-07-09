package br.com.accl.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("EnviaEmailSustentacaoDelegate")
public class EnviaEmailSustentacaoDelegate 
implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("executando EnviaEmailSustentacaoDelegate SUSTENTACAO");
		
		
		System.out.println("ENVINDO E-MAIL");
		
	}

}
