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
                menuPrincipal();
        }
    }

    private static void menuAlunos() {
        System.out.println("====Alunos====");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Alunos");
        System.out.println("3 - Atualizar Alunos");
        System.out.println("4 - Excluir Alunos");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:");
        switch (opcao) {
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
        System.out.println("3 - Atualizar Turmas");
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
        if (listaTurmas.isEmpty()) {
            System.out.println("Não á turmas cadastradas");
            return;
        }
        listaTurmasIndiceSigla();
        String opcao = Leitura.dados("Digite o nímero da turma que deseja excluir:");
       int opcaoValida =-1;
       int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarExclusaoCurso(opcao);
            if (opcaoUsuario ==-1){
                System.out.println("Opção inválida!");
                opcao = Leitura.dados("Digite o número da turma que deseja excluir");
            }else {
                opcaoValida = opcaoUsuario;
            }

       }
        if (confirmaExclusao()){
//            listaTurmas.remove(opcaoUsuario);
            listaTurmas.get(opcaoUsuario).setAtivo(false);
            System.out.println("Turma excluída com sucesso!");
        }
    }
public static  boolean isVazio(ArrayList<Turma> listaTurmas){
        if (listaTurmas.isEmpty()) return true;
        for (Turma turma: listaTurmas){
            if (turma.isAtivo()){
                System.out.println("Não a turmas cadastradas");
                return false;
            }
        }
        return true;
}
    private static boolean confirmaExclusao() {
        String confirma = Leitura.dados("Você tem certeza?S/N").toUpperCase();
        switch (confirma){
            case "S":
                return true;
            case "N":
                return false;
            default:
                System.out.println("Opção inválida");
               return confirmaExclusao();
        }

    }

    private static int validarExclusaoCurso(String opcao) {
        if (opcao.isBlank()) return -1;
        int opcaoNumero = -1;
        try{
            opcaoNumero = Integer.parseInt(opcao);
        }catch (NumberFormatException e){
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        if (opcaoNumero-1<=0 && opcaoNumero-1<listaTurmas.size()){
            return 0;
        }
        return -1;
    }

    private static void listaTurmasIndiceSigla() {
        System.out.println("Lista das Turmas");
        for (int i =0; i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo()) {
                System.out.printf("\n%d -- %s", i + 1, listaTurmas.get(i).getSigla());
            }
        }
    }

    private static void atualizarTurmas() {

    }

    private static void cadastrarTurmas() {
        Periodo periodo = validarPeriodo();

        String curso = Leitura.dados("Digite o curso: ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso: ");
        }

        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) {
            return false;
        }
        for (Turma t: listaTurmas){
            if (t.getSigla().equals(sigla)){
                return false;
            }
        }
        return true;
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
            if (t.isAtivo()) {
                System.out.println(t);
            }
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