package product_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import product_service.dto.ProductRequestDTO;
import product_service.dto.ProductResponseDTO;
import product_service.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toProductResponseDTO(Product product);

    void updateProductFromDTO(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
