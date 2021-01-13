package com.hcl.feedback_app.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.hcl.feedback_app.exception.FeedbackNotFoundException;
import com.hcl.feedback_app.model.Feedback;
import com.hcl.feedback_app.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;
    @Autowired
    private FeedbackModelAssembler assembler;

    @GetMapping("/feedback")
    CollectionModel<EntityModel<Feedback>> all() {
        List<EntityModel<Feedback>> feedback = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).all()).withSelfRel());
    }

    @GetMapping("/feedback/{id}")
    EntityModel<Feedback> one(@PathVariable Long id) {
        Feedback feedback = repository.findById(id)
            .orElseThrow(() -> new FeedbackNotFoundException(id));
        return assembler.toModel(feedback);
    }
}
