import java.io.*;
import java.util.*;

public class Main {
    public static double per = 0.0;
    public static boolean[][] visit = new boolean[30][30];

    public static int[] mR = {0, 0, -1, 1}; // 동서남북 이동
    public static int[] mC = {1, -1, 0, 0};
    public static double[] mP;
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        double EP = Double.parseDouble(st.nextToken()) / 100.0;
        double WP = Double.parseDouble(st.nextToken()) / 100.0;
        double SP = Double.parseDouble(st.nextToken()) / 100.0;
        double NP = Double.parseDouble(st.nextToken()) / 100.0;

        mP = new double[] {EP, WP, SP, NP}; // 각 방향의 확률 저장

        // 시작 위치 (15, 15)에서 탐색 시작
        recursion(0, 15, 15, 1.0);

        // 오차 10^-9까지 출력
        System.out.printf("%.9f\n", per);
    }

    public static void recursion(int index, int r, int c, double nowP) {
        if (visit[r][c]) return; // 이미 방문한 경우 종료

        if (index == N) { // N번 이동 완료 시 확률 누적
            per += nowP;
            return;
        }

        visit[r][c] = true; // 방문 체크

        for (int i = 0; i < 4; i++) {
            if (mP[i] == 0) continue; // 확률이 0인 방향은 탐색하지 않음

            int nowR = r + mR[i];
            int nowC = c + mC[i];

            recursion(index + 1, nowR, nowC, nowP * mP[i]);
        }

        visit[r][c] = false; // 백트래킹 (되돌리기)
    }
}