import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private ArrayList<Aluno> listaTurmas = new ArrayList<>();

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
            break;
        case "2":
            cadastarAlunos();
            break;
        case "3":
            atualizarAlunos();
            break;
        case "4":
            excluirAlunos();
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
                break;
            case "2":
                cadastrarTurmas();
                break;
            case "3":
                atualizarTurmas();
                break;
            case "4":
                excluirTurmas();
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
        
    }

    private static void listarTurmas() {
        for (Turma  turma : listaTurmas){
            System.out.println(turma);
        }
    }

    private static void excluirAlunos() {
        
    }

    private static void atualizarAlunos() {
        
    }

    private static void cadastarAlunos() {
        
    }

    private static void listarAlunos() {
        
    }
}