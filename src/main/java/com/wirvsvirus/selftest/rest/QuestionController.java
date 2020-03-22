package com.wirvsvirus.selftest.rest;

import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Justus Schneider
 */

@RestController
@RequestMapping(path = "/api/subject/{id}/selftest/{sid}/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public Mono<Question> getNextQuestion(@PathVariable Long id, @PathVariable Long sid) {
        return Mono.just(questionService.getNextQuestionForSelftest(sid));
    }

    @PostMapping
    public void answerQuestion(@PathVariable Long id, @PathVariable Long sid, @RequestBody Question question) {
        questionService.answerQuestion(sid, question);
    }


}
