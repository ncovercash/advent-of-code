package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day8Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day8();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/8/example.txt", "14", "34"),
      Arguments.of("y2024/8/input.txt", "351", "1259")
    );
  }
}
