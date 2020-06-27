package exercises1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise1 {
	
	/**
	 * This function gives an array that is contains difference between source and other one. 
	 * @param sourceArray
	 * @param arr
	 * @return
	 */
	public static List<Integer> findMissing(List<Integer> sourceArray, List<Integer> arr) {
		List<Integer> notFoundInSecondArray = new ArrayList<>();
		for (int counter1=0; counter1<sourceArray.size(); counter1++) {
			//System.out.println("=== Looking for [" + counter1 + "] " + sourceArray.get(counter1) + " ===");
			for (int counter2=0; counter2<arr.size(); counter2++) {
				if (sourceArray.get(counter1)==arr.get(counter2)) {
					//System.out.println("Found in second array [" + counter2 + "] " + arr.get(counter2));
					break;
				}
				;
				if (counter2==arr.size()-1) {
					//System.out.println("Can't found [" + counter1 + "] " + sourceArray.get(counter1));
					notFoundInSecondArray.add(sourceArray.get(counter1));
				}
			}
		}		
		return notFoundInSecondArray;
	}
	
	public static void main(String[] args) {
	    Integer[] sourceArr1 = {4,12,9,5,6,1};
	    Integer[] sourceArr2 = {4,9,12,6};
	    List<Integer> arr1 = Arrays.asList(sourceArr1); 
	    List<Integer> arr2 = Arrays.asList(sourceArr2);
		System.out.println(findMissing(arr1, arr2));
	}

}
