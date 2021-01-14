package com.hcl.feedback_app.controller;
/**
 * @author Phil Fernandez
 */

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class FeedbackController {

    @Autowired
    private FeedbackRepository repository;
    @Autowired
    private FeedbackModelAssembler assembler;

    // Rest endpoint for obtaining all feedback entities.
    // CollectionModel and EntityModel make it possible to return the data along with hyperlinks to all data
    // See com.hcl.feedback_app.controller.FeedbackAssembler for implimentation details.
    @GetMapping("/feedback")
    public CollectionModel<EntityModel<Feedback>> all() {
        List<EntityModel<Feedback>> feedback = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).all()).withSelfRel());
    }

    // Rest endpoint for particular feedback entity by ID
    @GetMapping("/feedback/{id}")
    public EntityModel<Feedback> one(@PathVariable Long id) {
        Feedback feedback = repository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException(id));
        return assembler.toModel(feedback);
    }

    // Rest endpoint for adding a new feedback entitiy via Rest POST request
    @PostMapping("/feedback")
    public Feedback newFeedback(@RequestBody Feedback newFeedback) {
        return repository.save(newFeedback);
    }

    // Handle get request for form test page
    @GetMapping("/formfeedback")
    public ModelAndView formFeedbackView() {
        return new ModelAndView("formfeedback", "feedback", new Feedback());
    }

    // Handl post request when submitting test form. Redirects to aggregate endpoint after saving to DB
    @PostMapping("/formfeedback")
    public RedirectView addFormFeedback(@RequestParam String product, 
    @RequestParam String description, @RequestParam Integer rating) {
        Feedback feedback = new Feedback(description, product, rating);
        repository.save(feedback);
        return new RedirectView("/feedback");
    }
}
