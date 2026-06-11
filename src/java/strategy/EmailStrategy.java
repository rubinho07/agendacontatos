package strategy;
import model.Contato;

public class EmailStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Contato contato) {
        if (contato.getEmail() == null || contato.getEmail().isEmpty()) {
            return "Email nao cadastrado para " + contato.getNome();
        }
        return "Email enviado para: " + contato.getEmail();
    }
    @Override
    public String getTipo() { return "EMAIL"; }
}
