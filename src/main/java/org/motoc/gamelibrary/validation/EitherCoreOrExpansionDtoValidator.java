package org.motoc.gamelibrary.validation;

import org.motoc.gamelibrary.dto.GameDto;
import org.motoc.gamelibrary.validation.annotation.EitherCoreOrExpansion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EitherCoreOrExpansionDtoValidator implements ConstraintValidator<EitherCoreOrExpansion, GameDto> {

    @Override
    public void initialize(EitherCoreOrExpansion constraintAnnotation) {

    }

    @Override
    public boolean isValid(GameDto gameDto, ConstraintValidatorContext constraintValidatorContext) {
        if (gameDto.getExpansions() != null)
            if (gameDto.getExpansions().isEmpty())
                return true;
        return gameDto.getCoreGame() == null;
    }
}
