import java.math.BigDecimal;

public class hw3 {
	public static void main(String[] args) {
		// input intervals by entering the start point followed by the end point
		// input data type is a double so make sure to add a ".0" after integers
		// sample input (1,3), (4,5), (6,9)
		Double[] intervals = { 1.0, 3.0, 4.0, 5.0, 6.0, 9.0}; // input intervals
		System.out.println("Maximum number of common points: " + maxCommonPoint(intervals));
	}

	private static int maxCommonPoint(Double[] intervals) {
		Double[] starts = new Double[intervals.length / 2];
		Double[] ends = new Double[intervals.length / 2];
		// split into 2 arrays O(n/2 + n/2)
		for (int k = 0; k < intervals.length; k += 2)
			starts[k / 2] = intervals[k];
		for (int k = 1; k < intervals.length; k += 2)
			ends[k / 2] = intervals[k];

		// merge sort both arrays O(nlogn + nlogn)
		mergeSort(starts);
		mergeSort(ends);
		
		// calculates the max in O(n/2)
		int i = 0, j = 0, tempMax = 0, max = 0;
		while (i != intervals.length/2) {
			if (starts[i] < ends[j]) {
				i++;
				tempMax++;
			} else {
				j++;
				tempMax--;
			}
			if (tempMax > max)
				max = tempMax;
		}
		return max;
	}

	// Merge Sort: O(nlogn)
	// ******************************************************************************************
	public static void mergeSort(Double[] array) {
		Double[] temp = new Double[array.length];
		mergeSort(array, temp, 0, array.length - 1);
	}

	private static void mergeSort(Double[] array, Double[] temp, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(array, temp, left, center);
			mergeSort(array, temp, center + 1, right);
			merge(array, temp, left, center + 1, right);
		}
	}

	private static void merge(Double[] array, Double[] temp, int left, int right, int rightEnd) {
		int leftEnd = right - 1;
		int k = left;
		int num = rightEnd - left + 1;

		while (left <= leftEnd && right <= rightEnd)
			// BigDecimal class allows us to compare doubles without having to worry about
			// rounding errors
			if (BigDecimal.valueOf(array[left]).compareTo(BigDecimal.valueOf(array[right])) <= 0)
				temp[k++] = array[left++];
			else
				temp[k++] = array[right++];

		while (left <= leftEnd)
			temp[k++] = array[left++];

		while (right <= rightEnd)
			temp[k++] = array[right++];

		for (int i = 0; i < num; i++, rightEnd--)
			array[rightEnd] = temp[rightEnd];
	}
}
