import java.util.*;
import java.io.*;

public class Main {

	public static List<List<Integer>> adj;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] Ns = br.readLine().toCharArray();

		System.out.println(recursion(Ns, 0, Ns.length - 1));
	}

	public static int recursion(char[] ns, int s, int e) {

		boolean allSame = true;
		for (int i = s; i < e; i++) {
			if (ns[i] != ns[i + 1]) {
				allSame = false;
				break;
			}
		}

		if (allSame) {
			return 1;
		} else {
			int sum = 0;
			sum += recursion(ns, s + 1, e);
			sum += recursion(ns, s, e - 1);
			return sum;
		}
	}
}


// 숫자하나, 왼쪽 오른쪽에 숫자를 붙이면 N을 만든다.

// 특정수 왼쪽 오른쪽 선택

// N을 만드는 방법의 수