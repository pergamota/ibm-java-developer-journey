import java.util.ArrayList;

public class Livro {

    private int ID;
    private String titulo;
    private String autor;
    private ArrayList<Emprestimo> emprestimos;

    public Livro (int ID, String titulo, String autor) {

        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        ArrayList emprestimos = new ArrayList<>();

    }

    public int getID() {
        return ID;
    }

    public String getTitulo() {
        return getTitulo();
    }

    public String getAutor () {
        return getAutor();
    } 

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void adicionarLivros(Emprestimo emprestimo) {

    }

    public int getTotalEmprestimos() {
        
    }

}