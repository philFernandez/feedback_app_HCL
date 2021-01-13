package com.hcl.feedback_app.configuration;

import com.hcl.feedback_app.model.Feedback;
import com.hcl.feedback_app.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDB(FeedbackRepository repository) {
        return args -> {
            repository.save(new Feedback("The product was good", "Laptop", 5));
            repository.save(new Feedback("The product was bad", "SimpliLearn", 1));
            repository.findAll() 
                .forEach(feedback -> log.info("Preloaded " + feedback));
        };
    }
    
}
