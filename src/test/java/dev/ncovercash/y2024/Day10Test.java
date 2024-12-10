package dev.ncovercash.y2024;

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
      Arguments.of("y2024/10/example.txt", "36", "81"),
      Arguments.of("y2024/10/input.txt", "746", "")
    );
  }
}
