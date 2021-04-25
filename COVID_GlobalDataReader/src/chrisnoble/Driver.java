package chrisnoble;

import java.time.LocalDate;

/**
 * @author Chris Noble
 * @version 1.0.0
 *          April 1, 2021
 *
 *          This class has a main method with three parameter conditions:
 * 
 *          1. If date is entered, print countries between specified date parameters. If no countries are entered, print default
 *          countries Canada and South Africa between specified date parameters.
 * 
 *          2. If countries are entered and no date is entered, print data from those countries from the last week.
 * 
 *          3. If no dates or countries are entered, print default countries Canada and South Africa from the last week.
 */
public class Driver
{

	public static final int MAX_COUNTRIES = 5;
	public static final int MAX_ARGS = 9;
	public static final int ONE_WEEK = 7;
	public static final int DATE_LENGTH = 4;

	public static void main(String[] args)
	{
		String URI = null;
		LocalDate oneWeekAgo = LocalDate.now().minusDays(ONE_WEEK);
		String startDate = null;
		String endDate = null;

		String[] URIDefaults =
		{ "south-africa", "panama" };

		if (args.length > 0)
		{
			for (String s : args)
			{
				if (s.equalsIgnoreCase("from")) // condition #1
				{
					startDate = args[1];
					endDate = args[3];

					if (args.length == DATE_LENGTH)
					{

						URI = "https://api.covid19api.com/country/canada?from=" + startDate + "T00:00:00Z&to=" + endDate + "T00:00:00Z";
						CovidReport.getData(URI);
						URI = "https://api.covid19api.com/country/south-africa?from=" + startDate + "T00:00:00Z&to=" + endDate + "T00:00:00Z";
						CovidReport.getData(URI);

					}
					else if (args.length > DATE_LENGTH && args.length < MAX_ARGS)
					{
						int i = 3;

						s = args[++i];

						URI = "https://api.covid19api.com/country/" + s + "?from=" + startDate + "T00:00:00Z&to=" + endDate + "T00:00:00Z";
						CovidReport.getData(URI);

					}
				}

				else if (!s.equalsIgnoreCase("from") && args.length < MAX_COUNTRIES) // condition #2
				{
					URI = "https://api.covid19api.com/live/country/" + s + "/status/confirmed/date/" + oneWeekAgo.toString();
					CovidReport.getData(URI);
				}
			}
		}
		else
		{
			for (String s : URIDefaults) // condition #3
			{
				URI = "https://api.covid19api.com/live/country/" + s + "/status/confirmed/date/" + oneWeekAgo.toString();

				CovidReport.getData(URI);
			}
		}
	}

}
