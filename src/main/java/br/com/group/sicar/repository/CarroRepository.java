package br.com.group.sicar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.group.sicar.entity.Carro;

/**
 *
 * @author Lucas
 */
@Repository
public interface CarroRepository extends JpaRepository<Carro, String>{
	public List<Carro> findByProprietario(String proprietario);
}
