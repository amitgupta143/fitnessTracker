package com.deloitte.fitnesstracker;

import java.util.List;

import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class ModelObjectsTests {


	@Test
	public void testGettersAndSetters() {
		List<PojoClass> voClasses = PojoClassFactory.getPojoClasses("com.deloitte.fitnesstracker.vo");
		List<PojoClass> dtoClasses = PojoClassFactory.getPojoClasses("com.deloitte.fitnesstracker.dto");

		Validator validator = ValidatorBuilder.create()
				.with(new SetterTester())
				.with(new GetterTester())
				.with(new ToStringAndEqualsTester())
				.build();

		validator.validate(voClasses);
		validator.validate(dtoClasses);

	}

}