package java8.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperations2 
{
	/**
	 * from a given sentence get frequency of each word
	 */
	public void frequencyOfWord()
	{
		String sentence = "hi, my name is Sovon Singha. I am a hardworking java developer";
		
	}
	
	/*
	 * get the frequency of characters in a sentence
	 */
	public void frequencyOfChar()
	{
		String sentence = "himnameisSovonSinghaIamahardworkingjavadeveloper";
		String[] words = sentence.split("");
		long count = Arrays.stream(words).peek(s->System.out.print(s+",")).filter(c->c.equalsIgnoreCase("s")).count();
		System.out.println("\nfrquency of S: "+count);
		
		// or recommended approach
		Map<String, Long> collect = Arrays.stream(words).collect(Collectors.groupingBy(w->w.toLowerCase(), Collectors.counting()));
		System.out.println("frequency of elements: "+collect);
		
		Map<Character,Long> map = sentence.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c, Collectors.counting()));
		System.out.println(map);
	}
	
	/*
	 * given a list of names , group them by their first letter and count number of names in each group
	 */
	public void grouppByName()
	{
		String names[] = {"sovon", "sougata", "lishakar","lisha","ankur"};
		Map<Character, Long> namegroup = Arrays.stream(names).collect(Collectors.groupingBy(s->s.charAt(0), Collectors.counting()));
		System.out.println("number of names in each group: "+namegroup);
	}
	
	/*
	 * find and print duplicate numbers in an array if it contains multiple duplicates
	 */
	public void printDuplicates()
	{
		Integer arr[] = {2,4,2,3,1,78,3,1};
		Map<Integer, List<Integer>> groupByNum = Arrays.stream(arr).collect(Collectors.groupingBy(n->n));
		List<Integer> list = groupByNum.entrySet().stream().filter(es->es.getValue().size()>1).map(es->es.getKey()).collect(Collectors.toList());
		System.out.println(list);
	}
	
	/*
	 * filter and print the palindromes
	 */
	public void printPalindromes()
	{
		List<String> items = Arrays.asList("level","hello","radar","world","deed");
		List<String> palindromeList = items.stream().filter(s-> s.equals(new StringBuilder(s).reverse().toString())).collect(Collectors.toList());
		System.out.println(palindromeList);
		//OR
		List<String> collect = items.stream().filter(i-> (Arrays.stream(i.split("")).collect(Collectors.groupingBy(s->s, Collectors.counting()))
				.entrySet().stream().filter(es->es.getValue()%2!=0).count())<=1).collect(Collectors.toList());
		System.out.println("pslindrome strings: "+collect);
	}
	
	/*
	 * how to merge two sorted arrays into single sorted array ?
	 */
	public void mergeSortedArrays()
	{
		int arr1[] = {1,3,6,8};
		int arr2[] = {2,4,5,7};
		int[] array = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).sorted().toArray();
		System.out.println("single sorted array: "+Arrays.toString(array));
		
	}
	/*
	 * given list of strings concatenate them and remove the duplicates
	 */
	public void concatAndRemoveDuplicates()
	{
		List<String> list1 = Arrays.asList("apple", "banana","orange");
		List<String> list2 = Arrays.asList("banana", "qiwi", "grape");
		
		List<String> collect = Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());
		System.out.println("concatenate them and remove the duplicates: "+collect);
	}
	
	/*
	 * student grade classification - 70 pass
	 */
	class Student
	{
		private String name;
		private int grade;
		public Student(String name, int grade) {
			super();
			this.name = name;
			this.grade = grade;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		@Override
		public String toString() {
			return "Student [name=" + name + ", grade=" + grade + "]";
		}
	}
	public void gradeClassification()
	{
		List<Student> students = Arrays.asList(new Student("sovon", 85), new Student("abc", 60), new Student("cde", 55), new Student("efg", 88));
		Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(std->std.getGrade()>=70?"pass":"fail"));
		System.out.println("classification of grades: "+collect);
	}
	
	/*
	 * classify student names based on pass and fail
	 */
	public void classifyNamesBasedOnGrades()
	{
		List<Student> students = Arrays.asList(new Student("sovon", 85), new Student("abc", 60), new Student("cde", 55),
				new Student("efg", 88));
		/*
		 * Collectors.mapping provides more fine grain control over what we want to map, like above method
		 *  in pass and fail category we are mapping the whole Student class but if we want to map only student names 
		 *  we can further map using Collection.mapping()
		 */
		Map<String, List<String>> collect = students.stream()
				.collect(Collectors.groupingBy(std -> std.getGrade() > 70 ? "pass" : "fail",
						Collectors.mapping(std -> std.getName(), Collectors.toList())));
		System.out.println("grouped student names: " + collect);
	}
	
	public static void main(String args[])
	{
		StreamOperations2 operations = new StreamOperations2();
		operations.frequencyOfWord();
		operations.frequencyOfChar();
		operations.grouppByName();
		operations.printDuplicates();
		operations.printPalindromes();
		operations.mergeSortedArrays();
		operations.concatAndRemoveDuplicates();
		operations.gradeClassification();
		operations.classifyNamesBasedOnGrades();
	}
	
}
