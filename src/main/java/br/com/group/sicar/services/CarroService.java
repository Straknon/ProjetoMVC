package br.com.group.sicar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import br.com.group.sicar.entity.Carro;
import br.com.group.sicar.repository.CarroRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> list() throws ObjectNotFoundException {
		return carroRepository.findAll();
	}

	public Carro get(String placa) throws ObjectNotFoundException {
		Optional<Carro> optional = carroRepository.findById(placa);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ObjectNotFoundException("Não foi possível encontrar um carro com essa placa");
		}
	}
	
	public List<Carro> getByProprietario(String proprietario) {
		return carroRepository.findByProprietario(proprietario);
	}
	
	public Carro create(Carro carro) {
		return carroRepository.save(carro);
	}

	public Carro update(Carro carro) {
		return carroRepository.save(carro);
	}

	public ResponseEntity<Carro> delete(String placa) {
		try {
			carroRepository.deleteById(placa);
			return ResponseEntity.ok().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
