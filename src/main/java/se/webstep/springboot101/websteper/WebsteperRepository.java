package se.webstep.springboot101.websteper;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsteperRepository extends JpaRepository<Websteper, Long> { }