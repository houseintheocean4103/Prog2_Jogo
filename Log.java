import java.util.ArrayList; //Biblioteca para manipulação de listas dinâmicas 
import java.util.Date;      //Biblioteca para manipulação de datas
import java.util.InputMismatchException; //Captura de exceção para adequação de entrada
import java.text.SimpleDateFormat; //Biblioteca para manipulação de datas
import java.util.Scanner; //Biblioteca para manipulação do fluxo de entrada

public class Log { //Classe responsável por coletar aspectos da execução do programa
    
    private ArrayList<String> logsJogo; //Lista de variados tipos de registros
    private SimpleDateFormat formatoData; //Formatação de data e horário
    
    public Log() {
        this.logsJogo = new ArrayList<>();
        this.formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    public void adicionarLog(String categoria, String mensagem) { //Captura o horário, a categoria da informação e a própria informação para armazenar
        String timestamp = formatoData.format(new Date());
        String logCompleto = "[" + timestamp + "] [" + categoria + "] " + mensagem;
        
        logsJogo.add(logCompleto); //Adiciona a informação ao registro geral
    }
    
    public void logInicioJogo(Dificuldade dificuldade) { //Captura informação de início do jogo
        adicionarLog("JOGO", "Jogo iniciado - Dificuldade: " + dificuldade);
    }
    
    public void logFimJogo(Scanner scanner) { //Captura informação de fim de jogo
        
    	System.out.println("\nDeseja ver o log da partida?\n"
				+ "[1] Sim\n"
				+ "[2] Não");
		
		int n = 0;
		boolean continua = false;
		
		do {
			try {
				n = scanner.nextInt();		//Restrição de intervalo e tratamento de exceção para a entrada de dados, 
				if (n >= 1 && n <= 2) {     //a qual deve ser do tipo inteiro
		            continua = true;
		        } else {
		            System.out.println("Digite 1 ou 2."); //Usuário é guiado à entrada adequada
		        }
			} catch(InputMismatchException e) {
				System.err.printf("%nExceção %s%n", e); 
				scanner.nextLine();
				System.out.println("Digite um único inteiro válido.");
			}
			
		} while(!continua);
			
		if(n == 1) {                    //Desvio para conforto do usuário
			System.out.println("\nSegue o registro da partida:");       
	        exibirLogs(); 
		}
		System.out.print("Programa finalizado.");
		scanner.close();
    }
    
    public void logHeroiEscolhido(String tipoHeroi, int posicao) { //Captura informação de Player
        adicionarLog("HEROI", "Herói " + (posicao + 1) + " escolhido: " + tipoHeroi);
    }
    
    public void logMonstroGerado(String nomeMonstro) { //Captura informação de Player
        adicionarLog("MONSTRO", "Monstro gerado: " + nomeMonstro);
    }
    
    public void logTotalPersonagens(int numHerois, int numMonstros) { //Captura informação de todos os Players
        adicionarLog("JOGO", "Total: " + numHerois + " heróis vs " + numMonstros + " monstros");
    }
    
    public void logInicioTurno(int numeroTurno) { //Captura informação de início do turno
        adicionarLog("TURNO", "-- Turno " + numeroTurno + " iniciado --");
    }
    
    public void logAcaoTurno(String nomePlayer, String acao, String alvo, int dano, int vida) { //Captura informação de ação do turno
        adicionarLog("TURNO", nomePlayer + acao + " o " + alvo + " (dano: " + dano + ", vida: " + vida + ")");
    }
    
    public void logFimTurno(int numeroTurno) { //Captura informação de fim do turno
        adicionarLog("TURNO", "-- Turno " + numeroTurno + " finalizado --");
    }
    
    public void logInicioBatalha() { //Captura informação de início da batalha
        adicionarLog("BATALHA", "Inicio da batalha.");
    }
    
    public void logMortePlayer(String nomePlayer, String tipo) { //Captura informação de Player
        adicionarLog("BATALHA", tipo + " " + nomePlayer + " foi derrotado!");
    }
    
    public void logVitoria(String time) { //Captura informação de resultado da batalha
        adicionarLog("BATALHA", "Time vencedor: " + time);
    }
    
    public void logFimBatalha() { //Captura informação de fim
        adicionarLog("BATALHA", "Fim da batalha.");
    }
    
    public void exibirLogs() { //método para impressão dos registros
        System.out.println("\n-- Histórico de logs do jogo --");
        for (String log : logsJogo) {
            System.out.println(log);
        }
        System.out.println("--------------------");
    }

}