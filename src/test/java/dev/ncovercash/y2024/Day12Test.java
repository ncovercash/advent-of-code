package dev.ncovercash.y2024;

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
      Arguments.of("y2024/12/example1.txt", "140", "80"),
      Arguments.of("y2024/12/example2.txt", "772", "436"),
      Arguments.of("y2024/12/example3.txt", "1930", "1206"),
      Arguments.of("y2024/12/input.txt", "1473620", "")
    );
  }
}
