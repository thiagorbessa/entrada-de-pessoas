package estudos.thiago.lab_padroes_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudos.thiago.lab_padroes_spring.model.Cliente;
import estudos.thiago.lab_padroes_spring.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos(){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		return ResponseEntity.ok(clienteService.buscarTodos());//vai buscar todos os ceps
	}
	
	@GetMapping("/{id}")//id digitado vai buscar as informacoes
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		return ResponseEntity.ok(clienteService.buscarPorId(id));//vai buscar o id
	}
	
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		clienteService.inserir(cliente);//inlcui o cliente adicionado no body
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
		clienteService.atualizar(id, cliente);//atualiza o cliente pelo id e o cliente fornecido
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		clienteService.deletar(id);//deleta um usuario pelo id fornecido
		return ResponseEntity.ok().build();
	}
	
}
