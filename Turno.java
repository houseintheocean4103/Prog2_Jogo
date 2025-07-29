import java.util.ArrayList;


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
			for(int i = 0; i < lista_de_monstros.size(); i++) {
				m = lista_de_monstros.get(i);
				v = m.getVida(); 
				m.setVida(v - ((int)v/4));
			}
		case DIFICIL:
			for(int i = 0; i < lista_de_monstros.size(); i++) {
				m = lista_de_monstros.get(i);
				v = m.getVida(); 
				m.setVida(v + ((int)v/4));
			}
		}
	}
	
	public void batalha(ArrayList<Player> herois, ArrayList<Player> monstros, Dificuldade d) {
		
		this.multiplicadorDiff(d, monstros);
		
		Player pivo;
		Player inimigo;
		Dado r = new Dado();
		
		while(herois.size() != 0 & monstros.size() != 0) {
			
			int i = r.dado(herois.size() - 1);
			int j = r.dado(monstros.size() - 1);
			
			boolean heroi_comeca = (herois.get(i).getVel() > monstros.get(j).getVel());
			
			if(heroi_comeca) {
				pivo = herois.get(i);
				inimigo = monstros.get(j);
			} else {
				pivo = monstros.get(j);
				inimigo = herois.get(i);
			}
			
						
			ResultadoAtaque atq_res = pivo.realizarAtaque(inimigo);

			
			System.out.printf("%s atacou %s e causou %d de dano\n\n", pivo.getNome(), inimigo.getNome(), pivo.getAtqR());
			
			System.out.println(pivo.toString());
			System.out.println(inimigo.toString() + "\n");
			
			if(inimigo.getVida() <= 0) {
				System.out.printf("%s foi derrotado!\n\n", inimigo.getNome());
				if(herois.contains(inimigo)){
					herois.remove(i);
				} else if(monstros.contains(inimigo)) {
					monstros.remove(j);
				}
			}					
		}		
	}
	
	
}
