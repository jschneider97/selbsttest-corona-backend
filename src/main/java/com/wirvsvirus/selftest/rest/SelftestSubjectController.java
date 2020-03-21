package com.wirvsvirus.selftest.rest;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.service.SelftestSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Justus Schneider
 */

@RestController
@RequestMapping(path = "/api/subject")
public class SelftestSubjectController {

    @Autowired
    private SelftestSubjectService selftestSubjectService;

    @GetMapping("/{id}")
    public Mono<SelftestSubject> getOne(@PathVariable Long id) {
        return Mono.just(this.selftestSubjectService.getSelftestSubject(id));
    }

    @PostMapping
    public Mono<Long> createOne(@RequestBody SelftestSubject subject) {
        return Mono.just(this.selftestSubjectService.createSelftestSubject(subject).getId());
    }

    @PutMapping("/{id}")
    public Mono<Long> update(@PathVariable Long id, @RequestBody SelftestSubject selftestSubject) {
        return Mono.just(this.selftestSubjectService.updateSelftestSubject(id, selftestSubject).getId());
    }

    @GetMapping
    public Flux<SelftestSubject> findByDeviceID(@RequestParam(name = "deviceId") String deviceId) {
        return Flux.fromStream(this.selftestSubjectService.findByDeviceId(deviceId).stream());
    }
}
