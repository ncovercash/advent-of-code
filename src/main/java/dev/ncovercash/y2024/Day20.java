package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.Direction;
import dev.ncovercash.InputUtils;
import dev.ncovercash.MiscUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day20 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);

    int numStepsToSave = Integer.parseInt(input.get(0));
    input = input.subList(1, input.size());

    boolean[][] map = new boolean[input.size()][input.get(0).length()];

    Pair<Integer, Integer> start = null;
    Pair<Integer, Integer> end = null;
    for (int r = 0; r < input.size(); r++) {
      for (int c = 0; c < input.get(r).length(); c++) {
        char ch = input.get(r).charAt(c);
        if (ch == 'S') {
          start = Pair.of(r, c);
        } else if (ch == 'E') {
          end = Pair.of(r, c);
        } else if (ch == '#') {
          map[r][c] = true;
        }
      }
    }

    List<Pair<Integer, Integer>> path = findPath(map, start, end);

    Map<Integer, Integer> savingsQuantity = new HashMap<>();

    for (int i = 0; i < path.size(); i++) {
      // for lambda purposes
      int j = i;
      Arrays
        .stream(Direction.all())
        .map(d -> d.move2(path.get(j)))
        .map(path::indexOf)
        .filter(destIndex -> destIndex > (j + 2))
        .map(destIndex -> destIndex - (j + 2))
        .forEach(savings ->
          savingsQuantity.compute(savings, (k, v) -> v == null ? 1 : v + 1)
        );
    }

    return (
      savingsQuantity
        .entrySet()
        .stream()
        .filter(e -> e.getKey() >= numStepsToSave)
        .mapToInt(Map.Entry::getValue)
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);

    int numStepsToSave = Integer.parseInt(input.get(0));
    input = input.subList(1, input.size());

    boolean[][] map = new boolean[input.size()][input.get(0).length()];

    Pair<Integer, Integer> start = null;
    Pair<Integer, Integer> end = null;
    for (int r = 0; r < input.size(); r++) {
      for (int c = 0; c < input.get(r).length(); c++) {
        char ch = input.get(r).charAt(c);
        if (ch == 'S') {
          start = Pair.of(r, c);
        } else if (ch == 'E') {
          end = Pair.of(r, c);
        } else if (ch == '#') {
          map[r][c] = true;
        }
      }
    }

    List<Pair<Integer, Integer>> path = findPath(map, start, end);
    // optimization
    Map<Pair<Integer, Integer>, Integer> pathToIndexMap = new HashMap<>();
    for (int i = 0; i < path.size(); i++) {
      pathToIndexMap.put(path.get(i), i);
    }

    Map<Integer, Integer> savingsQuantity = new HashMap<>();

    for (int i = 0; i < path.size(); i++) {
      // for lambda purposes
      int j = i;
      getPointsWithin20ManhattanDistance(path.get(i))
        .stream()
        .map(p -> {
          Integer index = pathToIndexMap.getOrDefault(p, null);
          if (index == null) {
            return null;
          } else {
            return Pair.of(
              p,
              index - (j + MiscUtils.manhattanDistance(path.get(j), p))
            );
          }
        })
        .filter(Objects::nonNull)
        .filter(pair -> pair.getRight() > 0)
        .filter(MiscUtils.distinctByKey(Pair::getLeft))
        .map(Pair::getRight)
        .forEach(savings ->
          savingsQuantity.compute(savings, (k, v) -> v == null ? 1 : v + 1)
        );
    }

    return (
      savingsQuantity
        .entrySet()
        .stream()
        .filter(e -> e.getKey() >= numStepsToSave)
        .mapToInt(Map.Entry::getValue)
        .sum() +
      ""
    );
  }

  private List<Pair<Integer, Integer>> getPointsWithin20ManhattanDistance(
    Pair<Integer, Integer> p
  ) {
    List<Pair<Integer, Integer>> points = new ArrayList<>();
    for (int r = p.getLeft() - 20; r <= p.getLeft() + 20; r++) {
      for (int c = p.getRight() - 20; c <= p.getRight() + 20; c++) {
        if (MiscUtils.manhattanDistance(p, Pair.of(r, c)) <= 20) {
          points.add(Pair.of(r, c));
        }
      }
    }
    return points;
  }

  private List<Pair<Integer, Integer>> findPath(
    boolean[][] map,
    Pair<Integer, Integer> start,
    Pair<Integer, Integer> end
  ) {
    List<Pair<Integer, Integer>> path = new ArrayList<>();

    Pair<Integer, Integer> current = start;
    Pair<Integer, Integer> previous = start;

    while (!current.equals(end)) {
      path.add(current);
      if (
        ArrayUtils.canGoMap(map, Direction.LEFT.move(current)) &&
        !Direction.LEFT.move(current).equals(previous)
      ) {
        previous = current;
        current = Direction.LEFT.move(current);
      } else if (
        ArrayUtils.canGoMap(map, Direction.UP.move(current)) &&
        !Direction.UP.move(current).equals(previous)
      ) {
        previous = current;
        current = Direction.UP.move(current);
      } else if (
        ArrayUtils.canGoMap(map, Direction.RIGHT.move(current)) &&
        !Direction.RIGHT.move(current).equals(previous)
      ) {
        previous = current;
        current = Direction.RIGHT.move(current);
      } else if (
        ArrayUtils.canGoMap(map, Direction.DOWN.move(current)) &&
        !Direction.DOWN.move(current).equals(previous)
      ) {
        previous = current;
        current = Direction.DOWN.move(current);
      } else {
        throw new IllegalStateException("No path found");
      }
    }

    path.add(end);

    return path;
  }
}
