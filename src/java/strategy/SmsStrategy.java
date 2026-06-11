package strategy;
import model.Contato;

public class SmsStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Contato contato) {
        String tel = contato.getCelular() != null ? contato.getCelular() : contato.getTelefone();
        if (tel == null || tel.isEmpty()) {
            return "Telefone nao cadastrado para " + contato.getNome();
        }
        return "SMS enviado para: " + tel;
    }
    @Override
    public String getTipo() { return "SMS"; }
}
