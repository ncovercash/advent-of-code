package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day1Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day1();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/1/example.txt", "11", "31"),
      Arguments.of("y2024/1/input.txt", "2086478", "24941624")
    );
  }
}
