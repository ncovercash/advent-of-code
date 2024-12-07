package dev.ncovercash.y2023;

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
      Arguments.of("y2023/15/example.txt", "1320", "145"),
      Arguments.of("y2023/15/input.txt", "507291", "296921")
    );
  }
}
