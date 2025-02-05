import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		String[] Ts = T.split("");

		String[] Ss = S.split("");

		while (true) {
			if (Ss.length == Ts.length) {
				if (Arrays.equals(Ss, Ts)) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				return;
			}

			if (Ts[Ts.length - 1].equals("A")) {
				Ts = Arrays.copyOf(Ts, Ts.length - 1);
			} else {
				Ts = Arrays.copyOf(Ts, Ts.length - 1);
				reverse(Ts);
			}
		}
	}

	public static void reverse(String[] arr) {
		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			String temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}
}