package com.mycompany.academiaapp;

import java.time.LocalDate;
import java.util.Scanner;

public class CadastroAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de nascimento (aaaa-mm-dd): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email);
        alunoDAO.inserir(aluno);
    }
}