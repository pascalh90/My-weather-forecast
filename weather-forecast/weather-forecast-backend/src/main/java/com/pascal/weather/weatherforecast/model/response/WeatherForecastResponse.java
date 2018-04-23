package com.pascal.weather.weatherforecast.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastResponse {

	@JsonProperty("Headline")
	private Headline headline;

	@JsonProperty("DailyForecasts")
	private List<DailyForecast> dailyForecasts;

	public Headline getHeadline() {
		return headline;
	}

	public void setHeadline(Headline headline) {
		this.headline = headline;
	}

	public List<DailyForecast> getDailyForecasts() {
		return dailyForecasts;
	}

	public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
		this.dailyForecasts = dailyForecasts;
	}

	public static class Headline {

		@JsonProperty("EffectiveDate")
		private String effectiveDate;
		@JsonProperty("EffectiveEpochDate")
		private Integer effectiveEpochDate;
		@JsonProperty("Severity")
		private Integer severity;
		@JsonProperty("Text")
		private String text;
		@JsonProperty("Category")
		private String category;
		@JsonProperty("EndDate")
		private String endDate;
		@JsonProperty("EndEpochDate")
		private Integer endEpochDate;
		@JsonProperty("MobileLink")
		private String mobileLink;
		@JsonProperty("Link")
		private String link;

		public String getEffectiveDate() {
			return effectiveDate;
		}

		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public Integer getEffectiveEpochDate() {
			return effectiveEpochDate;
		}

		public void setEffectiveEpochDate(Integer effectiveEpochDate) {
			this.effectiveEpochDate = effectiveEpochDate;
		}

		public Integer getSeverity() {
			return severity;
		}

		public void setSeverity(Integer severity) {
			this.severity = severity;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public Integer getEndEpochDate() {
			return endEpochDate;
		}

		public void setEndEpochDate(Integer endEpochDate) {
			this.endEpochDate = endEpochDate;
		}

		public String getMobileLink() {
			return mobileLink;
		}

		public void setMobileLink(String mobileLink) {
			this.mobileLink = mobileLink;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DailyForecast {

		@JsonProperty("Date")
		private String date;
		@JsonProperty("EpochDate")
		private Integer epochDate;
		@JsonProperty("Temperature")
		private Temperature temperature;
		@JsonProperty("Day")
		private Day day;
		@JsonProperty("Night")
		private Night night;
		@JsonProperty("Sources")
		private List<String> sources = null;
		@JsonProperty("MobileLink")
		private String mobileLink;
		@JsonProperty("Link")
		private String link;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public Integer getEpochDate() {
			return epochDate;
		}

		public void setEpochDate(Integer epochDate) {
			this.epochDate = epochDate;
		}

		public Temperature getTemperature() {
			return temperature;
		}

		public void setTemperature(Temperature temperature) {
			this.temperature = temperature;
		}

		public Day getDay() {
			return day;
		}

		public void setDay(Day day) {
			this.day = day;
		}

		public Night getNight() {
			return night;
		}

		public void setNight(Night night) {
			this.night = night;
		}

		public List<String> getSources() {
			return sources;
		}

		public void setSources(List<String> sources) {
			this.sources = sources;
		}

		public String getMobileLink() {
			return mobileLink;
		}

		public void setMobileLink(String mobileLink) {
			this.mobileLink = mobileLink;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Night {
		@JsonProperty("Icon")
		private Integer icon;
		@JsonProperty("IconPhrase")
		private String iconPhrase;

		public Integer getIcon() {
			return icon;
		}

		public void setIcon(Integer icon) {
			this.icon = icon;
		}

		public String getIconPhrase() {
			return iconPhrase;
		}

		public void setIconPhrase(String iconPhrase) {
			this.iconPhrase = iconPhrase;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Day {
		@JsonProperty("Icon")
		private Integer icon;
		@JsonProperty("IconPhrase")
		private String iconPhrase;

		public Integer getIcon() {
			return icon;
		}

		public void setIcon(Integer icon) {
			this.icon = icon;
		}

		public String getIconPhrase() {
			return iconPhrase;
		}

		public void setIconPhrase(String iconPhrase) {
			this.iconPhrase = iconPhrase;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Temperature {

		@JsonProperty("Minimum")
		private Minimum minimum;
		@JsonProperty("Maximum")
		private Maximum maximum;

		public Minimum getMinimum() {
			return minimum;
		}

		public void setMinimum(Minimum minimum) {
			this.minimum = minimum;
		}

		public Maximum getMaximum() {
			return maximum;
		}

		public void setMaximum(Maximum maximum) {
			this.maximum = maximum;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Minimum {

		@JsonProperty("Value")
		private Integer value;
		@JsonProperty("Unit")
		private String unit;
		@JsonProperty("UnitType")
		private Integer unitType;

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public Integer getUnitType() {
			return unitType;
		}

		public void setUnitType(Integer unitType) {
			this.unitType = unitType;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Maximum {

		@JsonProperty("Value")
		private Double value;
		@JsonProperty("Unit")
		private String unit;
		@JsonProperty("UnitType")
		private Integer unitType;

		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public Integer getUnitType() {
			return unitType;
		}

		public void setUnitType(Integer unitType) {
			this.unitType = unitType;
		}

	}
}
