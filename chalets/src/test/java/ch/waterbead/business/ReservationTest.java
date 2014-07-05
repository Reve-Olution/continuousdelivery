package ch.waterbead.business;

import java.text.DateFormatSymbols;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class ReservationTest {
	@Test
	public void getWeekdaysShouldReturnDaysOfWeek() {
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.FRENCH);
		String[] weekdays = dfs.getWeekdays();
		Assert.assertNotNull(weekdays);
	}
}
