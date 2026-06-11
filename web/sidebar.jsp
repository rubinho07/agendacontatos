<%@page import="model.Usuario"%>
<%@page import="dao.ContatoDAO,dao.GrupoDAO"%>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioLogado");
    if (u == null) { response.sendRedirect(request.getContextPath()+"/login.jsp"); return; }
    String uri = request.getRequestURI();
    int totalContatos = 0; int totalGrupos = 0;
    try { totalContatos = new ContatoDAO().listarTodos().size(); totalGrupos = new GrupoDAO().listarTodos().size(); } catch(Exception ignored){}
    String perfil = u.getPerfil();
    String roleCss = "ADMIN".equals(perfil) ? "role-admin" : "SUB".equals(perfil) ? "role-sub" : "role-user";
%>
<link rel="preconnect" href="https://fonts.googleapis.com"/>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700;900&family=Merriweather:wght@300;400;700&family=Sacramento&display=swap" rel="stylesheet"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<aside class="sidebar">
    <div class="sidebar-logo">
        <div class="logo-script">Agenda</div>
        <div class="logo-tagline">Meus Contatos</div>
    </div>
    <nav>
        <div class="nav-divider">Principal</div>
        <a href="<%=request.getContextPath()%>/dashboard" class="nav-item <%=uri.contains("index")?"active":""%>">
            <span class="nav-icon">&#128202;</span> Dashboard
        </a>
        <div class="nav-divider">Contatos</div>
        <a href="<%=request.getContextPath()%>/contato?acao=listar" class="nav-item <%=uri.contains("listarContato")?"active":""%>">
            <span class="nav-icon">&#128100;</span> Todos os Contatos
            <span class="nav-badge"><%= totalContatos %></span>
        </a>
        <a href="<%=request.getContextPath()%>/contato?acao=novo" class="nav-item">
            <span class="nav-icon">&#10010;</span> Novo Contato
        </a>
        <div class="nav-divider">Grupos</div>
        <a href="<%=request.getContextPath()%>/grupo?acao=listar" class="nav-item <%=uri.contains("listarGrupo")?"active":""%>">
            <span class="nav-icon">&#128101;</span> Grupos
            <span class="nav-badge"><%= totalGrupos %></span>
        </a>
        <a href="<%=request.getContextPath()%>/grupo?acao=novo" class="nav-item">
            <span class="nav-icon">&#10010;</span> Novo Grupo
        </a>
        <% if("ADMIN".equals(perfil)){ %>
        <div class="nav-divider">Admin</div>
        <a href="<%=request.getContextPath()%>/contato?acao=logs" class="nav-item">
            <span class="nav-icon">&#128196;</span> Logs de Auditoria
        </a>
        <% } %>
    </nav>
    <div class="sidebar-bottom">
        <div class="user-card">
            <div class="user-avatar"><%= u.getNome().substring(0,1).toUpperCase() %></div>
            <div>
                <div class="user-name"><%= u.getNome() %></div>
                <span class="user-role-badge <%= roleCss %>"><%= perfil %></span>
            </div>
        </div>
        <a href="<%=request.getContextPath()%>/logout" class="btn-logout">
            &#10006; Sair da Agenda
        </a>
    </div>
</aside>
