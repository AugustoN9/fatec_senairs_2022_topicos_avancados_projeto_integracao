package br.com.mbs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mbs.servico.UsuarioServico;

@Configuration
public class AppConfiguration {

	//@Bean
	public UsuarioServico usuarioServico() {
		return new UsuarioServico();
	}
}
