
public class Player {
	
	int destreza;
	int vida;
	int ataque;
	int defesa;
	int velocidade;
	String nome;
	int ataque_r = 0;

	
	public Player(int des, int vid, int ata, int def, int vel, String nom) {
		this.destreza = des;
		this.vida = vid;
		this.ataque = ata;
		this.defesa = def;
		this.velocidade = vel;
		this.nome = nom;
	}

	public int getDest() {
		return this.destreza;
	}
	
	public void setVida(int a) {
		this.vida = a;
	}
	
	public int getVida() {
		return this.vida;
	}

	public int getAtq() {
		return this.ataque;
	}
	
	public String getNome() {
		return this.nome;
	}

	public int getDef() {
		return this.defesa;
	}

	public void setAtqR(int a) {
		this.ataque_r = a;
	}
	
	public int getAtqR() {
		return this.ataque_r;
	}


	public ResAtq realizarAtaque(Player alvo) {
		return null;
	}

}