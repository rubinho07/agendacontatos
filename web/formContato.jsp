<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Contato,model.Grupo,java.util.List"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head><meta charset="UTF-8"><title>Contato | Agenda</title></head>
<body>
<%@ include file="sidebar.jsp" %>
<%
    Contato c=(Contato)request.getAttribute("contato");
    boolean ed=c!=null&&c.getId()>0;
    List<Grupo> grupos=(List<Grupo>)request.getAttribute("grupos");
    String[] tipos={"Pessoal","Profissional","Familiar","Outro"};
    String n=ed?"Editar Contato":"Novo Contato";
%>
<div class="main">
    <div class="topbar">
        <div class="breadcrumb">
            <a href="contato?acao=listar" style="color:var(--texto3);text-decoration:none;">&#128100; Contatos</a>
            <span class="breadcrumb-sep">›</span>
            <span class="current"><%= ed?"Editar":"Novo" %></span>
        </div>
    </div>
    <div class="content fade-in">
        <% if(request.getAttribute("erro")!=null){ %><div class="alert alert-error">&#9888; <%= request.getAttribute("erro") %></div><% } %>
        <div class="card" style="max-width:780px;">
            <form action="contato" method="post">
                <input type="hidden" name="acao" value="<%= ed?"alterar":"inserir" %>"/>
                <% if(ed){ %><input type="hidden" name="id" value="<%= c.getId() %>"/><% } %>

                <div class="form-section">
                    <div class="form-section-title">&#128100; Dados Pessoais</div>
                    <div class="form-grid">
                        <div class="form-group full"><label>Nome Completo *</label>
                            <input type="text" name="nome" placeholder="Ex: Joao Silva" required value="<%= ed&&c.getNome()!=null?c.getNome():"" %>"/></div>
                        <div class="form-group"><label>CPF</label>
                            <input type="text" name="cpf" placeholder="000.000.000-00" value="<%= ed&&c.getCpf()!=null?c.getCpf():"" %>"/></div>
                        <div class="form-group"><label>Data de Nascimento</label>
                            <input type="text" name="dataNascimento" placeholder="dd/mm/aaaa" value="<%= ed&&c.getDataNascimento()!=null?c.getDataNascimento():"" %>"/></div>
                        <div class="form-group"><label>Tipo de Contato</label>
                            <select name="tipoContato">
                                <option value="">Selecione...</option>
                                <% for(String t:tipos){ %><option value="<%= t %>" <%= ed&&t.equals(c.getTipoContato())?"selected":"" %>><%= t %></option><% } %>
                            </select></div>
                        <div class="form-group"><label>Grupo</label>
                            <select name="grupoId">
                                <option value="">Sem grupo</option>
                                <% if(grupos!=null){for(Grupo g:grupos){ %><option value="<%= g.getId() %>" <%= ed&&c.getGrupoId()==g.getId()?"selected":"" %>><%= g.getNome() %></option><% }} %>
                            </select></div>
                    </div>
                </div>

                <div class="form-section">
                    <div class="form-section-title">&#128222; Contatos</div>
                    <div class="form-grid">
                        <div class="form-group"><label>Telefone</label>
                            <input type="text" name="telefone" placeholder="(11) 3333-3333" value="<%= ed&&c.getTelefone()!=null?c.getTelefone():"" %>"/></div>
                        <div class="form-group"><label>Celular</label>
                            <input type="text" name="celular" placeholder="(11) 99999-9999" value="<%= ed&&c.getCelular()!=null?c.getCelular():"" %>"/></div>
                        <div class="form-group full"><label>Email</label>
                            <input type="email" name="email" placeholder="joao@email.com" value="<%= ed&&c.getEmail()!=null?c.getEmail():"" %>"/></div>
                    </div>
                </div>

                <div class="form-section">
                    <div class="form-section-title">&#127970; Profissional e Endereco</div>
                    <div class="form-grid">
                        <div class="form-group"><label>Empresa</label>
                            <input type="text" name="empresa" placeholder="Nome da empresa" value="<%= ed&&c.getEmpresa()!=null?c.getEmpresa():"" %>"/></div>
                        <div class="form-group"><label>Cargo</label>
                            <input type="text" name="cargo" placeholder="Ex: Gerente" value="<%= ed&&c.getCargo()!=null?c.getCargo():"" %>"/></div>
                        <div class="form-group full"><label>Endereco</label>
                            <input type="text" name="endereco" placeholder="Rua, numero, bairro, cidade" value="<%= ed&&c.getEndereco()!=null?c.getEndereco():"" %>"/></div>
                    </div>
                </div>

                <div class="form-section">
                    <div class="form-section-title">&#128203; Observacoes</div>
                    <div class="form-group"><label>Observacoes</label>
                        <textarea name="observacoes" placeholder="Anotacoes sobre o contato..."><%= ed&&c.getObservacoes()!=null?c.getObservacoes():"" %></textarea></div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary"><%= ed?"Atualizar Contato":"Salvar Contato" %></button>
                    <a href="contato?acao=listar" class="btn btn-outline">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
