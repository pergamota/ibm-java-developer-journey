import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet {

    private String idUnico;
    private String name;
    private String raca;
    private int idade;
    private String nomeProprietario;
    private String informacoesDeContato;
    private LocalDate dataRegistro;
    private ArrayList <Appointment> encontros;

    public Pet (String idUnico, String name, String raca, int idade, String nomeProprietario, String informacoes, LocalDate dataRegistro) {

        this.idUnico = idUnico;
        this.name = name;
        this.raca = raca;
        this.idade = idade;
        this.nomeProprietario = nomeProprietario;
        this.informacoesDeContato = informacoes;
        this.dataRegistro = dataRegistro;
        this.encontros = new ArrayList<>();

    }

    public String getIdUnico() {
        return idUnico;
    }

    public String getName() {
        return name;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public String getInformacoes() {
        return informacoesDeContato;
    }

    public LocalDate getDate() {
        return dataRegistro;
    }

    public ArrayList <Appointment> getEncontros() {
        return encontros;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setRaca (String raca) {
        this.raca = raca;
    }

    public void setIdade (int idade) {
        this.idade = idade;
    }

    public void setInformacoes (String informacoes) {
        this.informacoesDeContato = informacoes;
    }

    public void addEncontro(Appointment appointment) {
        encontros.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return encontros;
    }

    @Override
    public String toString() {

        return "Pet{" + "Id = " + idUnico + '\'' + ", nome = " + name +
        '\'' + ", raca = " +  raca + '\'' + "Idade = " + idade + '\'' +
        ", Nome Do Proprietario" + nomeProprietario + '\'' + ", Informacoes = "
        + informacoesDeContato + '\'' + ", Data = " + dataRegistro  + '}';

    }

}