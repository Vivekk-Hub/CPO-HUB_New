package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOPublishTokenTypeTest {

    @Test
    public void testGetAndSetUid() {
        CPOPublishTokenType tokenType = new CPOPublishTokenType();
        tokenType.setUid("12345");
        assertEquals("12345", tokenType.getUid());
    }

    @Test
    public void testGetAndSetVisualNumber() {
        CPOPublishTokenType tokenType = new CPOPublishTokenType();
        tokenType.setVisual_number("VN12345");
        assertEquals("VN12345", tokenType.getVisual_number());
    }

    @Test
    public void testGetAndSetIssuer() {
        CPOPublishTokenType tokenType = new CPOPublishTokenType();
        tokenType.setIssuer("ExampleIssuer");
        assertEquals("ExampleIssuer", tokenType.getIssuer());
    }

    @Test
    public void testGetAndSetGroupId() {
        CPOPublishTokenType tokenType = new CPOPublishTokenType();
        tokenType.setGroup_id("Group123");
        assertEquals("Group123", tokenType.getGroup_id());
    }

    @Test
    public void testGetAndSetType() {
        CPOPublishTokenType tokenType = new CPOPublishTokenType();
        tokenType.setType("RFID");
        assertEquals("RFID", tokenType.getType());
    }
}
