package org.opentripplanner.framework.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListUtils {

  /**
   * Combine a number of collections into a single list.
   */
  @SafeVarargs
  public static <T> List<T> combine(Collection<T>... lists) {
    return Arrays.stream(lists).flatMap(Collection::stream).toList();
  }

  public static <T> T first(List<T> list) {
    return list.get(0);
  }

  public static <T> T last(List<T> list) {
    return list.get(list.size() - 1);
  }
}
