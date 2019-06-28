package cotrim.nelson.sorteador_cfc.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Grupo que será os participantes na fase de luta por grupos
 * 
 * @author drigo
 *
 */
public class Grupo {
    private final Character nome;
    private final List<Participante> participantes = new ArrayList<>();
    private final Integer capacidadeParticipante;

    public Grupo(Character nome, Integer capacidadeParticipante) {
        super();
        this.nome = nome;
        this.capacidadeParticipante = capacidadeParticipante;
    }

    /**
     * Tenta adicionar um participante ao grupo. Não é permitido adicionar um
     * participante ao grupo caso o jogador já tenha uma participação neste grupo ou
     * se o grupo já está cheio.
     * 
     * @param participante o participante a ser adicionado.
     * @return true se foi permitido adicionar o participante ao grupo
     */
    public boolean adicionarParticipante(Participante participante) {
        if (jahTemJogador(participante.getJogador()) || isGrupoCheio()) {
            return false;
        }
        this.participantes.add(participante);
        return true;
    }

    /**
     * Verifica se nesse grupo já tem um participante referente um jogador
     * 
     * @param jogador o nome do jogador
     * @return true se já tem um participante referente ao jogador
     */
    private boolean jahTemJogador(String jogador) {
        return this.participantes.stream().anyMatch(participante -> participante.isMesmoJodaor(jogador));
    }

    /**
     * Verifica se o grupo já atingiu a quantidade máxima de participantes
     * permitido.
     * 
     * @return
     */
    private boolean isGrupoCheio() {
        return this.participantes.size() >= this.capacidadeParticipante;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Grupo: " + this.nome + "\n");
        this.participantes.forEach(sb::append);
        return sb.toString();
    }

}
