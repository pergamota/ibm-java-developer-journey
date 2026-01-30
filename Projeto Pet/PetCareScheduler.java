import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PetCareScheduler {
    public static void main (String [] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Pet> pets = new ArrayList<>();
        carregarDados(pets);

        int opcao;

        do {
            System.out.println("\n--- SISTEMA ---");
            System.out.println("1 - Registrar pet");
            System.out.println("2 - Agendar compromisso");
            System.out.println("3 - Exibir pets");
            System.out.println("4 - Sair");
            System.out.println("5 - Relatórios");


            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                
                case 1:

                    System.out.println("Digite o Id único: ");
                    String id = sc.nextLine();

                    boolean idExiste = false;

                    for (Pet p : pets) {
                        if (p.getIdUnico().equals(id)) {
                            idExiste = true;
                            break;
                        }
                    }

                    if (idExiste) {
                        System.out.println("Erro: ID já cadastrado.");
                        break;
                    }

                    System.out.println("Digite o nome do pet: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite a raça do pet: ");
                    String raca = sc.nextLine();

                    System.out.println("Digite a idade do pet: ");
                    int idade = sc.nextInt();
                    sc.nextLine();

                    if (idade < 0) {
                        System.out.println("Erro: idade inválida.");
                        break;
                    }

                    System.out.println("Digite o nome do proprietario: ");
                    String nomeProprietario = sc.nextLine();

                    System.out.println("Digite as informacoes: ");
                    String informacoesDeContato = sc.nextLine();
                    
                    LocalDate data = LocalDate.now();

                    Pet pet = new Pet(id, nome, raca, idade, nomeProprietario, informacoesDeContato, data);
                    pets.add(pet);

                    System.out.println("Pet registrado !");

                    break;

                case 2:

                    System.out.println("Digite o ID do pet: ");
                    String idPet = sc.nextLine();

                    Pet petEncontrado = null;

                    for (Pet p : pets) {
                        if (p.getIdUnico().equals(idPet)) {
                            petEncontrado = p;
                            break;
                        }
                    }

                    if (petEncontrado == null) {
                        System.out.println("Pet não encontrado.");
                        break;
                    }

                    System.out.println("Digite o tipo do compromisso: ");
                    String type = sc.nextLine();

                    System.out.println("Digite a data e hora (yyyy-MM-dd HH:mm): ");
                    String dataEntrada = sc.nextLine();

                    LocalDateTime dateTime;

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        dateTime = LocalDateTime.parse(dataEntrada, formatter);
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido.");
                        break;
                    }

                    System.out.println("Digite as notas (opcional): ");
                    String notes = sc.nextLine();

                    Appointment appointment = new Appointment(type, dateTime, notes);
                    petEncontrado.addEncontro(appointment);

                    System.out.println("Compromisso agendado com sucesso!");
                    break;


                case 3:
                    
                    System.out.println("1 - Listar todos os pets");
                    System.out.println("2 - Listar compromissos de um pet");
                    System.out.println("3 - Próximos compromissos");
                    System.out.println("4 - Histórico de compromissos passados");
                    

                    int opcao1 = sc.nextInt();
                    sc.nextLine();
                    
                    if(opcao1 == 1) {

                        if(pets.isEmpty()) {
                            System.out.println("Nenhum pet cadastrado.");
                        } else {

                            for(Pet p : pets) {
                                System.out.println("ID: " + p.getIdUnico());
                                System.out.println("Nome: " + p.getName());
                                System.out.println("Raca: " + p.getRaca());
                                System.out.println("Idade: " + p.getIdade());
                                System.out.println("Nome do proprietario: " + p.getNomeProprietario());
                                System.out.println("Informações: " + p.getInformacoes());
                                System.out.println("Data de adesão: " + p.getDate()); 
                                System.out.println("----------------------");
                            }

                        }

                    } else {

                        if(opcao1 == 2) {

                            System.out.println("Digite o Id único do pet:");
                            String iD = sc.nextLine();

                            Pet Encontrado = null;

                            for (Pet p : pets) {
                                if (p.getIdUnico().equals(iD)) {
                                    Encontrado = p;
                                    break;
                                }
                            }

                            if (Encontrado == null) {
                                System.out.println("Pet não encontrado.");
                            } else {
                                if (Encontrado.getAppointments().isEmpty()) {
                                    System.out.println("Este pet não possui compromissos.");
                                } else {
                                    for (Appointment a : Encontrado.getAppointments()) {
                                        System.out.println(a);
                                    }
                                }
                            }

                        } else {
                            
                            if(opcao1 == 3) {

                                LocalDateTime agora = LocalDateTime.now();
                                boolean encontrou = false;

                                for (Pet p : pets) {
                                    for (Appointment a : p.getAppointments()) {
                                        if (a.getDateTime().isAfter(agora)) {
                                            System.out.println("Pet: " + p.getName());
                                            System.out.println(a);
                                            System.out.println("-------------------");
                                            encontrou = true;
                                        }
                                    }
                                }

                                if (!encontrou) {
                                    System.out.println("Não há compromissos futuros.");
                                }

                            } else {

                                if(opcao1 == 4) {

                                    LocalDateTime agora = LocalDateTime.now();
                                    boolean encontrou = false;

                                    for (Pet p : pets) {
                                        for (Appointment a : p.getAppointments()) {
                                            if (a.getDateTime().isBefore(agora)) {
                                                System.out.println("Pet: " + p.getName());
                                                System.out.println(a);
                                                System.out.println("-------------------");
                                                encontrou = true;
                                            }
                                        }
                                    }

                                    if (!encontrou) {
                                        System.out.println("Não há histórico de compromissos.");
                                    }

                                }
                            }

                        }

                    }

                    break;

                case 4:
                    salvarDados(pets);
                    System.out.println("Encerrando o sistema...");
                    break;

                case 5:
                    
                    System.out.println("1 - Pets com compromissos na próxima semana");
                    System.out.println("2 - Pets sem visita ao veterinário há 6 meses");

                    int relatorio = sc.nextInt();
                    sc.nextLine();

                    if (relatorio == 1) {
                        relatorioProximaSemana(pets);
                    } else if (relatorio == 2) {
                        relatorioVeterinarioAtrasado(pets);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;


                default: 
                    
                    System.out.println("Opcao invalida");

            }

        }
        while (opcao != 4);

        sc.close();

    }

    private static void salvarDados(ArrayList<Pet> pets) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pets.txt"))) {

            for (Pet p : pets) {
                writer.write("PET|" +
                    p.getIdUnico() + "|" +
                    p.getName() + "|" +
                    p.getRaca() + "|" +
                    p.getIdade() + "|" +
                    p.getNomeProprietario() + "|" +
                    p.getInformacoes() + "|" +
                    p.getDate());
                writer.newLine();

            for (Appointment a : p.getAppointments()) {
                writer.write("APPOINTMENT|" +
                    a.getType() + "|" +
                    a.getDateTime() + "|" +
                    a.getNotes());
                writer.newLine();
            }
        }

        } catch (IOException e) {
            System.out.println("Erro ao salvar dados em arquivo.");
        }
    }

    private static void carregarDados(ArrayList<Pet> pets) {

        File file = new File("pets.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            Pet petAtual = null;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts[0].equals("PET")) {

                    petAtual = new Pet(
                        parts[1],
                        parts[2],
                        parts[3],
                        Integer.parseInt(parts[4]),
                        parts[5],
                        parts[6],
                        LocalDate.parse(parts[7])
                    );

                    pets.add(petAtual);

                } else if (parts[0].equals("APPOINTMENT") && petAtual != null) {

                    Appointment a = new Appointment(
                        parts[1],
                        LocalDateTime.parse(parts[2]),
                        parts[3]
                    );

                    petAtual.addEncontro(a);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar dados do arquivo.");
        }
    }

    private static void relatorioProximaSemana(ArrayList<Pet> pets) {

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.plusWeeks(1);
        boolean encontrou = false;

        for (Pet p : pets) {
            for (Appointment a : p.getAppointments()) {
                if (a.getDateTime().isAfter(agora) &&
                    a.getDateTime().isBefore(limite)) {

                    System.out.println("Pet: " + p.getName());
                    System.out.println(a);
                    System.out.println("-------------------");
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum compromisso na próxima semana.");
        }
    }   

    private static void relatorioVeterinarioAtrasado(ArrayList<Pet> pets) {

        LocalDateTime limite = LocalDateTime.now().minusMonths(6);

        for (Pet p : pets) {

            LocalDateTime ultimaVisita = null;

            for (Appointment a : p.getAppointments()) {
                if (a.getType().equalsIgnoreCase("Veterinário")) {
                    if (ultimaVisita == null || a.getDateTime().isAfter(ultimaVisita)) {
                        ultimaVisita = a.getDateTime();
                    }
                }
            }

            if (ultimaVisita == null || ultimaVisita.isBefore(limite)) {
                System.out.println("Pet atrasado para veterinário:");
                System.out.println("Nome: " + p.getName());
                System.out.println("Última visita: " +
                    (ultimaVisita == null ? "Nunca" : ultimaVisita));
                System.out.println("-------------------");
            }
        }
    }

}