package se.webstep.springboot101.employee;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/websteper")
public class WebsteperController {

   private final WebsteperRepository websteperRepository;
   private final Timer timer;
   private final Counter addedCounter;

   public WebsteperController(
      WebsteperRepository websteperRepository,
      MeterRegistry registry
   ) {
      this.websteperRepository = websteperRepository;
      this.timer = registry.timer("interest-matrix-request-timer");
      this.addedCounter = registry.counter("");
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void addWebsteper(@RequestBody Websteper websteper) {
      websteperRepository.save(websteper);
      addedCounter.increment();
   }

   @GetMapping
   public List<Websteper> getWebstepers(@RequestParam(required = false) Set<String> interests) {
      return websteperRepository.findAll().stream()
         .filter(e -> interests == null || e.getInterestsAsSet().stream().anyMatch(interests::contains))
         .collect(toList());
   }

   @GetMapping("/interest")
   public Set<String> getInterest() {
      return websteperRepository.findAll().stream()
         .flatMap(e -> e.getInterestsAsSet().stream())
         .collect(toSet());
   }

   @GetMapping("/interest-matrix")
   public Map<String, Set<String>> getInterestMatrix() {

      long startTime = System.nanoTime();

      Set<String> interests = websteperRepository.findAll().stream()
         .flatMap(e -> e.getInterestsAsSet().stream())
         .collect(toSet());

      List<Websteper> webstepers = websteperRepository.findAll();

      Map<String, Set<String>> interestMap = interests.stream()
         .collect(
            toMap(identity(),
                  interest -> webstepers.stream()
                     .filter(e -> e.getInterests().contains(interest))
                     .map(e -> e.getFirstName() + " " + e.getLastName())
                     .collect(toSet())
            )
         );

      timer.record(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

      return interestMap;
   }
}
