package dev.ncovercash.y2024;

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
      Arguments.of("y2024/6/example.txt", "41", "6"),
      Arguments.of("y2024/6/input.txt", "5564", "1976")
    );
  }
}
