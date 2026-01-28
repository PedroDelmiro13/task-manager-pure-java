package com.pedrodelmiro.taskmanager.db.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    private SchemaInitializer(){}

    public static void run(DataSource dataSource) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            createTables(connection);
        }catch(SQLException e){
            throw new SQLException("Erro: " + e.getMessage() + e.getNextException());
        }

    }
    private static void createTables(Connection connection) throws SQLException {
        String query = "CREATE TABLE USUARIO " + "(USUARIO_ID SERIAL PRIMARY KEY, " + "NOME VARCHAR(60) NOT NULL, " +
        "EMAIL VARCHAR(50) NOT NULL, " + "SENHA VARCHAR(40) NOT NULL, " + "STATUS BOOLEAN NOT NULL, "
                + "ROLE VARCHAR(11) NOT NULL, " + "CREATED_AT TIMESTAMPTZ NOT NULL)";
        try(Statement stmt = connection.createStatement()){
            stmt.executeUpdate(query);
        }catch(SQLException e){
            throw new SQLException("Erro: " + e.getMessage() + e.getNextException());
        }
    }
}

//**User**: Id, Nome, Email, Senha, Profissão, Descrição, Status, Role, created_at, updated_at, deleted_at.
//**ServiçoProvedor**: Id, Id do provedor, Nome, Descrição, Horários disponíveis, Imagem, Preço, created_at, updated_at, deleted_at.
//**ServiçoCliente**: Id, Id do cliente, Nome, Descrição, Horários disponíveis, Imagem, Preço, created_at, updated_at, deleted_at.
//**Pedido**: Id, Id do cliente, Id do provedor, Id do serviço, status, created_at, updated_at.
//**Avaliação**: Id, Id do usuário, Id do Pedido, Avaliação, Comentário, created_at, updated_at, deleted_at.
//**Pagamento**: Id, Id do pedido, Quantia, Status, Método, created_at, updated_at.
//**Chat**: Id, created_at, updated_at, deleted_at.
//**PartipantesChat**: Id, Id do chat, Id usuario.
//**Mensagem**: Id, Id do Chat, Id do Usuário, Texto, created_at, updated_at, deleted_at.

