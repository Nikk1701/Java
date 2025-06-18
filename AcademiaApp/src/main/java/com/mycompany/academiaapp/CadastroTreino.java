package com.mycompany.academiaapp;

import java.sql.Date;
import java.util.Scanner;

public class CadastroTreino {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreinoDAO treinoDAO = new TreinoDAO();

        System.out.print("ID do aluno: ");
        int alunoId = Integer.parseInt(scanner.nextLine());

        System.out.print("Tipo de treino (ex: Cardio, Musculação): ");
        String tipoTreino = scanner.nextLine();

        System.out.print("Descrição do treino: ");
        String descricao = scanner.nextLine();

        System.out.print("Duração (ex: 45 minutos): ");
        String duracao = scanner.nextLine();

        System.out.print("Data de início (AAAA-MM-DD): ");
        Date dataInicio = Date.valueOf(scanner.nextLine());

        Treino treino = new Treino(alunoId, tipoTreino, descricao, duracao, dataInicio);
        treinoDAO.inserir(treino);
    }
}