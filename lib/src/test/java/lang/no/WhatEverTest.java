package lang.no;

import lang.no.concrete.VariableDef;
import org.junit.Test;
import static org.junit.Assert.*;
//import lang.no.parser;

public class WhatEverTest {
    @Test public void testAppHasAGreeting() {
        assertEquals(1, 1);
    }
    @Test public void testVariableDef() {
        assertEquals(new VariableDef(""), new VariableDef(""));
    }
}
