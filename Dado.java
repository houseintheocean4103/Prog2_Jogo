import java.util.Random; //Biblioteca para gerar números aleatórios

public class Dado { 
	
	public Dado() {
		
	}
	
	public int dado(int t){
		
		Random random = new Random(); //Instancia objeto Random

        return random.nextInt(t); //Retorna um número aleatório de 0 a t
	}
}