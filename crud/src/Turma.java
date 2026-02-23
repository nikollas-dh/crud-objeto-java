public class Turma {
    private String curso;
    private String sigla;
    private Periodo periodo;


    public Turma() {
        this.curso = curso;
        this.sigla = sigla;
        this.periodo = periodo;
    }
    public Turma(String curso, String sigla, Periodo periodo) {
        this.curso = "";
        this.sigla = "";
        this.periodo = Periodo.MATUTINO;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "curso='" + curso + '\'' +
                ", sigla='" + sigla + '\'' +
                ", periodo=" + periodo +
                '}';
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
