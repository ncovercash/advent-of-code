package dev.ncovercash.y2024;

import dev.ncovercash.DayTest;
import dev.ncovercash.Solution;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

class Day18Test extends DayTest {

  @Override
  public Solution getInstance() {
    return new Day18();
  }

  static List<Arguments> testCases() {
    return List.of(
      // map size (upper bound, exclusive)\n
      // number of bytes to fall in p1\n
      Arguments.of("y2024/18/example.txt", "22", "6,1"),
      Arguments.of("y2024/18/input.txt", "262", "22,20")
    );
  }
}
