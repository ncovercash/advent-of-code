package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day5Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day5();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/5/example.txt", "35", "46"),
      Arguments.of("y2023/5/input.txt", "3374647", "6082852")
    );
  }
}
