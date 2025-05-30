import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static char[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		S = new char[N + 1]; // 기존 문자열
		for (int n = 1; n <= N; n++) {
			S[n] = br.readLine().toCharArray()[0];
		}

		result = new char[N + 1]; //최종 결과 배열

		int index = 1;
		int s = 1;
		int e = N;
		while (s <= e) {
			boolean leftIsSmaller = false;
			int i = s, j = e;
			while (i <= j) {
				if (S[i] < S[j]) {
					leftIsSmaller = true;
					break;
				} else if (S[i] > S[j]) {
					leftIsSmaller = false;
					break;
				}
				i++;
				j--;
			}

			if (leftIsSmaller) {
				result[index++] = S[s++];
			} else {
				result[index++] = S[e--];
			}
		}

		// 80글자마다 새줄문자 출력!
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= N; i++) {
			bw.write(String.valueOf(result[i]));
			if (i % 80 == 0) {
				bw.write("\n");
			}
		}
		bw.flush();
	}

	public static char[] result;
}

// N개의 문자로 이뤄진 문자열 S

// 1. S의 가장 앞문자를 T의 마지막에 추가
// 2. S의 가장 뒤의 문자하나를T의 마지막에 추가

// 같은 경우 두 가지로 나눠서 진행하면 안되나?
//
