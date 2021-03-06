package se.webstep.springboot101;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

// Man kan overrida var db-filerna ska ligga om man vill.
//@Configuration
public class DbConfig {

   @Bean
   public DataSource dataSource() {
      return new EmbeddedDatabaseBuilder()
         .setType(EmbeddedDatabaseType.H2)
         .addScript("db/schema.sql")
         .addScript("db/data.sql")
         .build();
   }
}
