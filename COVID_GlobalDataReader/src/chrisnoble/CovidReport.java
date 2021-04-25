package chrisnoble;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Chris Noble
 * @version 1.0.0
 *          April 1, 2021
 *
 *          This class has three methods to get api data, parse it, and output to a csv file.
 * 
 *          Method 1: Send an HTTP client request to the api. This method takes one String parameter for a custom URI.
 * 
 *          Method 2: Parses the data into columns, creates records of the data using a String Builder class, and
 *          add the records to a list, then calls printToFile().
 * 
 *          Method 3: Outputs the data to covid_report.csv using PrintWriter.
 */
public class CovidReport
{

	public static final String REPORT_NAME = "covid_report.csv";

	static List<Records> list = new ArrayList<>();

	/**
	 * Method 1
	 * 
	 * Makes a GET request to the api using HTTP Client.
	 * 
	 * @return
	 */
	public static String getData(String uri)
	{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		String data = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(CovidReport::parse)
				.join();

		return data;

	}

	/**
	 * Method 2
	 * 
	 * Parses JSON data and prints a report to a csv file titled "covid_report.csv".
	 * Record objects are created using String Builder.
	 * 
	 * @param responseBody
	 * @return
	 */
	public static String parse(String responseBody)
	{

		JSONArray data = new JSONArray(responseBody);

		String country;
		int confirmed;
		int deaths;
		int recovered;
		int active;
		String date;

		for (int i = 0; i < data.length(); i++)
		{
			JSONObject record = data.getJSONObject(i);

			date = record.getString("Date");
			country = record.getString("Country");
			confirmed = record.getInt("Confirmed");
			deaths = record.getInt("Deaths");
			recovered = record.getInt("Recovered");
			active = record.getInt("Active");

			Records records = new Records.Builder().setDate(date).setCountry(country).setConfirmed(confirmed).setRecovered(recovered)
					.setDeaths(deaths).setActive(active).build();

			list.add(records);

		}

		printToFile();

		return null;
	}

	/**
	 * Method 3
	 * 
	 * Iterates through records list, prints to csv file
	 */
	public static void printToFile()
	{
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(new FileWriter(REPORT_NAME));

			writer.println("Date," + "Country," + "Confirmed Cases," + "Recovered," + "Deaths," + "Active,");

			for (Records r : list)
			{
				writer.write(r.toString());
			}

		} catch (IOException e)
		{
			e.getMessage();
		}

		writer.close();
	}
}
