package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealVector;

@Log4j2
public class Day13 implements Solution {

  private static final double EPSILON = 1e-4;

  private static final Pattern BUTTON_PATTERN = Pattern.compile(
    "^Button .: X\\+(\\d+), Y\\+(\\d+)$"
  );
  private static final Pattern PRIZE_PATTERN = Pattern.compile(
    "^Prize: X=(\\d+), Y=(\\d+)$"
  );

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<String>> games = InputUtils.getLinesByClump(filename);

    return (
      games
        .stream()
        .mapToLong((List<String> game) -> {
          Matcher buttonA = BUTTON_PATTERN.matcher(game.get(0));
          Matcher buttonB = BUTTON_PATTERN.matcher(game.get(1));
          Matcher prize = PRIZE_PATTERN.matcher(game.get(2));

          if (!buttonA.matches() || !buttonB.matches() || !prize.matches()) {
            throw new RuntimeException("Invalid input");
          }

          int aX = Integer.parseInt(buttonA.group(1));
          int aY = Integer.parseInt(buttonA.group(2));
          int bX = Integer.parseInt(buttonB.group(1));
          int bY = Integer.parseInt(buttonB.group(2));

          int goalX = Integer.parseInt(prize.group(1));
          int goalY = Integer.parseInt(prize.group(2));

          return solve(aX, aY, bX, bY, goalX, goalY);
        })
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<String>> games = InputUtils.getLinesByClump(filename);

    return (
      games
        .stream()
        .mapToLong((List<String> game) -> {
          Matcher buttonA = BUTTON_PATTERN.matcher(game.get(0));
          Matcher buttonB = BUTTON_PATTERN.matcher(game.get(1));
          Matcher prize = PRIZE_PATTERN.matcher(game.get(2));

          if (!buttonA.matches() || !buttonB.matches() || !prize.matches()) {
            throw new RuntimeException("Invalid input");
          }

          int aX = Integer.parseInt(buttonA.group(1));
          int aY = Integer.parseInt(buttonA.group(2));
          int bX = Integer.parseInt(buttonB.group(1));
          int bY = Integer.parseInt(buttonB.group(2));

          int goalX = Integer.parseInt(prize.group(1));
          int goalY = Integer.parseInt(prize.group(2));

          return solve(
            aX,
            aY,
            bX,
            bY,
            goalX + 10000000000000L,
            goalY + 10000000000000L
          );
        })
        .sum() +
      ""
    );
  }

  private static long solve(
    int aX,
    int aY,
    int bX,
    int bY,
    long goalX,
    long goalY
  ) {
    DecompositionSolver solver = new LUDecomposition(
      new Array2DRowRealMatrix(new double[][] { { aX, bX }, { aY, bY } }, false)
    )
      .getSolver();

    RealVector solution = solver.solve(
      new ArrayRealVector(new double[] { goalX, goalY }, false)
    );

    if (
      Math.abs(solution.getEntry(0) - Math.round(solution.getEntry(0))) >
      EPSILON ||
      Math.abs(solution.getEntry(1) - Math.round(solution.getEntry(1))) >
      EPSILON
    ) {
      return 0;
    }

    return (
      3 * Math.round(solution.getEntry(0)) + Math.round(solution.getEntry(1))
    );
  }
}
