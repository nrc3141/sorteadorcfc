package cotrim.nelson.sorteador_cfc.modelo;

/**
 * Um participante do campeonato que será identificado pelo nome do jogador e do
 * personagem
 * 
 * @author drigo
 *
 */
public class Participante {
    private final String jogador;
    private final String personagem;

    public Participante(String jogador, String personagem) {
        super();
        this.jogador = jogador;
        this.personagem = personagem;
    }

    /**
     * Verifica se esse participante refere-se ao mesmo jogador
     * 
     * @param jogador o jogador
     * @return retorna true se é o mesmo jogador
     */
    public boolean isMesmoJodaor(String jogador) {
        return this.jogador.equals(jogador);
    }

    // PADRÕES

    public String getJogador() {
        return jogador;
    }

    @Override
    public String toString() {
        return this.jogador + " (" + this.personagem + ")\n";
    }

}
