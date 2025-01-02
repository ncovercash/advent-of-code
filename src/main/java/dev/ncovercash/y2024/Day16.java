package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.Direction;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day16 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Character>> mapRaw = InputUtils.getCharacterArray(filename);

    boolean[][] map = new boolean[mapRaw.size()][mapRaw.get(0).size()];

    for (int i = 0; i < mapRaw.size(); i++) {
      for (int j = 0; j < mapRaw.get(i).size(); j++) {
        map[i][j] = mapRaw.get(i).get(j) == '#';
      }
    }

    // all the same
    int startR = map.length - 2;
    int startC = 1;
    int goalR = 1;
    int goalC = map[0].length - 2;

    return (
      "" +
      findBestPath(
        map,
        new State(startR, startC, Direction.RIGHT, 0, List.of()),
        goalR,
        goalC,
        new ArrayList<>()
      )
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Character>> mapRaw = InputUtils.getCharacterArray(filename);

    boolean[][] map = new boolean[mapRaw.size()][mapRaw.get(0).size()];

    for (int i = 0; i < mapRaw.size(); i++) {
      for (int j = 0; j < mapRaw.get(i).size(); j++) {
        map[i][j] = mapRaw.get(i).get(j) == '#';
      }
    }

    // all the same
    int startR = map.length - 2;
    int startC = 1;
    int goalR = 1;
    int goalC = map[0].length - 2;

    List<Pair<List<Position>, Integer>> paths = new ArrayList<>();
    int bestScore = findBestPath(
      map,
      new State(startR, startC, Direction.RIGHT, 0, List.of()),
      goalR,
      goalC,
      paths
    );

    return (
      "" +
      paths
        .stream()
        .filter(p -> p.getRight() == bestScore)
        // get Position only
        .map(Pair::getLeft)
        // get just [r,c]
        .flatMap(List::stream)
        .map(Position::asPair)
        .distinct()
        .count()
    );
  }

  private static int findBestPath(
    boolean[][] map,
    State startState,
    int goalR,
    int goalC,
    List<Pair<List<Position>, Integer>> pathsTaken
  ) {
    Map<Position, Integer> cache = new HashMap<>();

    int best = Integer.MAX_VALUE;

    // stupid BFS
    List<State> queue = new ArrayList<>();
    queue.add(startState);

    while (!queue.isEmpty()) {
      List<State> newQueue = new ArrayList<>();
      for (State state : queue) {
        Position p = Position.fromState(state);
        if (cache.getOrDefault(p, Integer.MAX_VALUE) < state.score()) {
          continue;
        }

        List<Position> newPath = state.getPathWith(p);

        cache.put(p, state.score());

        if (state.r() == goalR && state.c() == goalC) {
          pathsTaken.add(Pair.of(newPath, state.score()));
          best = Math.min(best, state.score());
          continue;
        }

        if (ArrayUtils.canGoMap(map, state.direction().move(state.asPair()))) {
          newQueue.add(
            new State(
              state.direction().move(state.asPair()).getLeft(),
              state.direction().move(state.asPair()).getRight(),
              state.direction(),
              state.score() + 1,
              newPath
            )
          );
        }

        if (
          ArrayUtils.canGoMap(map, state.direction().cw().move(state.asPair()))
        ) {
          newQueue.add(
            new State(
              state.direction().cw().move(state.asPair()).getLeft(),
              state.direction().cw().move(state.asPair()).getRight(),
              state.direction().cw(),
              state.score() + 1001,
              newPath
            )
          );
        }

        if (
          ArrayUtils.canGoMap(map, state.direction().ccw().move(state.asPair()))
        ) {
          newQueue.add(
            new State(
              state.direction().ccw().move(state.asPair()).getLeft(),
              state.direction().ccw().move(state.asPair()).getRight(),
              state.direction().ccw(),
              state.score() + 1001,
              newPath
            )
          );
        }
      }

      queue = newQueue;
    }

    return best;
  }

  private record Position(int r, int c, Direction direction) {
    public static Position fromState(State state) {
      return new Position(state.r(), state.c(), state.direction());
    }
    public Pair<Integer, Integer> asPair() {
      return Pair.of(r, c);
    }
  }

  private record State(
    int r,
    int c,
    Direction direction,
    int score,
    List<Position> path
  ) {
    public Pair<Integer, Integer> asPair() {
      return Pair.of(r, c);
    }

    public List<Position> getPathWith(Position p) {
      List<Position> newPath = new ArrayList<>(path);
      newPath.add(p);
      return newPath;
    }
  }
}
