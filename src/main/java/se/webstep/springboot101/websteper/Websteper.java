package se.webstep.springboot101.websteper;

import static java.util.stream.Collectors.joining;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Websteper {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Long id;

   @NotNull
   private String firstName;
   @NotNull
   private String lastName;

   private String interests;

   public Websteper() {
   }

   public Websteper(String firstName, String lastName, String interests) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.interests = interests;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @JsonIgnore
   public String getInterests() {
      return interests;
   }

   public void setInterests(String interests) {
      this.interests = interests;
   }

   @JsonSetter("interests")
   public void setInterestsAsSet(Set<String> interests) {
      this.interests = interests.stream().collect(joining(","));
   }

   @JsonGetter("interests")
   public Set<String> getInterestsAsSet() {
      return new HashSet<>(Arrays.asList(interests.split(",")));
   }
}