package factorial;

public class Main {

	public static void main(String[] args) {
		System.out.println(factorial(0));
		System.out.println(factorial(1));
		System.out.println(factorial(2));
		System.out.println(factorial(3));
		System.out.println(factorial(4));
		System.out.println(factorial(5));
		System.out.println(factorial(6));

	}
	
	private static int factorial(int n) {
	    int result = 1;
	    if (n == 1 || n == 0) {
	        return result;
	    }
	    result = n * factorial(n-1);
	    return result;
	}

}
