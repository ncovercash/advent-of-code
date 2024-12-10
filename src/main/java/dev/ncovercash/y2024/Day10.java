package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day10 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Character>> map = InputUtils.getCharacterArray(filename);

    int count = 0;

    for (int r = 0; r < map.size(); r++) {
      for (int c = 0; c < map.get(r).size(); c++) {
        if (map.get(r).get(c) == '0') {
          count += trail(map, r, c, 0).size();
        }
      }
    }

    return count + "";
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Character>> map = InputUtils.getCharacterArray(filename);

    int count = 0;

    for (int r = 0; r < map.size(); r++) {
      for (int c = 0; c < map.get(r).size(); c++) {
        if (map.get(r).get(c) == '0') {
          count += trailUniquePaths(map, r, c, 0);
        }
      }
    }

    return count + "";
  }

  private static Set<Pair<Integer, Integer>> trail(
    List<List<Character>> map,
    int r,
    int c,
    int v
  ) {
    if (v == 9) {
      return Set.of(Pair.of(r, c));
    }
    Set<Pair<Integer, Integer>> destinations = new HashSet<>();
    if (ArrayUtils.getOrDefault(map, r - 1, c, '0') - '0' == v + 1) {
      destinations.addAll(trail(map, r - 1, c, v + 1));
    }
    if (ArrayUtils.getOrDefault(map, r + 1, c, '0') - '0' == v + 1) {
      destinations.addAll(trail(map, r + 1, c, v + 1));
    }
    if (ArrayUtils.getOrDefault(map, r, c - 1, '0') - '0' == v + 1) {
      destinations.addAll(trail(map, r, c - 1, v + 1));
    }
    if (ArrayUtils.getOrDefault(map, r, c + 1, '0') - '0' == v + 1) {
      destinations.addAll(trail(map, r, c + 1, v + 1));
    }
    return destinations;
  }

  private static int trailUniquePaths(
    List<List<Character>> map,
    int r,
    int c,
    int v
  ) {
    if (v == 9) {
      return 1;
    }
    int count = 0;
    if (ArrayUtils.getOrDefault(map, r - 1, c, '0') - '0' == v + 1) {
      count += trailUniquePaths(map, r - 1, c, v + 1);
    }
    if (ArrayUtils.getOrDefault(map, r + 1, c, '0') - '0' == v + 1) {
      count += trailUniquePaths(map, r + 1, c, v + 1);
    }
    if (ArrayUtils.getOrDefault(map, r, c - 1, '0') - '0' == v + 1) {
      count += trailUniquePaths(map, r, c - 1, v + 1);
    }
    if (ArrayUtils.getOrDefault(map, r, c + 1, '0') - '0' == v + 1) {
      count += trailUniquePaths(map, r, c + 1, v + 1);
    }
    return count;
  }
}
