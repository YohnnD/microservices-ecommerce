package product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import product_service.model.Product;

public interface ProductRepository extends MongoRepository <Product, String> {
}
