// package com.evgateway.cpohubserver.form;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// class CPOHoursTest {

// 	@Test
// 	public void testIsTwentyFourSeven() {
// 		CPOHours hours = new CPOHours();
// 		hours.setTwentyfourseven(true);
// 		assertTrue(hours.isTwentyfourseven());
// 	}

// 	@Test
// 	public void testSetTwentyFourSeven() {
// 		CPOHours hours = new CPOHours();
// 		hours.setTwentyfourseven(false);
// 		assertEquals(false, hours.isTwentyfourseven());
// 	}

// 	@Test
// 	public void testGetRegularHours() {
// 		CPOHours hours = new CPOHours();
// 		CPORegularHours regularHour1 = new CPORegularHours();
// 		CPORegularHours regularHour2 = new CPORegularHours();
// 		List<CPORegularHours> regularHours = Arrays.asList(regularHour1, regularHour2);
// 		hours.setRegular_hours(regularHours);
// 		assertEquals(regularHours, hours.getRegular_hours());
// 	}

// 	@Test
// 	public void testSetRegularHours() {
// 		CPOHours hours = new CPOHours();
// 		CPORegularHours regularHour1 = new CPORegularHours();
// 		CPORegularHours regularHour2 = new CPORegularHours();
// 		List<CPORegularHours> regularHours = Arrays.asList(regularHour1, regularHour2);
// 		hours.setRegular_hours(regularHours);
// 		assertEquals(regularHours, hours.getRegular_hours());
// 	}

// 	@Test
// 	public void testGetExceptionalOpenings() {
// 		CPOHours hours = new CPOHours();
// 		CPOExceptionalPeriod opening1 = new CPOExceptionalPeriod();
// 		CPOExceptionalPeriod opening2 = new CPOExceptionalPeriod();
// 		List<CPOExceptionalPeriod> exceptionalOpenings = Arrays.asList(opening1, opening2);
// 		hours.setExceptional_openings(exceptionalOpenings);
// 		assertEquals(exceptionalOpenings, hours.getExceptional_openings());
// 	}

// 	@Test
// 	public void testSetExceptionalOpenings() {
// 		CPOHours hours = new CPOHours();
// 		CPOExceptionalPeriod opening1 = new CPOExceptionalPeriod();
// 		CPOExceptionalPeriod opening2 = new CPOExceptionalPeriod();
// 		List<CPOExceptionalPeriod> exceptionalOpenings = Arrays.asList(opening1, opening2);
// 		hours.setExceptional_openings(exceptionalOpenings);
// 		assertEquals(exceptionalOpenings, hours.getExceptional_openings());
// 	}

// 	@Test
// 	public void testGetExceptionalClosings() {
// 		CPOHours hours = new CPOHours();
// 		CPOExceptionalPeriod closing1 = new CPOExceptionalPeriod();
// 		CPOExceptionalPeriod closing2 = new CPOExceptionalPeriod();
// 		List<CPOExceptionalPeriod> exceptionalClosings = Arrays.asList(closing1, closing2);
// 		hours.setExceptional_closings(exceptionalClosings);
// 		assertEquals(exceptionalClosings, hours.getExceptional_closings());
// 	}

// 	@Test
// 	public void testSetExceptionalClosings() {
// 		CPOHours hours = new CPOHours();
// 		CPOExceptionalPeriod closing1 = new CPOExceptionalPeriod();
// 		CPOExceptionalPeriod closing2 = new CPOExceptionalPeriod();
// 		List<CPOExceptionalPeriod> exceptionalClosings = Arrays.asList(closing1, closing2);
// 		hours.setExceptional_closings(exceptionalClosings);
// 		assertEquals(exceptionalClosings, hours.getExceptional_closings());
// 	}

// 	@Test
// 	void testToString() {
// 		CPOHours hours = new CPOHours();
// 		hours.setTwentyfourseven(true);
// 		hours.setRegular_hours(Arrays.asList(new CPORegularHours()));
// 		hours.setExceptional_openings(Arrays.asList(new CPOExceptionalPeriod()));
// 		hours.setExceptional_closings(Arrays.asList(new CPOExceptionalPeriod()));

// 		String expectedString = "Hours [twentyfourseven=true, regular_hours=[CPORegularHours@<hashcode>], exceptional_openings=[CPOExceptionalPeriod@<hashcode>], exceptional_closings=[CPOExceptionalPeriod@<hashcode>]]";
// 		assertTrue(hours.toString().contains("twentyfourseven=true"),
// 				"The toString method should include twentyfourseven=true");
// 		assertTrue(hours.toString().contains("regular_hours="), "The toString method should include regular_hours");
// 		assertTrue(hours.toString().contains("exceptional_openings="),
// 				"The toString method should include exceptional_openings");
// 		assertTrue(hours.toString().contains("exceptional_closings="),
// 				"The toString method should include exceptional_closings");
// 	}

// }
