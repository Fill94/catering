package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Piatto;
@Component
public class PiattoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Piatto piatto = (Piatto) target;
		if(piatto.getIngredienti()==null) {
			errors.rejectValue("ingredienti", "NotNull.piatto.ingredienti");
		}
	}

}
