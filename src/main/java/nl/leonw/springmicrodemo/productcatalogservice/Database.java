package nl.leonw.springmicrodemo.productcatalogservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Database {

    private final ObjectMapper objectMapper;
    private List<DBProduct> products;

    @PostConstruct
    void loadFile() throws IOException {
        products = objectMapper.readValue(
                new ClassPathResource("/products.json").getFile(),
                DBProductDB.class
        ).getProducts();
    }

    public List<DBProduct> getProducts() {
        return Collections.unmodifiableList(products);
    }
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class DBProductDB {
    private List<DBProduct> products;
}

@Data
class DBProduct {
    private String id, name, description, picture;
    private Money priceUsd;
    private List<String> categories;
}