package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;

@Log4j2
public class Day14 implements Solution {

  private static final Pattern PATTERN = Pattern.compile(
    "^p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)$"
  );

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> lines = InputUtils.getLines(filename);

    int width = Integer.parseInt(lines.get(0).split(",")[0]);
    int height = Integer.parseInt(lines.get(0).split(",")[1]);
    lines.remove(0);

    List<Pair<Integer, Integer>> positions = lines
      .stream()
      .map(Robot::new)
      .map(r -> r.iterate(width, height, 100))
      .toList();

    int midX = width / 2;
    int midY = height / 2;

    return (
      (
        count(positions, 0, 0, midX - 1, midY - 1) *
        count(positions, 0, midY + 1, midX - 1, height - 1) *
        count(positions, midX + 1, 0, width - 1, midY - 1) *
        count(positions, midX + 1, midY + 1, width - 1, height - 1)
      ) +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> lines = InputUtils.getLines(filename);

    int width = Integer.parseInt(lines.get(0).split(",")[0]);
    int height = Integer.parseInt(lines.get(0).split(",")[1]);
    lines.remove(0);

    List<Robot> robots = lines.stream().map(Robot::new).toList();

    int midX = width / 2;
    int midY = height / 2;

    for (int i = 0; i < 20000; i++) {
      final int j = i;
      List<Pair<Integer, Integer>> positions = robots
        .stream()
        .map(r -> r.iterate(width, height, j))
        .toList();

      if (
        // tree is not centered, but does have a border and so we can easily check for that
        IntStream
          .range(0, width)
          .filter(col -> {
            List<Integer> rows = positions
              .stream()
              .filter(p -> p.getLeft() == col)
              .map(Pair::getRight)
              .sorted()
              .toList();

            int successive = 0;

            for (int row : rows) {
              if (rows.contains(row + 1)) {
                successive++;
              } else {
                successive = 0;
              }

              // number determined from a large number of plotting and checking
              if (successive >= 25) {
                return true;
              }
            }
            return false;
          })
          .count() >=
        // two different side borders
        2
      ) {
        plot(
          robots.stream().map(r -> r.iterate(width, height, j)).toList(),
          width,
          height
        );
        return "" + j;
      }
    }

    return "could not find a tree in 20k iterations :(";
  }

  private static void plot(
    List<Pair<Integer, Integer>> positions,
    int width,
    int height
  ) {
    for (int r = 0; r < height; r++) {
      String s = "" + (char) (33 + r) + " ".repeat(5);
      for (int c = 0; c < width; c++) {
        if (positions.contains(Pair.of(c, r))) {
          s += "#";
        } else {
          s += ".";
        }
      }
      log.info(s);
    }
  }

  private static long count(
    List<Pair<Integer, Integer>> positions,
    int minX,
    int minY,
    int maxX,
    int maxY
  ) {
    return positions
      .stream()
      .filter(p ->
        p.getLeft() >= minX &&
        p.getLeft() <= maxX &&
        p.getRight() >= minY &&
        p.getRight() <= maxY
      )
      .count();
  }

  private class Robot {

    private int x;
    private int y;
    private int dx;
    private int dy;

    public Robot(String s) {
      Matcher matcher = PATTERN.matcher(s);
      if (!matcher.matches()) {
        throw new RuntimeException();
      }

      int x = Integer.parseInt(matcher.group(1));
      int y = Integer.parseInt(matcher.group(2));
      int dx = Integer.parseInt(matcher.group(3));
      int dy = Integer.parseInt(matcher.group(4));

      this.x = x;
      this.y = y;
      this.dx = dx;
      this.dy = dy;
    }

    public Pair<Integer, Integer> iterate(int width, int height, int num) {
      return Pair.of(
        (((x + dx * num) % width) + width) % width,
        (((y + dy * num) % height) + height) % height
      );
    }
  }
}
