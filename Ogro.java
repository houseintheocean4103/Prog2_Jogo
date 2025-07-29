public class Ogro extends Player {
    private static final int destreza_base = 40;
    private static final int vida_base = 50;
    private static final int ataque_base = 10;
    private static final int defesa_base = 5;
    private static final int velocidade_base = 10;

    public Ogro(String nome) {
        super(destreza_base, vida_base, ataque_base, defesa_base, velocidade_base, nome);
    }

    @Override
    public void realizarAtaque(Player alvo) {
     
        boolean isCritical = (Math.random() < (double) destreza /100);
        int dano = isCritical ? this.getAtq() * 2 : this.getAtq();
        int danoFinal = Math.max(0, dano - alvo.getDef());
        alvo.setVida(alvo.getVida() - danoFinal);
        this.setAtqR(danoFinal);

    }

    @Override
    public String toString() {
        return String.format("%s (HP: %d, ATQ: %d, DEST: %d)",
               getNome(), getVida(), getAtq(), getDest());
    }
}