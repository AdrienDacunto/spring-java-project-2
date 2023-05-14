package fr.limayrac.infovente.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`USER`")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, length = 100) //!\ longueur importante => hashage du mdp
	private String password;
	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name = "ville", nullable = true, length = 20)
	private String ville;

	@Column(name = "code_postal", nullable = true, length = 20)
	private Integer codePostal;

	@Column(name = "pays", nullable = true, length = 20)
	private String pays;

	@Column(name = "adresse", nullable = true, length = 100)
	private String adresse;
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setPassword(String encodedPassword) {
		this.password = encodedPassword;
	}
}
