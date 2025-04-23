import java.util.*;
import java.io.*;

public class Main {

	public static int H;
	public static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			int[] rs = {0, 0, -1, 1};
			int[] cs = {1, -1, 0, 0};

			// 평면도
			String[][] arr = new String[H + 1][W + 1];
			for (int r = 1; r <= H; r++) {
				String[] tmp = br.readLine().split("");

				for (int c = 1; c <= W; c++) {
					arr[r][c] = tmp[c - 1];
				}
			}

			// 열쇠 받기
			String[] tmp = br.readLine().split("");
			Set<String> keys = new HashSet<>();
			for (int i = 0; i < tmp.length; i++) {
				if (Objects.equals(tmp[i], "0")) {
					continue;
				}

				if (!keys.contains(tmp[i])) {
					keys.add(tmp[i]);
				}
			}

			// 시작 위치 찾기
			List<Vertex> vs = new ArrayList<>();
			for (int r = 1; r <= H; r++) {
				if (!Objects.equals(arr[r][1],"*")) {
					vs.add(new Vertex(r, 1, arr[r][1]));
				}

				if (!Objects.equals(arr[r][W], "*")) {
					vs.add(new Vertex(r, W, arr[r][W]));
				}
			}

			for (int c = 2; c < W; c++) {
				if (!Objects.equals(arr[1][c], "*")) {
					vs.add(new Vertex(1, c, arr[1][c]));
				}

				if (!Objects.equals(arr[H][c], "*")) {
					vs.add(new Vertex(H, c, arr[H][c]));
				}
			}
			// for (Vertex v : vs) {
			// 	System.out.println(v.r + " " + v.c + " : " + v.v);
			// }

			// dfs
			Stack<Vertex> myS = new Stack<>();
			boolean[][] visit = new boolean[H + 1][W + 1];
			for (Vertex v : vs) {
				myS.add(v);
			}

			int count = 0;
			List<Vertex> doors = new ArrayList<>();
			while (!myS.isEmpty()) {
				Vertex nowVertex = myS.pop();
				int nowR = nowVertex.r;
				int nowC = nowVertex.c;
				String nowV = nowVertex.v;

				if (visit[nowR][nowC]) {
					continue;
				}

				// System.out.println("visit : " + nowR + " " + nowC + " : " + nowV);

				visit[nowR][nowC] = true; // 추후 키로 도어 방문하기 전 false만들기

				if (Objects.equals(nowV,"*")) {
					continue;
				} else if (Objects.equals(nowV, "$")) {
					// System.out.println("find $ : " + nowR + " " + nowC);
					count++;
				} else if (!Objects.equals(nowV, ".")) {
					if (isLowerCase(nowV)) {
						// System.out.println("key : " + nowV);
						// 소문자일경우
						// 키 추가
						if (!keys.contains(nowV)) {
							keys.add(nowV);
						}

						// door 중 열 수 있는 거 체크
						for (Vertex door : doors) {
							// System.out.println(door.v + " : " + nowV.toUpperCase());
							if (Objects.equals(door.v,nowV.toUpperCase())) {
								arr[door.r][door.c] = ".";
								myS.add(new Vertex(door.r, door.c, arr[door.r][door.c]));
								visit[door.r][door.c] = false;
							}
						}

					} else {
						// 대문자일경우
						// 열쇠로 열 수 있는지 체크
						// 못열면 door로 넣기
						// System.out.println("door : " + nowV);

						if (keys.contains(nowV.toLowerCase())) {
							// System.out.println("open door : " + nowV + "R : " + nowR + " C : " + nowC);
							arr[nowR][nowC] = ".";
						} else {
							doors.add(new Vertex(nowR, nowC, nowV));
							continue;
						}
					}
				}

				for (int i = 0; i <= 3; i++) {
					int r = nowR + rs[i];
					int c = nowC + cs[i];

					if (r < 1 || r > H || c < 1 || c > W) {
						continue;
					}

					if (Objects.equals(arr[r][c], "*")) {
						continue;
					}

					myS.add(new Vertex(r, c, arr[r][c]));
				}
			}
			System.out.println(count);
		}
	}

	public static class Vertex {
		int r;
		int c;
		String v;

		public Vertex(int r, int c, String v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}

	public static boolean isLowerCase(String s) {
		return s == s.toLowerCase();
	}
}

// 일부 열쇠 존재, 일부 열쇠는 바닥에
// 문서의 위치는 알고 있다.
// 상하좌우 이동

// 방문했으나 열쇠가 없었던 장소는 저장해둔다.
// 추후 열쇠를 발견하면 방문할 장소에 추가한다.
// $를 발견하면 count한다.

// 입구 저장해두기, 입구들을 모두 큐에 넣기
// dfs
// 방문체크
// 문을 발견하면 현재 열쇠로 열 수 있는지 확인
// 열 수 있으면 벽을 .으로 변경
// 열 수 없으면 장소 저장 후 패스
// $를 발견하면 count한다.
// 상하좌우 이동 체크

// 끝 체크
