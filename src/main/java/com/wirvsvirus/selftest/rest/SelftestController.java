package com.wirvsvirus.selftest.rest;

import com.wirvsvirus.selftest.api.selftest.Selftest;
import com.wirvsvirus.selftest.service.SelftestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Justus Schneider
 */

@RestController
@RequestMapping(path = "/api/subject/{id}/selftest")
public class SelftestController {

    @Autowired
    private SelftestService selftestService;

    @GetMapping("/{sid}")
    public Mono<Selftest> getSelftest(@PathVariable Long id, @PathVariable Long sid) {
        return Mono.just(selftestService.getById(sid));
    }

    @GetMapping
    public Flux<Selftest> getBySubject(@PathVariable Long id) {
        return Flux.fromStream(selftestService.findBySubject(id).stream());
    }

    @PostMapping
    public Mono<Long> createSelftestForSubject(@PathVariable Long id) {
        return Mono.just(this.selftestService.createForSubject(id)).map(Selftest::getId);
    }

}
