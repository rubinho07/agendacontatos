<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,model.Contato"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Contatos | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<div class="main">
    <div class="topbar">
        <div class="breadcrumb">
            <span>&#128100;</span><span class="breadcrumb-sep">›</span>
            <span class="current">Contatos</span>
        </div>
        <div class="topbar-actions">
            <a href="contato?acao=novo" class="btn btn-primary btn-sm">&#43; Novo Contato</a>
        </div>
    </div>
    <div class="content fade-in">
        <% if(request.getAttribute("erro")!=null){ %><div class="alert alert-error">&#9888; <%= request.getAttribute("erro") %></div><% } %>
        <% if(request.getAttribute("mensagem")!=null){ %><div class="alert alert-success">&#10003; <%= request.getAttribute("mensagem") %></div><% } %>

        <div class="card">
            <div class="card-header">
                <div class="card-title">&#128100; Lista de Contatos</div>
                <% List<Contato> lista=(List<Contato>)request.getAttribute("lista"); %>
                <span class="badge badge-cinza"><%= lista!=null?lista.size():0 %> contatos</span>
            </div>
            <div class="table-wrap">
                <table>
                    <thead><tr><th>Nome</th><th>Telefone</th><th>Celular</th><th>Email</th><th>Empresa/Cargo</th><th>Tipo</th><th>Grupo</th><th>Acoes</th></tr></thead>
                    <tbody>
                    <% if(lista!=null&&!lista.isEmpty()){for(Contato c:lista){ %>
                        <tr>
                            <td><strong><%= c.getNome() %></strong><% if(c.getCpf()!=null&&!c.getCpf().isEmpty()){ %><br><span class="text-muted" style="font-size:11px;">CPF: <%= c.getCpf() %></span><% } %></td>
                            <td><%= c.getTelefone()!=null&&!c.getTelefone().isEmpty()?c.getTelefone():"—" %></td>
                            <td><%= c.getCelular()!=null&&!c.getCelular().isEmpty()?c.getCelular():"—" %></td>
                            <td><%= c.getEmail()!=null&&!c.getEmail().isEmpty()?c.getEmail():"—" %></td>
                            <td><% if(c.getEmpresa()!=null&&!c.getEmpresa().isEmpty()){ %><strong><%= c.getEmpresa() %></strong><% if(c.getCargo()!=null&&!c.getCargo().isEmpty()){ %><br><span class="text-muted" style="font-size:11px;"><%= c.getCargo() %></span><% } %><% }else{ %>—<% } %></td>
                            <td><% if(c.getTipoContato()!=null&&!c.getTipoContato().isEmpty()){ String tipo=c.getTipoContato(); String cls="badge-cinza"; if("Profissional".equals(tipo))cls="badge-azul"; else if("Pessoal".equals(tipo))cls="badge-verde"; else if("Familiar".equals(tipo))cls="badge-roxo"; %><span class="badge <%= cls %>"><%= tipo %></span><% }else{ %>—<% } %></td>
                            <td><% if(c.getNomeGrupo()!=null&&!c.getNomeGrupo().isEmpty()){ %><span class="badge badge-verde"><%= c.getNomeGrupo() %></span><% }else{ %>—<% } %></td>
                            <td>
                                <div class="actions-cell">
                                    <a href="contato?acao=editar&id=<%= c.getId() %>" class="btn btn-info btn-xs">Editar</a>
                                    <a href="contato?acao=notificar&id=<%= c.getId() %>&tipo=EMAIL" class="btn btn-outline btn-xs" title="Notificar por Email">&#128140;</a>
                                    <a href="contato?acao=notificar&id=<%= c.getId() %>&tipo=WHATSAPP" class="btn btn-outline btn-xs" title="Notificar por WhatsApp">&#128241;</a>
                                    <% if(c.getGrupoId()>0){ %><a href="contato?acao=sairGrupo&id=<%= c.getId() %>" class="btn btn-warning btn-xs" onclick="return confirm('Remover do grupo?')">Grupo</a><% } %>
                                    <a href="contato?acao=excluir&id=<%= c.getId() %>" class="btn btn-danger btn-xs" onclick="return confirm('Excluir contato?')">Excluir</a>
                                </div>
                            </td>
                        </tr>
                    <% }}else{ %>
                        <tr><td colspan="8"><div class="empty-state"><span class="icon">&#128100;</span><h3>Nenhum contato cadastrado</h3><p>Clique em Novo Contato para adicionar.</p><a href="contato?acao=novo" class="btn btn-primary btn-sm">Adicionar Contato</a></div></td></tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
