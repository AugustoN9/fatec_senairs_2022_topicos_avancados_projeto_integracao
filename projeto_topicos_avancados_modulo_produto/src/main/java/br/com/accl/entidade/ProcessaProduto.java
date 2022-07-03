package br.com.accl.entidade;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessaProduto implements Serializable{
	
	/**
	 * 
	 */
	private Integer id;
	private String nome;
	private String marca;
	private Float preco;
	private String dataProcessamento = criaData();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public void setPreco(Integer id2) {
		// TODO Auto-generated method stub
		
	}
	
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	
	private String criaData() { 
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		return DateFor.format(date);
	}
	
	
	
	
	

}
