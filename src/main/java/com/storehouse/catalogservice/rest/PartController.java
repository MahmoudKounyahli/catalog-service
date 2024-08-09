package com.storehouse.catalogservice.rest;

import com.storehouse.catalogservice.domain.Part;
import com.storehouse.catalogservice.service.PartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("parts")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public Iterable<Part> get() {
        return partService.viewPartList();
    }

    @GetMapping("{partNumber}")
    public Part getByPartNumber(@PathVariable String partNumber) {
        return partService.viewPartDetails(partNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part post(@Valid @RequestBody Part part) {
        return partService.addPartToCatalog(part);
    }

    @DeleteMapping("{partNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String partNumber) {
        partService.removePartFromCatalog(partNumber);
    }

    @PutMapping("{partNumber}")
    public Part put(@PathVariable String partNumber, @Valid @RequestBody Part part) {
        return partService.editPartDetails(partNumber, part);
    }

}
