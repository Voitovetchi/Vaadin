package com.Voitovetchi.vaadinProj;

import com.Voitovetchi.vaadinProj.presenter.services.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VaadinProjApplicationTests {

	@Test
	public void isbnIsValidTest() {
		Assertions.assertTrue(Validator.isbnIsValid("1111111111"));
		Assertions.assertFalse(Validator.isbnIsValid("isbn"));
		Assertions.assertFalse(Validator.isbnIsValid("isbnisbnis"));
		Assertions.assertFalse(Validator.isbnIsValid("-111555222"));
	}

	@Test
	public void priceIsValidTest() {
		Assertions.assertTrue(Validator.priceIsValid("45.6"));
		Assertions.assertFalse(Validator.priceIsValid("-45.6"));
		Assertions.assertFalse(Validator.priceIsValid("price"));
	}

}
