package com.mycompany.academiaapp;

import java.util.List;

public class ListarAlunos {
    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = dao.listarTodos();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("Lista de alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("CPF: " + aluno.getCpf());
                System.out.println("Data Nascimento: " + aluno.getDataNascimento());
                System.out.println("Telefone: " + aluno.getTelefone());
                System.out.println("Email: " + aluno.getEmail());
                System.out.println("-----------------------------");
            }
        }
    }
}