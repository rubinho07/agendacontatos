<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Logs | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<div class="main">
    <div class="topbar">
        <div class="breadcrumb">
            <span>&#128196;</span><span class="breadcrumb-sep">›</span>
            <span class="current">Logs de Auditoria</span>
        </div>
        <div class="topbar-actions">
            <a href="contato?acao=listar" class="btn btn-outline btn-sm">&#8592; Voltar</a>
        </div>
    </div>
    <div class="content fade-in">
        <div class="alert alert-info">&#128202; Os logs abaixo sao gerados automaticamente pelo <strong>Decorator LogContatoDAODecorator</strong> — processo de negocio automatizado que registra todas as operacoes.</div>
        <div class="card">
            <div class="card-header">
                <div class="card-title">&#128196; Registro de Operacoes</div>
            </div>
            <% List<String> logs=(List<String>)request.getAttribute("logs"); %>
            <% if(logs!=null&&!logs.isEmpty()){for(String log:logs){ %>
                <div class="log-entry"><%= log %></div>
            <% }}else{ %>
                <div class="empty-state"><span class="icon">&#128196;</span><h3>Nenhum log ainda</h3><p>Os logs aparecem automaticamente quando voce inserir, alterar ou excluir contatos.</p></div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
