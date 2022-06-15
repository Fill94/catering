package it.uniroma3.siw.catering.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
public class Piatto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	@Column(nullable = false)
	private String nome;
	@NotBlank
	private String descrizione;
	@ManyToMany(mappedBy = "piatti")
	@Cascade(CascadeType.DELETE)
	private List<Buffet> BuffetsList;
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<Buffet> getBuffetsList() {
		return BuffetsList;
	}
	public void setBuffetsList(List<Buffet> buffetsList) {
		BuffetsList = buffetsList;
	}
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Piatto that = (Piatto) obj;
		return this.getNome().equals(that.getNome()) && this.getId() == that.getId();
	}
	
}
