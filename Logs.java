import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Log {
    
    private ArrayList<String> logsJogo;
    private SimpleDateFormat formatoData;
    
    public Log() {
        this.logsJogo = new ArrayList<>();
        this.formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    public void adicionarLog(String categoria, String mensagem) {
        String timestamp = formatoData.format(new Date());
        String logCompleto = "[" + timestamp + "] [" + categoria + "] " + mensagem;
        
        logsJogo.add(logCompleto);
        System.out.println(logCompleto);
    }
    
    public void logInicioJogo(Dificuldade dificuldade) {
        adicionarLog("JOGO", "Jogo iniciado - Dificuldade: " + dificuldade);
    }
    
    public void logFimJogo(String resultado) {
        adicionarLog("JOGO", "Jogo finalizado - Resultado: " + resultado);
    }
    
    public void logHeroiEscolhido(String tipoHeroi, int posicao) {
        adicionarLog("HEROI", "Herói " + (posicao + 1) + " escolhido: " + tipoHeroi);
    }
    
    public void logMonstroGerado(String nomeMonstro) {
        adicionarLog("MONSTRO", "Monstro gerado: " + nomeMonstro);
    }
    
    public void logTotalPersonagens(int numHerois, int numMonstros) {
        adicionarLog("JOGO", "Total: " + numHerois + " heróis vs " + numMonstros + " monstros");
    }
    
    public void logInicioTurno(int numeroTurno) {
        adicionarLog("TURNO", "-- Turno " + numeroTurno + " iniciado --");
    }
    
    public void logAcaoTurno(String nomePlayer, String acao, String alvo, int dano) {
        adicionarLog("TURNO", nomePlayer + " usou " + acao + " em " + alvo + " (dano: " + dano + ")");
    }
    
    public void logFimTurno(int numeroTurno) {
        adicionarLog("TURNO", "-- Turno " + numeroTurno + " finalizado --");
    }
    
    public void logInicioBatalha() {
        adicionarLog("BATALHA", "Inicio da batalha");
    }
    
    public void logMortePlayer(String nomePlayer, String tipo) {
        adicionarLog("BATALHA", tipo + " " + nomePlayer + " foi derrotado!");
    }
    
    public void logVitoria(String time) {
        adicionarLog("BATALHA", "Time vencedor: " + time);
    }
    
    public void logFimBatalha() {
        adicionarLog("BATALHA", "Fim da batalha");
    }
    
    public ArrayList<String> getTodosLogs() {
        return new ArrayList<>(logsJogo);
    }
    
    public void exibirLogs() {
        System.out.println("\n-- Histórico de logs do jogo --");
        for (String log : logsJogo) {
            System.out.println(log);
        }
        System.out.println("--------------------\n");
    }
    
    public void limparLogs() {
        logsJogo.clear();
        adicionarLog("JOGO", "Logs limpos");
    }
    
    public int getTotalLogs() {
        return logsJogo.size();
    }
}