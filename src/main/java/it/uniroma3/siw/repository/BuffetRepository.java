package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Chef;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	public boolean existsBuffetByNomeAndChef(String nome, Chef chef);

}
