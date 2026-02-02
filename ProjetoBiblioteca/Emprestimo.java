import java.time.LocalDate;

public class Emprestimo {
    
    private String nomeLeitor;
    private LocalDate data;

    public Emprestimo (String nomeLeitor, LocalDate data) {
        
        this.nomeLeitor = nomeLeitor;
        this.data = LocalDate.now();

    }

    public String getNomeLeitor () {
        return nomeLeitor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    

}
