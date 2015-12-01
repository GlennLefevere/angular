/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author infglef
 *
 */
@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date end;
	private Boolean allDay = false;

	private String color;

	public Event(String title, Integer dayOfWeek, Integer startHourOfDay, Integer endHourOfDay) {
		this.title = title;

		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		startCal.set(Calendar.HOUR_OF_DAY, startHourOfDay);
		start = startCal.getTime();

		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		endCal.set(Calendar.HOUR_OF_DAY, startHourOfDay);
		end = endCal.getTime();

		color = "green";
	}

	public Event() {

	}

	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @return the allDay
	 */
	public Boolean getAllDay() {
		return allDay;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
}