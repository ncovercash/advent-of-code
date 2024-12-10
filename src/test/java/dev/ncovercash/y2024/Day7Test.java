package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day7Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day7();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/7/example.txt", "3749", "11387"),
      Arguments.of("y2024/7/input.txt", "1153997401072", "97902809384118")
    );
  }
}
