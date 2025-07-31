import java.util.ArrayList;              // Importa a classe ArrayList para manipular listas dinâmicas
import java.util.Scanner;                // Importa a classe Scanner para entrada de dados via console
import java.util.InputMismatchException; // Importa exceção para tratar entradas inválidas no Scanner

public class Turno {
	
	private static int n_turno; //Variável que incrementa conforme o turno da partida
	private Log logger; //Variável de registro cujos métodos armazenam dados relativos a seu nome
	
	public Turno(Log log) {
		n_turno = 1;
		this.logger = log;
	}
	
	public void result(ResAtq r, Player a, Player b) { //Desvio de resposta ao usuário conforme resultado do método realizarAtaque()
		                                               //Destaca o atacante, o alvo e o dano relativo
		switch(r) {
			case ACERTOU:
				System.out.printf("%s atacou %s e causou %d de dano.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				logger.logAcaoTurno(a.getNome(), " atacou", b.getNome(), a.getAtqR(), b.getVida());
				break;
			case CRITICAL_HIT:
				System.out.printf("%s atacou %s e causou %d de dano crítico.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				logger.logAcaoTurno(a.getNome(), " atacou criticamente", b.getNome(), a.getAtqR(), b.getVida());
				break;
			case ERROU:
				System.out.printf("%s errou %s, %d de dano causado.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				logger.logAcaoTurno(a.getNome(), " errou", b.getNome(), a.getAtqR(), b.getVida());
				break;
		} 
		
	}
	
	//Método de desvio de nível de dificuldade
	public void multiplicadorDiff(Dificuldade d, ArrayList<Player> lista_de_monstros) { 
		
		Player m;
		int v;
		
		switch(d) {
			case FACIL:
				for (Player lista_de_monstro : lista_de_monstros) { //Itera sobre cada elemento da lista, diminuindo sua vida em 25%
					m = lista_de_monstro;
					v = m.getVida();
					m.setVida(v - (v / 6));
				}
				break;

			case DIFICIL:
				for (Player listaDeMonstro : lista_de_monstros) { //Itera sobre cada elemento da lista, aumentando sua vida em 25%
					m = listaDeMonstro;
					v = m.getVida();
					m.setVida(v + (v / 6));
				}
				break;
			}            //Dado que Médio é a dificuldade padrão, não houve necessidade de desvio
		}
	
	//Método principal da classe que executa a lógica do combate entre heróis e monstros
    //Controla os turnos, define a ordem de ataque, realiza ataques e verifica se alguém venceu
   
	public void batalha(ArrayList<Player> herois, ArrayList<Player> monstros, Dificuldade d, Scanner scanner) {

        this.multiplicadorDiff(d, monstros); //Inicialmente aplica a dificuldade desejada à monstros
        
        //Apresenta os monstros gerados
		System.out.println("\nINIMIGOS ENCONTRADOS!"); 
		for(Player monstro : monstros) {
			System.out.println(monstro.nome+".");   
		}
		System.out.println();

        ArrayList<Player> fila = new ArrayList<>(herois);  
		fila.addAll(monstros);
		
		//Cria a fila de combate (heróis + monstros), ordenada por velocidade
		fila.sort((p1, p2) -> p2.velocidade - p1.velocidade); // Ordem decrescente
		
		//Objeto auxiliar para sorteio de alvos
		Dado r = new Dado();

		//Enquanto ambos os lados tiverem integrantes vivos, a batalha continua
		while(!herois.isEmpty() && !monstros.isEmpty()) {
			Player alvo;
			
            for(Player pivo : fila) {
            	if (pivo.getVida() <= 0) continue; //Pula turno se personagem já está derrotado
            	
				if(!herois.isEmpty() & !monstros.isEmpty()) { //Ainda há combate possível
					
					System.out.printf("Turno %d: ", n_turno++);
					logger.logInicioTurno(n_turno);
					
					if(herois.contains(pivo)) {
						//Se o pivo atual é um herói, jogador escolhe o monstro a ser atacado
						System.out.println("QUEM "+pivo+" VAI ATACAR?\n");
						
						for(int i = 0; i < monstros.size(); i++) { //Apresenta os status dos monstros
							System.out.println("["+i+"]"+monstros.get(i)+ ")");
						}
						
						int monstro = 0;		
						boolean continua = false;
						
						//Restrição de intervalo de entrada e tratamento de exceção para tipos de entrada errados
						do {
							try {
								monstro = scanner.nextInt();			
								if (monstro >= 0 && monstro <= monstros.size() - 1) {
						            continua = true;
						        } else {
						            System.out.printf("Digite um número entre 0 e %d.\n", monstros.size() - 1);
						        }
							} catch(InputMismatchException e) {
								System.err.printf("%nExceção %s%n", e);
								scanner.nextLine();
								System.out.println("Digite um único inteiro válido.");
							}
							
						} while(!continua);
						
						alvo = monstros.get(monstro); //Seleciona o monstro como alvo
						result(pivo.realizarAtaque(alvo), pivo, alvo); //Executa o ataque
						System.out.println(alvo + "\n");

						//Verifica se o monstro foi derrotado, determinando uma baixa ou uma vitória
						if(alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							logger.logMortePlayer(alvo.getNome(), "Monstro");
							monstros.remove(alvo);
							if(monstros.isEmpty()) {
								System.out.printf("Os Monstros foram derrotados!\n\n", monstros);
							} else {
								System.out.printf("Resta(m) %s no grupo dos Monstros.\n\n", monstros);
							}
						}
						logger.logFimTurno(n_turno);
						
                    } else {
                    	//Se o pivo atual é um monstro, ele ataca um herói aleatório
						alvo = herois.get(r.dado(herois.size()));		
						result(pivo.realizarAtaque(alvo), pivo, alvo);
						System.out.println(alvo + "\n");

						//Verifica se o herói foi derrotado, determinando uma baixa ou uma derrota
						if (alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							logger.logMortePlayer(alvo.getNome(), "Herói");
							herois.remove(alvo);
							if(herois.isEmpty()) {
								System.out.printf("Os Heróis lutaram bravamente.\n\n", herois);
							} else {
								System.out.printf("Resta(m) %s no grupo dos Heróis.\n\n", herois);
							}
						}
						logger.logFimTurno(n_turno);
                    }
                    continue; //Próximo da fila
				}    
			break; //Sai do for interno se não há mais batalha
			}
		} 
           
        // Mensagens de fim de jogo
		if(herois.isEmpty()) {
			System.out.print("Jogo finalizado. Você perdeu...\n");
			logger.logVitoria("Monstros");
		} else {
			System.out.print("Jogo finalizado. Você ganhou!!!\n");
			logger.logVitoria("Heróis");
		}
		
	}
		
}

