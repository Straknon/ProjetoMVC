package br.com.group.sicar.controller;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.group.sicar.config.ProjectDefaults;
import br.com.group.sicar.entity.Carro;
import br.com.group.sicar.services.CarroService;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping(path = "/api/v1/carros")
public class CarroController {

    @Resource
    private CarroService carroService;

    @GetMapping(produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<List<Carro>> list() throws ObjectNotFoundException {
    	return ResponseEntity.ok(carroService.list());
    }

    @GetMapping(path = "/placa/{placa}",produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<Carro> get(@PathVariable String placa) throws ObjectNotFoundException {
    	return ResponseEntity.ok(carroService.get(placa));
    }
    
    @GetMapping(path = "/proprietario/{proprietario}",produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<List<Carro>> getByProprietario(@PathVariable String proprietario) throws ObjectNotFoundException {
    	return ResponseEntity.ok(carroService.getByProprietario(proprietario));
    }

    @PostMapping(consumes = ProjectDefaults.CONSUMED_MEDIA_TYPE,produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<Carro> create(@RequestBody Carro carro) {
    	return ResponseEntity.ok(carroService.create(carro));
    }

    @PutMapping(consumes = ProjectDefaults.CONSUMED_MEDIA_TYPE,produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<Carro> update(@RequestBody Carro carro) {
    	return ResponseEntity.ok(carroService.update(carro));
    }

    @DeleteMapping(path = "/placa/{placa}",produces = ProjectDefaults.PRODUCED_MEDIA_TYPE)
    public ResponseEntity<Carro> delete(@PathVariable String placa) {
    	return carroService.delete(placa);
    }
    
}
