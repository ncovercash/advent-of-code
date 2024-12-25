package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day25Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day25();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/25/example.txt", "3", "skip"),
      Arguments.of("y2024/25/input.txt", "", "skip")
    );
  }
}
