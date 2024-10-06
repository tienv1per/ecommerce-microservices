package com.sume.ecommerce.product;

import com.sume.ecommerce.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
            .id(request.Id())
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .availableQuantity(request.availableQuantity())
            .category(
                Category
                    .builder()
                    .id(request.categoryId())
                    .build()
            )
            .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double productQuantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                productQuantity
        );
    }
}
