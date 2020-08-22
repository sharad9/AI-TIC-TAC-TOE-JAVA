import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Board{
	Scanner sc=new Scanner(System.in);
	
String box[][]=new String [3][3];

	public void display(){
		
		for(int i=0;i<box.length;i++){
			System.out.print("|");
			for(int j=0;j<box[i].length;j++){
				
			System.out.print((box[i][j]==null? " ":box[i][j])+"|");
			}
			System.out.println();
		}
	}
	public void placeElement(Point p,String s){
		
		box[p.x][p.y]=s;
    	}
    public void remove(Point p){
    	box[p.x][p.y]=null;
    	} 	
		public List<Point> getAvailableBoxes(){
			List<Point> availableBoxes;
		availableBoxes=new ArrayList<Point>();
		for(int i=0;i<box.length;i++){
			for(int j=0;j<box[i].length;j++){
				if(box[i][j]==null){
					availableBoxes.add(new Point(i,j));
					}
				}
			}
			return availableBoxes;
		}
    		
    public void takeHumanInput(){
    	int i=sc.nextInt();
    	List <Point> EmptySpaces=getAvailableBoxes();
    	placeElement(EmptySpaces.get(i-1),"X");
    	}
	public boolean HasWon(String s){	   
if((box[0][0]==box[1][1]&&box[1][1]==box[2][2]&&box[2][2]==s)||(box[0][2]==box[1][1]&&box[1][1]==box[2][0]&&box[2][0]==s))
	  { return true;}  
		for(int i=0;i<box.length;i++){
		     if(box[i][0]==box[i][1]&&box[i][1]==box[i][2]&&box[i][2]==s||box[0][i]==box[1][i]&&box[1][i]==box[2][i]&&box[2][i]==s){  
		     
		     return true; }
		     }		    
		  	
		  	 return false;
		  			
		}
	
	public boolean isGameOver(){
		return (HasWon("X")||HasWon("O")||getAvailableBoxes().isEmpty());
		}

	public int minimax(int depth,boolean isMax){
		List <Point> boxAvailable=getAvailableBoxes();
		
		if(HasWon("O")){return 1;}
		if(HasWon("X")){return -1;}
		if(boxAvailable.isEmpty()){ return 0;}
	
	int min=Integer.MAX_VALUE; int max=Integer.MIN_VALUE;	
	 for(int i=0;i<boxAvailable.size();i++){
 	if(isMax){  
   	
		  	placeElement(boxAvailable.get(i),"O");
		  
		      max=Math.max(minimax(depth+1,!isMax),max);
		      remove(boxAvailable.get(i));
		}
		else {
			  
		  	placeElement(boxAvailable.get(i),"X");
	      	min=Math.min(minimax(depth+1,!isMax),min);	
		  	remove(boxAvailable.get(i));
			}
		
	}
	return isMax?max+depth:min;
	}
	

	
	 	public  Point findBestMove(){
	 		Point A=new Point(-1,-1);
			List<Point> leftBoxes=getAvailableBoxes();
			int bestVal=Integer.MIN_VALUE;
		
			for(int i=0;i<leftBoxes.size();i++){
				 
				placeElement(leftBoxes.get(i),"O");
				
				int moveVal=minimax(0,false);
				if(moveVal>bestVal){A=leftBoxes.get(i); }
				bestVal=Math.max(moveVal,bestVal);
				remove(leftBoxes.get(i));
				}
				return A;
			}	
			
}

class TicTacToe{
	public static void main(String args[]){
		Board b=new Board();
		
		Random rand=new Random();
		System.out.println("HELLO THIS IS ARTIFICIAL INTELLIGENT : ");
		int toss=rand.nextInt(2);
		if(toss==0){System.out.println("I won the toss");
		
	     Point c=new Point(2,2);//new Point(rand.nextInt(3),rand.nextInt(3));
	     b.placeElement(c,"O");
	    
	     }else{System .out.println("You Won The toss :");}
	     while(!b.isGameOver()){
	     System.out.println("Make Your Move:");
	      b.display();
	      System.out.println("_______");
	     b.takeHumanInput();
	   
	   if(b.isGameOver()){break;}
	    
	    b.placeElement(b.findBestMove(),"O");
	    
		}
		      
		     b.display();
 			 if (b.HasWon("O")) 
            System.out.println("Unfortunately, you lost!");
        else if (b.HasWon("X")) 
            System.out.println("You win!"); //impossible
        else 
            System.out.println("It's a draw!");
	}
	
	}
