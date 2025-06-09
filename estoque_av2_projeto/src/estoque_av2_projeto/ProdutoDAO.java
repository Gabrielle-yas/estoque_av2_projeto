package estoque_av2_projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO() throws SQLException {
        this.conn = Conexao.conectar();
    }

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (id, nome, descricao, preco, qnt_estoque, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, produto.getId());
        stmt.setString(2, produto.getNome());
        stmt.setString(3, produto.getDescricao());
        stmt.setDouble(4, produto.getPreco());
        stmt.setInt(5, produto.getQuantidadeEstoque());
        stmt.setString(6, produto.getCategoria().name());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Produto> listarTodos() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("qnt_estoque"),
                    Categoria.valueOf(rs.getString("categoria"))
            );
            lista.add(p);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produto SET nome=?, descricao=?, preco=?, qnt_estoque=?, categoria=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setDouble(3, produto.getPreco());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setString(5, produto.getCategoria().name());
        stmt.setInt(6, produto.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Produto produto = null;
        if (rs.next()) {
            produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("qnt_estoque"),
                    Categoria.valueOf(rs.getString("categoria"))
            );
        }

        rs.close();
        stmt.close();
        return produto;
    }
}

