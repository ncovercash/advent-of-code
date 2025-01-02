package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.Direction;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day18 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);
    int size = Integer.parseInt(input.get(0));
    int numToConsider = Integer.parseInt(input.get(1));

    boolean[][] map = new boolean[size][size];
    for (int i = 3; i < 3 + numToConsider; i++) {
      String[] split = input.get(i).split(",");
      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);
      map[x][y] = true;
    }

    return (
      findPaths(map).stream().mapToInt(Set::size).min().orElseThrow() + ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);
    int size = Integer.parseInt(input.get(0));
    int numToConsider = Integer.parseInt(input.get(1));

    boolean[][] map = new boolean[size][size];
    for (int i = 3; i < 3 + numToConsider; i++) {
      String[] split = input.get(i).split(",");
      int x = Integer.parseInt(split[0]);
      int y = Integer.parseInt(split[1]);
      map[x][y] = true;
    }

    List<Set<Pair<Integer, Integer>>> paths = findPaths(map);

    return (
      eliminatePathsUntilFailure(
        map,
        paths,
        input
          .subList(3 + numToConsider, input.size())
          .stream()
          .map(s -> s.split(","))
          .map(p -> Pair.of(Integer.parseInt(p[0]), Integer.parseInt(p[1])))
          .toList()
      ) +
      ""
    );
  }

  private List<Set<Pair<Integer, Integer>>> findPaths(boolean[][] map) {
    Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();

    List<State> queue = new ArrayList<>();
    queue.add(new State(0, 0, Set.of()));

    List<Set<Pair<Integer, Integer>>> paths = new ArrayList<>();

    while (!queue.isEmpty()) {
      List<State> newQueue = new ArrayList<>();
      for (State current : queue) {
        if (
          cache.getOrDefault(current.asPair(), Integer.MAX_VALUE) <=
          current.path().size()
        ) {
          continue;
        }

        cache.put(current.asPair(), current.path().size());

        if (current.r() == map.length - 1 && current.c() == map[0].length - 1) {
          paths.add(current.path());
          continue;
        }

        Direction
          .getAllMoves(current.asPair())
          .stream()
          .filter(p -> ArrayUtils.canGoMap(map, p))
          .map(p -> new State(p.getLeft(), p.getRight(), current.pathPlus(p)))
          .forEach(newQueue::add);
      }

      queue = newQueue;
    }

    return paths;
  }

  private record State(int r, int c, Set<Pair<Integer, Integer>> path) {
    public Pair<Integer, Integer> asPair() {
      return Pair.of(r, c);
    }

    public Set<Pair<Integer, Integer>> pathPlus(Pair<Integer, Integer> p) {
      Set<Pair<Integer, Integer>> newPath = new HashSet<>(path);
      newPath.add(p);
      return newPath;
    }
  }

  private String eliminatePathsUntilFailure(
    boolean[][] map,
    List<Set<Pair<Integer, Integer>>> paths,
    List<Pair<Integer, Integer>> points
  ) {
    for (int i = 0; i < points.size(); i++) {
      Pair<Integer, Integer> point = points.get(i);

      map[point.getLeft()][point.getRight()] = true;
      paths.removeIf(p -> p.contains(point));

      if (paths.isEmpty()) {
        // the path-finding algorithm has some eager optimization, to prevent
        // making 70^70 paths or something ridiculous, so if we run out of candidates
        // we will just fetch more...
        paths = findPaths(map);
        if (paths.isEmpty()) {
          return point.getLeft() + "," + point.getRight();
        } else {
          i--;
        }
      }
    }

    return "ran out of bytes, all paths worked...?";
  }

  private void printPath(Set<Pair<Integer, Integer>> path) {
    log.info(path.stream().map(p -> p.getLeft() + "," + p.getRight()).toList());
  }
}
