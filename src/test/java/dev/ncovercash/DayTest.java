package dev.ncovercash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Log4j2
public abstract class DayTest {

  public abstract Solution getInstance();

  // inheriting classes must declare a `static List<Arguments> testCases()`

  @ParameterizedTest
  @MethodSource("testCases")
  void testAnswer(String filename, String expectedPart1, String expectedPart2) {
    Solution instance = getInstance();

    if (!"skip".equals(expectedPart1)) {
      int start = (int) System.currentTimeMillis();

      String actual = instance.completeChallengePartOne(filename);
      log.info(
        "{} Part 1: {} in {}ms",
        instance.getClass().getSimpleName(),
        actual,
        (int) System.currentTimeMillis() - start
      );

      assertEquals(expectedPart1, actual);
    }

    if (!"skip".equals(expectedPart2)) {
      int start = (int) System.currentTimeMillis();

      String actual = instance.completeChallengePartTwo(filename);
      log.info(
        "{} Part 2: {} in {}ms",
        instance.getClass().getSimpleName(),
        actual,
        (int) System.currentTimeMillis() - start
      );

      assertEquals(expectedPart2, actual);
    }
  }
}
