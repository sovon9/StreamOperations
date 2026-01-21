package java8.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class StreamOperations 
{
	/**
	 * sum of all elements
	 */
	public void sumOfAllElements()
	{
		Integer arr[] = new Integer[] { 1, 8, 54, 89, 4, 5, 9, 5, 21, 8, 0 };

		Integer sum = Arrays.stream(arr).reduce(0, (n1, n2) -> n1 + n2);
		System.out.println(sum);

		List<Integer> numList = Arrays.asList(1, 8, 54, 89, 4, 5, 9, 5, 21, 8, 0);
		Integer sumVal = numList.stream().reduce(0, (n1, n2) -> n1 + n2);
		System.out.println(sumVal);

		/**
		 * converting to intStream to use the sum mehtod for adding mapToInt converts
		 * Integer to int
		 */
		int sumValint = numList.stream().mapToInt(Integer::intValue).sum();
		System.out.println(sumValint);
	}
	
	/*
	 * given the list of integers find the max element and print it
	 */
	public void maxElement()
	{
		List<Integer> numbers = Arrays.asList(1, 8, 54, 89, 4, 5, 9, 5, 21, 8, 0);
		
		Optional<Integer> max = numbers.stream().max((n1,n2)->Integer.compare(n1,n2));
		System.out.println("max element using Integer compare: "+max.get());
		
		Optional<Integer> max1 = numbers.stream().max((n1,n2)->n1-n2);
		System.out.println("max element using n2-n1: "+max1.get());
	}
	
	/*
	 * given a list of strings, count the number of string that start with a specific charecter
	 */
	public void countStringwithAChar()
	{
		List<String> fruits = Arrays.asList("cherry","banana","coconut","berry","apple","ber");
		// fruits starts with b
		long bcount = fruits.stream().filter(s->s.startsWith("b")).count();
		System.out.println(bcount);
		
		Map<String, Long> map = fruits.stream().collect(Collectors.groupingBy(s->s.substring(0,1),Collectors.counting()));
		System.out.println(map);
	}
	
	/*
	 * convert a list of strings to uppercase
	 */
	public void convertToUpperCase()
	{
		List<String> fruits = Arrays.asList("cherry","banana","coconut","berry","apple","ber");
		List<String> upeercase = fruits.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
		System.out.println(upeercase);
	}
	
	/*
	 * from the given list of numbers filter out the even numbers and count total even
	 */
	public void countEven()
	{
		List<Integer> numList=Arrays.asList(1,8,0,5,3,7,9,2,6,4);
		long count = numList.stream().filter(n->(n%2)==0).count();
		System.out.println("count of even numbers: "+count);
	}
	
	/*
	 * get average of the floating numbers
	 */
	public void averageFloat()
	{
		List<Double> nums= Arrays.asList(1.1,1.2,1.3,1.4,1.5);
		OptionalDouble average = nums.stream().mapToDouble(Double::doubleValue).average();
		System.out.println("average of doublke: "+average.getAsDouble());
	}
	
	/*
	 * concat all the strings in a list
	 */
	public void concatStrings()
	{
		List<String> fruits = Arrays.asList("cherry","banana","coconut","berry","apple","ber");
		String concat = fruits.stream().reduce("",(s1,s2)->s1.concat(s2));
		System.out.println("concatinated string value: "+concat);
	}
	
	/*
	 * concat strings with delimiter
	 */
	public void concatWithDelimiter()
	{
		List<String> fruits = Arrays.asList("cherry","banana","coconut","berry","apple","ber");
		String delimittedString = fruits.stream().collect(Collectors.joining(";"));
		System.out.println("delimited string: "+delimittedString);
	}
	
	/*
	 * remove duplicates from a string
	 */
	public void removeDuplicates()
	{
		List<Integer> numbers = Arrays.asList(1,7,9,5,2,1,5);
		List<Integer> collect = numbers.stream().distinct().collect(Collectors.toList());
		System.out.println("list of numbers after removing duplicates: "+collect);
	}
	
	/*
	 * check if all the elements in a list satisfy the given condition
	 */
	public void satisfyCondition()
	{
		//check if all the numbers are even
		List<Integer> numbers = Arrays.asList(8,2,4,10);
		boolean allMatch = numbers.stream().allMatch(n->n%2==0);
		System.out.println("list satisfy the given condition= "+allMatch);
	}
	
	public static void main(String args[])
	{
		StreamOperations operations = new StreamOperations();
		operations.sumOfAllElements();
		operations.maxElement();
		operations.countStringwithAChar();
		operations.convertToUpperCase();
		operations.countEven();
		operations.concatStrings();
		operations.concatWithDelimiter();
		operations.removeDuplicates();
		operations.satisfyCondition();
	}
	
}
