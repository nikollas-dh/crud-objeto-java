import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("\n==== Secretaria ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurmas();
                break;
            case "3":
                System.out.println("Até breve...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                menuPrincipal();
        }

    }

    private static void menuTurmas() {
        System.out.println("\n==== Turmas ====");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turma");
        System.out.println("3 - Atualizar Turma");
        System.out.println("4 - Excluir Turma");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listar(listaTurmas);
                menuTurmas();
                break;
            case "2":
                cadastrarTurma();
                menuTurmas();
                break;
            case "3":
                atualizar("turma",listaTurmas);
                menuTurmas();
                break;
            case "4":
                excluir(listaTurmas);
                menuTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                menuTurmas();
        }
    }

    private static void menuAlunos() {
        System.out.println("\n==== Alunos ====");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar Aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                cadastrarAluno();
                menuAlunos();
                break;
            case "3":
                atualizar("aluno",listaAlunos);
                menuAlunos();
                break;
            case "4":
                excluir(listaAlunos);
                menuAlunos();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                menuAlunos();
        }
    }

    private static void excluir(ArrayList<? extends Ativavel> lista) {
        if(isVazio(lista)) {
            System.out.println("Não há items cadastrados");
            return;
        }
        int idExcluir = validaId(lista);
        if (confirmaExclusao()){
//            listaTurmas.remove(idExcluir);
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluída com sucesso!");
        }
    }

//    private static boolean isVazio(ArrayList<?> lista) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        if (lista.isEmpty()) return true;
//
//        for (var item : lista){
//            if ((boolean) item.getClass().getMethod("isAtivo").invoke(item)) return false;
//        }
//
//        return true;
//    }


    private static boolean isVazio(ArrayList<? extends Ativavel> lista) {
        if (lista.isEmpty()) return true;

        for (var item : lista){
            if (item.isAtivo()) return false;
        }

        return true;
    }

    private static boolean confirmaExclusao() {
        while (true) {
            String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opção inválida, digite S para sim ou N para não!");
                    break;
            }
        }
    }

    private static int validarItemLista(String opcao, ArrayList<?> lista) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && lista.size() > indiceLista ? indiceLista : -1;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas:");
        for (int i=0;i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s\n",i+1, listaTurmas.get(i).getSigla());
        }
    }


    private static void atualizar(String editar, ArrayList<?>lista) {
        if(isVazio((ArrayList<? extends Ativavel>) lista)) {
            System.out.println("Não há itens cadastrados");
            return;
        }

        listar((ArrayList<? extends Ativavel>) lista);

        int idAtualizar = validaId(lista);
        if (editar.equals("turma")){
            System.out.printf("O período atual é: %s", listaTurmas.get(idAtualizar).getPeriodo());
            atualizarParcial(editar,"período", idAtualizar);

            System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
            atualizarParcial(editar,"curso", idAtualizar);

            System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
            atualizarParcial(editar,"sigla", idAtualizar);
        }
        else if (editar.equals("Aluno")){
            System.out.printf("O nome atual é: %s", listaAlunos.get(idAtualizar).getNome());
            atualizarParcial(editar,"nome", idAtualizar);

            System.out.printf("A data de nascimento atual é: %s", listaAlunos.get(idAtualizar).getDatanascimento());
            atualizarParcial(editar,"datanascimento", idAtualizar);

            System.out.printf("A turma atual é: %s", listaAlunos.get(idAtualizar).getTurma());
            atualizarParcial(editar,"turma", idAtualizar);
        }
    }
private static void atualizarTurma() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();

        int idAtualizar = validaId(listaTurmas);

        System.out.printf("O período atual é: %s", listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("turma","período", idAtualizar);

        System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("turma","curso", idAtualizar);

        System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("turma","sigla", idAtualizar);
    }

    private static void atualizarParcial(String editar , String atributo, int idAtualizar){
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    if (editar.equals("turma")){
                        switch (atributo){
                            case "período":
                                Periodo periodo = validarPeriodo();
                                listaTurmas.get(idAtualizar).setPeriodo(periodo);
                                break;
                            case "curso":
                                String curso = validarCurso();
                                listaTurmas.get(idAtualizar).setCurso(curso);
                                break;
                            case "sigla":
                                String sigla = validarSigla();
                                listaTurmas.get(idAtualizar).setSigla(sigla);
                                break;
                        }
                        System.out.println(atributo + " atualizado com sucesso!");
                        rodarNovamente = false;
                        break;

                    }
                    else if(editar.equals("turma")){
                        switch (atributo){
                            case "nome":
                                String nome = Leitura.dados("Digite um novo nome");
                                listaAlunos.get(idAtualizar).setNome(nome);
                                break;
                            case "turma":
                                int idTurma = validaId(listaTurmas);
                                Turma turma = listaTurmas.get(idTurma);
                                listaAlunos.get(idAtualizar).setTurma(turma);
                                break;
                            case "datanascimento":
                                LocalDate datanascimento = validarData();
                                listaAlunos.get(idAtualizar).setDatanascimento(datanascimento);
                                break;
                        }
                    }
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");

            }
        }
    }
//    private static void atualizarParcial(String atributo, int idAtualizar){
//        boolean rodarNovamente = true;
//        while (rodarNovamente) {
//            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
//            switch (opcao) {
//                case "S":
//                    switch (atributo){
//                        case "período":
//                            Periodo periodo = validarPeriodo();
//                            listaTurmas.get(idAtualizar).setPeriodo(periodo);
//                            break;
//                        case "curso":
//                            String curso = validarCurso();
//                            listaTurmas.get(idAtualizar).setCurso(curso);
//                            break;
//                        case "sigla":
//                            String sigla = validarSigla();
//                            listaTurmas.get(idAtualizar).setSigla(sigla);
//                            break;
//                    }
//                    System.out.println(atributo + " atualizado com sucesso!");
//                    rodarNovamente = false;
//                    break;
//                case "N":
//                    rodarNovamente = false;
//                    break;
//                default:
//                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
//            }
//        }
//    }

    private static String validarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ");
        }
        return sigla;
    }

    private static String validarCurso() {
        String curso = Leitura.dados("Digite o curso: ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso: ");
        }
        return curso;
    }

    private static void atualizarPeriodo(int idAtualizar) {
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o período? (S/N): ").toUpperCase();
            switch (opcaoPeriodo) {
                case "S":
                    Periodo periodo = validarPeriodo();
                    listaTurmas.get(idAtualizar).setPeriodo(periodo);
                    System.out.println("Período atualizado com sucesso para " + periodo);
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
            }
        }

//        while (true) {
//            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o período? (S/N): ").toUpperCase();
//            switch (opcaoPeriodo) {
//                case "S":
////                Periodo periodo = validarPeriodo();
////                listaTurmas.get(idAtualizar).setPeriodo(periodo);
//                    listaTurmas.get(idAtualizar).setPeriodo(validarPeriodo());
//                    break;
//                case "N":
//                    break;
//                default:
//                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
//                    continue;
//            }
//            break;
//        }
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();
        String curso = validarCurso();
        String sigla = validarSigla();

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) return false;

        for (Turma turma : listaTurmas){
            if (turma.getSigla().equals(sigla)){
                return false;
            }
        }
        return true;
    }

//    private static boolean isCharacter(String texto) {
//        String textoSemNumeros = texto.replaceAll("\\d", "");
//        String textoSemEspecial = texto.replaceAll("[^!-@-#-$-%-¨-&-*-(-)--]", "");
//
//        return !texto.isBlank() && texto.equals(textoSemNumeros);
//    }

    private static boolean isCharacter(String texto) {
        String textoLimpo = texto.replaceAll("[^a-zA-Zá-úÁ-Úâ-ûÂ-ÛãõÃÕçÇ ]", "");
        return !texto.isBlank() && texto.equals(textoLimpo);
    }
    
    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o número do período escolhido:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral""");
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
                System.out.println("Opção inválida, digite novamente");
                return validarPeriodo();
        }
    }

    private static void listar(ArrayList<? extends Ativavel>lista ) {
        if(isVazio(lista)) {
            System.out.println("Não há itens cadastrados");
            return;
        }
        for(var item : lista){
            if (item.isAtivo())
                System.out.println(item);
        }
    }

    private static void excluirAluno() {
        if(isVazio(listaAlunos)) {
            System.out.println("Não há Alunos cadastrados");
            return;
        }

//        listarIndiceSigla();

        int idExcluir = validaId(listaAlunos);

        if (confirmaExclusao()){
//            listaTurmas.remove(idExcluir);
            listaAlunos.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluída com sucesso!");
        }
    }

    private static int validaId(ArrayList<?> lista) {
        String opcao = Leitura.dados("\nDigite o id desejado: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao, lista);

            if (opcaoUsuario==-1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("Digite o id desejado: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static void atualizarAluno() {

    }

    private static void cadastrarAluno() {
        String nome = Leitura.dados("Digite o nome do aluno: ");
        while(!isCharacter(nome)) {
            System.out.println("Nome inválido! Não use números ou caracteres especiais, por favor");
            nome = Leitura.dados("Digite o nome: ");
        }
        LocalDate datanascimento = validarData();
        listarTurmasIndiceSigla();
//      String idTurma = Leitura.dados("Selecione uma turma");
//      int idTurmaInteiro = Integer.parseInt(idTurma);
        int idTurma = validaId(listaTurmas);
//        Turma turma = listaTurmas.get(idTurmaInteiro-1);
        Turma turma = listaTurmas.get(idTurma);
        Aluno aluno = new Aluno(nome, datanascimento,turma);
        listaAlunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static LocalDate validarData() {
        LocalDate datanascimento=null;
        String dataTexto = Leitura.dados("Digite a data de nascimento no formato dd/MM/yyyy: ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while(datanascimento == null){
            try{
                datanascimento = LocalDate.parse(dataTexto,format);
            }catch (DateTimeParseException e){
                dataTexto = Leitura.dados("Formato inválido! Por favor, digite no formato dd/MM/yyyy");
            }
        }

        int idade = Period.between(datanascimento, LocalDate.now()).getYears();
        while(idade >=130 || idade<14){
            System.out.println("Idade inválida para cadastro!");
            validarData();
        }
        return datanascimento;
    }

    private static void listarAlunos() {
        for (Aluno aluno:listaAlunos){
            System.out.println(aluno);
        }
    }
}