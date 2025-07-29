import java.util.ArrayList;
import java.util.Scanner;


public class Turno {
	
	int n_turno;
	
	public Turno() {
		n_turno = 1;
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
		case DIFICIL:
            for (Player listaDeMonstro : lista_de_monstros) {
                m = listaDeMonstro;
                v = m.getVida();
                m.setVida(v + (v / 4));
            }
		}
	}
	
	public void batalha(ArrayList<Player> herois, ArrayList<Player> monstros, Dificuldade d, Scanner scanner) {

        this.multiplicadorDiff(d, monstros);



		System.out.println("INIMIGOS ENCONTRADOS!");
		for(Player monstro : monstros) {
			System.out.println(monstro.nome+".");
		}

        ArrayList<Player> fila = new ArrayList<>(herois);
		fila.addAll(monstros);

		fila.sort((p1, p2) -> p2.destreza - p1.destreza);
		Dado r = new Dado();

		while(!herois.isEmpty() & !monstros.isEmpty()) {
			Player alvo;
            for(Player pivo : fila) {
				if(!herois.isEmpty() & !monstros.isEmpty()) {
					if(herois.contains(pivo)) {
						System.out.println("QUEM "+pivo.nome+" VAI ATACAR?");
						for(int i = 0; i < monstros.size(); i++) {
							System.out.println("["+i+"]"+monstros.get(i).getNome());
						}
						int monstro = scanner.nextInt();
						alvo = monstros.get(monstro);
						pivo.realizarAtaque(alvo);
						System.out.printf("%s atacou %s e causou %d de dano\n\n", pivo.getNome(), alvo.getNome(), pivo.getAtqR());

						System.out.println(alvo + "\n");

						if(alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							monstros.remove(alvo);
						}
                    } else {
						alvo = herois.get(r.dado(herois.size()));
						pivo.realizarAtaque(alvo);

						System.out.printf("%s atacou %s e causou %d de dano\n\n", pivo.getNome(), alvo.getNome(), pivo.getAtqR());

						System.out.println(alvo + "\n");

						if (alvo.getVida() <= 0) {
							System.out.printf("%s foi derrotado!\n\n", alvo.getNome());
							herois.remove(alvo);
						}
                    }
                    continue;
                }
				break;
			}
		}		
	}
	
	
}
