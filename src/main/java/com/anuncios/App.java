package com.anuncios;

import com.anuncios.db.FlywayConfig;
import com.anuncios.models.Anuncio;
import com.anuncios.models.dao.AnuncioDAO;
import com.anuncios.models.dao.DAOFactory;
import com.anuncios.utils.Calculo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class App {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        AnuncioDAO anuncioDao = DAOFactory.createAnuncioDAO();

        FlywayConfig.iniciarFlyway();

        try {
            do {
                int qtdAnunciosCadastrados = anuncioDao.findAll().size();
                System.out.println();
                System.out.println("==== Cadastro de anuncios | "+qtdAnunciosCadastrados+" registro(s) no banco ====");
                System.out.println("1 - Cadastrar Anuncio");
                System.out.println("2 - Exibir anuncios");
                System.out.println("3 - Exibir anuncios por cliente");
                System.out.println("4 - Exibir anuncios por intervalo de tempo");
                System.out.println("0 - Finalizar programa");
                System.out.println();

                Scanner sc = new Scanner(System.in);
                System.out.print("Digite um numero de 0 a 4: ");
                int i = sc.nextInt();
                sc.nextLine();

                switch (i) {
                    case 0:
                        System.out.println("Programa finalizado");
                        System.exit(0);
                        break;
                    case 1:
                        inserirAnuncioNoBancoDeDados(anuncioDao, sc);
                        break;
                    case 2:
                        exibirTodosOsAnuncios(anuncioDao);
                        break;
                    case 3:
                        exibirAnuncioPorCliente(anuncioDao, sc);
                        break;
                    case 4:
                        exibirAnuncioPorData(anuncioDao, sc);
                        break;
                    default:
                        System.out.println("\nDigite uma opcao valida");
                        break;
                }
                System.out.print("Deseja continuar (1) ou finalizar (0): ");
                i = sc.nextInt();
                if (i == 0) {
                    System.out.println("Programa finalizado");
                    System.exit(0);
                }

            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Houve um erro ao inserir um valor ");
        } catch (DateTimeParseException e) {
            System.out.println("Houve um erro ao inserir uma data ==> " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void exibirAnuncio(Anuncio anuncio) {
        double totalInvestido = Calculo.valorTotalInvestido(anuncio.getInvestimentoPorDia(), anuncio.getDataInicio(), anuncio.getDataTermino());

        int visualizacoesPorInvestimento = Calculo.visualizacaoPorValorInvestido(totalInvestido);
        int pessoasQueClicaram = Calculo.cliquesPorVisualizacao(visualizacoesPorInvestimento);
        int pessoasQueCompartilharam = Calculo.compartilhamentosPorCliques(pessoasQueClicaram);
        int visualizacaoPorCompartilhar = Calculo.visualizacaoPorCompartilhamento(pessoasQueCompartilharam);
        int totalDeVisualizacao = Calculo.totalDeVisualizacao(visualizacoesPorInvestimento, visualizacaoPorCompartilhar);


        System.out.println("=============> " + anuncio.getNome());
        System.out.println("Cliente: "+anuncio.getCliente()+" | Data de inicio: "+anuncio.getDataInicio().format(formatter)+" | Data de Termino: "+anuncio.getDataTermino().format(formatter));
        System.out.printf("Valor total investido: %.2f\n", totalInvestido);
        System.out.println("Quantidade maxima de visualizacao: " + totalDeVisualizacao);
        System.out.println("Quantidade maxima de cliques: " + pessoasQueClicaram);
        System.out.println("Quantidade maxima de compartilhamentos: " + pessoasQueCompartilharam);
        System.out.println();
    }

    private static void exibirTodosOsAnuncios(AnuncioDAO anuncioDAO) {
        List<Anuncio> list = new ArrayList<>();
        list = anuncioDAO.findAll();
        if (list.isEmpty()) {
            System.out.println("Nenhum anuncio no banco de dados");
        } else {
            list.forEach(obj -> exibirAnuncio(obj));
        }
    }

    private static void exibirAnuncioPorCliente(AnuncioDAO anuncioDAO, Scanner sc) {
        System.out.print("Digite o nome do cliente: ");
        String cliente = sc.nextLine();
        List<Anuncio> list = new ArrayList<>();
        list = anuncioDAO.findByCliente(cliente);
        if (list.isEmpty()) {
            System.out.println("Nenhum cliente com esse nome encontrado");
        } else {
            System.out.println();
            list.forEach(obj -> exibirAnuncio(obj));
        }
    }

    private static void exibirAnuncioPorData(AnuncioDAO anuncioDAO, Scanner sc) {
        System.out.print("Insira a primeira data(Ex: 01/01/2000): ");
        LocalDate dataInicio = LocalDate.parse(sc.nextLine(), formatter);
        LocalDate dataFinal = LocalDate.now();
        do {
            System.out.print("Insira a segunda data(Ex: 01/01/2000): ");
            dataFinal = LocalDate.parse(sc.nextLine(), formatter);
            if (dataFinal.isBefore(dataInicio)) {
                System.out.println("A segunta data nao pode ser anterior a primeira!");
            } else {
                break;
            }
        } while (true);
        System.out.println();
        List<Anuncio> list = new ArrayList<>();
        list = anuncioDAO.findByDatas(dataInicio, dataFinal);
        list.forEach(obj -> exibirAnuncio(obj));
    }

    private static void inserirAnuncioNoBancoDeDados(AnuncioDAO anuncioDAO, Scanner sc) {

        Anuncio anuncio = new Anuncio();

        System.out.print("Insira o nome do anuncio: ");
        anuncio.setNome(sc.nextLine());
        System.out.print("Insira o nome do cliente: ");
        anuncio.setCliente(sc.nextLine());
        System.out.print("Insira a data de inicio do anuncio(Ex: 01/01/2000):  ");
        anuncio.setDataInicio(LocalDate.parse(sc.nextLine(), formatter));
        do {
            System.out.print("Insira a data de termino do anuncio(Ex: 01/01/2000): ");
            anuncio.setDataTermino(LocalDate.parse(sc.nextLine(), formatter));
            if (anuncio.getDataTermino().isBefore(anuncio.getDataInicio())) {
                System.out.println("Data de termino nao pode ser antes de inicio");
            } else {
                break;
            }
        } while (true);

        System.out.print("Insira o valor do investimento por dia(Ex: 100.00): ");
        anuncio.setInvestimentoPorDia(sc.nextDouble());

        int result = anuncioDAO.insert(anuncio);
        if (result > 0) {
            System.out.println("Dados salvos com sucesso!");
        } else {
            System.out.println("Não foi possivel salvar dados no banco de dados.");
        }

    }
}
