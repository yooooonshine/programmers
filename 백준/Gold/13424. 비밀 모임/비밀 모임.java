import java.util.*;
import java.io.*;

public class Main {

	public static int N;
	public static int M;
	public static int K;
	public static int[] friends;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); //TC

		for (int t = 1;t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 방 개수
			M = Integer.parseInt(st.nextToken()); // 비밀통로 개수

			int[][] adj = new int[N + 1][N + 1];
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					adj[r][c] = 10000000;
				}
				adj[r][r] = 0;
			}
			for (int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken()); //방1
				int b = Integer.parseInt(st.nextToken()); //방2
				int c = Integer.parseInt(st.nextToken()); // 통로 길이

				adj[a][b] = c;
				adj[b][a] = c;
			}

			for (int mid = 1; mid <= N; mid++) {
				for (int s = 1; s <= N; s++) {
					for (int e = 1; e <= N; e++) {
						if (adj[s][e] > adj[s][mid] + adj[mid][e]) {
							adj[s][e] = adj[s][mid] + adj[mid][e];
						}
					}
				}
			}

			K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			friends = new int[K + 1];
			for (int k = 1; k <= K; k++) {
				friends[k] = Integer.parseInt(st.nextToken());
			}

			int min = 100000000;
			int result = 0;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int k = 1; k <= K; k++) {
					sum += adj[i][friends[k]];
				}

				if (min > sum) {
					min = sum;
					result = i;
				}
			}

			System.out.println(result);
			// 장소가 여러 개일 경우, 그중 번호가 가장 작은 방의 번호를 출력한다.
		}
	}
}

// 방(1~N, 중복x) 100개
// M개의 마법 비밀통로(양방향, 자연수-> 벨만x) 10000
// 참여 인원 K명

// 각 참여 인원은 N개의 방중 한군데에 위치
// 최단거리
// 모임 참여 이동거리의 총합이 최소가 되는 방

// T개의 테스트

// 10000 * 5

// 모든 종착지까지 최단거리

// V가 작다. -> 플로이드 워셜**
