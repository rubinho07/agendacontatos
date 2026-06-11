<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Contato,java.util.List"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Dashboard | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<%
    Integer tcDash = (Integer) request.getAttribute("totalContatos");
    Integer tgDash = (Integer) request.getAttribute("totalGrupos");
    Integer tuDash = (Integer) request.getAttribute("totalUsuarios");
    List<Contato> ultimos = (List<Contato>) request.getAttribute("ultimosContatos");
    int tcVal = tcDash != null ? tcDash : 0;
    int tgVal = tgDash != null ? tgDash : 0;
    int tuVal = tuDash != null ? tuDash : 0;
%>
<div class="main">
    <div class="topbar">
        <div class="page-heading">
            <div class="page-title">Boa tarde, <%= u.getNome().split(" ")[0] %> &#128075;</div>
            <div class="page-sub">Aqui esta um resumo da sua agenda</div>
        </div>
        <div class="topbar-actions">
            <a href="contato?acao=novo" class="btn btn-primary btn-sm">&#10010; Novo Contato</a>
        </div>
    </div>
    <div class="content fade-in">
        <div class="stats-grid">
            <div class="stat-card terracota" data-icon="&#128100;">
                <div class="stat-label">Contatos</div>
                <div class="stat-value"><%= tcVal %></div>
            </div>
            <div class="stat-card sage" data-icon="&#128101;">
                <div class="stat-label">Grupos</div>
                <div class="stat-value"><%= tgVal %></div>
            </div>
            <div class="stat-card roxo" data-icon="&#128272;">
                <div class="stat-label">Usuarios</div>
                <div class="stat-value"><%= tuVal %></div>
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <div class="card-title">&#128197; Ultimos Contatos Adicionados</div>
                <a href="contato?acao=listar" class="btn btn-outline btn-sm">Ver Todos</a>
            </div>
            <div class="table-wrap">
                <table>
                    <thead><tr><th>Nome</th><th>Email</th><th>Telefone</th><th>Grupo</th><th>Tipo</th></tr></thead>
                    <tbody>
                    <% if(ultimos != null && !ultimos.isEmpty()) { for(Contato c : ultimos) { %>
                        <tr>
                            <td><strong><%= c.getNome() %></strong></td>
                            <td class="text-muted"><%= c.getEmail()!=null&&!c.getEmail().isEmpty()?c.getEmail():"—" %></td>
                            <td><%= c.getTelefone()!=null&&!c.getTelefone().isEmpty()?c.getTelefone():"—" %></td>
                            <td><% if(c.getNomeGrupo()!=null&&!c.getNomeGrupo().isEmpty()){ %><span class="badge badge-sage"><%= c.getNomeGrupo() %></span><% }else{ %>—<% } %></td>
                            <td><% if(c.getTipoContato()!=null&&!c.getTipoContato().isEmpty()){ %><span class="badge badge-terracota"><%= c.getTipoContato() %></span><% }else{ %>—<% } %></td>
                        </tr>
                    <% } } else { %>
                        <tr><td colspan="5">
                            <div class="empty-state">
                                <span class="icon">&#128100;</span>
                                <h3>Nenhum contato ainda</h3>
                                <p>Sua agenda esta vazia. Comece adicionando contatos!</p>
                                <a href="contato?acao=novo" class="btn btn-primary btn-sm">Adicionar Contato</a>
                            </div>
                        </td></tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>

        <div style="display:grid;grid-template-columns:1fr 1fr;gap:16px;">
            <div class="card" style="margin-bottom:0;">
                <div class="card-header" style="margin-bottom:14px;padding-bottom:10px;">
                    <div class="card-title">&#128640; Acesso Rapido</div>
                </div>
                <div style="display:flex;flex-direction:column;gap:8px;">
                    <a href="contato?acao=novo" class="btn btn-primary" style="justify-content:center;">&#10010; Novo Contato</a>
                    <a href="grupo?acao=novo" class="btn btn-sage" style="justify-content:center;">&#10010; Novo Grupo</a>
                    <a href="contato?acao=listar" class="btn btn-outline" style="justify-content:center;">&#128100; Ver Contatos</a>
                </div>
            </div>
            <div class="card" style="margin-bottom:0;">
                <div class="card-header" style="margin-bottom:14px;padding-bottom:10px;">
                    <div class="card-title">&#128272; Seu Perfil</div>
                </div>
                <div style="font-size:13px;color:var(--texto2);line-height:2;">
                    <div><strong>Nome:</strong> <%= u.getNome() %></div>
                    <div><strong>Email:</strong> <%= u.getEmail() %></div>
                    <div><strong>Perfil:</strong> <span class="user-role-badge <%= "ADMIN".equals(u.getPerfil())?"role-admin":"SUB".equals(u.getPerfil())?"role-sub":"role-user" %>"><%= u.getPerfil() %></span></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
