package br.thiago.entrada_saida_pessoas.usuarios.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.thiago.entrada_saida_pessoas.usuarios.model.User;
import br.thiago.entrada_saida_pessoas.usuarios.repository.UserRepository;
import br.thiago.entrada_saida_pessoas.usuarios.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> buscarTodos() {
        return userRepository.findAll();
    }
    
    @Override
    public User buscarPorId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    
    @Override
    public void inserir(User user) {
        salvarUser(user);
    }
    
    @Override
    public void atualizar(Long id, User user) {
        salvarUser(user);
    }
    
    @Override
    public void deletar(Long id) {
        userRepository.deleteById(id);
    }

    private void salvarUser(User user) {
        if (user.getId() != null) {
            // Se o ID do usuário já estiver definido, isso significa que é uma atualização
            Optional<User> userOptional = userRepository.findById(user.getId());
            if (userOptional.isPresent()) {
                // Verificar se o usuário existe no banco de dados antes de atualizá-lo
                User existingUser = userOptional.get();
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
                existingUser.setRole(user.getRole());
                userRepository.save(existingUser);
            } else {
                // Se o usuário não existir no banco de dados, lança uma exceção ou lida de acordo com a sua lógica de negócio
                throw new IllegalArgumentException("Usuário com ID " + user.getId() + " não encontrado.");
            }
        } else {
            // Se o ID do usuário não estiver definido, isso significa que é uma nova inserção
            userRepository.save(user);
        }
    }
}