package com.deloitte.fitnesstracker;

import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;


public class ToStringAndEqualsTester implements Tester {

  public void run(PojoClass pojoClass) {
    Object instance1 = RandomFactory.getRandomValue(pojoClass.getClazz());
    Object instance2 = RandomFactory.getRandomValue(pojoClass.getClazz());
    Affirm.affirmEquals("Expected string mismatch", instance1.toString(), instance1.toString());
    Affirm.affirmNotNull("Equals method should match", instance1.equals(instance2));
    Affirm.affirmNotNull("hashcode shouldnt be null", instance1.hashCode());
  }
}
