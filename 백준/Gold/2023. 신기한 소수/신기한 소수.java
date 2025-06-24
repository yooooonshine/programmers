import java.util.*;
import java.io.*;

public class Main {

	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dfs(1, 2);
		dfs(1, 3);
		dfs(1, 5);
		dfs(1, 7);

	}

	public static void dfs(int index, int x) {
		if (index == N) {
			System.out.println(x);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			int tmp = x * 10 + i;
			if (isPrime(tmp)) {
				dfs(index + 1, tmp);
			}
		}
	}

	public static boolean isPrime(int num) {
		if (num == 1) {
			return false;
		}
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}