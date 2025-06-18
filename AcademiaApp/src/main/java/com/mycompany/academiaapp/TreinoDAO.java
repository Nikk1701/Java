package com.mycompany.academiaapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public void inserir(Treino treino) {
        String sql = "INSERT INTO treinos (aluno_id, tipo_treino, descricao, duracao, data_inicio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, treino.getAlunoId());
            stmt.setString(2, treino.getTipoTreino());
            stmt.setString(3, treino.getDescricao());
            stmt.setString(4, treino.getDuracao());
            stmt.setDate(5, treino.getDataInicio());

            stmt.executeUpdate();
            System.out.println("Treino cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar treino: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public List<Treino> listarPorAluno(int alunoId) {
        List<Treino> lista = new ArrayList<>();
        String sql = "SELECT * FROM treinos WHERE aluno_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Treino treino = new Treino();
                treino.setId(rs.getInt("id"));
                treino.setAlunoId(rs.getInt("aluno_id"));
                treino.setTipoTreino(rs.getString("tipo_treino"));
                treino.setDescricao(rs.getString("descricao"));
                treino.setDuracao(rs.getString("duracao"));
                treino.setDataInicio(rs.getDate("data_inicio"));

                lista.add(treino);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar treinos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
    public boolean remover(int id) {
    String sql = "DELETE FROM treinos WHERE id = ?";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        System.out.println("Erro ao remover treino: " + e.getMessage());
        return false;
    }
}
    public boolean editar(Treino treino) {
    String sql = "UPDATE treinos SET tipo_treino = ?, descricao = ?, duracao = ?, data_inicio = ? WHERE id = ?";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, treino.getTipoTreino());
        stmt.setString(2, treino.getDescricao());
        stmt.setString(3, treino.getDuracao());
        stmt.setDate(4, new java.sql.Date(treino.getDataInicio().getTime()));
        stmt.setInt(5, treino.getId());

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        System.out.println("Erro ao editar treino: " + e.getMessage());
        return false;
    }
}
    
}
