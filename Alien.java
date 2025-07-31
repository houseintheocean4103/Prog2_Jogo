// Classe Alien que herda da classe Player
public class Alien extends Player {
	private static final int destreza_base = 15; //Define a chance de ataque crítico
    private static final int vida_base = 55; //Vida inicial do guerreiro
    private static final int ataque_base = 17; //Poder de ataque inicial
    private static final int defesa_base = 10; //Capacidade de defesa
    private static final int velocidade_base = 6; //Critério de prioridade na lista de ataque
    
    //Construtor da classe Alien, passando os atributos base para o construtor da superclasse Player
    public Alien(String nome) {
        super(destreza_base, vida_base, ataque_base, defesa_base, velocidade_base, nome);
    }
    
    //Método que define como o Alien realiza um ataque
    @Override
    public ResAtq realizarAtaque(Player alvo) {
    	
    	//Determina se o ataque será crítico com base na chance 
        boolean isCritical = (Math.random() < (double) destreza /100);
        
        //Se for crítico, o dano é dobrado; caso contrário, usa o ataque normal
        int dano = isCritical ? this.getAtq() * 2 : this.getAtq();
        
        //Determina se errará com base na chance fixa
        boolean isMissed = (Math.random() < 0.2);
        
        //Calcula o dano final levando em consideração a defesa do alvo (mínimo zero)
        int danoFinal = isMissed ? 0 : Math.max(0, dano - alvo.getDef());
        
        //Aplica o dano ao alvo subtraindo da sua vida
        alvo.setVida(alvo.getVida() - danoFinal);
        
        //Registra o dano causado neste ataque
        this.setAtqR(danoFinal);
        
        //Retorna a natureza do ataque realizado
        return isMissed ? ResAtq.ERROU : 
        	(danoFinal <= 0 ? ResAtq.ERROU : 
        	(isCritical ? ResAtq.CRITICAL_HIT : ResAtq.ACERTOU));

    }

    //Representação do Alien com suas principais estatísticas
    @Override
    public String toString() {
        return String.format("%s (HP: %d, ATQ: %d, DEF: %d, DES: %d)",
               getNome(), getVida(), getAtq(), getDef(), getDest());
    }
}