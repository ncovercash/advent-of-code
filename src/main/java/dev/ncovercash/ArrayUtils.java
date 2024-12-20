package dev.ncovercash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtils {

  public static List<Character> stringToCharacterList(String in) {
    List<Character> result = new ArrayList<>();
    for (char c : in.toCharArray()) {
      result.add(c);
    }
    return result;
  }

  public static <T> List<T> getColumn(Collection<List<T>> list, int col) {
    return list.stream().map(row -> row.get(col)).toList();
  }

  public static <T> int countOccurrences(Collection<T> haystack, T needle) {
    return (int) haystack.stream().filter(needle::equals).count();
  }

  public static <T> int countOccurrencesCached(
    Map<T, Integer> cache,
    Collection<T> haystack,
    T needle
  ) {
    return cache.computeIfAbsent(needle, k -> countOccurrences(haystack, k));
  }

  public static List<Integer> toIntegers(List<String> strings) {
    return strings.stream().map(Integer::parseInt).toList();
  }

  public static List<List<Integer>> to2DIntegers(List<List<String>> strings) {
    return strings.stream().map(ArrayUtils::toIntegers).toList();
  }

  public static List<String> transposeText(List<String> input) {
    return IntStream
      .range(0, input.get(0).length())
      .mapToObj(i ->
        String.valueOf(
          input.stream().map(row -> row.charAt(i)).toArray(Character[]::new)
        )
      )
      .toList();
  }

  public static <T> T getOrDefault(
    List<List<T>> list,
    int r,
    int c,
    T defaultValue
  ) {
    if (r < 0 || r >= list.size() || c < 0 || c >= list.get(r).size()) {
      return defaultValue;
    }
    return list.get(r).get(c);
  }
}
