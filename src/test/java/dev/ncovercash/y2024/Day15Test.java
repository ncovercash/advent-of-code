package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day15Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day15();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/15/example1.txt", "2028", "1751"),
      Arguments.of("y2024/15/example2.txt", "10092", "9021"),
      Arguments.of("y2024/15/input.txt", "1538871", "1543338")
    );
  }
}
