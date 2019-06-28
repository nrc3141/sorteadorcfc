package cotrim.nelson.sorteador_cfc.modelo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cotrim.nelson.sorteador_cfc.exception.SorteioJahRealizadoException;

/**
 * O sorteador recebe um conjunto de participantes e então distribúi
 * aleatoriamente cada participante para um grupo. Um jogador não pode aparece
 * em um grupo duas vezes.
 * 
 * @author drigo
 *
 */
public class Sorteador {

    private final Integer qtdGrupo;
    private final List<Grupo> gruposGerado = new ArrayList<>();
    private Integer capacidadeDosGrupos;
    private Integer qtdGruposComUmAmais;
    private final List<Participante> participantesAseremSorteadors = new ArrayList<>();
    private boolean sorteado;
    private static final Random tratadorDeAleatoriedade = new SecureRandom();

    public Sorteador(Integer qtdGrupo) {
        super();
        this.qtdGrupo = qtdGrupo;
    }

    /**
     * Adiciona um participante à lista de participantes que deverão ser sorteado
     * para os grupos
     * 
     * @param jogador    o nome do jogador
     * @param personagem os nomes do personagens que o jogador irá utilizar
     * @return o próprio objeto para permitir adicionar vários participantes em
     *         cadeia
     */
    public Sorteador addParticipante(String jogador, String... personagens) {
        if (personagens.length == 0) {
            throw new IllegalArgumentException("Adiciona ao menos um personagem para este jogador");
        }
        for (String personagem : personagens) {
            this.participantesAseremSorteadors.add(new Participante(jogador, personagem));
        }
        return this;
    }

    /**
     * Realiza todas as operaçoes para realizar o sorteio: verifica se já foi
     * sorteado, define o nome e a capacidade de cada grupo, e distribui os
     * jogadores entre os grupos.
     */
    public void realizarSorteio() {
        verificarSeJaFoiSorteado();
        definirCapacidadeDosGrupos();
        definirGruposEseusNomes();
        distribuirParticipantes();
    }

    /**
     * Imprime no console o resultado do sorteio.
     */
    public void mostrarResultado() {

        if (!this.sorteado) {
            throw new IllegalStateException("Não foi realizado o sorteio ainda");
        }

        this.gruposGerado.forEach(System.out::println);
    }

    /**
     * Distribui os jogaodores aletatoriamente nos grupos definidos
     */
    private void distribuirParticipantes() {
        
        Collections.shuffle(this.participantesAseremSorteadors);
        
        for (Participante participante : participantesAseremSorteadors) {
            Grupo grupoSorteado;
            do {
                int indexGrupoSorteado = tratadorDeAleatoriedade.nextInt(qtdGrupo);
                grupoSorteado = this.gruposGerado.get(indexGrupoSorteado);
            } while (!grupoSorteado.adicionarParticipante(participante));
        }
    }

    /**
     * Define a capacidade de cada grupo e o nome de cada um.
     */
    private void definirGruposEseusNomes() {
        int qtdGrupoQueFaltamAumentarCapacitade = qtdGruposComUmAmais;
        char nomeGrupoAtual = 'A';

        for (int i = 0; i < qtdGrupo; i++) {
            int capacidadeDoGrupo = capacidadeDosGrupos;
            if (qtdGrupoQueFaltamAumentarCapacitade > 0) {
                capacidadeDoGrupo++;
                qtdGrupoQueFaltamAumentarCapacitade--;
            }
            Grupo novoGrupo = new Grupo(nomeGrupoAtual, capacidadeDoGrupo);
            this.gruposGerado.add(novoGrupo);
            nomeGrupoAtual++;
        }
    }

    /**
     * Define o capacidade geral de todos os grupos e quantos grupo terão um
     * participante a mais.
     */
    private void definirCapacidadeDosGrupos() {
        this.capacidadeDosGrupos = participantesAseremSorteadors.size() / qtdGrupo;
        this.qtdGruposComUmAmais = participantesAseremSorteadors.size() % qtdGrupo;
    }

    /**
     * Verifica se já foi feito um sorteio para este sorteador. Caso sim, lança um
     * unchecked exception.
     */
    private void verificarSeJaFoiSorteado() {
        if (this.sorteado) {
            throw new SorteioJahRealizadoException();
        }

        this.sorteado = true;
    }

}
