

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] counts = new int[M];
		int[] s = new int[N + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			s[i] = (s[i - 1] + num) % M;
			counts[s[i]]++;
		}

		counts[0]++; // 부분합이 정확히 M의 배수인 경우 포함

		long answer = 0;
		for (int i = 0; i < M; i++) {
			int c = counts[i];
			if (c >= 2) {
				answer += (long)c * (c - 1) / 2;
			}
		}

		System.out.println(answer);
	}
}