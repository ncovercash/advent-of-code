package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day25 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<String>> groups = InputUtils.getLinesByClump(filename);

    int width = 5;
    int height = groups.get(0).size();

    List<List<Integer>> locks = groups
      .stream()
      .filter(l -> l.get(0).charAt(0) == '#')
      .map(l ->
        IntStream
          .range(0, width)
          .mapToObj(i -> ArrayUtils.getStringColumn(l, i))
          .map(s -> (int) s.stream().filter(c -> c.equals('#')).count())
          .toList()
      )
      .toList();

    List<List<Integer>> keys = groups
      .stream()
      .filter(l -> l.get(0).charAt(0) != '#')
      .map(l ->
        IntStream
          .range(0, width)
          .mapToObj(i -> ArrayUtils.getStringColumn(l, i))
          .map(s -> (int) s.stream().filter(c -> c.equals('#')).count())
          .toList()
      )
      .toList();

    return (
      locks
        .stream()
        .mapToLong(lock ->
          keys
            .stream()
            .filter((List<Integer> key) -> {
              for (int i = 0; i < width; i++) {
                if (lock.get(i) + key.get(i) > height) {
                  return false;
                }
              }
              return true;
            })
            .count()
        )
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    return "";
  }
}
