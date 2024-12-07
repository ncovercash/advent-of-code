package dev.ncovercash.y2023;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day20Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day20();
  }

  static List<Arguments> testCases() {
    return List.of(
      Arguments.of("y2023/20/example1.txt", "32000000", "skip"),
      Arguments.of("y2023/20/example2.txt", "11687500", "skip"),
      Arguments.of("y2023/20/input.txt", "818649769", "246313604784977")
    );
  }
}
