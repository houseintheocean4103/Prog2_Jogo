
public class Player {
	
	int destreza;     //Define a chance de ataque crítico
	int vida;         //Vida inicial do guerreiro
	int ataque;       //Poder de ataque base
	int defesa;       //Capacidade de defesa
	int velocidade;   //Critério de prioridade na lista de ataque
	String nome;      //Nome aleatório
	int ataque_r = 0; //Ataque relativo(pós alterações)

	//Construtor
	public Player(int des, int vid, int ata, int def, int vel, String nom) {
		this.destreza = des;
		this.vida = vid;
		this.ataque = ata;
		this.defesa = def;
		this.velocidade = vel;
		this.nome = nom;
	}
	
	//Setters e Getters
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

	//Método de ação/interação para sobreescrita
	public ResAtq realizarAtaque(Player alvo) { 
		return null;
	}

}