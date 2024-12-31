package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day17Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day17();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2024/17/example.txt", "4,6,3,5,6,3,5,2,1,0", "skip"),
      Arguments.of("y2024/17/input.txt", "1,5,0,3,7,3,0,3,1", "105981155568026")
    );
  }
}
