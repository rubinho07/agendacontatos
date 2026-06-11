# Agenda de Contatos

Sistema web desenvolvido em Java para gerenciamento de contatos e grupos. O projeto permite cadastrar, listar, editar, excluir e organizar contatos por grupos, além de aplicar regras de permissão por perfil de usuário.

O sistema foi desenvolvido com foco em Programação Orientada a Objetos, utilizando padrões de projeto como *DAO, **Decorator, **Strategy* e *Builder*.

## Funcionalidades

* Login de usuários
* Cadastro de contatos
* Listagem de contatos
* Edição de contatos
* Exclusão de contatos
* Organização de contatos por grupos
* Cadastro e gerenciamento de grupos
* Controle de permissões por perfil de usuário
* Registro de logs das ações realizadas
* Simulação de notificações por Email, SMS e WhatsApp

## Perfis de Usuário

O sistema possui três tipos de perfil:

| Perfil | Permissões                                                           |
| ------ | -------------------------------------------------------------------- |
| ADMIN  | Pode cadastrar, editar, excluir e visualizar contatos e grupos       |
| SUB    | Pode cadastrar e editar contatos, mas não pode excluir               |
| USER   | Pode visualizar e cadastrar contatos, mas não pode editar ou excluir |

## Padrões de Projeto Utilizados

### DAO

O padrão DAO foi utilizado para separar a lógica de acesso ao banco de dados da regra de negócio da aplicação.

Principais classes:

* ContatoDAO
* GrupoDAO
* UsuarioDAO
* IContatoDAO
* IGrupoDAO

### Decorator

O padrão Decorator foi utilizado para adicionar funcionalidades extras aos DAOs sem alterar diretamente as classes principais.

Decorators implementados:

* LogContatoDAODecorator
* SegurancaContatoDAODecorator
* SegurancaGrupoDAODecorator

O LogContatoDAODecorator registra as operações realizadas nos contatos, enquanto os decorators de segurança validam as permissões conforme o perfil do usuário logado.

### Strategy

O padrão Strategy foi utilizado para permitir diferentes formas de notificação de contatos.

Estratégias implementadas:

* EmailStrategy
* SmsStrategy
* WhatsAppStrategy

Todas implementam a interface NotificacaoStrategy.

### Builder

O padrão Builder foi utilizado na criação de objetos Contato, facilitando a montagem de contatos com vários atributos opcionais.

Classe principal:

* Contato.Builder

## Tecnologias Utilizadas

* Java 11
* Jakarta Servlet
* JSP
* MySQL
* HTML
* CSS
* Apache Tomcat
* NetBeans

## Estrutura do Projeto

````text
agenda_final/
│
├── banco_agenda_contatos.sql
├── build.xml
│
├── src/
│   └── java/
│       ├── controller/
│       │   ├── ContatoServlet.java
│       │   ├── DashboardServlet.java
│       │   ├── GrupoServlet.java
│       │   ├── LoginServlet.java
│       │   └── LogoutServlet.java
│       │
│       ├── dao/
│       │   ├── ContatoDAO.java
│       │   ├── ContatoDAODecorator.java
│       │   ├── GrupoDAO.java
│       │   ├── GrupoDAODecorator.java
│       │   ├── IContatoDAO.java
│       │   ├── IGrupoDAO.java
│       │   ├── LogContatoDAODecorator.java
│       │   ├── SegurancaContatoDAODecorator.java
│       │   ├── SegurancaGrupoDAODecorator.java
│       │   └── UsuarioDAO.java
│       │
│       ├── model/
│       │   ├── Contato.java
│       │   ├── DetalhesContato.java
│       │   ├── Grupo.java
│       │   └── Usuario.java
│       │
│       ├── strategy/
│       │   ├── EmailStrategy.java
│       │   ├── NotificacaoStrategy.java
│       │   ├── SmsStrategy.java
│       │   └── WhatsAppStrategy.java
│       │
│       └── util/
│           └── Conexao.java
│
└── web/
    ├── css/
    │   └── style.css
    ├── formContato.jsp
    ├── formGrupo.jsp
    ├── index.jsp
    ├── listarContato.jsp
    ├── listarGrupos.jsp
    ├── login.jsp
    ├── logs.jsp
    ├── sidebar.jsp
    └── WEB-INF/
        └── web.xml
````

## Banco de Dados

O sistema utiliza duas abordagens de armazenamento:

### Banco Relacional
- MySQL
- Responsável pelo armazenamento principal de usuários, contatos e grupos.

### Banco NoSQL
- Firebase Firestore
- Utilizado para armazenamento de logs e auditoria das operações do sistema.

Exemplo de dados armazenados:
- Usuário responsável pela ação
- Tipo da operação realizada
- Data da operação
- Sistema de origem

## Usuários de Teste

| Perfil | Email                                     | Senha |
| ------ | ----------------------------------------- | ----- |
| ADMIN  | [admin@email.com](mailto:admin@email.com) | 123   |
| USER   | [user@email.com](mailto:user@email.com)   | 123   |
| SUB    | [sub@email.com](mailto:sub@email.com)     | 123   |

## Como Executar o Projeto

### 1. Clonar o repositório

bash
git clone https://github.com/seu-usuario/seu-repositorio.git


### 2. Abrir o projeto

Abra o projeto em uma IDE compatível com Java Web, como o *NetBeans*.

### 3. Configurar o banco de dados

Execute o arquivo banco_agenda_contatos.sql no MySQL Workbench ou em outro gerenciador MySQL.

### 4. Verificar a conexão

A conexão com o banco está configurada na classe:

text
src/java/util/Conexao.java


Configuração padrão:

java
jdbc:mysql://localhost:3306/agenda_contatos
usuario: root
senha: vazia


Caso seu MySQL utilize outra senha, altere os dados nessa classe.

### 5. Adicionar o driver MySQL

Adicione o *MySQL Connector/J* ao projeto ou ao servidor Tomcat para que a aplicação consiga se conectar ao banco de dados.

### 6. Executar no Tomcat

Execute o projeto utilizando o Apache Tomcat.

A aplicação pode ser acessada pelo navegador em:

text
http://localhost:8080/agenda/login.jsp


## Fluxo Básico do Sistema

1. O usuário acessa a tela de login.
2. O sistema autentica o usuário no banco de dados.
3. Após o login, o usuário acessa o dashboard.
4. O usuário pode gerenciar contatos e grupos conforme seu perfil.
5. Antes de executar ações importantes, os decorators de segurança validam as permissões.
6. As operações de contato são registradas pelo decorator de log.
7. O usuário também pode simular notificações usando Email, SMS ou WhatsApp.

## Objetivo Acadêmico

Este projeto foi desenvolvido como atividade acadêmica com o objetivo de aplicar conceitos de Java Web, banco de dados, Programação Orientada a Objetos e padrões de projeto.

O principal foco do projeto é demonstrar o uso do padrão *Decorator*, permitindo adicionar funcionalidades como segurança e log sem modificar diretamente a classe DAO principal.

## Autores

Desenvolvido por:

* Arthur Zanelato de Souza
* Luis Fernando Rubinho de Souza
