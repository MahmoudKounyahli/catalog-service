package com.storehouse.catalogservice.demo;

import com.storehouse.catalogservice.domain.Part;
import com.storehouse.catalogservice.repository.PartRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class PartDataLoader {
    private final PartRepository partRepository;

    public PartDataLoader(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadPartTestData() {
        var part1 = Part.of(
                "0123456789",
                "part 1",
                "part 1 do something to something",
                22.5,
                5,
                "AAA");
        var part2 = Part.of(
                "9876543210",
                "part 2",
                "part 2 do something to something",
                22.5,
                5,
                "BBB");
        partRepository.save(part1);
        partRepository.save(part2);
    }
}
