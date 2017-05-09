package com.biscuit.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class DateCompleter {

	public static List<String> months = new ArrayList<String>(
			Arrays.asList("jan", "feb", "march", "april", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"));
	private static List<String> days31 = new ArrayList<String>();
	private static String[] years = new String[5];


	public static List<Completer> getDateCompleter() {
		List<Completer> completers = new ArrayList<Completer>();

		Calendar cal = new GregorianCalendar();
		int startingYear = cal.get(Calendar.YEAR) - 2;

		for (int i = 0; i < 31; i++) {
			days31.add(String.valueOf(i + 1));
		}

		for (int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(startingYear + i);
		}

		for (int i = 0; i < months.size(); i++) {
			// for Feb 28 days
			if (i == 1) {
				completers.add(new ArgumentCompleter(new StringsCompleter(months.get(i)),
						new StringsCompleter(days31.subList(0, 28)), new StringsCompleter(years), new NullCompleter()));
			}
			// for april, jun, sept, and nov
			else if (i == 4 || i == 6 || i == 9 || i == 11) {
				completers.add(new ArgumentCompleter(new StringsCompleter(months.get(i)),
						new StringsCompleter(days31.subList(0, 29)), new StringsCompleter(years), new NullCompleter()));
			}

			else {
				completers.add(new ArgumentCompleter(new StringsCompleter(months.get(i)), new StringsCompleter(days31),
						new StringsCompleter(years), new NullCompleter()));
			}
		}

		return completers;
	}
}
