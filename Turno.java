import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Turno {
	
	int n_turno;
	
	public Turno() {
		n_turno = 1;
	}
	
	public void result(ResAtq r, Player a, Player b) {
		
		switch(r) {
			case ACERTOU:
				System.out.printf("%s atacou %s e causou %d de dano.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				break;
			case CRITICAL_HIT:
				System.out.printf("%s atacou %s e causou %d de dano crítico.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				break;
			case ERROU:
				System.out.printf("%s errou %s, %d de dano causado.\n\n", a.getNome(), b.getNome(), a.getAtqR());
				break;
		} 
		
	}
	
	public void multiplicadorDiff(Dificuldade d, ArrayList<Player> lista_de_monstros) {
		
		Player m;
		int v;
		
		switch(d) {
			case FACIL:
				for (Player lista_de_monstro : lista_de_monstros) {
					m = lista_de_monstro;
					v = m.getVida();
					m.setVida(v - (v / 4));
				}
				break;

			case DIFICIL:
				for (Player listaDeMonstro : lista_de_monstros) {
					m = listaDeMonstro;
					v = m.getVida();
					m.setVida(v + (v / 4));
				}
				break;
			}
		}
	
	public void batalha(ArrayList<Player> herois, ArrayList<Player> monstros, Dificuldade d, Scanner scanner) {

        this.multiplicadorDiff(d, monstros);

		System.out.println("INIMIGOS ENCONTRADOS!");
		for(Player monstro : monstros) {
			System.out.println(monstro.nome+".");
		}
		System.out.println();

        ArrayList<Player> fila = new ArrayList<>(herois);
		fila.addAll(monstros);

		fila.sort((p1, p2) -> p2.velocidade - p1.velocidade);
		Dado r = new Dado();

		while(!herois.isEmpty() && !monstros.isEmpty()) {
			Player alvo;
            for(Player pivo : fila) {
            	if (pivo.getVida() <= 0) continue;
				if(!herois.isEmpty() & !monstros.isEmpty()) {
					System.out.printf("Turno %d: ", n_turno++);
					if(herois.contains(pivo)) {
						System.out.println("QUEM "+pivo+" VAI ATACAR?\n");
						for(int i = 0; i < monstros.size(); i++) {
							System.out.println("["+i+"]"+monstros.get(i).getNome()
									+" (HP: "+monstros.get(i).getVida()
									+", DEF: "+monstros.get(i).getDef() + ")");
						}
						int monstro = 0;
						
						boolean continua = false;
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
						
						alvo = monstros.get(monstro);
						
						result(pivo.realizarAtaque(alvo), pivo, alvo);

						System.out.println(alvo + "\n");

						if(alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							monstros.remove(alvo);
							if(monstros.isEmpty()) {
								System.out.printf("Acabaram os monstros.\n\n", monstros);
							} else {
								System.out.printf("Resta(m) %s no grupo dos monstros.\n\n", monstros);
							}
						}
                    } else {
						alvo = herois.get(r.dado(herois.size()));
						
						result(pivo.realizarAtaque(alvo), pivo, alvo);

						System.out.println(alvo + "\n");

						if (alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							herois.remove(alvo);
							if(herois.isEmpty()) {
								System.out.printf("Não restam heróis.\n\n", herois);
							} else {
								System.out.printf("Resta(m) %s no grupo dos heróis.\n\n", herois);
							}
						}
                    }
                    continue;
                }
				break;
			}
		} 
		if(herois.isEmpty()) {
			System.out.print("Jogo finalizado. Você perdeu...");
		} else {
			System.out.print("Jogo finalizado. Você ganhou!!!");
		}
		
	}
	
	
}