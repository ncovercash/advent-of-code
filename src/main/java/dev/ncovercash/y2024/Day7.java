package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day7 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);

    return (
      input
        .stream()
        .mapToLong(row -> {
          String[] parts = row.split(": ");
          long goal = Long.parseLong(parts[0]);

          List<Integer> values = List
            .of(parts[1].split(" "))
            .stream()
            .map(Integer::parseInt)
            .toList();

          return canCompute(
              goal,
              values.get(0),
              values.subList(1, values.size())
            )
            ? goal
            : 0;
        })
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);

    return (
      input
        .stream()
        .mapToLong(row -> {
          String[] parts = row.split(": ");
          long goal = Long.parseLong(parts[0]);

          List<Integer> values = List
            .of(parts[1].split(" "))
            .stream()
            .map(Integer::parseInt)
            .toList();

          return canComputeWithConcat(
              goal,
              values.get(0),
              values.subList(1, values.size())
            )
            ? goal
            : 0;
        })
        .sum() +
      ""
    );
  }

  private boolean canCompute(long goal, long current, List<Integer> values) {
    if (values.isEmpty()) {
      return current == goal;
    }
    if (current > goal) {
      return false;
    }

    return (
      canCompute(
        goal,
        current + values.get(0),
        values.subList(1, values.size())
      ) ||
      canCompute(
        goal,
        current * values.get(0),
        values.subList(1, values.size())
      )
    );
  }

  private boolean canComputeWithConcat(
    long goal,
    long current,
    List<Integer> values
  ) {
    if (values.isEmpty()) {
      return current == goal;
    }
    if (current > goal) {
      return false;
    }

    return (
      canComputeWithConcat(
        goal,
        current + values.get(0),
        values.subList(1, values.size())
      ) ||
      canComputeWithConcat(
        goal,
        current * values.get(0),
        values.subList(1, values.size())
      ) ||
      canComputeWithConcat(
        goal,
        Long.parseLong("" + current + values.get(0)),
        values.subList(1, values.size())
      )
    );
  }
}
