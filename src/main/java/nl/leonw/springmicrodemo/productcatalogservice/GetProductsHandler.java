package nl.leonw.springmicrodemo.productcatalogservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetProductsHandler {
    private final Database db;

    public List<DomainProduct> getAllProducts() {
        return db.getProducts().stream()
                .map(DomainProduct::fromDb)
                .collect(Collectors.toList());
    }
}
