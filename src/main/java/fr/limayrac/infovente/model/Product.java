package fr.limayrac.infovente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom", nullable = false, length = 50)
	private String nom;
	@Column(name="description", nullable = false, length = 100) //!\ longueur importante 
	private String description;
	@Column(name = "prix", nullable = false, length = 20)
	private String prix;
	@Column(name = "quantite", nullable = false, length = 20)
	private String quantite;
	@Column(name = "categorie", nullable = false, length = 20)
	private String categorie;
}
