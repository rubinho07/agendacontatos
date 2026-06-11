package strategy;
import model.Contato;

/**
 * Padrao Strategy: define contrato para diferentes formas de notificacao
 */
public interface NotificacaoStrategy {
    String notificar(Contato contato);
    String getTipo();
}
