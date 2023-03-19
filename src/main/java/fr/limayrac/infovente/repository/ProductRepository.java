package fr.limayrac.infovente.repository;

import fr.limayrac.infovente.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.categorie = ?1")
	public Product findByCategory(String categorie);
	
}
