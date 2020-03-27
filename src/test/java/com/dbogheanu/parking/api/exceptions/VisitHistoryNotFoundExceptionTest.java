package com.dbogheanu.parking.api.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VisitHistoryNotFoundExceptionTest {

  @Test
  void test() {
    VisitHistoryNotFoundException exception = new VisitHistoryNotFoundException("test message");

    assertEquals("test message", exception.getMessage());
  }
}
