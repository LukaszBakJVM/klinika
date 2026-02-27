package org.example.utils.validation;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorService {
  private final   ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
   private final Validator validator = factory.getValidator();



    public ValidatorService() {

    }


    public <T> void validation(T t) {

        Set<ConstraintViolation<T>> violations =
                validator.validate(t);

        if (!violations.isEmpty()) {

            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .collect(Collectors.joining(" | "));

            throw new RuntimeException(errors);
        }
    }
}
