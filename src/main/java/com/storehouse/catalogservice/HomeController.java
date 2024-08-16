package com.storehouse.catalogservice;
import com.storehouse.catalogservice.config.StorehouseProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final StorehouseProperties storehouseProperties;

    public HomeController(StorehouseProperties storehouseProperties) {
        this.storehouseProperties = storehouseProperties;
    }
    @GetMapping("/")
    public String getGreeting() {
        return storehouseProperties.getGreeting();
    }
}