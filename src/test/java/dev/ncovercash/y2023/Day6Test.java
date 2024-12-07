package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day6Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day6();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/6/example.txt", "288", "71503"),
      Arguments.of("y2023/6/input.txt", "2269432", "35865985")
    );
  }
}
