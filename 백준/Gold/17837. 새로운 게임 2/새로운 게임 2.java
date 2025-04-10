import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[][] table;
    static Stack<Horse>[][] board;

    static int[] dr = {0, 0, 0, -1, 1}; // 1~4: 오, 왼, 위, 아래
    static int[] dc = {0, 1, -1, 0, 0};

    static List<Horse> horses = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 체스판 색상
        table = new int[N + 2][N + 2];
        for (int i = 0; i <= N + 1; i++) {
            Arrays.fill(table[i], 2); // 바깥은 파란색 처리
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 말 정보
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horses.add(new Horse(i, r, c, d));
        }

        // 체스판 말 위치 초기화
        board = new Stack[N + 2][N + 2];
        for (int i = 0; i <= N + 1; i++) {
            for (int j = 0; j <= N + 1; j++) {
                board[i][j] = new Stack<>();
            }
        }

        for (Horse h : horses) {
            board[h.r][h.c].push(h);
        }

        int turn = 0;
        while (turn++ <= 1000) {
            for (int i = 0; i < K; i++) {
                if (move(horses.get(i))) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    // 이동 시 true면 말 4개 이상 쌓였음
    public static boolean move(Horse horse) {
        int r = horse.r;
        int c = horse.c;
        int d = horse.d;

        Stack<Horse> cell = board[r][c];
        List<Horse> moving = new ArrayList<>();

        // 이동할 말들 찾기
        while (!cell.isEmpty()) {
            Horse top = cell.pop();
            moving.add(top);
            if (top.id == horse.id) break;
        }

        Collections.reverse(moving); // 아래에서부터 위로

        int nr = r + dr[d];
        int nc = c + dc[d];

        int color = table[nr][nc];

        // 파란색 또는 벽이면 방향 반전 후 재시도
        if (color == 2) {
            horse.d = reverseDir(d);
            nr = r + dr[horse.d];
            nc = c + dc[horse.d];
            color = table[nr][nc];

            if (color == 2) {
                // 방향 바꾸고도 파랑이면 복귀
                for (Horse h : moving) board[r][c].push(h);
                return false;
            }
        }

        // 말 위치 갱신
        for (Horse h : moving) {
            h.r = nr;
            h.c = nc;
        }

        if (color == 0) { // 흰색
            for (Horse h : moving) board[nr][nc].push(h);
        } else if (color == 1) { // 빨간색
            Collections.reverse(moving);
            for (Horse h : moving) board[nr][nc].push(h);
        }

        return board[nr][nc].size() >= 4;
    }

    public static int reverseDir(int d) {
        if (d == 1) return 2;
        if (d == 2) return 1;
        if (d == 3) return 4;
        return 3;
    }

    static class Horse {
        int id;
        int r, c, d;

        public Horse(int id, int r, int c, int d) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}