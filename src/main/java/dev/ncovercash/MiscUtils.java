package dev.ncovercash;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;

@UtilityClass
public class MiscUtils {

  public static int manhattanDistance(
    Pair<Integer, Integer> p1,
    Pair<Integer, Integer> p2
  ) {
    return (
      Math.abs(p1.getLeft() - p2.getLeft()) +
      Math.abs(p1.getRight() - p2.getRight())
    );
  }

  // for use with Stream::filter to simulate Stream::distinct
  public static <T> Predicate<T> distinctByKey(
    Function<? super T, ?> keyExtractor
  ) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }
}
