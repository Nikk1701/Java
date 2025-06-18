package com.mycompany.academiaapp;

import java.time.LocalDate;
import java.util.Scanner;

public class EditarAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        System.out.print("Digite o ID do aluno a ser editado: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Nova data de nascimento (aaaa-mm-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email);
        aluno.setId(id);

        alunoDAO.atualizar(aluno);
    }
}
