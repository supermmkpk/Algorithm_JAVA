package day3_DP5_플로이드워샬;
import java.io.*;
import java.util.*;

import day3_DP5_플로이드워샬.Main_9205_맥주마시면서걸어가기_박봉균.Pair;

/**
 *  BJ_9205[G5] : 맥주 마시면서 걸어가기
 *
 */

public class reviewFloydWarshall {
	/** 상한 */
    static final int INF = (int) 1e9;

    static int N;
    static int dp[][]; // i -> j 경로 최적화
    static Pair[] point;

    /** i -> k가 가능한지 체크하는 함수 */
    static boolean check(int i, int k) {
        // 맨하튼 거리가 20병*50m 이내이면 가능!
        if (Math.abs(point[i].first - point[k].first)
                + Math.abs(point[i].second - point[k].second) <= 1000) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테케 개수

        // T개의 테케에 대하여
        for (int t = 1; t <= T; t++) {
            // 초기화!!
            N = Integer.parseInt(br.readLine()); // 편의점 수
            dp = new int[N + 2][N + 2];
            point = new Pair[N + 2];

            // 좌표 입력
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                point[i] = new Pair(y, x);
            }

            // dp 초기화
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (i != j)
                        dp[i][j] = INF;
                    else
                        dp[i][j] = 0;
                }
            }

            // 경유 경로
            for (int i = 0; i < N + 2; i++) {
                for (int k = 0; k < N + 2; k++) {
                    if (i != k) {
                        // i->k 가능하다면
                        if (check(i, k))
                            dp[i][k] = 1;
                    }
                }
            }

            // 경로 최적화
            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            // 출력 조건에 따라 출력
            if (dp[0][N + 1] != INF) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    /** 좌표 저장 위한 Pair 클래스 */
    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
