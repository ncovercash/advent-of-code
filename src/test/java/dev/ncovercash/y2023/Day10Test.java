package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day10Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day10();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/10/example.txt", "4", "skip"),
      Arguments.of("y2023/10/example2.txt", "8", "skip"),
      Arguments.of("y2023/10/example3.txt", "skip", "4"),
      Arguments.of("y2023/10/example4.txt", "skip", "8"),
      Arguments.of("y2023/10/example5.txt", "skip", "10"),
      Arguments.of("y2023/10/input.txt", "7066", "401")
    );
  }
}
