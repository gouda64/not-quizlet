package com.gouda.notquizlet.validator;

import com.gouda.notquizlet.entity.FlashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FlashSetValidator implements Validator {

    @Autowired
    public FlashSetValidator() {

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FlashSet.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FlashSet flashSet = (FlashSet) o;
        if (flashSet.getName().length() <= 0) {
            errors.rejectValue("name", "errors.set.name");
        }
        if (flashSet.getFlashcards().size() < 3) {
            errors.rejectValue("flashcards", "errors.set.size");
        }
    }
}
