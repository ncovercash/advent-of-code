package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day9Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day9();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/9/example.txt", "114", "2"),
      Arguments.of("y2023/9/input.txt", "1938731307", "948")
    );
  }
}
