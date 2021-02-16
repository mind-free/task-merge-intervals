package de.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalUtils {


  public final static Comparator<Interval> naturalOrder = (o1, o2) -> {

    if (o1.getStart() < o2.getStart()) {
      return -1;
    }

    if (o1.getStart() > o2.getStart()) {
      return 1;
    }


    if (o1.getEnd() < o2.getEnd()) {
      return -1;
    }

    if (o1.getEnd() > o2.getEnd()) {
      return 1;
    }


    return 0;
  };


  /*
   * 
   */
  public final static Comparator<Interval> mergeOrder = (o1, o2) -> {

    if (o1.getStart() < o2.getStart()) {
      return -1;
    }

    if (o1.getStart() > o2.getStart()) {
      return 1;
    }

    if (o1.getEnd() < o2.getEnd()) {
      return 1;
    }

    if (o1.getEnd() > o2.getEnd()) {
      return -1;
    }

    return 0;
  };

  /**
   * returns a list of merged intervals that had overlap.
   * The idea is to first<br>
   * sort all intervals and then <br>
   * scan the list from left to right checking if we need to <br>
   * case A: open a new interval or
   * case B: extend the end of the already discovered interval <br>
   * <br>
   * special cases: <br>
   * null / empty list-> empty list <br>
   * size = 1 -> return directly<br>
   * <br>
   * the original list is not mutated.
   * <br>
   *
   * time: O(n log(n))
   * space: O(n)
   * 
   * @param intervals
   * @return list of merged intervals
   */
  public static List<Interval> merged(List<Interval> intervals) {

    if ((intervals == null) || intervals.isEmpty()) {
      return new ArrayList<>();
    }

    intervals = new ArrayList<>(intervals);

    if (intervals.size() <= 1) {
      return intervals;
    }

    intervals.sort(mergeOrder);

    List<Interval> mergedIntervals = new ArrayList<>();

    Interval mergeInterval = intervals.get(0);
    for (int i = 1; i < intervals.size(); ++i) {

      Interval interval = intervals.get(i);

      // case A: save previous merge interval and create new merge interval
      if (mergeInterval.getEnd() < interval.getStart()) {

        mergedIntervals.add(mergeInterval);

        mergeInterval = interval;


      } else if (mergeInterval.getEnd() < interval.getEnd()) {
        // case B: extend merge interval
        // mergeInterval:    |________|
        // interval:               |________|     
        mergeInterval = new Interval(mergeInterval.getStart(), interval.getEnd());
      }


    }
    mergedIntervals.add(mergeInterval);

    return mergedIntervals;
  }

}
