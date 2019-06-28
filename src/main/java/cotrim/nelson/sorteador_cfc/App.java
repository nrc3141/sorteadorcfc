package cotrim.nelson.sorteador_cfc;

import cotrim.nelson.sorteador_cfc.modelo.Sorteador;

/**
 * 
 *
 */
public class App {

    public static void main(String[] args) {
        Sorteador sorteador = new Sorteador(6)
            .addParticipante("NELSON", "Deidara", "Véia dos bonecos", "Sasori")
            .addParticipante("MINORU", "Kakashi", "Orochimaru", "Temari")
            .addParticipante("JOHNE", "Shikamaru", "Minato", "Ino")
            .addParticipante("RAPHA", "Naruto", "Orochimaru", "Kankuro")
            .addParticipante("ALEXANDRE", "Naruto", "Gai", "Rock Lee")
            .addParticipante("MARCELO", "Hanabi", "Racú", "Shizune")
            .addParticipante("GILL", "Temari", "Tentem", "???")
            .addParticipante("CÍCERO", "Orochimaru", "Guy", "Minato")
            .addParticipante("BRUNO", "Garra", "Kakashi", "Minato");
        sorteador.realizarSorteio();
        sorteador.mostrarResultado();
    }
}
