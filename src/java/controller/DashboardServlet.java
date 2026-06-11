package controller;
import dao.*;
import model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if (u == null) { resp.sendRedirect("login.jsp"); return; }

        ContatoDAO contatoDAO = new ContatoDAO();
        GrupoDAO grupoDAO = new GrupoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        req.setAttribute("totalContatos", contatoDAO.listarTodos().size());
        req.setAttribute("totalGrupos", grupoDAO.listarTodos().size());
        req.setAttribute("totalUsuarios", usuarioDAO.contarUsuarios());
        req.setAttribute("ultimosContatos", contatoDAO.listarTodos().stream().limit(5).toList());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
