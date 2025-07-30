import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {

	public Game() {
		
	}
	
	public void iniciar() {
		
		Dificuldade dif = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bem vindo(a/e) ao Jornada do Herói!\n"
				+ "Em que lutas improváveis e nomes impossíveis são a norma!\n\n"
				+ "Como funciona:\n\n"
				+ "1.Escolha seu grupo de 3 heróis, das classes Guerreiro, Mago e Arqueiro, de acordo com a sua preferência de distribuição de atributos.\n"
				+ "Estes são: vida(pontos de vida base), ataque(dano causado), destreza(chance de incrementar o ataque) e defesa(capacidade de diminuir dano recebido)\n"
				+ "2.Um grupo de monstros aleatórios será criado com atributos semelhantes, e o jogador deve escolher qual atacar, de acordo com sua condição atual.\n"
				+ "3.Ao longo dos turnos, o objetivo é diminuir a vida do oponente até 0, causando sua morte.\n "
				+ "4.Após os 3 heróis atacarem, o grupo de monstros fará sua ofensiva, efetivamente afetando o time.\n"
				+ "5.Ganha o grupo que possuir personagens restantes ao final.\n"
				+ "6.Divirta-se!!!\n");

		System.out.println("Escolha a dificuldade:\n"
				+ "[1] Fácil\n"
				+ "[2] Médio\n"
				+ "[3] Difícil");
		
		int n = 0;
		boolean continua = false;
		
		do {
			try {
				n = scanner.nextInt();			
				if (n >= 1 && n <= 3) {
		            continua = true;
		        } else {
		            System.out.println("Digite um número entre 1 e 3.");
		        }
			} catch(InputMismatchException e) {
				System.err.printf("%nExceção %s%n", e);
				scanner.nextLine();
				System.out.println("Digite um único inteiro válido.");
			}
			
		} while(!continua);
			
		if(n == 1) {
			dif = Dificuldade.FACIL;
		}else if(n == 2) {
			dif = Dificuldade.MEDIO;
		}else if(n == 3) {
			dif = Dificuldade.DIFICIL;
		}
		

		ArrayList<Player> herois = gerar_herois(scanner);
		ArrayList<Player> monstros = gerar_monstros();
		
		Turno turno = new Turno();
		
		turno.batalha(herois, monstros, dif, scanner);
		
	}
		
	public ArrayList<Player> gerar_monstros(){
		
		ArrayList<String> nomes = new ArrayList<>();
		
		nomes.add("Maluco");
		nomes.add("Maldito");
		nomes.add("Rabugento");
		nomes.add("Seboso");
		nomes.add("Pitiú");
		nomes.add("Voraz");
		nomes.add("Mentiroso");
		nomes.add("Enganador");
		nomes.add("Preguiçoso");
		nomes.add("Desleal");
		nomes.add("Tosco");
		
		ArrayList<Player> list = new ArrayList<>();
		Dado d = new Dado();
		
		for(int i = 0; i < 3;i++) {
			int n = d.dado(3);
			int j = d.dado(nomes.size());
			String k = nomes.get(j);
			if(n == 0) {
				list.add(new Ogro("Ogro " + k));
			}
			else if(n == 1) {
				list.add(new Duende("Duende " + k));
			}
			else if(n == 2) {
				list.add(new Alien("Alien " + k));
			}
			nomes.remove(j);
		}
		
		return list;
	}
	
	public ArrayList<Player> gerar_herois(Scanner scanner){
		
		Dado d = new Dado();
		ArrayList<String> nomes = new ArrayList<>();
		
		nomes.add("Sereno");
		nomes.add("Bondoso");
		nomes.add("Perspicaz");
		nomes.add("Brocado");
		nomes.add("Alegre");
		nomes.add("Amado");
		nomes.add("Confiável");
		nomes.add("Valente");
		nomes.add("Humilde");
		
		ArrayList<Player> list = new ArrayList<>();
		int h = 0;
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Digite seus heróis ["+ i + "/3]: \n[1] Guerreiro \n[2] Mago \n[3] Arqueiro");
			
			boolean continua = false;
			do {
				try {
					h = scanner.nextInt();			
					if (h >= 1 && h <= 3) {
			            continua = true;
			        } else {
			            System.out.println("Digite um número entre 1 e 3.");
			        }
				} catch(InputMismatchException e) {
					System.err.printf("%nExceção %s%n", e);
					scanner.nextLine();
					System.out.println("Digite um único inteiro válido.");
				}
				
			} while(!continua);
			
			int j = d.dado(nomes.size());
			String k = nomes.get(j);
			
			if(h == 1) {
				list.add(new Guerreiro("Guerreiro " + k));
			}
			else if(h == 2) {
				list.add(new Mago("Mago " + k));
			}
			else if(h == 3) {
				list.add(new Arqueiro("Arqueiro " + k));
			}
			nomes.remove(j);
			
		}
		return list;
		
	}
}