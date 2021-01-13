package com.hcl.feedback_app.exception;

public class FeedbackNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FeedbackNotFoundException(Long id) {
        super("Could not find feedback with id " + id);
    }
}
