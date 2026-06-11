package dao;
import model.Contato;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Decorator concreto: registra log de todas as operacoes realizadas
 * Processo de negocio: auditoria automatica de acoes na agenda
 */
public class LogContatoDAODecorator extends ContatoDAODecorator {
    private static final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LogContatoDAODecorator(IContatoDAO dao) { super(dao); }

    private void log(String msg) {
        String entrada = "[" + LocalDateTime.now().format(fmt) + "] " + msg;
        logs.add(entrada);
        System.out.println(entrada);
    }

    @Override
    public void inserir(Contato c) {
        log("INSERIR contato: " + c.getNome());
        super.inserir(c);
        log("Contato inserido com sucesso: " + c.getNome());
    }

    @Override
    public void alterar(Contato c) {
        log("ALTERAR contato ID=" + c.getId() + " nome=" + c.getNome());
        super.alterar(c);
        log("Contato alterado com sucesso.");
    }

    @Override
    public void excluir(int id) {
        log("EXCLUIR contato ID=" + id);
        super.excluir(id);
        log("Contato excluido com sucesso.");
    }

    public static List<String> getLogs() { return logs; }
    public static void limparLogs() { logs.clear(); }
}
