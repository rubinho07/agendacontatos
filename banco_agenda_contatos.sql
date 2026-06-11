-- =============================================
-- BANCO: agenda_contatos
-- Execute no MySQL Workbench
-- =============================================

CREATE DATABASE IF NOT EXISTS agenda_contatos;
USE agenda_contatos;

-- 1. TABELA grupo
CREATE TABLE IF NOT EXISTS grupo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- 2. TABELA contato (1:N com grupo)
CREATE TABLE IF NOT EXISTS contato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    celular VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200),
    dataNascimento VARCHAR(10),
    empresa VARCHAR(100),
    cargo VARCHAR(100),
    observacoes VARCHAR(255),
    tipoContato VARCHAR(50),
    cpf VARCHAR(20),
    grupo_id INT,
    FOREIGN KEY (grupo_id) REFERENCES grupo(id) ON DELETE SET NULL
);

-- 3. TABELA detalhes_contato (1:1 com contato)
CREATE TABLE IF NOT EXISTS detalhes_contato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contato_id INT UNIQUE NOT NULL,
    cpf VARCHAR(20),
    celular VARCHAR(20),
    redeSocial VARCHAR(100),
    FOREIGN KEY (contato_id) REFERENCES contato(id) ON DELETE CASCADE
);

-- 4. TABELA usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    perfil VARCHAR(20) NOT NULL DEFAULT 'USER'
);

-- =============================================
-- DADOS INICIAIS
-- =============================================

INSERT IGNORE INTO grupo (nome) VALUES
('Familia'),('Trabalho'),('Amigos'),('Clientes'),('Outros');

INSERT IGNORE INTO usuario (nome, email, senha, perfil) VALUES
('Administrador', 'admin@email.com', '123', 'ADMIN'),
('Usuario Comum', 'user@email.com', '123', 'USER'),
('Sub Usuario', 'sub@email.com', '123', 'SUB');

INSERT IGNORE INTO contato (nome, telefone, celular, email, endereco, dataNascimento, empresa, cargo, tipoContato, cpf, grupo_id) VALUES
('Ana Paula Silva', '(11) 3333-1111', '(11) 99999-1111', 'ana@email.com', 'Rua das Flores, 100 - Sao Paulo/SP', '15/03/1990', 'TechCorp', 'Gerente de TI', 'Profissional', '111.111.111-11', 2),
('Carlos Eduardo', '(11) 3333-2222', '(11) 99999-2222', 'carlos@email.com', 'Av. Paulista, 500 - Sao Paulo/SP', '22/07/1985', 'StartupXYZ', 'Desenvolvedor', 'Profissional', '222.222.222-22', 2),
('Maria Fernanda', NULL, '(11) 99999-3333', 'maria@email.com', 'Rua Augusta, 200 - Sao Paulo/SP', '10/12/1995', NULL, NULL, 'Pessoal', '333.333.333-33', 3),
('Jose Roberto', '(11) 3333-4444', '(11) 99999-4444', 'jose@email.com', 'Rua Oscar Freire, 300 - Sao Paulo/SP', '05/01/1980', 'Consultoria ABC', 'Diretor', 'Profissional', '444.444.444-44', 4),
('Luciana Santos', NULL, '(11) 99999-5555', 'luciana@email.com', 'Rua Vergueiro, 400 - Sao Paulo/SP', '28/06/1992', NULL, NULL, 'Familiar', '555.555.555-55', 1);

-- =============================================
-- VERIFICAR
-- =============================================
SELECT 'grupos' AS tabela, COUNT(*) AS total FROM grupo
UNION ALL SELECT 'contatos', COUNT(*) FROM contato
UNION ALL SELECT 'usuarios', COUNT(*) FROM usuario;
