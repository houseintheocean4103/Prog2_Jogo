import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	Dificuldade dif;
	Log logger;
	
	public Game(Dificuldade d) {
		
		this.dif = d;
		this.logger = new Log();
		
	}
	
	public void iniciar() {
		logger.logInicioJogo(dif);
		
		ArrayList<Player> herois = gerar_herois();
		ArrayList<Player> monstros = gerar_monstros();

		logger.logTotalPersonagens(herois.size(), monstros.size());
		
		Turno turno = new Turno();
		logger.logInicioBatalha();
		
		turno.batalha(herois, monstros, dif);
		logger.logFimBatalha();
		logger.logFimJogo("Jogo concluído");
		
	}
	
	
	public ArrayList<Player> gerar_monstros(){
		
		ArrayList<Player> list = new ArrayList<>();
		
		list.add(new Ogro("monstro A"));
		logger.logMonstroGerado("Ogro (monstro A)");

		list.add(new Duende("monstro B"));
		logger.logMonstroGerado("Duende (monstro B)");

		list.add(new Alien("monstro C"));
		logger.logMonstroGerado("Alien (monstro C)");
		
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
				logger.logHeroiEscolhido("Guerreiro", i);
			}
			else if(h == 2) {
				list.add(new Mago("mago"));
				logger.logHeroiEscolhido("Mago", i);
			}
			else if(h == 3) {
				list.add(new Arqueiro("arqueiro"));
				logger.logHeroiEscolhido("Arqueiro", i);
			}
		}
		
		entrada.close();
		return list;
		
	}
}
