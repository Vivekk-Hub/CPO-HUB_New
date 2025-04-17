package com.evgateway.cpohubserver.common;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VersionNumberTest {

    @Test
    void testVersionNumbers() {
        // Testing the version number constants
        assertEquals("2.1.1", VersionNumber.v2_1_1, "Version v2_1_1 should be '2.1.1'");
        assertEquals("2.2", VersionNumber.v2_2, "Version v2_2 should be '2.2'");
        assertEquals("2.2.1", VersionNumber.v2_2_1, "Version v2_2_1 should be '2.2.1'");
    }
}
