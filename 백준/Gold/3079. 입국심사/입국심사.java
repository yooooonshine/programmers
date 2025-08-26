import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 심사대 수
        long M = Long.parseLong(st.nextToken());  // 사람 수 (1e9까지, long 권장)

        long[] T = new long[N];
        long maxT = 0;
        for (int i = 0; i < N; i++) {
            T[i] = Long.parseLong(br.readLine());
            if (T[i] > maxT) maxT = T[i];
        }

        long s = 1;
        long e = maxT * M; // 안전한 상한
        long ans = e;

        while (s <= e) {
            long m = (s + e) >>> 1; // 음수 방지용 무부호 시프트로 mid

            // m 시간 안에 처리 가능한 총 인원
            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += m / T[i];
                if (sum >= M) { // 오버플로우/불필요 누적 방지
                    break;
                }
            }

            if (sum >= M) {      // m 시간 안에 M명 처리 가능 → 더 줄여본다
                ans = m;
                e = m - 1;
            } else {             // m 시간으론 부족 → 시간을 늘린다
                s = m + 1;
            }
        }

        System.out.println(ans);
    }
}