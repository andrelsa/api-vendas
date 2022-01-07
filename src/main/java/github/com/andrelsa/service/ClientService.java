package github.com.andrelsa.service;

import github.com.andrelsa.model.Client;
import github.com.andrelsa.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepositoryrepository) {
		this.clientRepository = clientRepositoryrepository;
	}
	
	public void saveClient(Client client) {
		validateClient(client);
		this.clientRepository.persist(client);
	}
	
	public void validateClient(Client client) {
		//aplicar validações
	}
	
}
