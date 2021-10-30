package a_Introductory;

public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0:
			case 1:
				return n;
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}
