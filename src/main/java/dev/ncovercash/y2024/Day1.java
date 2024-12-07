package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.CommonConstants;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day1 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<String>> lines = InputUtils.getDelimitedLines(
      filename,
      CommonConstants.WHITESPACE_REGEX
    );

    List<List<Integer>> numbers = lines
      .stream()
      .map(line ->
        line.stream().map(word -> Integer.parseInt(word, 10)).toList()
      )
      .toList();

    List<Integer> col1 = ArrayUtils
      .getColumn(numbers, 0)
      .stream()
      .sorted()
      .toList();
    List<Integer> col2 = ArrayUtils
      .getColumn(numbers, 1)
      .stream()
      .sorted()
      .toList();

    return (
      IntStream
        .range(0, col1.size())
        .map(i -> Math.abs(col1.get(i) - col2.get(i)))
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<String>> lines = InputUtils.getDelimitedLines(
      filename,
      CommonConstants.WHITESPACE_REGEX
    );

    List<List<Integer>> numbers = lines
      .stream()
      .map(line ->
        line.stream().map(word -> Integer.parseInt(word, 10)).toList()
      )
      .toList();

    List<Integer> col1 = ArrayUtils.getColumn(numbers, 0);
    List<Integer> col2 = ArrayUtils.getColumn(numbers, 1);

    // could i do something better than a linear search for each value?
    // yeah. but this is 1000 lines so /shrug
    Map<Integer, Integer> frequencyCache = new HashMap<>();

    return (
      col1
        .stream()
        .mapToInt(i ->
          i * ArrayUtils.countOccurrencesCached(frequencyCache, col2, i)
        )
        .sum() +
      ""
    );
  }
}
