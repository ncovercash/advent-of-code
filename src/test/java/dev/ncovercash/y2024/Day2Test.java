package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day2Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day2();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/2/example.txt", "2", "4"),
      Arguments.of("y2024/2/input.txt", "463", "")
    );
  }
}
