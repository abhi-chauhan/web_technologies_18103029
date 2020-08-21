import java.util.Scanner;

class kmp
{ 
	void lpsarray(String pattern, int m, int lps[]) 
	{ 
		int ln = 0, i=1; 
		lps[0] = 0;

		while (i < m) 
		{ 
			if (pattern.charAt(i) == pattern.charAt(ln)) 
			{ 
				ln++; 
				lps[i] = ln; 
				i++; 
			} 
			else 
			{ 
				if (ln != 0) 
				{ 
					ln = lps[ln-1]; 
				} 
				else
				{ 
					lps[i] = ln; 
					i++; 
				} 
			} 
		}
	}

	int search(String pattern, String text) 
	{ 
		int m = pattern.length(); 
		int N = text.length(); 

		int lps[] = new int[m]; 
		int j = 0; 

		lpsarray(pattern,m,lps); 

		int i = 0, count = 0, next_i = 0; 
		
		while (i < N) 
		{ 
			if (pattern.charAt(j) == text.charAt(i)) 
			{ 
				j++; 
				i++; 
			} 
			if (j == m) 
			{ 
				j = lps[j-1]; 
				count++; 

				if (lps[j]!=0) 
					i = ++next_i; 
				j = 0; 
			} 

			else if (i < N && pattern.charAt(j) != text.charAt(i)) 
			{ 
				if (j != 0) 
					j = lps[j-1]; 
				else
					i = i+1; 
			} 
		} 
		return count; 
	}

	public static void main(String args[]) 
	{ 
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter text ");
		String text = obj.nextLine();
		System.out.println("Enter the pattern to be searched for ");
		String pattern = obj.nextLine();
		int count = new kmp().search(pattern,text); 
		System.out.println(count); 
	}
}