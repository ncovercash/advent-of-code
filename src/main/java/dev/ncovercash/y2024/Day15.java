package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day15 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<String>> clumps = InputUtils.getLinesByClump(filename);
    List<String> mapRaw = clumps.get(0);
    String instructions = clumps.get(1).stream().collect(Collectors.joining());

    Cell[][] map = new Cell[mapRaw.size()][mapRaw.get(0).length()];
    Pair<Integer, Integer> robot = null;

    for (int i = 0; i < mapRaw.size(); i++) {
      for (int j = 0; j < mapRaw.get(i).length(); j++) {
        switch (mapRaw.get(i).charAt(j)) {
          case '.' -> map[i][j] = Cell.EMPTY;
          case '#' -> map[i][j] = Cell.WALL;
          case 'O' -> map[i][j] = Cell.BOX;
          case '@' -> {
            map[i][j] = Cell.EMPTY;
            robot = Pair.of(i, j);
          }
          default -> throw new IllegalStateException("Unexpected value");
        }
      }
    }

    for (int c : instructions.chars().toArray()) {
      Pair<Integer, Integer> direction =
        switch ((char) c) {
          case '^' -> Pair.of(-1, 0);
          case 'v' -> Pair.of(1, 0);
          case '<' -> Pair.of(0, -1);
          case '>' -> Pair.of(0, 1);
          default -> throw new IllegalStateException("Unexpected value: " + c);
        };

      robot = moveRobot(map, robot, direction);
    }

    int sum = 0;
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (map[r][c] == Cell.BOX) {
          sum += 100 * r + c;
        }
      }
    }

    return sum + "";
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<String>> clumps = InputUtils.getLinesByClump(filename);
    List<String> mapRaw = clumps.get(0);
    String instructions = clumps.get(1).stream().collect(Collectors.joining());

    Cell[][] map = new Cell[mapRaw.size()][mapRaw.get(0).length() * 2];
    Pair<Integer, Integer> robot = null;

    for (int i = 0; i < mapRaw.size(); i++) {
      for (int j = 0; j < mapRaw.get(i).length(); j++) {
        switch (mapRaw.get(i).charAt(j)) {
          case '.' -> {
            map[i][2 * j] = Cell.EMPTY;
            map[i][2 * j + 1] = Cell.EMPTY;
          }
          case '#' -> {
            map[i][2 * j] = Cell.WALL;
            map[i][2 * j + 1] = Cell.WALL;
          }
          case 'O' -> {
            map[i][2 * j] = Cell.BOX_LEFT;
            map[i][2 * j + 1] = Cell.BOX_RIGHT;
          }
          case '@' -> {
            map[i][2 * j] = Cell.EMPTY;
            map[i][2 * j + 1] = Cell.EMPTY;
            robot = Pair.of(i, 2 * j);
          }
          default -> throw new IllegalStateException("Unexpected value");
        }
      }
    }

    // print(map, robot);

    for (int c : instructions.chars().toArray()) {
      Pair<Integer, Integer> direction =
        switch ((char) c) {
          case '^' -> Pair.of(-1, 0);
          case 'v' -> Pair.of(1, 0);
          case '<' -> Pair.of(0, -1);
          case '>' -> Pair.of(0, 1);
          default -> throw new IllegalStateException("Unexpected value: " + c);
        };

      robot = moveRobot(map, robot, direction);
    }

    int sum = 0;
    for (int r = 0; r < map.length; r++) {
      for (int c = 0; c < map[r].length; c++) {
        if (map[r][c] == Cell.BOX_LEFT) {
          sum += 100 * r + c;
        }
      }
    }

    return sum + "";
  }

  private static void print(Cell[][] map, Pair<Integer, Integer> robot) {
    for (int i = 0; i < map.length; i++) {
      String s = "" + ((char) (33 + i)) + " ".repeat(3);
      for (int j = 0; j < map[i].length; j++) {
        if (robot.getLeft() == i && robot.getRight() == j) {
          s += "@";
        } else {
          s +=
            switch (map[i][j]) {
              case EMPTY -> ".";
              case WALL -> "#";
              case BOX -> "O";
              case BOX_LEFT -> "[";
              case BOX_RIGHT -> "]";
            };
        }
      }
      log.info(s);
    }
  }

  private static Pair<Integer, Integer> moveRobot(
    Cell[][] map,
    Pair<Integer, Integer> robot,
    Pair<Integer, Integer> direction
  ) {
    try {
      Set<Pair<Integer, Integer>> toMove = push(map, Set.of(robot), direction);
      Set<Pair<Integer, Integer>> moved = new HashSet<>(toMove.size());

      Cell[][] newMap = Arrays
        .stream(map)
        .map(m -> Arrays.copyOf(m, m.length))
        .toArray(Cell[][]::new);

      for (Pair<Integer, Integer> p : toMove) {
        Pair<Integer, Integer> newPos = Pair.of(
          p.getLeft() + direction.getLeft(),
          p.getRight() + direction.getRight()
        );
        moved.add(newPos);
        newMap[newPos.getLeft()][newPos.getRight()] =
          map[p.getLeft()][p.getRight()];
      }
      for (Pair<Integer, Integer> p : toMove) {
        if (!moved.contains(p)) {
          newMap[p.getLeft()][p.getRight()] = Cell.EMPTY;
        }
      }

      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[i].length; j++) {
          map[i][j] = newMap[i][j];
        }
      }

      return Pair.of(
        robot.getLeft() + direction.getLeft(),
        robot.getRight() + direction.getRight()
      );
    } catch (WallException e) {
      return robot;
    }
  }

  private static Set<Pair<Integer, Integer>> push(
    Cell[][] map,
    Set<Pair<Integer, Integer>> moving,
    Pair<Integer, Integer> direction
  ) {
    List<Pair<Integer, Integer>> moved = moving
      .stream()
      .map(p ->
        Pair.of(
          p.getLeft() + direction.getLeft(),
          p.getRight() + direction.getRight()
        )
      )
      .toList();

    Set<Pair<Integer, Integer>> pushedByTheseBoxes = new HashSet<>(moving);
    for (Pair<Integer, Integer> newPosition : moved) {
      switch (map[newPosition.getLeft()][newPosition.getRight()]) {
        case WALL -> throw new WallException();
        case BOX -> pushedByTheseBoxes.add(newPosition);
        case BOX_LEFT -> {
          pushedByTheseBoxes.add(
            Pair.of(newPosition.getLeft(), newPosition.getRight())
          );
          pushedByTheseBoxes.add(
            Pair.of(newPosition.getLeft(), newPosition.getRight() + 1)
          );
        }
        case BOX_RIGHT -> {
          pushedByTheseBoxes.add(
            Pair.of(newPosition.getLeft(), newPosition.getRight())
          );
          pushedByTheseBoxes.add(
            Pair.of(newPosition.getLeft(), newPosition.getRight() - 1)
          );
        }
      }
    }

    if (pushedByTheseBoxes.size() == moving.size()) {
      return moving;
    }

    return push(map, pushedByTheseBoxes, direction);
  }

  private static class WallException extends RuntimeException {

    public WallException() {
      super("hit wall :(");
    }
  }

  private enum Cell {
    EMPTY,
    WALL,
    BOX,
    BOX_LEFT,
    BOX_RIGHT,
  }
}
