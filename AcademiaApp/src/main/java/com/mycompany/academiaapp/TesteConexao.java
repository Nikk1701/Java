package com.mycompany.academiaapp;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection con = Conexao.conectar()) {
            System.out.println("Conexão com banco MySQL estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco:");
            e.printStackTrace();
        }
    }
}