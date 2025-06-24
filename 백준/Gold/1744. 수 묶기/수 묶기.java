
import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		for (int n = 0; n <= N - 1; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int zeroCount = 0;

		for (int n = 0; n <= N - 1; n++) {
			if (arr[n] > 0) {
				plus.offer(arr[n]);
			} else if (arr[n] < 0) {
				minus.offer(arr[n]);
			} else {
				zeroCount++;
			}
		}

		int sum = 0;

		while (plus.size() >= 2) {
			int a = plus.poll();
			int b = plus.poll();
			
			if (a + b < a *b) {
				sum += a * b;
			} else {
				sum += a + b;
			}
		}
		if (plus.size() == 1) {
			sum += plus.poll();
		}

		while (minus.size() >= 2) {
			int a = minus.poll();
			int b = minus.poll();
			sum += a * b;
		}

		if (minus.size() == 1) {
			int a = minus.poll();
			if (zeroCount > 0) {
				// Do nothing, zero can neutralize the negative number
			} else {
				sum += a;
			}
		}

		System.out.println(sum);
	}
}

