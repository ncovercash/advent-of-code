package dev.ncovercash.y2024;

import dev.ncovercash.ArrayUtils;
import dev.ncovercash.CommonConstants;
import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day2 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<Integer>> lines = ArrayUtils.to2DIntegers(
      InputUtils.getDelimitedLines(filename, CommonConstants.WHITESPACE_REGEX)
    );

    return (lines.stream().filter(Day2::rowIsSafe).count() + "");
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<Integer>> lines = ArrayUtils.to2DIntegers(
      InputUtils.getDelimitedLines(filename, CommonConstants.WHITESPACE_REGEX)
    );

    return (
      lines
        .stream()
        // just tries each row removing each member
        .filter((List<Integer> line) -> {
          if (rowIsSafe(line)) {
            return true;
          }

          for (int i = 0; i < line.size(); i++) {
            List<Integer> copy = new ArrayList<>(line);
            copy.remove(i);
            if (rowIsSafe(copy)) {
              return true;
            }
          }

          return false;
        })
        .count() +
      ""
    );
  }

  private static boolean rowIsSafe(List<Integer> row) {
    boolean isIncreasing = true;
    boolean isDecreasing = true;
    for (int i = 1; i < row.size(); i++) {
      int previous = row.get(i - 1);
      int current = row.get(i);

      // cannot have adjacent that are off by 0
      if (previous == current) {
        return false;
      }
      // cannot have adjacent that are off by >3
      if (Math.abs(previous - current) > 3) {
        return false;
      }

      if (previous <= current) {
        isDecreasing = false;
      } else if (previous >= current) {
        isIncreasing = false;
      }
    }

    return isIncreasing || isDecreasing;
  }
}
