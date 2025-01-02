package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day19 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);

    List<String> availableParts = Arrays.asList(input.get(0).split(", "));
    List<String> desired = input.subList(2, input.size());

    Map<String, Long> cache = new HashMap<>();

    return (
      desired
        .stream()
        .filter(design ->
          getNumPossibleCombinations(cache, availableParts, design) != 0
        )
        .count() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);

    List<String> availableParts = Arrays.asList(input.get(0).split(", "));
    List<String> desired = input.subList(2, input.size());

    Map<String, Long> cache = new HashMap<>();

    return (
      desired
        .stream()
        .mapToLong(design ->
          getNumPossibleCombinations(cache, availableParts, design)
        )
        .sum() +
      ""
    );
  }

  private long getNumPossibleCombinations(
    Map<String, Long> cache,
    List<String> availableParts,
    String design
  ) {
    if (cache.containsKey(design)) {
      return cache.get(design);
    }

    if ("".equals(design)) {
      cache.put("", 1L);
      return 1L;
    }

    long result = availableParts
      .stream()
      .filter(design::startsWith)
      .mapToLong(part ->
        getNumPossibleCombinations(
          cache,
          availableParts,
          design.substring(part.length())
        )
      )
      .sum();

    cache.put(design, result);
    return result;
  }
}
