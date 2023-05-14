package fr.limayrac.infovente.service;

import fr.limayrac.infovente.model.Cart;
import fr.limayrac.infovente.model.Product;
import fr.limayrac.infovente.repository.CartRepository;
import fr.limayrac.infovente.repository.ProductRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

public class ProductService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public ProductService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findCartById(cartId);
        Product product = productRepository.findProductById(productId);

        cart.addItem(product);
        cartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findCartById(cartId);
        Product product = productRepository.findProductById(productId);

        cart.removeItem(product);
        cartRepository.save(cart);
    }
}
