import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int max = 10000000;
		boolean[] isPrime = new boolean[max + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i <= max; i++) {
			if (!isPrime[i]) {
				continue;
			}
			if (i >= N && isPel(i)) {
				System.out.println(i);
				return;
			}
			for (int j = 2; j * i <= max; j++) {
				isPrime[i * j] = false;
			}
		}

	}

	public static boolean isPel(int num) {
		String[] numStrs = Integer.toString(num).split("");

		int start = 0;
		int end = numStrs.length - 1;

		while (start < end) {
			String tmp = numStrs[end];
			numStrs[end] = numStrs[start];
			numStrs[start] = tmp;
			start++;
			end--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= numStrs.length - 1; i++) {
			sb.append(numStrs[i]);
		}

		return Objects.equals(Integer.toString(num), sb.toString());
	}
}