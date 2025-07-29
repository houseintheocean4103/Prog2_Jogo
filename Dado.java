import java.util.Random;

public class Dado {
	
	public Dado() {
		
	}
	
	public int dado(int t){
		
		Random random = new Random();
		
		int aleatorio = random.nextInt(t - 0 + 1) + 0;
		
		return aleatorio;
	}
}
