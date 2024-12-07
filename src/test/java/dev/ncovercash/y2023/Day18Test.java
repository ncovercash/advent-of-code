package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day18Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day18();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/18/example.txt", "62", "skip"),
      Arguments.of("y2023/18/input.txt", "108909", "skip")
    );
  }
}
