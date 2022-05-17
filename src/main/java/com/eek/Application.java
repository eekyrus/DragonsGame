package com.eek;

import com.eek.service.GameLoopService;
import com.eek.util.CommandLineInputParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.eek.util.CommandLineInputParser.*;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

  @Autowired
  private GameLoopService gameLoop;
  @Autowired
  private CommandLineInputParser inputParser;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args).close();
  }

  @Override
  public void run(String... args) throws ParseException {
    CommandLine commandLine = inputParser.parseInput(args);
    long targetScore = (long) commandLine.getParsedOptionValue(INPUT_TARGET_SCORE);
    long score = gameLoop.playGame(targetScore);
    log.info("Game won with score: " + score);
  }
}
