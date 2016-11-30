import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Salary {
	private Map<Integer, List<EventType>> events = new TreeMap<>();

	public int howMuch(String[] arrival, String[] departure, int wage) {
		addSystemEvents();
		addPersonEvents(arrival, departure);
		return getSum(wage);
	}

	private void addSystemEvents() {
		addEvent(toSeconds(0, 0, 0), EventType.IncreaseCoef);
		addEvent(toSeconds(6, 0, 0), EventType.RestoreCoef);
		addEvent(toSeconds(18, 0, 0), EventType.IncreaseCoef);
	}

	private void addPersonEvents(String[] arrival, String[] departure) {
		for (int i = 0; i < arrival.length; ++i)
			addPersonEvent(arrival[i], departure[i]);
	}

	private void addPersonEvent(String arrival, String departure) {
		addEvent(getSeconds(arrival), EventType.StartWork);
		addEvent(getSeconds(departure), EventType.EndWork);
	}

	private void addEvent(int timeInSeconds, EventType event) {
		List<EventType> list = events.get(timeInSeconds);
		if (list == null) {
			list = new ArrayList<>();
			events.put(timeInSeconds, list);
		}
		list.add(event);
	}

	private int getSeconds(String string) {
		String[] parts = string.split(":");
		int hour = parseInt(parts[0]);
		int minute = parseInt(parts[1]);
		int second = parseInt(parts[2]);
		return toSeconds(hour, minute, second);
	}

	private int toSeconds(int hour, int minute, int second) {
		return second + minute * 60 + hour * 60 * 60;
	}

	private int getSum(int wage) {
		int secondsTimesTwo = new SecondsCounter().getNumberOfSecondsTimesTwo();
		return (int) (1L * secondsTimesTwo * wage / 60 / 60 / 2);
	}

	private class SecondsCounter {
		private int seconds;
		private int coefficient = 2;
		private Integer workStart;

		private int getNumberOfSecondsTimesTwo() {
			for (int time : events.keySet())
				for (EventType event : events.get(time))
					handleEvent(time, event);
			return seconds;
		}

		private void handleEvent(int time, EventType event) {
			if (event == EventType.IncreaseCoef) {
				handleCoefficientChange(time);
				coefficient = 3;
			} else if (event == EventType.RestoreCoef) {
				handleCoefficientChange(time);
				coefficient = 2;
			} else if (event == EventType.StartWork)
				workStart = time;
			else if (event == EventType.EndWork)
				addPeriod(time);
		}

		private void handleCoefficientChange(int time) {
			if (workStart != null) {
				addPeriod(time);
				workStart = time;
			}
		}

		private void addPeriod(int time) {
			int period = time - workStart;
			seconds += period * coefficient;
			workStart = null;
		}
	}

}

enum EventType {
	StartWork, EndWork, IncreaseCoef, RestoreCoef
}
