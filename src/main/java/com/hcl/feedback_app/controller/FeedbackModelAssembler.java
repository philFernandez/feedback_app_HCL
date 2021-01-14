package com.hcl.feedback_app.controller;
/**
 * @author Phil Fernandez
 */

import com.hcl.feedback_app.model.Feedback;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class FeedbackModelAssembler
        implements RepresentationModelAssembler<Feedback, EntityModel<Feedback>> {

    @Override
    public EntityModel<Feedback> toModel(Feedback feedback) {
        return EntityModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).one(feedback.getId()))
                        .withSelfRel(),
                linkTo(methodOn(FeedbackController.class).all()).withRel("feedback"));
    }

}
