import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 3];
		arr[0] = 0;
		arr[1] = 0;
		arr[2] = 1;
		arr[3] = 1;

		for (int n = 4; n <= N; n++) {
			boolean two = false;
			boolean three = false;

			if (n % 2 == 0) {
				two = true;
			}
			if (n % 3 == 0) {
				three = true;
			}

			if (two && three) {
				int tmp = Math.min(arr[n / 2] + 1, arr[n / 3] + 1);
				tmp = Math.min(tmp, arr[n - 1] + 1);
				arr[n] = tmp;
			} else if (two && !three) {
				int tmp = Math.min(arr[n / 2] + 1, arr[n - 1] + 1);
				arr[n] = tmp;
			} else if (!two && three) {
				int tmp = Math.min(arr[n / 3] + 1, arr[n - 1] + 1);
				arr[n] = tmp;
			} else {
				arr[n] = arr[n - 1] + 1;
			}
		}

		System.out.println(arr[N]);
	}
}

