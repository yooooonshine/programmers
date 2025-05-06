import java.util.*;
import java.io.*;

public class Main {

	public static boolean[] alphabet = new boolean[26];
	public static char[][] map;

	public static int result;

	public static int[] rs = {0,0,-1,1};
	public static int[] cs = {1,-1,0,0};

	public static int R;
	public static int C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R + 2][C + 2];
		for (int r = 1; r <= R; r++) {
			char[] tmp = br.readLine().toCharArray();
			for (int c = 1; c <= C; c++) {
				map[r][c] = tmp[c - 1];
			}
		}
		dfs(1, 1, 0);
		System.out.println(result);
	}

	public static void dfs(int r, int c, int count) {
		if (alphabet[getNum(map[r][c])]) {
			return;
		}
		alphabet[getNum(map[r][c])] = true;

		if (result < count + 1) {
			result = count + 1;
		}

		for (int i = 0; i <= 3; i++) {
			int nowR = r + rs[i];
			int nowC = c + cs[i];

			if (nowR < 1 || nowR > R || nowC < 1 || nowC > C) {
				continue;
			}

			dfs(nowR, nowC, count + 1);
		}

		alphabet[getNum(map[r][c])] = false;
	}

	public static int getNum(char c) {
		return c - 'A';
	}
}

// 세로 R칸, 가로 C칸
// 지금까지의 알파벳과 다르게 이동
// 최대 몇칸 이동?


// dfs 사용
// 재귀해야하지 않나?
// 배열 방문, 방문 x

// 26개