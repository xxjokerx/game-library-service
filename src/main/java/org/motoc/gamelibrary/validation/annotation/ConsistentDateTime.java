package org.motoc.gamelibrary.validation.annotation;

import org.motoc.gamelibrary.validation.ConsistentDateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * A custom annotation in order to check the consistency of loan date (is start date before end date)
 *
 * @author RouzicJ
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConsistentDateTimeValidator.class)
public @interface ConsistentDateTime {
    String message() default "Start date must be before end date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
