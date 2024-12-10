package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Pair;

@Log4j2
public class Day8 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Character>> input = InputUtils.getCharacterArray(filename);

    Map<Character, List<Pair<Integer, Integer>>> antennae = getAntennae(input);

    // including off-map, for ease here
    Set<Pair<Integer, Integer>> antinodes = new HashSet<>();

    for (List<Pair<Integer, Integer>> locations : antennae.values()) {
      for (int i = 0; i < locations.size(); i++) {
        for (int j = i + 1; j < locations.size(); j++) {
          Pair<Integer, Integer> p1 = locations.get(i);
          Pair<Integer, Integer> p2 = locations.get(j);

          int dr = p1.getFirst() - p2.getFirst();
          int dc = p1.getSecond() - p2.getSecond();

          antinodes.add(new Pair<>(p1.getFirst() + dr, p1.getSecond() + dc));
          antinodes.add(new Pair<>(p2.getFirst() - dr, p2.getSecond() - dc));
        }
      }
    }

    return (
      antinodes
        .stream()
        .filter(p ->
          p.getFirst() >= 0 &&
          p.getFirst() < input.size() &&
          p.getSecond() >= 0 &&
          p.getSecond() < input.get(0).size()
        )
        .count() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Character>> input = InputUtils.getCharacterArray(filename);

    Map<Character, List<Pair<Integer, Integer>>> antennae = getAntennae(input);

    // including off-map, for ease here
    Set<Pair<Integer, Integer>> antinodes = new HashSet<>();

    for (List<Pair<Integer, Integer>> locations : antennae.values()) {
      for (int i = 0; i < locations.size(); i++) {
        for (int j = i + 1; j < locations.size(); j++) {
          Pair<Integer, Integer> p1 = locations.get(i);
          Pair<Integer, Integer> p2 = locations.get(j);

          int dr = p1.getFirst() - p2.getFirst();
          int dc = p1.getSecond() - p2.getSecond();

          int gcd = ArithmeticUtils.gcd(dr, dc);
          dr /= gcd;
          dc /= gcd;

          // we'll be using dr as the guiding offset, so make sure it's positive
          if (dr < 0 || (dr == 0 && dc < 0)) {
            dr = -dr;
            dc = -dc;
          }

          Pair<Integer, Integer> p = new Pair<>(p1);

          while (p.getFirst() >= 0 && p.getSecond() >= 0) {
            antinodes.add(p);
            p = new Pair<>(p.getFirst() - dr, p.getSecond() - dc);
          }

          p = new Pair<>(p1);

          while (
            p.getFirst() < input.size() && p.getSecond() < input.get(0).size()
          ) {
            antinodes.add(p);
            p = new Pair<>(p.getFirst() + dr, p.getSecond() + dc);
          }
        }
      }
    }

    return (
      antinodes
        .stream()
        .filter(p ->
          p.getFirst() >= 0 &&
          p.getFirst() < input.size() &&
          p.getSecond() >= 0 &&
          p.getSecond() < input.get(0).size()
        )
        .count() +
      ""
    );
  }

  private Map<Character, List<Pair<Integer, Integer>>> getAntennae(
    List<List<Character>> input
  ) {
    Map<Character, List<Pair<Integer, Integer>>> antennae = new HashMap<>();

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).size(); j++) {
        char c = input.get(i).get(j);
        if (c == '.') {
          continue;
        }
        antennae
          .computeIfAbsent(c, k -> new ArrayList<>())
          .add(new Pair<>(i, j));
      }
    }

    return antennae;
  }
}
