package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day14Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day14();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/14/example.txt", "12", "skip"),
      Arguments.of("y2024/14/input.txt", "217132650", "6516")
    );
  }
}
