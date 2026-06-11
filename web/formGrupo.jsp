<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Grupo"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Grupo | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<% Grupo g=(Grupo)request.getAttribute("grupo"); boolean ed=g!=null&&g.getId()>0; %>
<div class="main">
    <div class="topbar">
        <div class="breadcrumb">
            <a href="grupo?acao=listar" style="color:var(--texto3);text-decoration:none;">&#128101; Grupos</a>
            <span class="breadcrumb-sep">›</span>
            <span class="current"><%= ed?"Editar":"Novo" %></span>
        </div>
    </div>
    <div class="content fade-in">
        <div class="card" style="max-width:500px;">
            <div class="form-section-title">&#128101; <%= ed?"Editar Grupo":"Novo Grupo" %></div>
            <form action="grupo" method="post" style="margin-top:16px;">
                <input type="hidden" name="acao" value="<%= ed?"alterar":"inserir" %>"/>
                <% if(ed){ %><input type="hidden" name="id" value="<%= g.getId() %>"/><% } %>
                <div class="form-group" style="margin-bottom:20px;">
                    <label>Nome do Grupo *</label>
                    <input type="text" name="nome" placeholder="Ex: Trabalho, Familia, Amigos..." required value="<%= ed&&g.getNome()!=null?g.getNome():"" %>"/>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary"><%= ed?"Atualizar":"Criar Grupo" %></button>
                    <a href="grupo?acao=listar" class="btn btn-outline">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
