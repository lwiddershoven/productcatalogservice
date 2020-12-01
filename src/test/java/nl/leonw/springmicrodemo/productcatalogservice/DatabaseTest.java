package nl.leonw.springmicrodemo.productcatalogservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    public void testLoadFile() throws IOException {
        Database db = new Database(new ObjectMapper());
        db.loadFile();
        var products = db.getProducts();
        assertEquals(9, products.size());
        assertTrue(products.stream().anyMatch(p -> p.getName().equals("Vintage Typewriter")));
        assertTrue(products.stream().anyMatch(p -> p.getName().equals("Air Plant")));
    }

}