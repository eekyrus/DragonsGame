package com.eek.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInputParserTest {

  private CommandLineInputParser parser = new CommandLineInputParser();

  @Test
  void parseInput() throws ParseException {
    CommandLine cmd = parser.parseInput(new String[] {"-targetScore", "1000"});

    assertEquals(1000L, cmd.getParsedOptionValue("targetScore"));
  }
}