package estoque_av2_projeto;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int qntEstoque;
    private String categoria;

    public Produto(int id, String nome, String descricao, double preco,
                   int qntEstoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.qntEstoque = qntEstoque;
        this.categoria = categoria;
    }
    @Override
    public String toString() {
        return "Produto:" +
                "\r\n Id: " + id +
                "\r\n Nome: " + nome + '\'' +
                "\r\n Descricao: " + descricao + '\'' +
                "\r\n Preco: " + preco +
                "\r\n Quantidade em Estoque: " + qntEstoque +
                "\r\n Categoria: " + categoria + '\'' +
                '}';
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getQuantidadeEstoque() {
        return this.qntEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.qntEstoque = quantidadeEstoque;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void mostrarProdutos(){
        System.out.println("Id: " + this.getId());
        System.out.println("Nome: " + this.getNome());
        System.out.println("Descrição: " + this.getDescricao());
        System.out.println("Preço: " + this.getPreco());
        System.out.println("Estoque: " + this.getQuantidadeEstoque());
        System.out.println("Categoria: " + this.getCategoria());

    }
}

