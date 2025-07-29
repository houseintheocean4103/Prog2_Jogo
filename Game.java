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
		
		list.add(new Ogro("monstro A"));
		list.add(new Duende("monstro B"));
		list.add(new Alien("monstro C"));
		
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
