package strategy;
import model.Contato;

public class WhatsAppStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Contato contato) {
        String cel = contato.getCelular();
        if (cel == null || cel.isEmpty()) {
            return "Celular nao cadastrado para " + contato.getNome();
        }
        return "WhatsApp enviado para: " + cel;
    }
    @Override
    public String getTipo() { return "WHATSAPP"; }
}
