package estudos.thiago.lab_padroes_spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import estudos.thiago.lab_padroes_spring.model.Cliente;
import estudos.thiago.lab_padroes_spring.model.ClienteRepository;
import estudos.thiago.lab_padroes_spring.model.Endereco;
import estudos.thiago.lab_padroes_spring.model.EnderecoRepository;
import estudos.thiago.lab_padroes_spring.service.ClienteService;
import estudos.thiago.lab_padroes_spring.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService{
	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		// Buscar todos os Clientes.
		return clienteRepository.findAll();//sabemos que os clientes estao em um repository
		//então para buscar ele retorna todos os clientes com a classe spring que tem o metodo findAll
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// Buscar Cliente por ID.
		Optional<Cliente> cliente = clienteRepository.findById(id);//todas essas funcoes podem ser feitas posi ja estao implementadas
		if(cliente.get().equals(null))
			return null;
		else
		return cliente.get();//pois cliente repository extend CrudRepository<Cliente, Long> então, findbyid vai procurar 
		//em cliente alguma variavel que voce colocou @Id
		//optional porque ele pode ou nao existir
	}

	@Override
	public void inserir(Cliente cliente) {
		/*String cep = cliente.getEndereco().getCep();
		//o id de endereco é o cep entao vamos pesquisar 
		//se existe o cep ja cadastrado em um cliente
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{//cria um callback caso o cep nao exista
		Endereco novoEndereco = viaCepService.consultarCep(cep);//somente se nao existir o endereco ja no banco de dados ele acessa
		//o viaCep, isso evita alto consumo externo de dados
		enderecoRepository.save(novoEndereco);
		return novoEndereco;//vai retornar algo
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);*/
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// Buscar Cliente por ID, caso exista:
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
			salvarClienteComCep(cliente);
		
	}

	@Override
	public void deletar(Long id) {
		// Deletar Cliente por ID.
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		clienteRepository.save(cliente);
	}

}
