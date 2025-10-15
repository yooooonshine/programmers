
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] tmp = recursion(N);

		System.out.println(tmp[0] + " " + tmp[1]);
	}

	public static int[] recursion(int v) {
		int count = oddCount(v);
		if (String.valueOf(v).length() == 1) {
			return new int[]{count, count};
		}

		if (String.valueOf(v).length() == 2) {
			int[] v1 = recursion(v % 10 + v / 10);

			return new int[]{count + v1[0], count + v1[1]};
		}

		// 3개 이상인 경우
		int size = String.valueOf(v).length();


		int max = 0;
		int min = Integer.MAX_VALUE;

		for (int i1 = size; i1 >= 2; i1--) {
			for (int i2 = i1 - 1; i2 >= 2; i2--) {
				int v1 = v / (int)Math.pow(10, i1 - 1);
				int v2 = (v - v1 * (int)Math.pow(10, i1 - 1)) / (int)Math.pow(10, i2 - 1);
				int v3 = v % (int)Math.pow(10, i2 - 1);

				int[] c1 = recursion(v1 + v2 + v3);

				if (max < count + c1[1]) {
					max = count + c1[1];
				}
				if (min > count + c1[0]) {
					min = count + c1[0];
				}
			}
		}

		return new int[]{min, max};
	}

	public static int oddCount(int v) {
		String[] tmp = String.valueOf(v).split("");

		int count = 0;
		for (int i = 0; i < tmp.length; i++) {
			if (Integer.valueOf(tmp[i]) % 2 != 0) {
				count++;
			}
		}

		return count;
	}
}
