package exercises1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			//System.out.println("toLeft =" + toLeft);
		} else if (toLeft<0) {
			if (-1*toLeft>arr.length) {
				toLeft = toLeft%arr.length;
				//System.out.println("toLeft =" + toLeft);
			}
			toLeft=toLeft+arr.length;
			//System.out.println("toLeft =" + toLeft);
		}
		int[] res = new int[arr.length];
		int[] accumulate1 = new int[arr.length-toLeft];
		accumulate1 = Arrays.copyOfRange(arr, toLeft, arr.length);
		int[] accumulate2 = new int[toLeft];
		accumulate2 = Arrays.copyOfRange(arr, 0, toLeft);
        for (int i : accumulate1) 
            System.out.print(i + "  ");
        System.out.println();
        for (int i : accumulate2) 
            System.out.print(i + "  ");
        System.out.println();
        System.arraycopy(accumulate1, 0, res, 0, accumulate1.length);  
        System.arraycopy(accumulate2, 0, res, accumulate1.length, accumulate2.length);
        for (int i : res) 
            System.out.print(i + "  ");        
		return res;
	}
	
	public static void main(String[] args) {
	    int[] sourceArr1 = {1, 2, 3, 4, 5, 6};
	    rotate(sourceArr1, -8);
	}

}
