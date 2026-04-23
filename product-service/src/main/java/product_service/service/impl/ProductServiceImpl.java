package product_service.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import product_service.config.exception.NotFoundException;
import product_service.dto.ProductRequestDTO;
import product_service.dto.ProductResponseDTO;
import product_service.mapper.ProductMapper;
import product_service.model.Product;
import product_service.repository.ProductRepository;
import product_service.service.ProductService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        return productMapper.toProductResponseDTO(
                productRepository.save(productMapper.toProduct(productRequestDTO)
                )
        );
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream()
                        .map(productMapper::toProductResponseDTO)
                        .toList();
    }


    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
    }

    @Override
    public ProductResponseDTO getById(String id) {
        return productMapper.toProductResponseDTO(getProductById(id));
    }

    @Override
    public ProductResponseDTO update(String id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = getProductById(id);
        productMapper.updateProductFromDTO(productRequestDTO, existingProduct);
        return productMapper.toProductResponseDTO(productRepository.save(existingProduct));
    }

    @Override
    public void delete(String id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }

}
