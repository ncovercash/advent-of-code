package dev.ncovercash.y2023;

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
      Arguments.of("y2023/2/example.txt", "8", "2286"),
      Arguments.of("y2023/2/input.txt", "2317", "74804")
    );
  }
}
