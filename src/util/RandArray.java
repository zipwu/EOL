package util;

public class RandArray {
	
	public int[] randarray(int min,int max,int n){
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int randnumlength = 10;
		int[] randnum = new RandArray().randarray(1, 13, randnumlength);
		for (int i = 0; i < randnum.length; i++) {
			System.out.println(randnum[i]);
		}
	}
}
