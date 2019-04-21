package com.jack.cucumberexplorer.calculator;

import cucumber.api.java8.En;
import org.junit.Assert;

public class CalculatorStepDefinitions implements En {

    private Long addend;
    private Long adder;
    private Long result;


    public static long add(Long addend, Long adder){
        return addend + adder;
    }

    public CalculatorStepDefinitions() {
        Given("addend is {int}", (Integer addend) -> this.addend = Long.valueOf(addend));
        Given("adder  is {int}", (Integer adder) -> this.adder = Long.valueOf(adder));
        When("I ask what's the answer for add", () -> {
            result = add(addend, adder);
        });

        Then("I should get the answer {int} for add", (Integer rs) -> {
            Assert.assertEquals(result, Long.valueOf(rs));
        });

    }

}
