package se.webstep.springboot101;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

   @GetMapping("/hello")
   public Hello hello() {

      return new Hello("Hello!");
   }

   class Hello {

      public String getGreeting() {
         return greeting;
      }

      public final String greeting;

      public Hello(String greeting) {this.greeting = greeting;}
   }
}
