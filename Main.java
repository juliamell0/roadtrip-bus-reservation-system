import java.util.Scanner;

public class Main {

    static int[] onibus = new int[4];
    static int[] assentos = new int[4];

    static String[] passageiros = new String[20];
    static int[] onibusReserva = new int[20];

    static int totalOnibus = 0;
    static int totalReservas = 0;

    public static void limparTela() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    public static void esperar() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }

    public static void listarOnibus() {

        if (totalOnibus == 0) {
            System.out.println("Nenhum ônibus cadastrado!");
            return;
        }

        System.out.println("\n===== ÔNIBUS DISPONÍVEIS =====");

        for (int i = 0; i < totalOnibus; i++) {

            System.out.println(
                    "Ônibus: " + onibus[i] +
                            " | Assentos disponíveis: " + assentos[i]);

        }

        System.out.println("===============================");
    }

    public static void menu() {

        System.out.println("=================================");
        System.out.println("     ROADTRIP BUS RESERVATION");
        System.out.println("=================================");
        System.out.println("1 - Registrar ônibus");
        System.out.println("2 - Registrar assentos");
        System.out.println("3 - Reservar passagem");
        System.out.println("4 - Consultar por ônibus");
        System.out.println("5 - Consultar por passageiro");
        System.out.println("6 - Encerrar");

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {

            limparTela();
            menu();

            System.out.print("\nEscolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:

                    if (totalOnibus >= 4) {

                        System.out.println("Não é possível cadastrar mais de 4 ônibus!");
                        esperar();
                        break;

                    }

                    System.out.print("Número do ônibus: ");
                    onibus[totalOnibus] = sc.nextInt();

                    totalOnibus++;

                    System.out.println("Ônibus registrado com sucesso!");
                    esperar();

                    break;

                case 2:

                    if (totalOnibus == 0) {

                        System.out.println("Cadastre os ônibus primeiro!");
                        esperar();
                        break;

                    }

                    for (int i = 0; i < totalOnibus; i++) {

                        System.out.print("Assentos disponíveis no ônibus " + onibus[i] + ": ");
                        assentos[i] = sc.nextInt();

                    }

                    System.out.println("Assentos registrados com sucesso!");
                    esperar();

                    break;

                case 3:

                    if (totalReservas >= 20) {

                        System.out.println("Limite máximo de reservas atingido!");
                        esperar();
                        break;

                    }

                    if (totalOnibus == 0) {

                        System.out.println("Cadastre os ônibus primeiro!");
                        esperar();
                        break;

                    }

                    listarOnibus();

                    System.out.print("Informe o número do ônibus: ");
                    int numBus = sc.nextInt();

                    int indice = -1;

                    for (int i = 0; i < totalOnibus; i++) {

                        if (onibus[i] == numBus) {

                            indice = i;

                        }

                    }

                    if (indice == -1) {

                        System.out.println("Este ônibus não existe!");
                        esperar();
                        break;

                    }

                    if (assentos[indice] == 0) {

                        System.out.println("Não há assentos disponíveis para este ônibus!");
                        esperar();
                        break;

                    }

                    sc.nextLine();

                    System.out.print("Nome do passageiro: ");
                    String nome = sc.nextLine();

                    passageiros[totalReservas] = nome;
                    onibusReserva[totalReservas] = numBus;

                    assentos[indice]--;

                    totalReservas++;

                    System.out.println("Reserva realizada com sucesso!");
                    esperar();

                    break;

                case 4:

                    System.out.print("Informe o número do ônibus: ");
                    int consulta = sc.nextInt();

                    boolean existe = false;
                    boolean encontrou = false;

                    for (int i = 0; i < totalOnibus; i++) {

                        if (onibus[i] == consulta) {
                            existe = true;
                        }

                    }

                    if (!existe) {

                        System.out.println("Este ônibus não existe!");
                        esperar();
                        break;

                    }

                    for (int i = 0; i < totalReservas; i++) {

                        if (onibusReserva[i] == consulta) {

                            System.out.println("Passageiro: " + passageiros[i]);
                            encontrou = true;

                        }

                    }

                    if (!encontrou) {

                        System.out.println("Não há reservas realizadas para este ônibus!");

                    }

                    esperar();

                    break;

                case 5:

                    sc.nextLine();

                    System.out.print("Nome do passageiro: ");
                    String busca = sc.nextLine();

                    boolean achou = false;

                    for (int i = 0; i < totalReservas; i++) {

                        if (passageiros[i].equalsIgnoreCase(busca)) {

                            System.out.println("Reserva no ônibus: " + onibusReserva[i]);
                            achou = true;

                        }

                    }

                    if (!achou) {

                        System.out.println("Não há reservas realizadas para este passageiro!");

                    }

                    esperar();

                    break;

                case 6:

                    System.out.println("Sistema encerrado.");

                    break;

                default:

                    System.out.println("Opção inválida!");
                    esperar();

            }

        } while (opcao != 6);

        sc.close();

    }
}