package com.mycompany.academiaapp;

import java.util.Scanner;

public class ExcluirAluno {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();

        System.out.print("Digite o ID do aluno que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        alunoDAO.excluir(id);
    }
}
