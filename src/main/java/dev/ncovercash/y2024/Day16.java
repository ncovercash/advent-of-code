package dev.ncovercash.y2024;

import dev.ncovercash.Direction;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
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
        new State(startR, startC, Direction.RIGHT, 0),
        goalR,
        goalC
      )
    );
  }

  private static int findBestPath(
    boolean[][] map,
    State startState,
    int goalR,
    int goalC
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

        cache.put(p, state.score());

        if (state.r() == goalR && state.c() == goalC) {
          best = Math.min(best, state.score());
          continue;
        }

        if (canGo(map, state.direction().move(state.asPair()))) {
          newQueue.add(
            new State(
              state.direction().move(state.asPair()).getLeft(),
              state.direction().move(state.asPair()).getRight(),
              state.direction(),
              state.score() + 1
            )
          );
        }

        if (canGo(map, state.direction().cw().move(state.asPair()))) {
          newQueue.add(
            new State(
              state.direction().cw().move(state.asPair()).getLeft(),
              state.direction().cw().move(state.asPair()).getRight(),
              state.direction().cw(),
              state.score() + 1001
            )
          );
        }

        if (canGo(map, state.direction().ccw().move(state.asPair()))) {
          newQueue.add(
            new State(
              state.direction().ccw().move(state.asPair()).getLeft(),
              state.direction().ccw().move(state.asPair()).getRight(),
              state.direction().ccw(),
              state.score() + 1001
            )
          );
        }
      }

      queue = newQueue;
    }

    return best;
  }

  private static boolean canGo(boolean[][] touched, Pair<Integer, Integer> p) {
    return (
      p.getLeft() >= 0 &&
      p.getLeft() < touched.length &&
      p.getRight() >= 0 &&
      p.getRight() < touched[0].length &&
      !touched[p.getLeft()][p.getRight()]
    );
  }

  private record Position(int r, int c, Direction direction) {
    public static Position fromState(State state) {
      return new Position(state.r(), state.c(), state.direction());
    }
  }

  private record State(int r, int c, Direction direction, int score) {
    public Pair<Integer, Integer> asPair() {
      return Pair.of(r, c);
    }
  }
}
