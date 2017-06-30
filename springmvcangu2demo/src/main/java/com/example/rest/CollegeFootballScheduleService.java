package com.example.rest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CollegeFootballSchedule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController()
@RequestMapping("/collegefootballscheduleservice")
public class CollegeFootballScheduleService {

	private static final Gson gson = (new GsonBuilder()).create();
	private static final String collegefootball_fpath = "data/collegefootball2015wk1.json";
	private static final Type listType = new TypeToken<List<CollegeFootballSchedule>>() {
	}.getType();

	@RequestMapping(value = "/countschedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody long countSchedule() {

		try {
			final List<CollegeFootballSchedule> result = this.retrieve();
			return result != null ? result.size() : 0;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -1;
	}

	@RequestMapping(value = "/getschedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CollegeFootballSchedule> getSchedule(
			@RequestParam(name = "startItemIndex", defaultValue = "0") int startItemIndex,
			@RequestParam(name = "endItemIndex", defaultValue = "25") int endItemIndex) {

		try {
			final List<CollegeFootballSchedule> result = this.retrieve();

			if (result == null || result.size() == 0 || startItemIndex < 0 || endItemIndex > result.size()
					|| startItemIndex > endItemIndex) {
				return Collections.EMPTY_LIST;
			}

			final List<CollegeFootballSchedule> pagedList = result.subList(startItemIndex, endItemIndex);
			return pagedList;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.EMPTY_LIST;
	}

	class Group<T> {
		private String groupId;
		private String keyGroup = "";
		private String valueGroup = "";
		private List<T> items = new ArrayList<T>();

		public Group() {
			super();
		}

		public Group(String keyGroup, String valueGroup) {
			super();
			this.keyGroup = keyGroup;
			this.valueGroup = valueGroup;
		}

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

		public String getKeyGroup() {
			return keyGroup;
		}

		public void setKeyGroup(String keyGroup) {
			this.keyGroup = keyGroup;
		}

		public String getValueGroup() {
			return valueGroup;
		}

		public void setValueGroup(String valueGroup) {
			this.valueGroup = valueGroup;
		}

		public List<T> getItems() {
			return items;
		}

		public void setItems(List<T> items) {
			this.items = items;
		}

		public void addItem(T item) {
			this.items.add(item);
		}

	}

	@RequestMapping(value = "/getscheduleandgroupup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Group<CollegeFootballSchedule>> getScheduleAndGroupUp() {

		try {
			final List<CollegeFootballSchedule> result = this.retrieve();

			final Map<String, Group<CollegeFootballSchedule>> mapGroups = new HashMap<String, Group<CollegeFootballSchedule>>();

			for (CollegeFootballSchedule collegeFootballSchedule : result) {
				final String mKey = collegeFootballSchedule.getDte();
				if (!mapGroups.containsKey(mKey)) {
					mapGroups.put(mKey, new Group<CollegeFootballSchedule>(mKey, ""));
				}

				final Group<CollegeFootballSchedule> group = mapGroups.get(mKey);
				group.addItem(collegeFootballSchedule);
			}
		
			final Collection<Group<CollegeFootballSchedule>> groups = mapGroups.values();
			for (Group<CollegeFootballSchedule> group : groups) {
				group.setGroupId(String.valueOf(group.hashCode()));
				group.setValueGroup(String.valueOf(group.getItems() != null ? group.getItems().size() : 0));
			}
			
			return groups;
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.EMPTY_LIST;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private synchronized List<CollegeFootballSchedule> retrieve() throws IOException {
		InputStream pa = getClass().getClassLoader().getResourceAsStream(collegefootball_fpath);
		final String json = IOUtils.toString(pa, Charset.defaultCharset()).replaceAll("\\r\\n", "");

		List<CollegeFootballSchedule> result = gson.fromJson(json, listType);

		return result;
	}
}
