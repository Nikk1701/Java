package com.mycompany.academiaapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EditarTreino {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreinoDAO treinoDAO = new TreinoDAO();

        System.out.print("Digite o ID do treino que deseja editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo tipo de treino: ");
        String tipo = scanner.nextLine();

        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Nova duração: ");
        String duracao = scanner.nextLine();

        System.out.print("Nova data de início (formato dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            java.sql.Date sqlDate = new java.sql.Date(data.getTime());

            Treino treino = new Treino();
            treino.setId(id);
            treino.setTipoTreino(tipo);
            treino.setDescricao(descricao);
            treino.setDuracao(duracao);
            treino.setDataInicio(sqlDate);
            boolean sucesso = treinoDAO.editar(treino);
            if (sucesso) {
                System.out.println("Treino atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar treino. Verifique o ID e tente novamente.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao converter data: " + e.getMessage());
        }
    }
}
