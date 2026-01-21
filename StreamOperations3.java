package java8.Collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOperations3 {

	/*
	 *  How to find Minimum and Maximum Niumber from the given list 
	 *  How to find average of all the numbers 		
	 *  How to find the sum of all the numbers in the list
	 *  Count number of elements in the list 
	 *  Input [10, 20, 30, 40, 50]
	 */
	public void mapToFunctions()
	{
		List<Integer> data = Arrays.asList(10,20,30,40,50);
		int min = data.stream().mapToInt(Integer::intValue).min().getAsInt();
		System.out.println("min value : "+min);
		int max = data.stream().mapToInt(Integer::intValue).max().getAsInt();
		System.out.println("max value : "+max);
		double average = data.stream().mapToInt(Integer::intValue).average().getAsDouble();
		System.out.println("average value : "+average);
		int sum = data.stream().mapToInt(Integer::intValue).sum();
		System.out.println("sum value : "+sum);
	}
	
	/**
	 *  Find first repeating character from String
	 */
	public void firstRepeatingChar()
	{
		String word = "abcdefghe";
		String data[] = word.split("");;
//		Optional<Entry<String, Long>> findFirst = Arrays.stream(data)
//				.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
//				.entrySet().stream().peek(System.out::println)
//				.filter(es -> es.getValue() > 1).findFirst();
		Map<String, Long> map = Arrays.stream(data)
				.collect(Collectors.groupingBy(s -> s, LinkedHashMap::new ,Collectors.counting()));
		System.out.println("map with occurances=> "+map);
				Optional<Entry<String, Long>> findFirst = map.entrySet().stream().peek(System.out::println)
				.filter(es -> es.getValue() > 1).findFirst();
		System.out.println(findFirst.get().getKey());
	}
	
	/**
	 * Find average
	 */
	public void removeDuplicates()
	{
		Integer arr[]={1,1,2,2,5,6,9,12,12,13};

		double avg = Arrays.stream(arr).mapToInt(Integer::intValue).average().getAsDouble();
		System.out.println(avg);
	}
	
	/**
	 * find first element from a collection
	 */
	public void findFirstElement()
	{
		List<Integer> numbers = Arrays.asList(2, 3, 5, 8, 15);
	    Integer  first = numbers.stream().limit(1).findFirst().orElse(0);
		System.out.println(first);
	}
	
	public void useOFflatMap()
	{
		List<List<Integer>> lists = Arrays.asList(
			    Arrays.asList(1, 2, 3),
			    Arrays.asList(4, 5),
			    Arrays.asList(6, 7)
			);
		List<Integer> inetegrs = lists.stream().flatMap(l->l.stream()).collect(Collectors.toList());
		System.out.println(inetegrs);
		List<Integer> inetegrs1 = lists.stream().flatMap(List::stream).collect(Collectors.toList());
		System.out.println(inetegrs1);
	}
	
	/**
	 * Find the most frequently occurring element in a list
	 */
	public void frequestOccuring()
	{
	    List<Integer> nums = List.of(1, 2, 3, 2, 4, 2, 5);
	    Integer max = nums.stream().collect(Collectors.groupingBy(i->i, Collectors.counting())).entrySet()
	    .stream().max((n1,n2)->Long.compare(n1.getValue(),n2.getValue())).get().getKey();
		System.out.println(max);
	}
	
	public void thenComparingEmp()
	{
		List<Employee> list = List.of(new Employee("SS", 100), new Employee("AA", 95), new Employee("CC",95));
		List<Employee> collect = list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName)).collect(Collectors.toList());
		System.out.println("sorted employee by salary: "+collect);
	}
	
	public void thenComparingEmpSalAscAndNameDesc()
	{
		List<Employee> list = List.of(new Employee("SS", 100), new Employee("AA", 95), new Employee("CC",95));
		List<Employee> collect = list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).thenComparing((e1,e2)->e2.getName().compareTo(e1.getName()))).collect(Collectors.toList());
		System.out.println("sorted employee by salary: "+collect);
	}
	
	public static void main(String args[])
	{
		StreamOperations3 operations = new StreamOperations3();
		operations.mapToFunctions();
		operations.firstRepeatingChar();
		operations.removeDuplicates();
		operations.findFirstElement();
		operations.useOFflatMap();
		operations.frequestOccuring();
		operations.thenComparingEmp();
		operations.thenComparingEmpSalAscAndNameDesc();
	}
	
}
