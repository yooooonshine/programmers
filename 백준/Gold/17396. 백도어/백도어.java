
import java.util.*;
import java.io.*;

public class Main {
    public static long MAX = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 분기점 수
        int M = Integer.parseInt(st.nextToken()); // 길의 수
        int[] Ns = new int[N]; // 0~N-1 (시야 여부)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Ns[i] = Integer.parseInt(st.nextToken()); // 1이면 시야에 보이는 곳
        }

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long t = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Edge(b, t));
            adj.get(b).add(new Edge(a, t));
        }

        // 다익스트라
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] dist = new long[N];
        Arrays.fill(dist, MAX);
        dist[0] = 0;
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int now = current.e;
            long weight = current.w;

            // 이미 더 짧은 경로를 찾았다면 스킵
            if (dist[now] < weight) continue;

            for (Edge next : adj.get(now)) {
                // 상대 넥서스(N-1) 제외하고, 시야에 보이는 곳은 지나갈 수 없음
                if (Ns[next.e] == 1 && next.e != N - 1) continue;

                if (dist[next.e] > dist[now] + next.w) {
                    dist[next.e] = dist[now] + next.w;
                    pq.add(new Edge(next.e, dist[next.e]));
                }
            }
        }

        System.out.println(dist[N - 1] == MAX ? -1 : dist[N - 1]);
    }

    public static class Edge implements Comparable<Edge> {
        int e;
        long w;

        public Edge(int e, long w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Long.compare(this.w, edge.w);
        }
    }
}
