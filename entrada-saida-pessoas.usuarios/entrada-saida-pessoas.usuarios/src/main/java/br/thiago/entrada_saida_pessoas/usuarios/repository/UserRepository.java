package br.thiago.entrada_saida_pessoas.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.thiago.entrada_saida_pessoas.usuarios.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	   User findByName(String name);
	}