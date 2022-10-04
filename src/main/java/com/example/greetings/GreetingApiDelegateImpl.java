package com.example.greetings;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.greetings.api.GreetingsApiDelegate;
import com.example.greetings.api.model.Greeting;

@Service
public class GreetingApiDelegateImpl implements GreetingsApiDelegate {

  private static final String template = "Hello Docker, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Override
  public ResponseEntity<Greeting> greetingsCreate(Greeting greeting) {
    // TODO Auto-generated method stub
    //call service to create greeting record in DB
		// log.info("date time  is {}", greeting.getCreationDate());
    greeting.setId(100L);
    Instant now = Instant.now();
    OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(now, ZoneOffset.UTC);
    greeting.setCreationDate(offsetDateTime.truncatedTo(ChronoUnit.SECONDS));
    return new ResponseEntity<Greeting>(greeting, HttpStatus.CREATED);
  }


  @Override
  public ResponseEntity<Greeting> greetingsGet(String greetingId) {
    Greeting greeting = new Greeting();
    greeting.setId(counter.incrementAndGet());
    greeting.setMessage(String.format(template, "shane!"));
		Date date = new Date();
		OffsetDateTime offsetDateTime = date.toInstant().atOffset(ZoneOffset.UTC);
		greeting.setCreationDate(offsetDateTime.truncatedTo(ChronoUnit.SECONDS));

    return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Greeting>> greetingsList() {
    // TODO Auto-generated method stub
    List<Greeting> greetings = new ArrayList<>();
    Greeting greeting = new Greeting();
    greeting.setId(counter.incrementAndGet());
    greeting.setMessage(String.format(template, "shane!"));
    Greeting anotherGreeting = new Greeting();
    anotherGreeting.setId(counter.incrementAndGet());
    anotherGreeting.setMessage(String.format(template, "miko!"));
    greetings.add(greeting);
    greetings.add(anotherGreeting);
    return new ResponseEntity<List<Greeting>>(greetings, HttpStatus.OK);

  }


}
