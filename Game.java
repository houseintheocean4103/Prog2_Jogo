import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	Dificuldade dif;
	
	public Game(Dificuldade d) {
		
		this.dif = d;
		
	}
	
	public void iniciar() {
		Scanner scanner = new Scanner(System.in);


		ArrayList<Player> herois = gerar_herois(scanner);
		ArrayList<Player> monstros = gerar_monstros();
		
		Turno turno = new Turno();
		
		turno.batalha(herois, monstros, dif, scanner);
		
	}
		
	public ArrayList<Player> gerar_monstros(){
		
		ArrayList<Player> list = new ArrayList<>();
		Dado d = new Dado();
		
		for(int i = 0; i < 3;i++) {
			int n = d.dado(3);
			if(n == 0) {
				list.add(new Ogro("Ogro"));
			}
			else if(n == 1) {
				list.add(new Duende("Duende"));
			}
			else if(n == 2) {
				list.add(new Alien("Alien"));
			}
		}
		
		return list;
	}
	
	public ArrayList<Player> gerar_herois(Scanner scanner){
		ArrayList<Player> list = new ArrayList<>();
		int h;
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Digite seus heróis ["+ i + "/3]: \n[1] Guerreiro \n[2] Mago \n[3] Arqueiro");
			h = scanner.nextInt();
			if(h == 1) {
				list.add(new Guerreiro("Guerreiro"));
			}
			else if(h == 2) {
				list.add(new Mago("Mago"));
			}
			else if(h == 3) {
				list.add(new Arqueiro("Arqueiro"));
			}
		}
		return list;
		
	}
}
