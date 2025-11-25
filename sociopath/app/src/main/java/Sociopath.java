import java.util.ArrayList;
import java.util.List;

public class Sociopath {

	public int findTheSociopath (int groupSize, List<int []> likeList) {
		//Check if groupSize is 0 --> b/c not possible
		if(groupSize <= 0) {
			return -1;
		}
		//Sociopath (doesn't like anyone) so no outgoing edges 
		//Use 2 arrays: num of people each person likes and num of people who like each other
		int[] likes = new int[groupSize + 1];
		int[] likedBy = new int[groupSize + 1];
		
		//Loop through likeList
		for(int[] pair : likeList) {
			int a = pair[0];
			int b = pair[1];
			
			//If either person is outside 1 that's invalid
			if(a < 1 || a > groupSize || b < 1 || b > groupSize) {
				return -1;
			}
			
			likes[a]++;
			likedBy[b]++;
		}
		
		//Look for sociopath
		for(int person = 1; person <= groupSize; person++) {
			//Create a variable to see if person is liked by everyone
			//Create a variable to see if person is liked by no one
			//If variables are true then return person
			boolean likedByEveryone = likedBy[person] == groupSize - 1;
			boolean likedByNobody = likes[person] == 0;
			if(likedByEveryone && likedByNobody) {
				return person;
			}
		}
		
		return -1;
	}
	
	public static List<int []> build(int[][] arr){
		List<int[]> list = new ArrayList<>();
		for(int[] pair : arr) {
			list.add(pair);
		}
		return list;
	}
	public static void main(String[] args) {
		Sociopath s = new Sociopath();
		//Test 1
		List<int[]> test1 = new ArrayList<>();
		test1.add(new int[] {1, 2});
		System.out.println(s.findTheSociopath(2, test1));
		//Test 2
		List<int[]> test2 = new ArrayList<>();
		test2.add(new int[] {1, 2});
		System.out.println(s.findTheSociopath(3, test2));
		//Test 3
		List<int[]> test3 = new ArrayList<>();
		test3.add(new int[] {1, 2});
		test3.add(new int[] {1, 3});
		test3.add(new int[] {2, 3});
		System.out.println(s.findTheSociopath(3, test3));
		//Test 4
		List<int[]> test4 = new ArrayList<>();
		test4.add(new int[] {1, 3});
		test4.add(new int[] {2, 3});
		test4.add(new int[] {3, 1});
		System.out.println(s.findTheSociopath(3, test4));
		//Test 5
		List<int[]> test5 = new ArrayList<>();
		test5.add(new int[] {1, 2});
		System.out.println(s.findTheSociopath(0, test5));
		//Test 6
		List<int[]> test6 = new ArrayList<>();
		test6.add(new int[] {1, 0});
		System.out.println(s.findTheSociopath(3, test6));
		
		//Test 7: No one likes anyone (expected -1)
		List<int[]> test7 = new ArrayList<>();
		System.out.println(s.findTheSociopath(4, test7));
		//Test 8: Everyone likes the same person and they like nobody (expected: 3
		List<int[]> test8 = new ArrayList<>();
		test8.add(new int[]{1,3});
		test8.add(new int[]{2,3});
		test8.add(new int[]{4,3});
		System.out.println(s.findTheSociopath(4, test8));
		//Test 9: Impossible two candidates liked by everyone (expect -1)
		List<int[]> test9 = new ArrayList<>();
		test9.add(new int[]{1,3});
		test9.add(new int[]{2,3});
		test9.add(new int[]{1,4});
		test9.add(new int[]{2,4});
		System.out.println(s.findTheSociopath(4, test9)); 
		//Test 10: A person likes someone, but is still liked by everyone (expect -1)
		List<int[]> test10 = new ArrayList<>();
		test10.add(new int[]{1,3});
		test10.add(new int[]{2,3});
		test10.add(new int[]{4,3});
		test10.add(new int[]{3,1});
		System.out.println(s.findTheSociopath(4, test10));
		//Test 11: One person group (expect 1)
		List<int[]> test11 = new ArrayList<>();
		System.out.println(s.findTheSociopath(1, test11));
		//Test 12: Multiple people like the same person but missing one (expect -1)
		List<int[]> test13 = new ArrayList<>();
		test13.add(new int[]{1,3});
		test13.add(new int[]{2,3});
		System.out.println(s.findTheSociopath(4, test13));
		//Test 13: Cycle (everyone likes someone, nobody likes no one) (expect -1)
		List<int[]> test14 = new ArrayList<>();
		test14.add(new int[]{1,2});
		test14.add(new int[]{2,3});
		test14.add(new int[]{3,4});
		test14.add(new int[]{4,1});
		System.out.println(s.findTheSociopath(4, test14));
		//Test 14: Larger example (expect 6)
		List<int[]> test15 = new ArrayList<>();
		test15.add(new int[]{1,6});
		test15.add(new int[]{2,6});
		test15.add(new int[]{3,6});
		test15.add(new int[]{4,6});
		test15.add(new int[]{5,6});
		System.out.println(s.findTheSociopath(6, test15)); 
		//Test 15: Negative person (expect -1)
		List<int[]> test16 = new ArrayList<>();
		test16.add(new int[]{-1, 2});
		System.out.println(s.findTheSociopath(3, test16));
		//Test 16: Person number exceeds groupSize (expect -1)
		List<int[]> test17 = new ArrayList<>();
		test17.add(new int[]{1, 5});  
		System.out.println(s.findTheSociopath(3, test17));
		//Test 17: Pair contains zero (expect -1)
		List<int[]> test18 = new ArrayList<>();
		test18.add(new int[]{0, 2});
		System.out.println(s.findTheSociopath(4, test18));			
	}
}
