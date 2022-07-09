package br.com.accl.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import br.com.accl.service.UsuarioServico;


@Configuration
public class AppConfiguration {

	//@Bean
	public UsuarioServico usuarioServico() {
		return new UsuarioServico();
	}
}
