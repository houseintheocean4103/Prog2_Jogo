public class Mago extends Player {
    private static final int destreza_base = 33;
    private static final int vida_base = 30;
    private static final int ataque_base = 15;
    private static final int defesa_base = 15;
    private static final int velocidade_base = 12;

    public Mago(String nome) {
        super(destreza_base, vida_base, ataque_base, defesa_base, velocidade_base, nome);
    }

    @Override
    public ResAtq realizarAtaque(Player alvo) {

        boolean isCritical = (Math.random() < (double) destreza /100);
        int dano = isCritical ? this.getAtq() * 2 : this.getAtq();
        int danoFinal = Math.max(0, dano - alvo.getDef());
        alvo.setVida(alvo.getVida() - danoFinal);
        this.setAtqR(danoFinal);
        
        return isCritical ? ResAtq.CRITICAL_HIT : 
            (danoFinal > 0 ? ResAtq.ACERTOU : ResAtq.ERROU);

    }
    @Override
    public String toString() {
        return String.format("%s (HP: %d, ATQ: %d, DEF: %d)",
               getNome(), getVida(), getAtq(), getDef());
    }
}