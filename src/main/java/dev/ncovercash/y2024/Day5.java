package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day5 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<List<String>> input = InputUtils.getLinesByClump(filename);

    List<String> orderings = input.get(0);
    List<String> updates = input.get(1);

    Map<Integer, List<Integer>> forwardDependencies = new HashMap<>();
    Map<Integer, List<Integer>> backwardDependencies = new HashMap<>();

    for (String ordering : orderings) {
      String[] parts = ordering.split("\\|");
      int before = Integer.parseInt(parts[0]);
      int after = Integer.parseInt(parts[1]);
      forwardDependencies
        .computeIfAbsent(before, k -> new ArrayList<>())
        .add(after);
      backwardDependencies
        .computeIfAbsent(after, k -> new ArrayList<>())
        .add(before);
    }

    return (
      updates
        .stream()
        .map(update ->
          Arrays.stream(update.split(",")).map(Integer::parseInt).toList()
        )
        .filter(update ->
          isUpdateCorrectlyOrdered(
            update,
            forwardDependencies,
            backwardDependencies
          )
        )
        .mapToInt(l -> l.get(l.size() / 2))
        .sum() +
      ""
    );
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<List<String>> input = InputUtils.getLinesByClump(filename);

    List<String> orderings = input.get(0);
    List<String> updates = input.get(1);

    Map<Integer, List<Integer>> forwardDependencies = new HashMap<>();
    Map<Integer, List<Integer>> backwardDependencies = new HashMap<>();

    for (String ordering : orderings) {
      String[] parts = ordering.split("\\|");
      int before = Integer.parseInt(parts[0]);
      int after = Integer.parseInt(parts[1]);
      forwardDependencies
        .computeIfAbsent(before, k -> new ArrayList<>())
        .add(after);
      backwardDependencies
        .computeIfAbsent(after, k -> new ArrayList<>())
        .add(before);
    }

    return (
      updates
        .stream()
        .map(update ->
          Arrays
            .stream(update.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toCollection(ArrayList::new))
        )
        .filter(update ->
          !isUpdateCorrectlyOrdered(
            update,
            forwardDependencies,
            backwardDependencies
          )
        )
        .map((List<Integer> update) -> {
          while (!fixUpdateOrder(update, forwardDependencies));

          return update;
        })
        .mapToInt(l -> l.get(l.size() / 2))
        .sum() +
      ""
    );
  }

  private boolean isUpdateCorrectlyOrdered(
    List<Integer> update,
    Map<Integer, List<Integer>> forwardDependencies,
    Map<Integer, List<Integer>> backwardDependencies
  ) {
    for (int i = 0; i < update.size(); i++) {
      for (int required : forwardDependencies.getOrDefault(
        update.get(i),
        List.of()
      )) {
        int index = update.indexOf(required);
        if (index != -1 && index < i) {
          return false;
        }
      }
      for (int required : backwardDependencies.getOrDefault(
        update.get(i),
        List.of()
      )) {
        int index = update.indexOf(required);
        if (index != -1 && index > i) {
          return false;
        }
      }
    }
    return true;
  }

  // may require multiple passes
  private boolean fixUpdateOrder(
    List<Integer> update,
    Map<Integer, List<Integer>> forwardDependencies
  ) {
    for (int i = 0; i < update.size(); i++) {
      for (int required : forwardDependencies.getOrDefault(
        update.get(i),
        List.of()
      )) {
        int index = update.indexOf(required);
        // we must go backwards...
        if (index != -1 && index < i) {
          update.add(index, update.remove(i));
          return false;
        }
      }
    }

    // backwards contains all constraints that forwards does, so we don't need to consider both

    return true;
  }
}
