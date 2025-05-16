import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[] arr = br.readLine().split("");
		int rCount = 0;
		int bCount = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("R")) {
				rCount++;
			} else {
				bCount++;
			}
		}

		int bMax = 0;
		int rMax = 0;

		int bTemp = 0;
		int index = 0;
		while(true) {
			if (index >= arr.length) {
				break;
			}
			
			if (arr[index].equals("B")) {
				bTemp++;
				index++;
			} else {
				break;
			}
		}
		bMax = bTemp;

		int rTemp = 0;
		index = 0;
		while(true) {
			if (index >= arr.length) {
				break;
			}

			if (arr[index].equals("R")) {
				rTemp++;
				index++;
			} else {
				break;
			}
		}
		rMax = rTemp;

		bTemp = 0;
		index = arr.length - 1;
		while(true) {
			if (index < 0) {
				break;
			}

			if (arr[index].equals("B")) {
				bTemp++;
				index--;
			} else {
				break;
			}
		}

		bMax = (int)Math.max(bTemp, bMax);

		rTemp = 0;
		index = arr.length - 1;
		while(true) {
			if (index < 0) {
				break;
			}

			if (arr[index].equals("R")) {
				rTemp++;
				index--;
			} else {
				break;
			}
		}
		rMax = (int)Math.max(rTemp, rMax);


		System.out.println(Math.min(bCount - bMax, rCount - rMax));
	}
}