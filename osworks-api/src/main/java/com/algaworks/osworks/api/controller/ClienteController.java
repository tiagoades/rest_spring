package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	
	@GetMapping("/clientes")
	public List<Cliente>  listar() {
		
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Joao");
		cliente1.setEmail("joao@xpto.com");
		cliente1.setTelefone("123456789");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setEmail("maria@xpto.com");
		cliente2.setTelefone("987654321");
		
		Cliente cliente3 = new Cliente();
		cliente3.setId(3L);
		cliente3.setNome("Paulo");
		cliente3.setEmail("paulo@xpto.com");
		cliente3.setTelefone("135798462");
		
		return Arrays.asList(cliente1, cliente2, cliente3);
		
		
	}
	
}
