<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Entrar | Minha Agenda</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700;900&family=Merriweather:wght@300;400;700&family=Sacramento&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-page">
    <div class="login-box fade-in">
        <div class="login-logo-wrap">
            <span class="login-script">Agenda</span>
            <div class="login-subtitle">Sistema de Contatos</div>
        </div>

        <% if(request.getAttribute("erro") != null){ %>
        <div class="alert alert-error">&#9888; <%= request.getAttribute("erro") %></div>
        <% } %>

        <div class="form-group" style="margin-bottom:14px;">
            <form action="login" method="post">
                <div class="form-group" style="margin-bottom:14px;">
                    <label>Email</label>
                    <input type="email" name="email" placeholder="seu@email.com" required/>
                </div>
                <div class="form-group" style="margin-bottom:22px;">
                    <label>Senha</label>
                    <input type="password" name="senha" placeholder="••••••" required/>
                </div>
                <button type="submit" class="btn btn-primary" style="width:100%;justify-content:center;padding:12px 20px;">
                    Entrar na Agenda
                </button>
            </form>
        </div>

        <div class="login-hint">
            <strong>&#128272; Admin:</strong> admin@email.com / 123<br>
            <strong>&#128218; Sub:</strong> sub@email.com / 123<br>
            <strong>&#128100; User:</strong> user@email.com / 123
        </div>
    </div>
</div>
</body>
</html>
