package com.ssafy.algorithm.day1_0129_재귀;

public class Ex1_배열출력 {
	static int arr[] = {10, 20, 30};
	
	//재귀
	//종료조건: 더이상 요소가 X (인덱스 = 배열의 길이)
	private static void printArray2(int index) {
		if(index == arr.length) {
			return;
		}
		System.out.println(arr[index]);
		printArray2(index + 1);
		
	}
	
	public static void main(String[] args) {
		
	}
}
