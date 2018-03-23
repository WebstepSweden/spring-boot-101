package se.webstep.springboot101.healthchecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealth implements HealthIndicator {

   @Override
   public Health health() {
      return new Health.Builder(Status.UP)
         .withDetail("Message", "All good!")
         .build();
   }
}
