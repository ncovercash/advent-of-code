package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day3 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    String input = InputUtils.getString(filename);

    Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    return (
      pattern
        .matcher(input)
        .results()
        .mapToInt((MatchResult match) -> {
          int a = Integer.parseInt(match.group(1));
          int b = Integer.parseInt(match.group(2));
          return a * b;
        })
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    String input = InputUtils.getString(filename);

    Pattern pattern = Pattern.compile(
      "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)"
    );

    // too lazy to not use a stream below, lol
    AtomicBoolean enabled = new AtomicBoolean(true);

    return (
      pattern
        .matcher(input)
        .results()
        .sequential()
        .mapToInt((MatchResult match) -> {
          if ("do()".equals(match.group())) {
            enabled.set(true);
            return 0;
          } else if (!enabled.get()) {
            return 0;
          } else if ("don't()".equals(match.group())) {
            enabled.set(false);
            return 0;
          }
          int a = Integer.parseInt(match.group(1));
          int b = Integer.parseInt(match.group(2));
          return a * b;
        })
        .sum() +
      ""
    );
  }
}
