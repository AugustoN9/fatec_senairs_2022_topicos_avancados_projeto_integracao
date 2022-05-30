package br.com.accl.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.accl.entidades.Cep;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController(value = "API para manipulacao de ceps")
@RequestMapping("cep")
@Api(description = "Api de cep")
public class CepController {

	Map<String, Cep> mapaCep = new HashMap<String, Cep>();

	public CepController() {
		Cep cep1 = new Cep();
		cep1.logradouro = "Avenida";
		cep1.nome = "Dom Claudio Jose Gonçalves Ponce de Leao";
		cep1.numero = 140;
		cep1.bairro = "Vila Ipiranga";
		mapaCep.put("91370170", cep1);

		Cep cep2 = new Cep();
		cep1.logradouro = "Avenida";
		cep1.nome = "Assis Brasil";
		cep1.numero = 140;
		cep1.bairro = "Vila Ipiranga";
		mapaCep.put("91379970", cep2);

		Cep cep3 = new Cep();
		cep1.logradouro = "Rua";
		cep1.nome = "Dom Claudio Jose Gonçalves Ponce de Leao";
		cep1.numero = 140;
		cep1.bairro = "Vila Ipiranga";
		mapaCep.put("91110170", cep3);

		Cep cep4 = new Cep();
		cep1.logradouro = "Beco";
		cep1.nome = "Dom Claudio Jose Gonçalves Ponce de Leao";
		cep1.numero = 140;
		cep1.bairro = "Vila Ipiranga";
		mapaCep.put("91450170", cep4);

	}

	@RequestMapping(value = "/teste/", method = RequestMethod.GET)
	public ResponseEntity<Void> teste() throws Exception {
		System.out.println("Processando teste");
		return null;
	}

	@RequestMapping(value = "/existe-cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> existeCep(@PathVariable("cep") String cep) throws Exception {
		System.out.println("Processando existe cep");

		Boolean existeCep = mapaCep.containsKey(cep);

		return ResponseEntity.ok(existeCep);
	}

	@RequestMapping(value = "/busca-cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Cep> buscaCep(@PathVariable("cep") String cep) throws Exception {
		System.out.println("Processando busca de cep");

		Cep objCep = mapaCep.get(cep);

		return ResponseEntity.ok(objCep);
	}

	@ApiOperation(value = "Verifica existencia de numero do logradouro via requestParam")
	@RequestMapping(value = "/verifica-numeroLogradouro/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verificaNumeroLogradouro(

			@RequestParam("cep") String cep, @RequestParam("numero") Integer numero

	)

			throws Exception {
		System.out.println("Processando verificação de numero do logradouro existente");

		Cep objCep = mapaCep.get(cep);
		Boolean retorno = false;
		if (objCep.numero == numero) {
			retorno = true;
		}

		return ResponseEntity.ok(retorno);
	}

}
