import java.util.*;
import java.io.*;

public class Main {

	public static int[][] friends;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 친구관계 수
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 돈


		st = new StringTokenizer(br.readLine());
		friends = new int[N + 1][2]; // 첫번쨰는 대표, 두번째는 최소비용
		for (int n = 1; n <= N; n++) {
			friends[n][1] = Integer.parseInt(st.nextToken());
		}


		// 친구관계
		for (int n = 0; n <= N; n++) {
			friends[n][0] = n;
		}

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			union(v1, v2);
		}

		int cost = 0;
		Set<Integer> myFriends = new HashSet<>();
		for (int n = 1; n <= N; n++) {
			if (!myFriends.contains(find(n))) {
				int repre = find(n);

				cost += friends[repre][1];
				myFriends.add(repre);
			}
		}


		if (cost <= K) {
			System.out.println(cost);
		} else {
			System.out.println("Oh no");
		}
	}

	public static void union(int v1, int v2) {
		int vr1 = find(v1);
		int vr2 = find(v2);

		if (vr1 > vr2) {
			friends[vr1][0] = vr2;
			friends[vr2][1] = Math.min(friends[vr1][1], friends[vr2][1]);
		} else if (vr1 < vr2) {
			friends[vr2][0] = vr1;
			friends[vr1][1] = Math.min(friends[vr1][1], friends[vr2][1]);
		}
	}

	public static int find(int v) {
		if (v == friends[v][0]) {
			return v;
		}
		return friends[v][0] = find(friends[v][0]);
	}
}


// N명인 학교
// 학생 i에게 Ai 돈을 주면 1달 친구
// 총 k원의 돈
// 가장 적은 비용으로 친구가 되는 법

// 모든 것을 유니온파인드하고
// 그 밑에 최소 용