package product_service.service;

import product_service.dto.ProductRequestDTO;
import product_service.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAll();

    ProductResponseDTO getById(String id);

    ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO);

    void delete(String id);

}
