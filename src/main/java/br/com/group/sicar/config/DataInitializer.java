package br.com.group.sicar.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import br.com.group.sicar.entity.Carro;
import br.com.group.sicar.repository.CarroRepository;
import java.time.LocalDate;
/**
 *
 * @author Lucas
 */
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent>{
    
	@Autowired
	private CarroRepository carroRepository;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
    	
    	
       List<Carro> carros = carroRepository.findAll();
       if (carros.isEmpty()) {
        	Carro carro = new Carro("JII7788","Toyota","Rav4","Lucas","lucas@email.com","61999998888",LocalDate.of(2000, 01, 01));
        	carroRepository.save(carro);
        	carro = new Carro("JII1199","Honda","Fit","Lucas","lucas@email.com","61999998888",LocalDate.of(2010, 01, 01));
        	carroRepository.save(carro);
        	carro = new Carro("JXX1010","Fiat","Uno","Marcos","marcos@email.com","61997777888",LocalDate.of(2008, 01, 01));
        	carroRepository.save(carro);
        	carro = new Carro("JYY8822","Chevrolet","Celta","Fabricio","fabricio@email.com","61922211111",LocalDate.of(2012, 01, 01));
        	carroRepository.save(carro);
        	carro = new Carro("JVV9911","Chevrolet","Onix","Fabricio","fabricio@email.com","61922211111",LocalDate.of(2020, 01, 01));
        	carroRepository.save(carro);
        	carro = new Carro("JZZ5533","Fiat","Palio","Marcos","marcos@email.com","61997777888",LocalDate.of(2011, 01, 01));
        	carroRepository.save(carro);
        }
        
        System.out.println("\nLOG: DATA INITIALIZER\n");        
    }
}
