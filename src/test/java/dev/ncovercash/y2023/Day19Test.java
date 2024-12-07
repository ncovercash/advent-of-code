package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day19Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day19();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/19/example.txt", "19114", "skip"),
      Arguments.of("y2023/19/input.txt", "391132", "skip")
    );
  }
}
