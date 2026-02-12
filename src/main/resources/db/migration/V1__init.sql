CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha TEXT NOT NULL,
    profissao TEXT,
    descricao TEXT,
    status TEXT NOT NULL,
    role TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE servico_provedor (
    id SERIAL PRIMARY KEY,
    provider_id INT NOT NULL REFERENCES users(id),
    nome TEXT NOT NULL,
    descricao TEXT,
    horarios TEXT,
    imagem TEXT,
    preco NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE servico_cliente (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL REFERENCES users(id),
    nome TEXT NOT NULL,
    descricao TEXT,
    horarios TEXT,
    imagem TEXT,
    preco NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL REFERENCES users(id),
    provedor_id INT NOT NULL REFERENCES users(id),
    servico_id INT NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE pagamentos (
    id SERIAL PRIMARY KEY,
    pedido_id INT UNIQUE REFERENCES pedidos(id),
    quantia NUMERIC(10,2) NOT NULL,
    status TEXT NOT NULL,
    metodo TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE avaliacoes (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES users(id),
    pedido_id INT UNIQUE REFERENCES pedidos(id),
    avaliacao INT CHECK (avaliacao BETWEEN 1 AND 5),
    comentario TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE chats (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE chat_participantes (
    id SERIAL PRIMARY KEY,
    chat_id INT REFERENCES chats(id),
    usuario_id INT REFERENCES users(id),
    UNIQUE(chat_id, usuario_id)
);

CREATE TABLE mensagens (
    id SERIAL PRIMARY KEY,
    chat_id INT REFERENCES chats(id),
    usuario_id INT REFERENCES users(id),
    texto TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
