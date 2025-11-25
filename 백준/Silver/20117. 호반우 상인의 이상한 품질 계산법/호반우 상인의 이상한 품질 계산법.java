import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static List<List<Integer>> adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int s = 0;
		int e = N - 1;

		long sum = 0L;
		while (s <= e) {
			if (s == e) {
				sum += (long)arr[e];
			} else {
				sum += (long)(arr[e] * 2);
			}
			s++;
			e--;
		}

		System.out.println(sum);
	}
}
