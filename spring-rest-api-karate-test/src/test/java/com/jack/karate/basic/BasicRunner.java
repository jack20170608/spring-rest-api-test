package com.jack.karate.basic;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;


//Will check all .feature files in the same folder and run it.
@RunWith(Karate.class)
@KarateOptions(features = "classpath:com/jack/karate/basic/basic.feature")
public class BasicRunner {
    
}
