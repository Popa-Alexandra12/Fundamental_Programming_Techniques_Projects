package ro.tuc.pt.assig5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;


public class MonitoredData {
	private String startTime;
	private String endTime;
	private String activity;

	public MonitoredData(String startTime, String endTime, String activity) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public MonitoredData() {
		super();
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	
	public String toString() {
		return "startTime=" + startTime + "  endTime=" + endTime + "  activity=" + activity;
	}

	public List<MonitoredData> createList() {
		List<MonitoredData> list = new ArrayList<MonitoredData>();

		File file = new File("E:\\PT2019\\pt2019_30224_popa_alexandra_assignment_5\\assig5\\Activities.txt");
		BufferedReader buffReader;
		List<String> lines = new ArrayList<String>();
		try {
			Stream<String> stream = Files.lines(Paths.get("Activities.txt"));

			lines = stream.map(String::trim).collect(Collectors.toList());

			buffReader = new BufferedReader(new FileReader(file));

			lines.stream().collect(toList()).forEach(l -> {
				String[] spl = new String[3];
				spl = l.split("		");
				String s1 = spl[0];
				String s2 = spl[1];
				String s3 = spl[2];
				MonitoredData m = new MonitoredData(s1, s2, s3);
				list.add(m);
			});

		} catch (IOException e) {

			e.printStackTrace();
		}
		return list;

	}

	public long countDays(List<MonitoredData> list) {
		List<String> difDays = list.stream().map(x -> x.getStartTime()).map(x -> x.split(" ")[0]).distinct()
				.collect(Collectors.toList());

		long nrDays = difDays.stream().count();
		return nrDays;
	}

	public Map<String, Long> activityFrequence(List<MonitoredData> list) {

		Map<String, Long> map = list.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		return map;

	}

	public String getDay() {
		String s = this.getStartTime().split(" ")[0];
		return s;
	}

	public Map<String, Map<String, Long>> activityFrequenceForDay(List<MonitoredData> list) {

		Map<String, Map<String, Long>> map = list.stream().collect(Collectors.groupingBy(MonitoredData::getDay,
				Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
		return map;
	}

	public Long getDurationLong() {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime sTime = LocalDateTime.parse(this.startTime, format);
		LocalDateTime eTime = LocalDateTime.parse(this.endTime, format);
		Duration duration = Duration.between(sTime, eTime);

		return duration.toMillis();

	}
	public Duration getDuration() {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime sTime = LocalDateTime.parse(this.startTime, format);
		LocalDateTime eTime = LocalDateTime.parse(this.endTime, format);
		Duration duration = Duration.between(sTime, eTime);

		return duration;

	}

	public Duration addDuration(Duration sum, String startTime, String endTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime sTime = LocalDateTime.parse(startTime, format);
		LocalDateTime eTime = LocalDateTime.parse(endTime, format);
		DatatypeFactory dtf = null;
		try {
			dtf = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {

			e.printStackTrace();
		}

		Duration duration = Duration.between(sTime, eTime);

		sum = sum.plus(duration);

		long seconds = sum.getSeconds();

		long hours = seconds / 3600;
		long minutes = ((seconds % 3600) / 60);
		long secs = (seconds % 60);
		String diffString = hours + ":" + minutes + ":" + secs;

		return sum;
	}
	
	public List<Long>   getDurationForLine(List<MonitoredData> list) {

		/*Map<String, String> map = new HashMap<String, String>();

		list.stream().collect(toList()).forEach(l -> {

			map.put(l.toString(), l.getDuration().toString());

		});*/
	List<Long> l=list.stream().map( m -> m.getDurationLong()).collect(Collectors.toList());
		return l;
		
	}

	public Map<String, Long> activityEntireDuration(List<MonitoredData> list) {
		Map<String, Long> map = new HashMap<String, Long>();
		map=list.stream().collect(Collectors.groupingBy(MonitoredData::getActivity,Collectors.summingLong(MonitoredData:: getDurationLong)));
		/*list.stream().collect(toList()).forEach(l -> {
			if (map.containsKey(l.getActivity())) {
				Duration d = map.get(l.getActivity());
				map.put(l.getActivity(), l.addDuration(d, l.getStartTime(), l.getEndTime()));

			} else {
				map.put(l.getActivity(), l.addDuration(Duration.ZERO, l.getStartTime(), l.getEndTime()));
			}
		});*/
		return map;

	}

	public Set<String> activityTimeFilter(List<MonitoredData> list) {
		Set<String> list1 = new HashSet<String>();
		Map<String, List<Duration>> map2 = list.stream().collect(Collectors.groupingBy(MonitoredData::getActivity,
				Collectors.mapping(MonitoredData::getDuration, Collectors.toList())));

		list.stream().collect(toList()).forEach(l -> {
			long x = map2.get(l.getActivity()).stream().filter(d -> d.toMinutes() < 5).count();
			if (((x * 100) / map2.get(l.getActivity()).size()) > 90) {
				list1.add(l.getActivity());
			}
		});
		return list1;

	}

	public static void main(String[] args) {
		MonitoredData m = new MonitoredData();
		List<MonitoredData> l = new ArrayList<MonitoredData>();
		l = m.createList();
		 /*
		  System.out.println("Lista de elemente de tip MonitoredData: ");
		  for(MonitoredData iter:l) { System.out.println(iter.toString()+"\n"); }
		*/
		//System.out.println("Numarul de zile dinstincte: "+m.countDays(l));

		
		  /*System.out.println("Frecventa de aparitie a activitatiilor: ");
		  Map<String,Long> map=m.activityFrequence(l); for(Map.Entry<String,Long> iter:
		  map.entrySet()) { System.out.println("Activitate: "+iter.getKey()+": "+
		  iter.getValue()+"\n"); }
		 */
		
		 /* System.out.println("Frecventa de aparitie a activitatiilor pentru fiecare zi: ");
		  Map<String, Map<String, Long>> map = m.activityFrequenceForDay(l); for
		  (Map.Entry<String, Map<String, Long>> iter : map.entrySet()) {
		  System.out.println(iter.getKey() + ": " + "\n"); for (Map.Entry<String, Long>
		  iter1 : iter.getValue().entrySet()) { System.out.println(iter1.getKey() + ": " + iter1.getValue() + "\n"); } }
		 */
		/*Map<String, String>map=m.getDurationForLine(l); 
		 
		  for(Entry<String, String> iter:map.entrySet()) { 
			  //Duration d = Duration.ZERO;
			  System.out.println(iter.getKey()+": "+
		iter.getValue()+"\n"); }*/
		 /* System.out.println("Timpul total pt o linie: "); 
		List<Long>list=m.getDurationForLine(l);
		 for(Long iter:list)
		  { 
		   Duration d = Duration.ZERO;
		   System.out.println(d.plusMillis(iter)+"\n"); }
		 */
		
		 /*  System.out.println("Timpul total pt o activitate:"); 
		  Map<String, Long>map=m.activityEntireDuration(l); 
		 
		  for(Entry<String, Long> iter:map.entrySet()) { 
			  Duration d = Duration.ZERO;System.out.println(iter.getKey()+": "+
		  d.plusMillis(iter.getValue())+"\n"); }
		*/
		
		 /* System.out.println("Activitati care au 90% din durata <5 min :"); Set<String>
		  list=m.activityTimeFilter(l); for(String s: list) { System.out.println(s); }
		 */

	}

}
