package exercises1;

import java.util.Arrays;

public class Exercise2 {
	
	/**
	 * This function provides to rotate an array with specified number. Default is rotate to left. 
	 * If you give negative numbers, function will rotate the array to right.
	 * @param arr
	 * @param toLeft
	 * @return
	 */
	public static int[] rotate (int[] arr, int toLeft) {
		if (toLeft>arr.length) {
			toLeft = toLeft%arr.length;
		} else if (toLeft<0) {
			if (-1*toLeft>arr.length) {
				toLeft = toLeft%arr.length;
			}
			toLeft=toLeft+arr.length;
		}
		int[] accumulate1 = new int[arr.length-toLeft];
		accumulate1 = Arrays.copyOfRange(arr, toLeft, arr.length);
		int[] accumulate2 = new int[toLeft];
		accumulate2 = Arrays.copyOfRange(arr, 0, toLeft);
        System.out.println();
        System.arraycopy(accumulate1, 0, arr, 0, accumulate1.length);  
        System.arraycopy(accumulate2, 0, arr, accumulate1.length, accumulate2.length);       
		return arr;
	}
	
	public static void main(String[] args) {
	    int[] sourceArr1 = {1, 2, 3, 4, 5, 6};
	    rotate(sourceArr1, 8);
	}

}
