import java.util.Random;

public class Dado {
	
	public Dado() {
		
	}
	
	public int dado(int t){
		
		Random random = new Random();

        return random.nextInt(t);
	}
}