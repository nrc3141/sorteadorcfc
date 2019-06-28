package cotrim.nelson.sorteador_cfc.exception;

/**
 * Exceção de tempo de execução a ser lançada quando tenta sortear novamente
 * atraves de Sorteador que já realizou o sorteio.
 * 
 * @author drigo
 *
 */
public class SorteioJahRealizadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SorteioJahRealizadoException() {
        super("Sorteio já realizado");
    }

}
