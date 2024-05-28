package br.thiago.entrada_saida_pessoas.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.thiago.entrada_saida_pessoas.usuarios.model.User;
import br.thiago.entrada_saida_pessoas.usuarios.service.UserService;


import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para obter todos os usuários
    @GetMapping
	public ResponseEntity<Iterable<User>> buscarTodos(){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		return ResponseEntity.ok(userService.buscarTodos());//vai buscar todos os ceps
	}

    // Endpoint para obter um usuário pelo ID
    @GetMapping("/{id}")//id digitado vai buscar as informacoes
	public ResponseEntity<User> buscarPorId(@PathVariable Long id){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		return ResponseEntity.ok(userService.buscarPorId(id));//vai buscar o id
	}

    // Endpoint para criar um novo usuário
    @PostMapping
	public ResponseEntity<User> inserir(@RequestBody User user){//metodo que exige que volte um ResponseEntity<Iterable<Cliente>>
		userService.inserir(user);//inlcui o cliente adicionado no body
		return ResponseEntity.ok(user);
	}

    // Endpoint para atualizar um usuário existente pelo ID
    @PutMapping("/{id}")
	public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user){
		userService.atualizar(id, user);//atualiza o cliente pelo id e o cliente fornecido
		return ResponseEntity.ok(user);
	}

    // Endpoint para deletar um usuário pelo ID
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		userService.deletar(id);//deleta um usuario pelo id fornecido
		return ResponseEntity.ok().build();
	}
}
