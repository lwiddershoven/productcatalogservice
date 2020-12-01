package nl.leonw.springmicrodemo.productcatalogservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProductByIdHandler {
    private final Database db;

    public Optional<DomainProduct> getProductById(String id) {
        return db.getProducts().stream().filter(p -> p.getId().equals(id)).findFirst().map(DomainProduct::fromDb);
    }
}
