package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.util.Pair;

@Log4j2
public class Day6 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Character>> input = InputUtils.getCharacterArray(filename);

    Set<Pair<Integer, Integer>> visited = new HashSet<>();

    Position position = new Position();

    // we know we will be facing up
    position.setDirection(new Pair<>(-1, 0));

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).size(); j++) {
        if (input.get(i).get(j) == '^') {
          position.setR(i);
          position.setC(j);
          break;
        }
      }
    }

    while (true) {
      visited.add(position.coordinates());

      if (!move(position, input)) {
        break;
      }
    }

    return (visited.size() + "");
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Character>> input = InputUtils.getCharacterArray(filename);

    Position position = new Position();

    // we know we will be facing up
    position.setDirection(new Pair<>(-1, 0));

    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).size(); j++) {
        if (input.get(i).get(j) == '^') {
          position.setR(i);
          position.setC(j);
          break;
        }
      }
    }

    int possibleCycles = 0;

    for (int r = 0; r < input.size(); r++) {
      for (int c = 0; c < input.get(r).size(); c++) {
        if (input.get(r).get(c) != '.') {
          continue;
        }

        List<List<Character>> copy = input
          .stream()
          .map(ArrayList::new)
          .collect(Collectors.toList());

        copy.get(r).set(c, '#');

        if (doesCycle(Position.of(position), copy)) {
          possibleCycles++;
        }
      }
    }

    return (possibleCycles + "");
  }

  private static boolean doesCycle(
    Position position,
    List<List<Character>> input
  ) {
    Position copy = Position.of(position);

    Set<Position> visited = new HashSet<>();

    while (true) {
      if (visited.contains(position)) {
        return true;
      }

      visited.add(Position.of(position));

      if (!move(position, input)) {
        return false;
      }
    }
  }

  private static boolean move(Position position, List<List<Character>> input) {
    int proposedR = position.getR() + position.getDirection().getKey();
    int proposedC = position.getC() + position.getDirection().getValue();

    // walking off map = done
    if (
      proposedR < 0 ||
      proposedR >= input.size() ||
      proposedC < 0 ||
      proposedC >= input.get(proposedR).size()
    ) {
      return false;
    }

    if (input.get(proposedR).get(proposedC) == '#') {
      // turn right
      if (position.getDirection().getKey() == -1) {
        position.setDirection(new Pair<>(0, 1));
      } else if (position.getDirection().getValue() == 1) {
        position.setDirection(new Pair<>(1, 0));
      } else if (position.getDirection().getKey() == 1) {
        position.setDirection(new Pair<>(0, -1));
      } else if (position.getDirection().getValue() == -1) {
        position.setDirection(new Pair<>(-1, 0));
      }
    } else {
      position.setR(proposedR);
      position.setC(proposedC);
    }

    return true;
  }

  @Data
  private static class Position {

    private int r;
    private int c;
    private Pair<Integer, Integer> direction;

    public Pair<Integer, Integer> coordinates() {
      return new Pair<>(r, c);
    }

    public static Position of(Position position) {
      Position copy = new Position();
      copy.setR(position.getR());
      copy.setC(position.getC());
      copy.setDirection(
        new Pair<>(
          position.getDirection().getKey(),
          position.getDirection().getValue()
        )
      );
      return copy;
    }
  }
}
