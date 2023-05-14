package fr.limayrac.infovente.repository;

import fr.limayrac.infovente.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.categorie = ?1")
	List<Product> findByCategory(String categorie);
	@Query("SELECT p FROM Product p WHERE p.id = ?1")
	Product findProductById(Long id);
	
}
