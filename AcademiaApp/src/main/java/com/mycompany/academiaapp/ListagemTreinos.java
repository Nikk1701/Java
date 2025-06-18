package com.mycompany.academiaapp;

import java.util.List;
import java.util.Scanner;

public class ListagemTreinos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreinoDAO treinoDAO = new TreinoDAO();

        System.out.print("Digite o ID do aluno para listar os treinos: ");
        int alunoId = Integer.parseInt(scanner.nextLine());

        List<Treino> treinos = treinoDAO.listarPorAluno(alunoId);

        if (treinos.isEmpty()) {
            System.out.println("Nenhum treino encontrado para o aluno com ID " + alunoId);
        } else {
            System.out.println("\n--- Treinos do Aluno ---");
            for (Treino t : treinos) {
                System.out.println("ID do Treino: " + t.getId());
                System.out.println("Tipo: " + t.getTipoTreino());
                System.out.println("Descrição: " + t.getDescricao());
                System.out.println("Duração: " + t.getDuracao());
                System.out.println("Data de Início: " + t.getDataInicio());
                System.out.println("------------------------");
            }
        }
    }
}