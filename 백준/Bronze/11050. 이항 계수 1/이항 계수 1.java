import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		System.out.println(combination(n, r));
	}

	public static int combination(int n, int r) {
		if (r == 0 || n == r) {
			return 1;
		}
		if (r == 1) {
			return n;
		}
		return combination(n - 1, r - 1) + combination(n - 1, r);
	}
}
