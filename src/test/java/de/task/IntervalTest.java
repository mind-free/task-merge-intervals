package de.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class IntervalTest {

  @Test
  void basic_getters() {

    Interval interval1 = new Interval(0, 10);

    assertTrue(interval1.getStart() == 0);
    assertTrue(interval1.getEnd() == 10);

  }

  @Test
  void basic_illegal_values() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Interval(100, 0);
    });
  }


  @Test
  void equals_a() {

    Interval i1 = new Interval(0, 10);
    Interval i2 = new Interval(0, 10);

    Interval i3 = new Interval(0, 20);

    Interval i4 = new Interval(50, 60);

    assertTrue(i1.equals(i1));

    assertTrue(i1.equals(i2));

    assertFalse(i1.equals(i3));
    assertFalse(i1.equals(i4));

  }


  @Test
  void equals_b() {

    Interval i1 = new Interval(0, 10);
    Object d = Double.valueOf(1.0);

    assertFalse(i1.equals(d));
    assertFalse(d.equals(i1));
  }



  @Test
  void compare_same() {

    Interval i1 = new Interval(0, 10);
    Interval i2 = new Interval(0, 10);


    assertTrue(i1.compareTo(i1) == 0);
    assertTrue(i1.compareTo(i2) == 0);
    assertTrue(i2.compareTo(i1) == 0);

  }


  @Test
  void compare_not_same() {

    Interval i1 = new Interval(0, 10);
    Interval i2 = new Interval(0, 20);

    Interval i3 = new Interval(20, 30);

    assertTrue(i1.compareTo(i2) == -1);
    assertTrue(i2.compareTo(i1) == 1);


    assertTrue(i1.compareTo(i3) == -1);
    assertTrue(i3.compareTo(i1) == 1);

    assertTrue(i2.compareTo(i3) == -1);
    assertTrue(i3.compareTo(i2) == 1);

  }

}
