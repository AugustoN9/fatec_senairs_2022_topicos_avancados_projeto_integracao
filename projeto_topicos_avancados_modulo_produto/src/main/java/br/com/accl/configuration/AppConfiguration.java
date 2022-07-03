package br.com.accl.configuration;

import org.springframework.context.annotation.Configuration;

import br.com.accl.servico.ProdutoServico;

@Configuration
public class AppConfiguration {

	//@Bean
	public ProdutoServico produtoServico() {
		return new ProdutoServico();
	}
}
