import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] cranes = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cranes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cranes, Collections.reverseOrder());

        int M = Integer.parseInt(br.readLine());
        Integer[] boxes = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) boxes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(boxes, Collections.reverseOrder());

        // 불가능한 경우
        if (boxes[0] > cranes[0]) {
            System.out.println(-1);
            return;
        }

        int count = 0;
        boolean[] visited = new boolean[M];
        int moved = 0; // 옮긴 박스 수

        while (moved < M) {
            count++;
            int boxIdx = 0;
            for (int i = 0; i < N; i++) { // 각 크레인
                while (boxIdx < M) {
                    if (!visited[boxIdx] && cranes[i] >= boxes[boxIdx]) {
                        visited[boxIdx] = true;
                        moved++;
                        boxIdx++;
                        break;
                    }
                    boxIdx++;
                }
            }
        }

        System.out.println(count);
    }
}