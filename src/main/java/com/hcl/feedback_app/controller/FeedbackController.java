package com.hcl.feedback_app.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.hcl.feedback_app.exception.FeedbackNotFoundException;
import com.hcl.feedback_app.model.Feedback;
import com.hcl.feedback_app.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;
    @Autowired
    private FeedbackModelAssembler assembler;

    @GetMapping("/feedback")
    public CollectionModel<EntityModel<Feedback>> all() {
        List<EntityModel<Feedback>> feedback = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).all()).withSelfRel());
    }

    @PostMapping("/feedback")
    public Feedback newFeedback(@RequestBody Feedback newFeedback) {
        return repository.save(newFeedback);
    }

    @GetMapping("/formfeedback")
    public ModelAndView formFeedbackView() {
        return new ModelAndView("formfeedback", "feedback", new Feedback());
    }

    @PostMapping("/formfeedback")
    public String addFormFeedback(@RequestParam String product, 
    @RequestParam String description, @RequestParam Integer rating) {
        Feedback feedback = new Feedback(description, product, rating);
        repository.save(feedback);
        return ("redirect:/success");
    }



    @GetMapping("/feedback/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {
        Feedback feedback = repository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException(id));
        return assembler.toModel(feedback);
    }
}
