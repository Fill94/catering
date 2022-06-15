package it.uniroma3.siw.catering.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.repository.CredentialsRepository;

@Service
public class CredentialsService {
	@Autowired
	private CredentialsRepository credRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credRepo.findById(id);
		return result.orElse(null);
	}
	@Transactional
	public Credentials getCredentials(String username) {
		// TODO Auto-generated method stub
		Optional<Credentials> result = this.credRepo.findByUsername(username);
		return result.orElse(null);
	}
	@Transactional
	public void saveCredentials(Credentials credentials) {
		credentials.setRole(Credentials.ADMIN_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		this.credRepo.save(credentials);
	}




}
