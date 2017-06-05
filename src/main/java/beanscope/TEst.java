package beanscope;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TEst {

	
	public static void main(String... args) {
		int arr []=new int []{3,5,64,3,4,6,7,45,87,8,55};
//		Arrays.parallelSort(arr, 0, arr.length);
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.println(i);
		}
		System.out.println("=====================");
//		for (int i = 1; i < arr.length; i++) {
//			int key=arr[i];
//			int j=i-1;
//			while(j>=0&&arr[j]>key){
//				arr[j+1]=arr[j];
//				j--;
//			}
//			arr[j+1]=key;
//		}
//		
//		System.out.println(Arrays.toString(arr));
//		for (int i = 0; i < arr.length-1; i++) {
//			for (int j = 0; j < arr.length-1-i; j++) {
//				if(arr[j]>arr[j+1]){
//					int temp=arr[j];
//					arr[j]=arr[j+1];
//					arr[j+1]=temp;
//				}
//			}
//		}
//		System.out.println(Arrays.toString(arr));
		
		for (int i = 0; i < arr.length; i++) {
			int min=i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[j]<arr[min]){
					min=j;
				}
			}
			int temp=arr[min];
			arr[min]=arr[i];
			arr[i]=temp;
		}
		System.out.println(Arrays.toString(arr));
		int f=0;
		int s=1;
		int n=0;
		int i=0;
		while(i<8){
			n=f+s;
			System.out.println(f);
			f=s;
			s=n;
			i++;
		}
		
		
		int fact=5;
		int result=1;
		while(fact>0){
			result=result*fact;
			fact--;
		}
		System.out.println(result);
		System.out.println(GCD(6,8));
		System.out.println(lcm(6,8));
		System.out.println(multiple(arr));

	}
	public static int multiple(int ... A){
		int smallest=Arrays.stream(A).min().getAsInt();
		for (int i = 0; i < A.length; i++) {
			smallest = lcm(A[i], smallest);
		}
		return smallest;
	}
	public static int GCD(int a, int b) {
		   if (b==0) return a;
		   return GCD(b,a%b);
		}
	 static int lcm(int a, int b)
	{

	    return (a * b) / GCD(a, b);
	}
	
	
}
