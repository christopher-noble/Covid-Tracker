package chrisnoble;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Chris Noble
 * @version 1.0.0
 *          April 1, 2021
 * 
 *          This class implements String Builder to build Record objects.
 *
 */
public class Records
{
	protected LocalDate date;
	private String country;
	private int confirmed;
	private int deaths;
	private int recovered;
	private int active;

	public static class Builder
	{
		private LocalDate date;
		private String country;
		private int confirmed;
		private int deaths;
		private int recovered;
		private int active;

		public Builder()
		{

		}

		public Builder setDate(String date)
		{
			this.date = LocalDate.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

			return this;
		}

		public Builder setCountry(String country)
		{
			this.country = country;
			return this;
		}

		public Builder setConfirmed(int confirmed)
		{
			this.confirmed = confirmed;
			return this;
		}

		public Builder setDeaths(int deaths)
		{
			this.deaths = deaths;
			return this;
		}

		public Builder setRecovered(int recovered)
		{
			this.recovered = recovered;
			return this;
		}

		public Builder setActive(int active)
		{
			this.active = active;
			return this;
		}

		public Records build()
		{
			return new Records(this);
		}
	}

	public Records(Builder b)
	{
		date = b.date;
		country = b.country;
		confirmed = b.confirmed;
		recovered = b.recovered;
		deaths = b.deaths;
		active = b.active;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public int getConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(int confirmed)
	{
		this.confirmed = confirmed;
	}

	public int getDeaths()
	{
		return deaths;
	}

	public void setDeaths(int deaths)
	{
		this.deaths = deaths;
	}

	public int getRecovered()
	{
		return recovered;
	}

	public void setRecovered(int recovered)
	{
		this.recovered = recovered;
	}

	public int getActive()
	{
		return active;
	}

	public void setActive(int active)
	{
		this.active = active;
	}

	@Override
	public String toString()
	{
		return date + "," + country + "," + confirmed + "," + deaths + "," + recovered + "," + active + ",\n";
	}

}
