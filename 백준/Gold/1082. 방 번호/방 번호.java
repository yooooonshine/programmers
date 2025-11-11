import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] P;
    static String[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) P[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        dp = new String[M + 1];
        Arrays.fill(dp, null);
        dp[0] = ""; // 초기 상태

        for (int m = 0; m <= M; m++) {
            if (dp[m] == null) continue;

            for (int i = 0; i < N; i++) {
                int cost = P[i];
                if (m + cost > M) continue;

                String next = dp[m] + i;

                // 0으로 시작하지 않게
                if (next.length() > 1 && next.charAt(0) == '0') continue;

                if (dp[m + cost] == null || bigger(next, dp[m + cost])) {
                    dp[m + cost] = next;
                }
            }
        }

        String ans = "0";
        for (int i = 0; i <= M; i++) {
            if (dp[i] != null && bigger(dp[i], ans)) ans = dp[i];
        }

        System.out.println(ans);
    }

    static boolean bigger(String a, String b) {
        if (a.length() != b.length()) return a.length() > b.length();
        return a.compareTo(b) > 0;
    }
}