import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * the program checks the entered number of ships will fit in the figure or not
 */
public class CheckRoominess {
	
	public static int[][] getFilledArea(){
		int N=9;
		int[][] filledArea=new int[N][N];
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("area.txt"));
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		
		StringBuilder sb = new StringBuilder();
		String line = ""; 
		try {
			while((line = br.readLine())!=null) {
				sb.append(line).append(" ");
				}
			}
		catch (IOException e) {
            e.printStackTrace();
            }
    
		String [] str = sb.toString().split(" ");
		
		for(int i=0,k=0;i<N;i++){
			for (int j=0;j<N;j++,k++) {
				filledArea[i][j] = Integer.parseInt(str[k]);
				}
			}
		str = null;
		sb = null;
		try {
			br.close();
			} catch (IOException e) {
				e.printStackTrace();
				}
		return filledArea;
}
	public static boolean searchThreeDeckShip(int[][] filledArea,int countOfThreeDeckShip){
		boolean flag=false;
		int start=0;
		int end=0;
		int count=0;
		for(int i=0;i<filledArea.length;i++){
			for(int j=0;j<filledArea.length;j++){				
				if(filledArea[i][j]==1){
					start=j;
					while(filledArea[i][j]!=0 && j<filledArea.length && count!=countOfThreeDeckShip){
						end=j;								
						if((end-start)==2){
							int k=j;
							int l=k-3;
							while(k!=l){
								filledArea[i][k]=3;
								k--;
							}
							count++;
							break;
						}
						j++;
						if(j==filledArea.length) break;
						}
					}
				}
			}
		//System.out.println(count);
		if(count==countOfThreeDeckShip) flag=true;
		return flag;
	}
	public static boolean searchDoubleDeckShip(int[][] filledArea,int countOfDoubleDeckShip){
		boolean flag=false;
		int start=0;
		int end=0;
		int count=0;
		for(int i=0;i<filledArea.length;i++){
			for(int j=0;j<filledArea.length;j++){				
				if(filledArea[i][j]==1){
					start=j;
					while(filledArea[i][j]!=0 && filledArea[i][j]!=3
							&& j<filledArea.length && count!=countOfDoubleDeckShip){
						end=j;										
						if((end-start)==1){
							int k=j;
							int l=k-2;
							while(k!=l){
								filledArea[i][k]=2;
								k--;
							}
							count++;
							break;
						}
						j++;
						if(j==filledArea.length) break;
					}
				}
			}
		}
		//System.out.println(count);
		if(count==countOfDoubleDeckShip) flag=true;
		return flag;
	}
	public static int countOfSingleDeckShip(int[][] filledArea){
		int count=0;
		for(int i=0;i<filledArea.length;i++){
			for(int j=0;j<filledArea.length;j++){
				if(filledArea[i][j]==1) count++;
			}
		}
		return count;
	}
	public static void main(String[] args){
		int N=9;
		int[][] filledArea=getFilledArea();
		for(int i=0;i<N;i++){
			for (int j=0;j<N;j++){
				if(filledArea[i][j]==0) System.out.print(" ");
				else System.out.print(filledArea[i][j]);
				}
			System.out.println();
			}
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int countOfSingleDeck=0;
		int countOfDoubleDeck=0;
		int countOfThreeDeck=0;
		try {
			System.out.println("Enter the number of single-deck ship: ");
			countOfSingleDeck=Integer.parseInt((String) br.readLine());
			System.out.println("Enter the number of double-decked ship: ");
			countOfDoubleDeck=Integer.parseInt((String) br.readLine());
			System.out.println("Enter the number of three-deck ship: ");
			countOfThreeDeck=Integer.parseInt((String) br.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		//System.out.println(countOfSingleDeck+" "+countOfDoubleDeck+" "+countOfThreeDeck);    
		
		boolean searchThreeDeckShip=searchThreeDeckShip(filledArea,countOfThreeDeck);
		//System.out.println("TD "+searchThreeDeckShip);
		
		boolean searchDoubleDeckShip = false;
		if(searchThreeDeckShip!=false){
			searchDoubleDeckShip=searchDoubleDeckShip(filledArea,countOfDoubleDeck);
			//System.out.println("DD "+searchDoubleDeckShip);
		}
		//else System.out.println("NO");
		
		boolean searchSingleDeckShip = false;
		if(searchDoubleDeckShip!=false){
			if(countOfSingleDeck==countOfSingleDeckShip(filledArea) ||
					countOfSingleDeck<countOfSingleDeckShip(filledArea)){
				searchSingleDeckShip=true;
			}
			//System.out.println("SD "+searchSingleDeckShip);
		}
		//else System.out.println("NO");
		for(int i=0;i<N;i++){
			for (int j=0;j<N;j++){
				if(filledArea[i][j]==0) System.out.print(" ");
				else System.out.print(filledArea[i][j]);
				}
			System.out.println();
			}
		if(searchSingleDeckShip==true && searchDoubleDeckShip==true && searchThreeDeckShip==true){
			System.out.println("YES");
		}
		else System.out.println("NO");
	}
}
