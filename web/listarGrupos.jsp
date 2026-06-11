<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,model.Grupo"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Grupos | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<div class="main">
    <div class="topbar">
        <div class="breadcrumb">
            <span>&#128101;</span><span class="breadcrumb-sep">›</span>
            <span class="current">Grupos</span>
        </div>
        <div class="topbar-actions">
            <a href="grupo?acao=novo" class="btn btn-primary btn-sm">&#43; Novo Grupo</a>
        </div>
    </div>
    <div class="content fade-in">
        <% if(request.getAttribute("erro")!=null){ %><div class="alert alert-error">&#9888; <%= request.getAttribute("erro") %></div><% } %>
        <div class="card">
            <div class="card-header">
                <div class="card-title">&#128101; Grupos de Contatos</div>
                <% List<Grupo> lista=(List<Grupo>)request.getAttribute("lista"); %>
                <span class="badge badge-cinza"><%= lista!=null?lista.size():0 %> grupos</span>
            </div>
            <div class="table-wrap">
                <table>
                    <thead><tr><th>ID</th><th>Nome do Grupo</th><th>Acoes</th></tr></thead>
                    <tbody>
                    <% if(lista!=null&&!lista.isEmpty()){for(Grupo g:lista){ %>
                        <tr>
                            <td class="text-muted">#<%= g.getId() %></td>
                            <td><span class="badge badge-verde">&#128101;</span> &nbsp;<strong><%= g.getNome() %></strong></td>
                            <td>
                                <div class="actions-cell">
                                    <a href="grupo?acao=editar&id=<%= g.getId() %>" class="btn btn-info btn-xs">Editar</a>
                                    <a href="grupo?acao=excluir&id=<%= g.getId() %>" class="btn btn-danger btn-xs" onclick="return confirm('Excluir grupo?')">Excluir</a>
                                </div>
                            </td>
                        </tr>
                    <% }}else{ %>
                        <tr><td colspan="3"><div class="empty-state"><span class="icon">&#128101;</span><h3>Nenhum grupo criado</h3><p>Crie grupos para organizar seus contatos.</p><a href="grupo?acao=novo" class="btn btn-primary btn-sm">Criar Grupo</a></div></td></tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
