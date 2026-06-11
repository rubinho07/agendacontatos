package controller;
import dao.*;
import model.*;
import strategy.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/contato")
public class ContatoServlet extends HttpServlet {

    private IContatoDAO getDao(HttpServletRequest req) {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        IContatoDAO base = new ContatoDAO();
        IContatoDAO comLog = new LogContatoDAODecorator(base);
        return new SegurancaContatoDAODecorator(comLog, u);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";
        IContatoDAO dao = getDao(req);

        try {
            switch (acao) {
                case "listar":
                    List<Contato> lista = dao.listarTodos();
                    req.setAttribute("lista", lista);
                    req.setAttribute("totalContatos", lista.size());
                    req.getRequestDispatcher("/listarContato.jsp").forward(req, resp);
                    break;
                case "novo":
                    req.setAttribute("contato", new Contato());
                    req.setAttribute("grupos", new GrupoDAO().listarTodos());
                    req.getRequestDispatcher("/formContato.jsp").forward(req, resp);
                    break;
                case "editar":
                    int idEditar = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("contato", dao.buscarPorId(idEditar));
                    req.setAttribute("grupos", new GrupoDAO().listarTodos());
                    req.getRequestDispatcher("/formContato.jsp").forward(req, resp);
                    break;
                case "excluir":
                    dao.excluir(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("contato?acao=listar");
                    break;
                case "sairGrupo":
                    dao.sairDoGrupo(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("contato?acao=listar");
                    break;
                case "notificar":
                    processarNotificacao(req, resp, dao);
                    break;
                case "logs":
                    req.setAttribute("logs", LogContatoDAODecorator.getLogs());
                    req.getRequestDispatcher("/logs.jsp").forward(req, resp);
                    break;
                default:
                    resp.sendRedirect("contato?acao=listar");
            }
        } catch (SecurityException e) {
            req.setAttribute("erro", e.getMessage());
            req.setAttribute("lista", dao.listarTodos());
            req.getRequestDispatcher("/listarContato.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Erro: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        IContatoDAO dao = getDao(req);
        String acao = req.getParameter("acao");
        if (acao == null) acao = "inserir";

        try {
            int grupoId = 0;
            String grupoIdParam = req.getParameter("grupoId");
            if (grupoIdParam != null && !grupoIdParam.trim().isEmpty()) {
                try { grupoId = Integer.parseInt(grupoIdParam); } catch (NumberFormatException ignored) {}
            }

            Contato c = new Contato.Builder()
                .nome(req.getParameter("nome")).telefone(req.getParameter("telefone"))
                .celular(req.getParameter("celular")).email(req.getParameter("email"))
                .endereco(req.getParameter("endereco")).dataNascimento(req.getParameter("dataNascimento"))
                .empresa(req.getParameter("empresa")).cargo(req.getParameter("cargo"))
                .observacoes(req.getParameter("observacoes")).tipoContato(req.getParameter("tipoContato"))
                .cpf(req.getParameter("cpf")).grupoId(grupoId).build();

            if ("alterar".equals(acao)) {
                c.setId(Integer.parseInt(req.getParameter("id")));
                dao.alterar(c);
            } else {
                dao.inserir(c);
            }
            resp.sendRedirect("contato?acao=listar");
        } catch (SecurityException e) {
            req.setAttribute("erro", e.getMessage());
            req.setAttribute("grupos", new GrupoDAO().listarTodos());
            req.getRequestDispatcher("/formContato.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Erro: " + e.getMessage(), e);
        }
    }

    // PROCESSO DE NEGOCIO AUTOMATIZADO: notificacao usando Strategy
    private void processarNotificacao(HttpServletRequest req, HttpServletResponse resp, IContatoDAO dao) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        String tipo = req.getParameter("tipo");
        Contato contato = dao.buscarPorId(id);

        NotificacaoStrategy strategy;
        switch (tipo != null ? tipo.toUpperCase() : "EMAIL") {
            case "SMS": strategy = new SmsStrategy(); break;
            case "WHATSAPP": strategy = new WhatsAppStrategy(); break;
            default: strategy = new EmailStrategy(); break;
        }

        String resultado = strategy.notificar(contato);
        req.setAttribute("mensagem", resultado);
        req.setAttribute("lista", dao.listarTodos());
        req.getRequestDispatcher("/listarContato.jsp").forward(req, resp);
    }
}
