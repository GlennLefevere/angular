/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.provikmo.model.Event;
import be.provikmo.service.EventRepo;

/**
 * @author infglef
 *
 */
@Controller
public class EventController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/calendar")
	public String calendar() {
		return "calendar";
	}

	@Autowired
	private EventRepo repo;

	@RequestMapping("/greeting")
	public @ResponseBody List<Event> greeting(@Param("start") String start, @Param("end") String end) {
		Date startDate = new Date(Long.parseLong(start));
		Date endDate = new Date(Long.parseLong(end));
		System.out.println("request made " + startDate + " " + endDate);

		return repo.findAll();
	}

	@RequestMapping(value = "/storeevents", method = RequestMethod.POST)
	public ResponseEntity<Event> update(@RequestBody Event event) {
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping("/daysoff")
	public @ResponseBody List<Date> dates() {
		List<Date> dates = new ArrayList<>();

		for (int i = 1; i <= 30; i++) {
			if (i % 2 != 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, i);
				dates.add(calendar.getTime());
			}
		}

		return dates;
	}

}
