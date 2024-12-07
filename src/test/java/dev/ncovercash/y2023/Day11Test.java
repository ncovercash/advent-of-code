package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day11Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day11();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/11/example.txt", "374", "skip"),
      Arguments.of("y2023/11/input.txt", "9545480", "406725732046")
    );
  }
}
