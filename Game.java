import java.util.ArrayList;              // Importa a classe ArrayList para manipular listas dinâmicas
import java.util.Scanner;                // Importa a classe Scanner para entrada de dados via console
import java.util.InputMismatchException; // Importa exceção para tratar entradas inválidas no Scanner

public class Game { 
	
	Log logger; //Variável de registro cujos métodos armazenam dados relativos a seu nome

	public Game() { //Construtor da classe de chamada principal
		
		this.logger = new Log();
	}
	
	public void iniciar() { //Método para iniciar a partida
		
		Dificuldade dif = null; //Enum de dificuldade
		Scanner scanner = new Scanner(System.in);
		
		//Como jogar
		System.out.println("Bem vindo(a/e) ao Jornada do Herói!\n"
				+ "Em que lutas improváveis e nomes impossíveis são a norma!\n\n"
				+ "Como funciona:\n\n"
				+ "1.Escolha seu grupo de 3 heróis, das classes Guerreiro, Mago e Arqueiro, de acordo com a sua preferência de distribuição de atributos.\n"
				+ "Estes são: vida(pontos de vida base), ataque(dano causado), destreza(chance de incrementar o ataque) e defesa(capacidade de diminuir dano recebido)\n"
				+ "2.Um grupo de monstros aleatórios será criado com atributos semelhantes, e o jogador deve escolher qual atacar, de acordo com sua condição atual.\n"
				+ "3.Quanto à dificuldade: Fácil enfraquece os monstros, Médio é o desafio padrão, e Difícil fortalece os monstros.\n"
				+ "4.Ao longo dos turnos, o objetivo é diminuir a vida do oponente até 0, causando sua morte.\n"
				+ "5.Após os 3 heróis atacarem, o grupo de monstros fará sua ofensiva, efetivamente afetando o time.\n"
				+ "6.Ganha o grupo que possuir personagens restantes ao final.\n"
				+ "7.Divirta-se!!!\n");
		
		//Menu de dificuldade
		System.out.println("Escolha a dificuldade:\n"
				+ "[1] Fácil\n"
				+ "[2] Médio\n"
				+ "[3] Difícil");
		
		int n = 0;
		boolean continua = false;
		
		do {
			try {
				n = scanner.nextInt();		//Restrição de intervalo e tratamento de exceção para a entrada da dificuldade, 
				if (n >= 1 && n <= 3) {     //a qual deve ser do tipo inteiro
		            continua = true;
		        } else {
		            System.out.println("Digite um número entre 1 e 3."); //Usuário é guiado à entrada adequada
		        }
			} catch(InputMismatchException e) {
				System.err.printf("%nExceção %s%n", e); 
				scanner.nextLine();
				System.out.println("Digite um único inteiro válido.");
			}
			
		} while(!continua);
			
		if(n == 1) {                    //Desvio para o Enum Dificuldade
			dif = Dificuldade.FACIL;
		}else if(n == 2) {
			dif = Dificuldade.MEDIO;
		}else if(n == 3) {
			dif = Dificuldade.DIFICIL;
		}
		logger.logInicioJogo(dif);

		ArrayList<Player> herois = gerar_herois(scanner); //Geração do grupo de heróis
		ArrayList<Player> monstros = gerar_monstros();    //Geração do grupo de monstros
		
		logger.logTotalPersonagens(herois.size(), monstros.size());
		
		Turno turno = new Turno(logger); //Instancia objeto Turno, para propriamente realizar o embate
		logger.logInicioBatalha();		
		
		turno.batalha(herois, monstros, dif, scanner); //Método batalha() de Turno
		logger.logFimBatalha();
		logger.logFimJogo(scanner);
		
	}
		
	public ArrayList<Player> gerar_monstros(){ //Método para gerar grupo de monstros
		
		ArrayList<String> nomes = new ArrayList<>(); //Lista de nomes para diversificação
		
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
		
		ArrayList<Player> list = new ArrayList<>(); //Lista de objetos Player
		Dado d = new Dado(); //Instancia um objeto Dado
		
		for(int i = 0; i < 3;i++) {
			int n = d.dado(3);            //Adiciona um monstro aleatoriamente
			int j = d.dado(nomes.size()); //incrementa o nome do monstro aleatoriamente
			String k = nomes.get(j);
			if(n == 0) {
				list.add(new Ogro("Ogro " + k));
				logger.logMonstroGerado("Ogro " + k);
			}
			else if(n == 1) {
				list.add(new Duende("Duende " + k));
				logger.logMonstroGerado("Duende " + k);
			}
			else if(n == 2) {
				list.add(new Alien("Alien " + k));
				logger.logMonstroGerado("Alien " + k);
			}
			nomes.remove(j); //Garante que o nome não seja repetido
		}
		
		return list; //Retorna o grupo de monstros
	}
	
	public ArrayList<Player> gerar_herois(Scanner scanner){ //Método para gerar grupo de heróis
		
		Dado d = new Dado(); //Instancia um objeto Dado
		ArrayList<String> nomes = new ArrayList<>(); //Lista de nomes para diversificação
		
		nomes.add("Sereno");
		nomes.add("Bondoso");
		nomes.add("Perspicaz");
		nomes.add("Brocado");
		nomes.add("Alegre");
		nomes.add("Amado");
		nomes.add("Confiável");
		nomes.add("Valente");
		nomes.add("Humilde");
		
		ArrayList<Player> list = new ArrayList<>(); //Lista de objetos Player
		int h = 0;
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Digite seus heróis ["+ i + "/3]: \n[1] Guerreiro \n[2] Mago \n[3] Arqueiro");
			
			boolean continua = false;
			do {
				try {
					h = scanner.nextInt();   //Restrição de intervalo e tratamento de exceção para a entrada da dificuldade,
					if (h >= 1 && h <= 3) {  //a qual deve ser do tipo inteiro
			            continua = true;
			        } else {
			            System.out.println("Digite um número entre 1 e 3.");
			        }
				} catch(InputMismatchException e) {
					System.err.printf("%nExceção %s%n", e);  //Usuário é guiado à entrada adequada
					scanner.nextLine();
					System.out.println("Digite um único inteiro válido.");
				}
				
			} while(!continua);
			
			int j = d.dado(nomes.size());  
			String k = nomes.get(j);
			
			if(h == 1) {                                   //Adiciona um herói sob demanda
				list.add(new Guerreiro("Guerreiro " + k)); //Incrementa o nome do herói aleatoriamente
				logger.logHeroiEscolhido("Guerreiro " + k, i);
			}
			else if(h == 2) {
				list.add(new Mago("Mago " + k));
				logger.logHeroiEscolhido("Mago " + k, i);
			}
			else if(h == 3) {
				list.add(new Arqueiro("Arqueiro " + k));
				logger.logHeroiEscolhido("Arqueiro " + k, i);
			}
			nomes.remove(j); //Retorna o grupo de monstros
			
		}
		return list;
		
	}
}