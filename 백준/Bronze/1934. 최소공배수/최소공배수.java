import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a < b
			int b = Integer.parseInt(st.nextToken());

			int max;
			int min;
			if (a > b) {
				max = a;
				min = b;
			} else {
				max = b;
				min = a;
			}

			int mod = max % min;

			while (mod != 0) {
				max = min;
				min = mod;
				mod = max % min;
			}

			System.out.println( a * b / min);
		}
	}


}