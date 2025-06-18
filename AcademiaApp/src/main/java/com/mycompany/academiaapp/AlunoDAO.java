package com.mycompany.academiaapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, cpf, data_nascimento, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());

            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }
    public List<Aluno> listarTodos() {
    List<Aluno> lista = new ArrayList<>();
    String sql = "SELECT * FROM alunos";

    try (Connection conn = Conexao.conectar();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setEmail(rs.getString("email"));

            lista.add(aluno);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar alunos: " + e.getMessage());
    }

    return lista;
    }
    public void atualizar(Aluno aluno) {
    String sql = "UPDATE alunos SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, email = ? WHERE id = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getCpf());
        stmt.setDate(3, java.sql.Date.valueOf(aluno.getDataNascimento()));
        stmt.setString(4, aluno.getTelefone());
        stmt.setString(5, aluno.getEmail());
        stmt.setInt(6, aluno.getId());

        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Aluno atualizado com sucesso!");
        } else {
            System.out.println("Nenhum aluno encontrado com o ID informado.");
        }

    } catch (SQLException e) {
        System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        e.printStackTrace();
    }
    }
    public void excluir(int id) {
    String sql = "DELETE FROM alunos WHERE id = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Nenhum aluno encontrado com o ID informado.");
        }

    } catch (SQLException e) {
        System.out.println("Erro ao excluir aluno: " + e.getMessage());
        e.printStackTrace();
    }
    }
  

}
