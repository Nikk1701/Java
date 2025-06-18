package com.mycompany.academiaapp.gui;

import com.mycompany.academiaapp.Aluno;
import com.mycompany.academiaapp.AlunoDAO;
import com.mycompany.academiaapp.Treino;
import com.mycompany.academiaapp.TreinoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class AcademiaAppGUI extends JFrame {

    private AlunoDAO alunoDAO = new AlunoDAO();
    private TreinoDAO treinoDAO = new TreinoDAO();

    // Componentes alunos
    private JTable tabelaAlunos;
    private DefaultTableModel modelAlunos;

    // Componentes treinos
    private JTable tabelaTreinos;
    private DefaultTableModel modelTreinos;

    public AcademiaAppGUI() {
        setTitle("Sistema Academia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Alunos", criarPainelAlunos());
        abas.addTab("Treinos", criarPainelTreinos());

        add(abas);
        carregarAlunos();
    }

    private JPanel criarPainelAlunos() {
        JPanel painel = new JPanel(new BorderLayout());

        modelAlunos = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Nascimento", "Telefone", "Email"}, 0) {
            public boolean isCellEditable(int row, int col) {
                return false; // tabela só leitura
            }
        };
        tabelaAlunos = new JTable(modelAlunos);
        JScrollPane scroll = new JScrollPane(tabelaAlunos);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);

        painel.add(botoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> mostrarFormularioAluno(null));
        btnEditar.addActionListener(e -> {
            int linha = tabelaAlunos.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelAlunos.getValueAt(linha, 0);
                Aluno aluno = buscarAlunoPorId(id);
                if (aluno != null) mostrarFormularioAluno(aluno);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para editar.");
            }
        });
        btnExcluir.addActionListener(e -> {
            int linha = tabelaAlunos.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelAlunos.getValueAt(linha, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Confirma excluir aluno?");
                if (confirm == JOptionPane.YES_OPTION) {
                    alunoDAO.excluir(id);
                    carregarAlunos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir.");
            }
        });

        return painel;
    }

    private JPanel criarPainelTreinos() {
        JPanel painel = new JPanel(new BorderLayout());

        modelTreinos = new DefaultTableModel(new Object[]{"ID", "Tipo", "Descrição", "Duração", "Data Início"}, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabelaTreinos = new JTable(modelTreinos);
        JScrollPane scroll = new JScrollPane(tabelaTreinos);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("ID do Aluno:"));
        JTextField txtAlunoId = new JTextField(5);
        JButton btnCarregarTreinos = new JButton("Carregar Treinos");
        topo.add(txtAlunoId);
        topo.add(btnCarregarTreinos);
        painel.add(topo, BorderLayout.NORTH);

        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        botoes.add(btnAdicionar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);
        painel.add(botoes, BorderLayout.SOUTH);

        btnCarregarTreinos.addActionListener(e -> {
            try {
                int alunoId = Integer.parseInt(txtAlunoId.getText());
                carregarTreinos(alunoId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do aluno inválido.");
            }
        });

        btnAdicionar.addActionListener(e -> {
            try {
                int alunoId = Integer.parseInt(txtAlunoId.getText());
                mostrarFormularioTreino(null, alunoId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um ID de aluno válido para adicionar treino.");
            }
        });

        btnEditar.addActionListener(e -> {
            int linha = tabelaTreinos.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelTreinos.getValueAt(linha, 0);
                Treino treino = buscarTreinoPorId(id);
                if (treino != null) {
                    try {
                        int alunoId = Integer.parseInt(txtAlunoId.getText());
                        mostrarFormularioTreino(treino, alunoId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "ID do aluno inválido.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um treino para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int linha = tabelaTreinos.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelTreinos.getValueAt(linha, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Confirma excluir treino?");
                if (confirm == JOptionPane.YES_OPTION) {
                    treinoDAO.remover(id);
                    try {
                        int alunoId = Integer.parseInt(txtAlunoId.getText());
                        carregarTreinos(alunoId);
                    } catch (Exception ex) {
                        modelTreinos.setRowCount(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um treino para excluir.");
            }
        });

        return painel;
    }

    private void carregarAlunos() {
        List<Aluno> alunos = alunoDAO.listarTodos();
        modelAlunos.setRowCount(0);
        for (Aluno a : alunos) {
            modelAlunos.addRow(new Object[]{
                    a.getId(),
                    a.getNome(),
                    a.getCpf(),
                    a.getDataNascimento(),
                    a.getTelefone(),
                    a.getEmail()
            });
        }
    }

    private void carregarTreinos(int alunoId) {
        List<Treino> treinos = treinoDAO.listarPorAluno(alunoId);
        modelTreinos.setRowCount(0);
        for (Treino t : treinos) {
            modelTreinos.addRow(new Object[]{
                    t.getId(),
                    t.getTipoTreino(),
                    t.getDescricao(),
                    t.getDuracao(),
                    t.getDataInicio()
            });
        }
    }

    private Aluno buscarAlunoPorId(int id) {
        for (Aluno a : alunoDAO.listarTodos()) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    private Treino buscarTreinoPorId(int id) {
        // Poderia criar um método no DAO, mas vamos buscar na lista carregada
        // Aqui simplificamos: buscar na base direta
        // Ou você pode criar método específico no DAO
        // Por enquanto, buscar entre todos alunos, seus treinos
        for (Aluno a : alunoDAO.listarTodos()) {
            List<Treino> treinos = treinoDAO.listarPorAluno(a.getId());
            for (Treino t : treinos) {
                if (t.getId() == id) return t;
            }
        }
        return null;
    }

    private void mostrarFormularioAluno(Aluno aluno) {
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtDataNasc = new JTextField();
        JTextField txtTelefone = new JTextField();
        JTextField txtEmail = new JTextField();

        if (aluno != null) {
            txtNome.setText(aluno.getNome());
            txtCpf.setText(aluno.getCpf());
            txtDataNasc.setText(aluno.getDataNascimento().toString());
            txtTelefone.setText(aluno.getTelefone());
            txtEmail.setText(aluno.getEmail());
        }

        Object[] campos = {
                "Nome:", txtNome,
                "CPF:", txtCpf,
                "Data de nascimento (AAAA-MM-DD):", txtDataNasc,
                "Telefone:", txtTelefone,
                "Email:", txtEmail
        };

        int opcao = JOptionPane.showConfirmDialog(this, campos, aluno == null ? "Cadastrar Aluno" : "Editar Aluno", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                String nome = txtNome.getText();
                String cpf = txtCpf.getText();
                LocalDate dataNascimento = LocalDate.parse(txtDataNasc.getText());
                String telefone = txtTelefone.getText();
                String email = txtEmail.getText();

                if (aluno == null) {
                    Aluno novoAluno = new Aluno(nome, cpf, dataNascimento, telefone, email);
                    alunoDAO.inserir(novoAluno);
                } else {
                    aluno.setNome(nome);
                    aluno.setCpf(cpf);
                    aluno.setDataNascimento(dataNascimento);
                    aluno.setTelefone(telefone);
                    aluno.setEmail(email);
                    alunoDAO.atualizar(aluno);
                }
                carregarAlunos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar aluno: " + e.getMessage());
            }
        }
    }

    private void mostrarFormularioTreino(Treino treino, int alunoId) {
        JTextField txtTipo = new JTextField();
        JTextField txtDescricao = new JTextField();
        JTextField txtDuracao = new JTextField();
        JTextField txtDataInicio = new JTextField();

        if (treino != null) {
            txtTipo.setText(treino.getTipoTreino());
            txtDescricao.setText(treino.getDescricao());
            txtDuracao.setText(treino.getDuracao());
            txtDataInicio.setText(treino.getDataInicio().toString());
        }

        Object[] campos = {
                "Tipo de treino:", txtTipo,
                "Descrição:", txtDescricao,
                "Duração:", txtDuracao,
                "Data de início (AAAA-MM-DD):", txtDataInicio
        };

        int opcao = JOptionPane.showConfirmDialog(this, campos, treino == null ? "Cadastrar Treino" : "Editar Treino", JOptionPane.OK_CANCEL_OPTION);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                String tipo = txtTipo.getText();
                String descricao = txtDescricao.getText();
                String duracao = txtDuracao.getText();
                java.sql.Date dataInicio = java.sql.Date.valueOf(txtDataInicio.getText());

                if (treino == null) {
                    Treino novoTreino = new Treino(alunoId, tipo, descricao, duracao, dataInicio);
                    treinoDAO.inserir(novoTreino);
                } else {
                    treino.setTipoTreino(tipo);
                    treino.setDescricao(descricao);
                    treino.setDuracao(duracao);
                    treino.setDataInicio(dataInicio);
                    treinoDAO.editar(treino);
                }
                carregarTreinos(alunoId);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar treino: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AcademiaAppGUI().setVisible(true);
        });
    }
}
