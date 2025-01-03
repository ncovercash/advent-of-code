package dev.ncovercash;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public enum Direction {
  LEFT,
  RIGHT,
  UP,
  DOWN;

  public Direction cw() {
    return switch (this) {
      case LEFT -> UP;
      case UP -> RIGHT;
      case RIGHT -> DOWN;
      case DOWN -> LEFT;
    };
  }

  public Direction ccw() {
    return switch (this) {
      case LEFT -> DOWN;
      case DOWN -> RIGHT;
      case RIGHT -> UP;
      case UP -> LEFT;
    };
  }

  public Pair<Integer, Integer> move(int curR, int curC) {
    return switch (this) {
      case LEFT -> Pair.of(curR, curC - 1);
      case RIGHT -> Pair.of(curR, curC + 1);
      case UP -> Pair.of(curR - 1, curC);
      case DOWN -> Pair.of(curR + 1, curC);
    };
  }

  public Pair<Integer, Integer> move(Pair<Integer, Integer> p) {
    return move(p.getLeft(), p.getRight());
  }

  public Pair<Integer, Integer> move2(Pair<Integer, Integer> p) {
    return move(move(p));
  }

  public static Direction[] all() {
    return new Direction[] { LEFT, RIGHT, UP, DOWN };
  }

  public static List<Pair<Integer, Integer>> getAllMoves(
    Pair<Integer, Integer> p
  ) {
    return Arrays.stream(all()).map(d -> d.move(p)).toList();
  }
}
