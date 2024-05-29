package estudos.thiago.lab_padroes_spring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import estudos.thiago.lab_padroes_spring.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
	
	//@RequestMapping(method = RequestMethod.GET, value= "/{cep}/json/")//cria um json
	//Endereco consultarCep(@PathVariable("cep")String cep);//esse json vai ser convertido no objeto cep consultarcep
	//e vai devolver o endereco preenchido
	
	@GetMapping("/{cep}/json/") //funcionaria tambem assim
	Endereco consultarCep(@PathVariable("cep")String cep);

}
