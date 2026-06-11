package controller;
import dao.*;
import model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/grupo")
public class GrupoServlet extends HttpServlet {

    private IGrupoDAO getDao(HttpServletRequest req) {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        return new SegurancaGrupoDAODecorator(new GrupoDAO(), u);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";
        IGrupoDAO dao = getDao(req);

        try {
            switch (acao) {
                case "listar":
                    List<Grupo> lista = dao.listarTodos();
                    req.setAttribute("lista", lista);
                    req.setAttribute("totalGrupos", lista.size());
                    req.getRequestDispatcher("/listarGrupos.jsp").forward(req, resp);
                    break;
                case "novo":
                    req.setAttribute("grupo", new Grupo());
                    req.getRequestDispatcher("/formGrupo.jsp").forward(req, resp);
                    break;
                case "editar":
                    req.setAttribute("grupo", dao.buscarPorId(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/formGrupo.jsp").forward(req, resp);
                    break;
                case "excluir":
                    dao.excluir(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("grupo?acao=listar");
                    break;
                default:
                    resp.sendRedirect("grupo?acao=listar");
            }
        } catch (SecurityException e) {
            req.setAttribute("erro", e.getMessage());
            req.setAttribute("lista", dao.listarTodos());
            req.getRequestDispatcher("/listarGrupos.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Erro: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        IGrupoDAO dao = getDao(req);
        String acao = req.getParameter("acao");

        try {
            Grupo g = new Grupo();
            g.setNome(req.getParameter("nome"));
            if ("alterar".equals(acao)) {
                g.setId(Integer.parseInt(req.getParameter("id")));
                dao.alterar(g);
            } else {
                dao.inserir(g);
            }
            resp.sendRedirect("grupo?acao=listar");
        } catch (SecurityException e) {
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("/formGrupo.jsp").forward(req, resp);
        }
    }
}
