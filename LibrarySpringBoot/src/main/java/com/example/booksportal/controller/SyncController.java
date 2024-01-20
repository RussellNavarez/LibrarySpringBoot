package com.example.booksportal.controller;

import com.example.booksportal.dao.DaoBook;
import com.example.booksportal.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sync")
public class SyncController {

    private final RestTemplate restTemplate;

    @Autowired
    private DaoBook bookRepository;

    @Autowired
    public SyncController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String sincronizzaLibri() {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=\"\"maxResults=20";

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(apiUrl, Map.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = responseEntity.getBody();
            if (responseBody != null) {
                List<LinkedHashMap<String, Object>> items = (List<LinkedHashMap<String, Object>>) responseBody.get("items");

                for (LinkedHashMap<String, Object> item : items) {
                    LinkedHashMap<String, Object> volumeInfo = (LinkedHashMap<String, Object>) item.get("volumeInfo");

                    String title = (String) volumeInfo.get("title");
                    List<String> authors = (List<String>) volumeInfo.get("authors");
                    String release_year = (String) volumeInfo.get("publishedDate");
                    String publisher = (String) volumeInfo.get("publisher");

                    Book libro = new Book(title, authors.toString(), release_year, publisher);
                    bookRepository.save(libro);
                }
                return "Sincronizzazione completata";
            }
        }
        return "Errore durante la sincronizzazione";
    }
}
