import java.time.LocalDate;

public class Aluno implements Ativavel{
    private String nome;
    private LocalDate datanascimento;
    private Turma turma;
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public boolean setAtivo() {
        return false;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Aluno() {
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.turma = turma;
    }

    public Aluno(String nome, LocalDate datanascimento, Turma turma) {
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.turma = turma;
    }





    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", datanascimento=" + datanascimento +
                ", turma=" + turma +
                '}';
    }

    public LocalDate getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(LocalDate datanascimento) {
        this.datanascimento = datanascimento;
    }
}
