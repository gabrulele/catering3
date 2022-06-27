package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;

	@Transactional
	public void save(Buffet buffet) {
		buffetRepository.save(buffet);
	}

	@Transactional
	public void deleteById(Long id) {
		buffetRepository.deleteById(id); 
	}
	
	@Transactional
	public void update(Buffet buffet, Chef chef) {
		buffet.setChef(chef);
	}
	
	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}

	public List<Buffet> findAll() {
		List<Buffet> buffetTotali = new ArrayList<Buffet>();
		
		for(Buffet buffet: buffetRepository.findAll()) {
			buffetTotali.add(buffet);
		}
		
		return buffetTotali;
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return this.buffetRepository.existsBuffetByNomeAndChef
				(buffet.getNome(), buffet.getChef());
	}

}
