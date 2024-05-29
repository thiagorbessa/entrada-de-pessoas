package estudos.thiago.lab_padroes_spring.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String>{//o extends de CrudRepository é a injecao de interfaces na classe que voce mencionar
	//no array

}
