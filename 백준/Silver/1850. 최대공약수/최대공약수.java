import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Long.parseLong(st.nextToken()); // a < b
		long b = Long.parseLong(st.nextToken());

		long max;
		long min;
		if (a > b) {
			max = a;
			min = b;
		} else {
			max = b;
			min = a;
		}

		long mod = max % min;

		while (mod != 0) {
			max = min;
			min = mod;
			mod = max % min;
		}

		StringBuilder sb = new StringBuilder();
		for (long i = 0L; i < min; i++) {
			sb.append("1");
		}

		System.out.println(sb.toString());
	}


}