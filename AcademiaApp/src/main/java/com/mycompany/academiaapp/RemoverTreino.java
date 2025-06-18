package com.mycompany.academiaapp;

import java.util.Scanner;

public class RemoverTreino {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreinoDAO treinoDAO = new TreinoDAO();

        System.out.print("Digite o ID do treino que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean sucesso = treinoDAO.remover(id);
        if (sucesso) {
            System.out.println("Treino removido com sucesso!");
        } else {
            System.out.println("Erro ao remover treino. Verifique o ID e tente novamente.");
        }
    }
}
