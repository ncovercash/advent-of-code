package dev.ncovercash;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
// regex unicode character nonsense
@SuppressWarnings("java:S5867")
public class CommonConstants {

  public static final Pattern WHITESPACE_REGEX = Pattern.compile("\\s+");
}
