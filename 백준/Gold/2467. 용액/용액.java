
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N =  Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = N - 1;

		int abs = 2000000001;
		int a1 = 0;
		int a2 = 0;

		while (s < e) {
			if (arr[e] + arr[s] == 0) {
				a1 = s;
				a2 = e;
				break;
			}

			if (abs > (int)Math.abs(arr[e] + arr[s])) {
				abs = (int)Math.abs(arr[e] + arr[s]);
				a1 = s;
				a2 = e;
			}

			if (arr[e] + arr[s] > 0) {
				e--;
			} else {
				s++;
			}
		}

		System.out.println(arr[a1] + " " + arr[a2]);
	}
}