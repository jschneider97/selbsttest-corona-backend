package com.wirvsvirus.selftest.rest;

import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Justus Schneider
 */

@RestController
@RequestMapping(path = "/api/subject/{id}/contact")

public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Mono<Contact> createContact(@PathVariable Long id, @RequestBody Contact contact) {

        return Mono.just(contactService.addContactToSubject(id, contact));
    }

}
