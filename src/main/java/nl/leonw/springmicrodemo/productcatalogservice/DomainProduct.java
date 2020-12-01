package nl.leonw.springmicrodemo.productcatalogservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class DomainProduct {
    private String id, name, description, picture;
    private Money price;
    private List<String> categories;

    public static DomainProduct fromDb(DBProduct db) {
        return new DomainProduct(
                db.getId(),
                db.getName(),
                db.getDescription(),
                db.getPicture(),
                db.getPriceUsd(), // I know, I should have done the DB - Domain thing here too for consistency...
                db.getCategories()
        );
    }
}
