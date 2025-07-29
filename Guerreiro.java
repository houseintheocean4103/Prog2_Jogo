
public class Guerreiro extends Player {
	private static final int destreza_base = 40;
    private static final int vida_base = 15;
    private static final int ataque_base = 10;
    private static final int defesa_base = 5;
    private static final int velocidade_base = 12;

    public Guerreiro(String nome) {
        super(destreza_base, vida_base, ataque_base, defesa_base, velocidade_base, nome);
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {

        boolean isCritical = (Math.random() < destreza/100);
        int dano = isCritical ? this.getAtq() * 2 : this.getAtq();
        int danoFinal = Math.max(0, dano - alvo.getDef());
        alvo.setVida(alvo.getVida() - danoFinal);
        this.setAtqR(danoFinal);

        return isCritical ? ResultadoAtaque.CRITICAL_HIT : 
               (danoFinal > 0 ? ResultadoAtaque.ACERTOU : ResultadoAtaque.ERROU);
    }

    @Override
    public String toString() {
        return String.format("Guerreiro %s (HP: %d, ATQ: %d, DEF: %d)", 
               getNome(), getVida(), getAtq(), getDef());
    }
}
