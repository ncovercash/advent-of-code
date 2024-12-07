package dev.ncovercash.y2023;

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
      Arguments.of("y2023/14/example.txt", "136", "64"),
      Arguments.of("y2023/14/input.txt", "105249", "88680")
    );
  }
}
