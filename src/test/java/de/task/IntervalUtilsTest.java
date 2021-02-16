package de.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntervalUtilsTest {

  private void testOrder(Interval i1, Interval i2, Interval i3, Interval i4, Comparator<Interval> comparator) {

    List<Interval> intervalsE = Arrays.asList(i1, i2, i3, i4);
    List<Interval> intervalsZ = Arrays.asList(i4, i2, i3, i1);


    List<Interval> intervalsA;

    intervalsA = Arrays.asList(i1, i2, i3, i4);

    intervalsA.sort(comparator);


    assertTrue(intervalsA.equals(intervalsE));
    assertFalse(intervalsA.equals(intervalsZ));


    intervalsA = Arrays.asList(i1, i3, i2, i4);
    intervalsA.sort(comparator);
    assertTrue(intervalsA.equals(intervalsE));

    intervalsA = Arrays.asList(i4, i3, i2, i1);
    intervalsA.sort(comparator);
    assertTrue(intervalsA.equals(intervalsE));


    intervalsA = Arrays.asList(i4, i4, i4, i1);
    intervalsA.sort(comparator);
    assertTrue(intervalsA.equals(Arrays.asList(i1, i4, i4, i4)));

  }

  @Test
  void comparator_natural_order() {

    Interval i1 = new Interval(0, 10);
    Interval i2 = new Interval(10, 20);
    Interval i3 = new Interval(15, 25);
    Interval i4 = new Interval(25, 30);

    testOrder(i1, i2, i3, i4, IntervalUtils.naturalOrder);
  }


  @Test
  void comparator_merge_order() {

    Interval i1 = new Interval(0, 20);
    Interval i2 = new Interval(0, 10);
    Interval i3 = new Interval(10, 20);
    Interval i4 = new Interval(10, 10);

    testOrder(i1, i2, i3, i4, IntervalUtils.mergeOrder);
  }


  @Test
  void merged_some_overlaps_a() {

    Interval i1 = new Interval(0, 20);
    Interval i2 = new Interval(0, 10);
    Interval i3 = new Interval(10, 20);
    Interval i4 = new Interval(10, 10);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(new Interval(0, 20));
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_witout_overlaps() {

    Interval i1 = new Interval(6, 7);
    Interval i2 = new Interval(4, 5);
    Interval i3 = new Interval(2, 3);
    Interval i4 = new Interval(0, 1);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(i4, i3, i2, i1);
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_complete_overlap() {

    Interval i1 = new Interval(6, 7);
    Interval i2 = new Interval(4, 5);
    Interval i3 = new Interval(2, 3);
    Interval i4 = new Interval(0, 10);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(new Interval(0, 10));
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_some_overlaps_b() {

    Interval i1 = new Interval(6, 7);
    Interval i2 = new Interval(4, 5);
    Interval i3 = new Interval(2, 3);
    Interval i4 = new Interval(0, 4);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(new Interval(0, 5), new Interval(6, 7));
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_duplicate_intervals() {

    Interval i1 = new Interval(0, 1);
    Interval i2 = new Interval(0, 1);
    Interval i3 = new Interval(0, 1);
    Interval i4 = new Interval(0, 1);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(new Interval(0, 1));
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_null_input() {

    List<Interval> intervals = null;

    List<Interval> mergedIntervalsExpected = Arrays.asList();
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_empty_input() {

    List<Interval> intervals = new ArrayList<>();

    List<Interval> mergedIntervalsExpected = Arrays.asList();
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_size_one_input() {

    List<Interval> intervals = Arrays.asList(new Interval(0, 0));

    List<Interval> mergedIntervalsExpected = Arrays.asList(new Interval(0, 0));
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }


  @Test
  void merged_original_sample_input() {

    Interval i1 = new Interval(25, 30);
    Interval i2 = new Interval(2, 19);
    Interval i3 = new Interval(14, 23);
    Interval i4 = new Interval(4, 8);


    Interval iE1 = new Interval(2, 23);
    Interval iE2 = new Interval(25, 30);

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4);

    List<Interval> mergedIntervalsExpected = Arrays.asList(iE1, iE2);
    List<Interval> mergedIntervals = IntervalUtils.merged(intervals);

    assertTrue(mergedIntervals.equals(mergedIntervalsExpected));

  }
}
