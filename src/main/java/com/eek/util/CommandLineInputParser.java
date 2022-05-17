package com.eek.util;

import org.apache.commons.cli.*;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInputParser {

  public static final String INPUT_TARGET_SCORE = "targetScore";
  private final CommandLineParser parser = new DefaultParser();

  public CommandLine parseInput(String[] arguments) throws ParseException {
    Options options = new Options();
    options.addOption(
      Option.builder()
        .option(INPUT_TARGET_SCORE)
        .desc("Score to reach to achieve victory")
        .argName(INPUT_TARGET_SCORE)
        .hasArg()
        .required()
        .type(Number.class).build());
    return parser.parse(options, arguments);
  }

}
