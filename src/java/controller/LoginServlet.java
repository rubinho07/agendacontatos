package controller;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(email, senha);
        if (usuario != null) {
            req.getSession().setAttribute("usuarioLogado", usuario);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("erro", "Email ou senha invalidos.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
