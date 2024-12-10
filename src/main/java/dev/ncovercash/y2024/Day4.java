package dev.ncovercash.y2024;

import dev.ncovercash.InputUtils;
import dev.ncovercash.Solution;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day4 implements Solution {

  @Override
  public String completeChallengePartOne(String filename) {
    List<String> input = InputUtils.getLines(filename);

    int count = 0;

    // horizontal
    for (String row : input) {
      for (int i = 0; i < row.length() - 3; i++) {
        if (
          row.charAt(i) == 'X' &&
          row.charAt(i + 1) == 'M' &&
          row.charAt(i + 2) == 'A' &&
          row.charAt(i + 3) == 'S'
        ) {
          count++;
        }
        if (
          row.charAt(i) == 'S' &&
          row.charAt(i + 1) == 'A' &&
          row.charAt(i + 2) == 'M' &&
          row.charAt(i + 3) == 'X'
        ) {
          count++;
        }
      }
    }

    // vertical
    for (int r = 0; r < input.size() - 3; r++) {
      for (int c = 0; c < input.get(r).length(); c++) {
        if (
          input.get(r).charAt(c) == 'X' &&
          input.get(r + 1).charAt(c) == 'M' &&
          input.get(r + 2).charAt(c) == 'A' &&
          input.get(r + 3).charAt(c) == 'S'
        ) {
          count++;
        }
        if (
          input.get(r).charAt(c) == 'S' &&
          input.get(r + 1).charAt(c) == 'A' &&
          input.get(r + 2).charAt(c) == 'M' &&
          input.get(r + 3).charAt(c) == 'X'
        ) {
          count++;
        }
      }
    }

    // diagonal
    for (int r = 0; r < input.size() - 3; r++) {
      for (int c = 0; c < input.get(r).length() - 3; c++) {
        // major diagonal, forwards
        if (
          input.get(r).charAt(c) == 'X' &&
          input.get(r + 1).charAt(c + 1) == 'M' &&
          input.get(r + 2).charAt(c + 2) == 'A' &&
          input.get(r + 3).charAt(c + 3) == 'S'
        ) {
          count++;
        }
        // major diagonal, backwards
        if (
          input.get(r).charAt(c) == 'S' &&
          input.get(r + 1).charAt(c + 1) == 'A' &&
          input.get(r + 2).charAt(c + 2) == 'M' &&
          input.get(r + 3).charAt(c + 3) == 'X'
        ) {
          count++;
        }
        // minor diagonal, forwards
        if (
          input.get(r).charAt(c + 3) == 'X' &&
          input.get(r + 1).charAt(c + 2) == 'M' &&
          input.get(r + 2).charAt(c + 1) == 'A' &&
          input.get(r + 3).charAt(c) == 'S'
        ) {
          count++;
        }
        // minor diagonal, backwards
        if (
          input.get(r).charAt(c + 3) == 'S' &&
          input.get(r + 1).charAt(c + 2) == 'A' &&
          input.get(r + 2).charAt(c + 1) == 'M' &&
          input.get(r + 3).charAt(c) == 'X'
        ) {
          count++;
        }
      }
    }

    return count + "";
  }

  @Override
  public String completeChallengePartTwo(String filename) {
    List<String> input = InputUtils.getLines(filename);

    int count = 0;

    // is there a more clever way? probably certainly.
    for (int r = 1; r < input.size() - 1; r++) {
      for (int c = 1; c < input.get(r).length() - 1; c++) {
        // major diagonal, forwards
        if (
          input.get(r).charAt(c) == 'A' &&
          (
            (
              input.get(r - 1).charAt(c - 1) == 'M' &&
              input.get(r + 1).charAt(c + 1) == 'S'
            ) ||
            (
              input.get(r - 1).charAt(c - 1) == 'S' &&
              input.get(r + 1).charAt(c + 1) == 'M'
            )
          ) &&
          (
            (
              input.get(r - 1).charAt(c + 1) == 'M' &&
              input.get(r + 1).charAt(c - 1) == 'S'
            ) ||
            (
              input.get(r - 1).charAt(c + 1) == 'S' &&
              input.get(r + 1).charAt(c - 1) == 'M'
            )
          )
        ) {
          count++;
        }
      }
    }

    return count + "";
  }
}
