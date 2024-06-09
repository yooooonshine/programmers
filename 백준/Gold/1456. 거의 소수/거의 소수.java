import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long start = Long.parseLong(st.nextToken());
		long end = Long.parseLong(st.nextToken());

		int sqrtEnd = (int) Math.sqrt(end);
		boolean[] isPrime = new boolean[sqrtEnd + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;

		int count = 0;
		for (int i = 2; i <= sqrtEnd; i++) {
			if (!isPrime[i]) {
				continue;
			}

			for (int j = 2; j * i <= sqrtEnd; j++) {
				isPrime[j * i] = false;
			}

			int primeNum = 2;
			double squareNum = Math.pow(i, primeNum);
			while (squareNum <= end) {
				if (start <= squareNum) {
					count++;
				}
				primeNum++;
				squareNum = Math.pow(i, primeNum);
			}

		}

		System.out.println(count);
	}
}