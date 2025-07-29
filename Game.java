import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	Dificuldade dif;
	
	public Game(Dificuldade d) {
		
		this.dif = d;
		
	}
	
	public void iniciar() {
		
		ArrayList<Player> herois = gerar_herois();
		ArrayList<Player> monstros = gerar_monstros();
		
		Turno turno = new Turno();
		
		turno.batalha(herois, monstros, dif);
		
	}
		
	public ArrayList<Player> gerar_monstros(){
		
		ArrayList<Player> list = new ArrayList<>();
		Dado d = new Dado();
		
		for(int i = 0; i < 3;i++) {
			int n = d.dado(3 - 1);
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
	
	public ArrayList<Player> gerar_herois(){
		
		ArrayList<Player> list = new ArrayList<>();
		Scanner entrada = new Scanner(System.in);
		int h;
		
		System.out.println("Digite seus heróis: "
				+ "[1] Guerreiro"
				+ "[2] Mago"
				+ "[3] Arqueiro");
		
		for(int i = 0; i < 3; i++) {
			h = entrada.nextInt();
			if(h == 1) {
				list.add(new Guerreiro("guerreiro"));
			}
			else if(h == 2) {
				list.add(new Mago("mago"));
			}
			else if(h == 3) {
				list.add(new Arqueiro("arqueiro"));
			}
		}
		
		entrada.close();
		return list;
		
	}
}
