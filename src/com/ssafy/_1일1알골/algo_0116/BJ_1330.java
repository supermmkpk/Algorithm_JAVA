package com.ssafy._1일1알골.algo_0116;

import java.util.*;

/**
 * <pre>
 * BJ1330_�� �� ���ϱ�
 * </pre>
 * 
 * @author �ں���
 * @since JDK17
 *
 * �� ���� A�� B�� �־����� ��, A�� B�� ���Ͻÿ�.

[�Է�]
1) A, B (-10,000 �� A, B �� 10,000)

[���]
ù �ٿ� ���� �� ���� �� �ϳ��� ���.
	A > B : '>'
	A < B ; '<'
	A == B : '=='
 */
public class BJ_1330 {
	public static void main(String[] args) {
		
		//a,b�� �Է� �޽��ϴ�.
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		//�־��� ���ǿ� ���� ����մϴ�.
		if(a > b) {
			System.out.println(">");
		}
		else if(a < b) {
			System.out.println("<");
		} 
		else { //a == b
			System.out.println("==");
		}
	}

}
