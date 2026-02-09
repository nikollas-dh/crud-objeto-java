import java.util.ArrayList;

public class Main {
    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {

        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("====Secretaria====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");

        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurmas();
                break;
        case "3":
        System.out.println("Saindo...");
        System.exit(0);
        default:
        System.out.println("Opção inválida!");
    }
}

private static void menuAlunos(){
    System.out.println("====Alunos====");
    System.out.println("1 - Listar Alunos");
    System.out.println("2 - Cadastrar Alunos");
    System.out.println("3 - Atualizar Alunos");
    System.out.println("4 - Excluir Alunos");
    System.out.println("5 - Voltar ao menu principal");
   String opcao = Leitura.dados("Digite a opção desejada:");
    switch (opcao){
        case "1":
            listarAlunos();
            menuAlunos();
            break;
        case "2":
            cadastarAlunos();
            menuAlunos();
            break;
        case "3":
            atualizarAlunos();
            menuAlunos();
            break;
        case "4":
            excluirAlunos();
            menuAlunos();
            break;
        case "5":
            menuPrincipal();
            break;
        default:
            System.out.println("Opção inválida!");

        }
    }

    private static void menuTurmas() {
        System.out.println("====Turmas====");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turmas");
        System.out.println("3 - Atualizar Alunos");
        System.out.println("4 - Excluir Turmas");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
            case "1":
                listarTurmas();
                menuTurmas();
                break;
            case "2":
                cadastrarTurmas();
                menuTurmas();
                break;
            case "3":
                atualizarTurmas();
                menuTurmas();
                break;
            case "4":
                excluirTurmas();
                menuTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void excluirTurmas() {
    }

    private static void atualizarTurmas() {
        
    }

    private static void cadastrarTurmas() {
        Periodo periodo = validarPeriodo();
        String curso = Leitura.dados("Digite o curso");
        curso.replaceAll("\\d", "");

        while (!isCharacter(curso)){
            System.out.println("Nome do curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso novamente:");
        }
        String sigla = Leitura.dados("Digite a sigla");

        boolean repetido = true;
        while (sigla.isBlank() && !repetido){
            System.out.println("Sigla inválida");
            sigla = Leitura.dados("Digite a sigla novamente:");
            sigla = sigla.toUpperCase();

            for (Turma t : listaTurmas){
                if (t.getSigla()){
                    System.out.println("Turma já cadastrada");
                    repetido =true;                }
            }
            repetido = false;
        }
        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
    }

    private static boolean isCharacter(String texto) {
        String textoSemNumeros = texto.replaceAll("\\d","");
        return !texto.isBlank() && texto.equals(textoSemNumeros);
    }

    private static Periodo validarPeriodo() {
            String opcaoPeriodo = Leitura.dados(""" 
                Digite o período escolhido:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral
                """);
            Periodo periodo;
            switch (opcaoPeriodo){
                case "1":
                    return Periodo.MATUTINO;
                case "2":
                   return Periodo.VESPERTINO;

                case "3":
                   return Periodo.NOTURNO;

                case "4":
                   return Periodo.INTEGRAL;
                default:
                    System.out.println("Opção inválida");
                validarPeriodo();
            }

            return null;
        }



    private static void listarTurmas() {

        if (listaTurmas.isEmpty()) {
            System.out.println("Não á turmas cadastradas");
        }
        for (Turma t : listaTurmas){
            System.out.println(t);
        }
    }

    private static void excluirAlunos() {
        
    }

    private static void atualizarAlunos() {
        
    }

    private static void cadastarAlunos() {
        
    }

    private static void listarAlunos() {
        if (listaAlunos.isEmpty()) {
            System.out.println("Não á turmas cadastradas");
        }
        for (Aluno a : listaAlunos){
            System.out.println(a);
        }
    }
}