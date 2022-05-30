package br.com.accl.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import br.com.accl.entity.Usuario;

@Service
public class UsuarioServico {

	private Map<Integer,Usuario> mapaUsuario = new HashMap<Integer,Usuario>();
	private Integer contador = 1;
	
	public Integer getContador() {
		return contador;
	}
	
	public Map<Integer,Usuario> getMapaUsuario() {
		return mapaUsuario;				
	}
	
	public void adiciona(Usuario usuario) {
		usuario.id = contador;
		mapaUsuario.put(contador, usuario);		 
		 contador++;
	}
	
	public Usuario buscar(Integer id) {
		return mapaUsuario.get(id);
	}
	
	public void remover(Integer id) {
		mapaUsuario.remove(id);
	}
	
	public ArrayList<Usuario> listar(){
		return new ArrayList<>(mapaUsuario.values());
	}
	
}
