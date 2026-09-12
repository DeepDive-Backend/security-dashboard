package org.sangyunpark.securitydashboard.controller;

import lombok.RequiredArgsConstructor;
import org.sangyunpark.securitydashboard.SecurityEventSeeder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SeedController {

    private final SecurityEventSeeder seeder;

    @PostMapping("/seed/{count}")
    public void setSeed(@PathVariable int count) {
        seeder.seed(count);
    }
}
