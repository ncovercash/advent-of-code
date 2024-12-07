package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day17Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day17();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/17/example.txt", "skip", "skip")
      // Arguments.of("y2023/17/input.txt", "skip", "skip")
    );
  }
}
