package it.uniroma3.siw.catering.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.catering.model.Buffet;

@Component
public class BuffetValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Buffet buffet = (Buffet) target;
		if(buffet.getChef() == null)
			errors.rejectValue("chef", "NotNull.buffet.chef");
		if(buffet.getPiatti() == null)
			errors.rejectValue("piatti", "NotNull.buffet.piatti");
	}

}
