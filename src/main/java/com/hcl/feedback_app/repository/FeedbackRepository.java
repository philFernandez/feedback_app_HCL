package com.hcl.feedback_app.repository;
/**
 * @author Phil Fernandez
 */

import com.hcl.feedback_app.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}
