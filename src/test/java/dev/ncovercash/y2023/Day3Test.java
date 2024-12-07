package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day3Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day3();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/3/example.txt", "4361", "467835"),
      Arguments.of("y2023/3/input.txt", "539433", "75847567")
    );
  }
}
