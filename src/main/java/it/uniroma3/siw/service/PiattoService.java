package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;

	@Transactional
	public void save(Piatto Piatto) {
		piattoRepository.save(Piatto);
	}

	@Transactional
	public void deleteById(Long id) {
		piattoRepository.deleteById(id); 
	}

	/* Le seguenti operazioni in lettura non è necessario
	 * che siano @Transactional,
	 * nota valida per i metodi findById e findAll
	 */
	
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}

	public List<Piatto> findAll() {
		List<Piatto> piattiTotali = new ArrayList<Piatto>();
		
		for(Piatto Piatto: piattoRepository.findAll()) {
			piattiTotali.add(Piatto);
		}
		
		return piattiTotali;
	}
	
	public boolean alreadyExists(Piatto piatto) {
		return this.piattoRepository.existsPiattoByNome
				(piatto.getNome());
	}

	public void updateBuffets(Piatto piatto) {
		List<Buffet> buffetsConPiattoDaRimuovere = piatto.getBuffets();
		
		for(Buffet buffet: buffetsConPiattoDaRimuovere) {
			buffet.getPiatti().remove(piatto);
		}
		
	}
	
}
