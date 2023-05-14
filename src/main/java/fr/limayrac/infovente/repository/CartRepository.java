package fr.limayrac.infovente.repository;

import fr.limayrac.infovente.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user_id = ?1")
    Cart findCartById(Long userId);
}
