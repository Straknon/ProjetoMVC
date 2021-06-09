package br.com.group.sicar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lucas
 */
@Entity
public class Carro implements Serializable{
    
	@Id
    private String placa;
    private String marca;
    private String modelo;
    private String proprietario;
    private String email;
    private String telefone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFabricacao;
    
    public Carro()
    {
    }

	public Carro(String placa, String marca, String modelo, String proprietario, String email, String telefone, LocalDate dataFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.proprietario = proprietario;
		this.email = email;
		this.telefone = telefone;
		this.dataFabricacao = dataFabricacao;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	
	
	
    
}
