package com.avanade.demo.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SegmentTests {

    @Test
    void segmentConstructor_setsFieldsCorrectly() {
        Segment segment = new Segment(1L, "Retail");

        assertEquals(1L, segment.getId());
        assertEquals("Retail", segment.getName());
    }

    @Test
    void setName_updatesName() {
        Segment segment = new Segment();
        segment.setName("Corporate");

        assertEquals("Corporate", segment.getName());
    }

    @Test
    void getId_returnsNull_whenIdNotSet() {
        Segment segment = new Segment();
        assertNull(segment.getId());
    }

    @Test
    void getName_returnsNull_whenNameNotSet() {
        Segment segment = new Segment();
        assertNull(segment.getName());
    }
}