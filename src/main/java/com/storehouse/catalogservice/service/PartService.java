package com.storehouse.catalogservice.service;

import com.storehouse.catalogservice.domain.Part;
import com.storehouse.catalogservice.exception.PartAlreadyExistsException;
import com.storehouse.catalogservice.exception.PartNotFoundException;
import com.storehouse.catalogservice.repository.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartService {
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Iterable<Part> viewPartList() {
        return partRepository.findAll();
    }

    public Part viewPartDetails(String partNumber) {
        return partRepository.findByPartNumber(partNumber)
                .orElseThrow(() -> new PartNotFoundException(partNumber));
    }

    public Part addPartToCatalog(Part part) {
        if (partRepository.existsByPartNumber(part.partNumber())) {
            throw new PartAlreadyExistsException(part.partNumber());
        }
        return partRepository.save(part);
    }

    public void removePartFromCatalog(String partNumber) {
        partRepository.deleteByPartNumber(partNumber);
    }

    public Part editPartDetails(String partNumber, Part part) {
        return partRepository.findByPartNumber(partNumber)
                .map(existingPart -> {
                    var partToUpdate = new Part(
                            existingPart.id(),
                            existingPart.partNumber(),
                            part.name(),
                            part.description(),
                            part.price(),
                            part.quantity(),
                            part.category(),
                            existingPart.createdDate(),
                            existingPart.lastModifiedDate(),
                            existingPart.version());
                    return partRepository.save(partToUpdate);
                })
                .orElseGet(() -> addPartToCatalog(part));
    }

}
