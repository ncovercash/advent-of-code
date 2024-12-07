package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day12Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day12();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/12/example.txt", "21", "525152"),
      Arguments.of("y2023/12/input.txt", "8419", "160500973317706")
    );
  }
}
