package dev.ncovercash.y2024;

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
      Arguments.of("y2024/9/example.txt", "1928", "2858"),
      Arguments.of("y2024/9/input.txt", "6279058075753", "6301361958738")
    );
  }
}
