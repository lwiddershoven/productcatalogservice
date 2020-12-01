package nl.leonw.springmicrodemo.productcatalogservice;

import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductCatalogResource {
    private final GetProductsHandler getProductsHandler;
    private final SearchProductsHandler searchProductsHandler;
    private final GetProductByIdHandler getProductByIdHandler;


    @GetMapping("/products")
    @ApiOperation("Get all products, or just the ones matching the optional query")
    public ProductResponse getProducts(
            @RequestParam(value = "query", required = false) String query
    ) {
        var domainProducts = query == null
                ? getProductsHandler.getAllProducts()
                : searchProductsHandler.searchProducts(query);

            return ProductResponse.builder()
                    .products(
                            domainProducts.stream()
                                    .map(Product::fromDomain)
                                    .collect(Collectors.toList())
                    ).build();
    }

    @GetMapping("/products/{productId}")
    @ApiOperation("Get a single product by id, or 404 if not found")
    public Product getProduct(@PathVariable("productId") String productId) {
        return getProductByIdHandler.getProductById(productId)
                .map(Product::fromDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

@Data
@Builder
class ProductResponse {
    private List<Product> products;
}

@Data
@Builder
class Product {
    private String id, name, description, picture;
    private Money price;
    private List<String> categories;

    public static Product fromDomain(DomainProduct domain) {
        return Product.builder()
                .id(domain.getId())
                .name(domain.getName())
                .categories(domain.getCategories())
                .description(domain.getDescription())
                .picture(domain.getPicture())
                .price(domain.getPrice()) // Yes, that should have had a conversion too.
                .build();
    }
}

@Data
class Money {
    private String currencyCode;
    private int units, nanos; // This was in the Google example of the store. Don't blame me.
}