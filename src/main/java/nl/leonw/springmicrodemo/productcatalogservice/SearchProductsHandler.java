package nl.leonw.springmicrodemo.productcatalogservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchProductsHandler {
    private final Database db;

    public List<DomainProduct> searchProducts(String partOfName) {
        return db.getProducts().stream()
                .filter(p -> p.getName().toLowerCase().contains(partOfName.toLowerCase()))
                .map(DomainProduct::fromDb)
                .collect(Collectors.toList());
    }
}
