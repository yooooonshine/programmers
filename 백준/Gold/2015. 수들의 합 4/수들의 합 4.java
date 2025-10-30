import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long[] a = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) a[i] = Long.parseLong(st.nextToken());

        Map<Long, Long> freq = new HashMap<>();
        freq.put(0L, 1L);       // sum[0] = 0 한 번 등장
        long sum = 0, ans = 0;

        for (int i = 1; i <= N; i++) {
            sum += a[i];
            ans += freq.getOrDefault(sum - K, 0L);
            freq.put(sum, freq.getOrDefault(sum, 0L) + 1);
        }
        System.out.println(ans);
    }
}