package estoque_av2_projeto;

import estoque_av2_projeto.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {
    private JFrame frame;
    private JTextArea textArea;
    private ProdutoDAO produtoDAO;


    public App() {
    	    try {
    	        produtoDAO = new ProdutoDAO();
    	    } catch (SQLException e) {
    	        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco: " + e.getMessage());
    	        System.exit(1);
    	    }
    	
        frame = new JFrame("Sistema de gerenciamento de estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 5));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVisualizar = new JButton("Visualizar");
        JButton btnSair = new JButton("Sair");

        panel.add(btnCadastrar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnVisualizar);
        panel.add(btnSair);

        frame.add(panel, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarProdutos();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void cadastrarProduto() {
    	 try {
    	        int id = Integer.parseInt(JOptionPane.showInputDialog("Id: "));
    	        String nome = JOptionPane.showInputDialog("Nome: ");
    	        String descricao = JOptionPane.showInputDialog("Descrição: ");
    	        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço: "));
    	        int qntEstoque = Integer.parseInt(JOptionPane.showInputDialog("Estoque: "));
    	        String categoria = JOptionPane.showInputDialog("Categoria: ");

    	        Produto novoProduto = new Produto(id, nome, descricao, preco, qntEstoque, categoria);
    	        produtoDAO.inserir(novoProduto);

    	        JOptionPane.showMessageDialog(frame, "Produto cadastrado com sucesso!");
    	    } catch (SQLException e) {
    	        JOptionPane.showMessageDialog(frame, "Erro ao acessar o banco: " + e.getMessage());
    	    } catch (Exception e) {
    	        JOptionPane.showMessageDialog(frame, "Erro ao cadastrar produto: " + e.getMessage());
    	    }
    }

    private void editarProduto() {
    	 try {
    	        int idBusca = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do item:"));
    	        Produto produtoParaEditar = produtoDAO.buscarPorId(idBusca);

    	        if (produtoParaEditar == null) {
    	            JOptionPane.showMessageDialog(frame, "Produto com ID " + idBusca + " não encontrado!");
    	            return;
    	        }

    	        String novoNome = JOptionPane.showInputDialog("Novo Nome: ", produtoParaEditar.getNome());
    	        String novaDescricao = JOptionPane.showInputDialog("Nova Descrição: ", produtoParaEditar.getDescricao());
    	        double novoPreco = Double.parseDouble(JOptionPane.showInputDialog("Novo Preço: ", produtoParaEditar.getPreco()));
    	        int novaQntEstoque = Integer.parseInt(JOptionPane.showInputDialog("Novo Estoque: ", produtoParaEditar.getQuantidadeEstoque()));
    	        String novaCategoria = JOptionPane.showInputDialog("Nova Categoria: ", produtoParaEditar.getCategoria());

    	        produtoParaEditar.setNome(novoNome);
    	        produtoParaEditar.setDescricao(novaDescricao);
    	        produtoParaEditar.setPreco(novoPreco);
    	        produtoParaEditar.setQuantidadeEstoque(novaQntEstoque);
    	        produtoParaEditar.setCategoria(novaCategoria);

    	        produtoDAO.atualizar(produtoParaEditar);

    	        JOptionPane.showMessageDialog(frame, "Produto atualizado com sucesso!");
    	    } catch (SQLException e) {
    	        JOptionPane.showMessageDialog(frame, "Erro ao acessar o banco: " + e.getMessage());
    	    } catch (Exception e) {
    	        JOptionPane.showMessageDialog(frame, "Erro ao editar produto: " + e.getMessage());
    	    }
    }

    private void excluirProduto() {
    	try {
            int idBusca = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do item:"));
            Produto produtoParaExcluir = produtoDAO.buscarPorId(idBusca);

            if (produtoParaExcluir == null) {
                JOptionPane.showMessageDialog(frame, "Produto com ID " + idBusca + " não encontrado!");
                return;
            }

            produtoDAO.excluir(idBusca);
            JOptionPane.showMessageDialog(frame, "Produto removido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao acessar o banco: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir produto: " + e.getMessage());
        }
    }

    private void visualizarProdutos() {
    	try {
            List<Produto> listaProdutos = produtoDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (Produto produto : listaProdutos) {
                sb.append(produto.toString()).append("\n\n");
            }
            textArea.setText(sb.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao acessar o banco: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new App();
    }
}

