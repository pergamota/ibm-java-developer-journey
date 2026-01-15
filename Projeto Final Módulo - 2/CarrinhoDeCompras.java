import java.util.Scanner;

// Exceção personalizada para item não encontrado
class ItemNaoEncontradoException extends Exception {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

public class CarrinhoDeCompras {

    public static void main(String[] args) {

        // Vetores para armazenar itens e preços
        String[] itens = new String[10];
        float[] precos = new float[10];

        itens[0] = "Maçã";     precos[0] = 0.50f;
        itens[1] = "Banana";   precos[1] = 0.30f;
        itens[2] = "Pão";      precos[2] = 2.00f;
        itens[3] = "Leite";    precos[3] = 1.50f;
        itens[4] = "Ovos";     precos[4] = 2.50f;
        itens[5] = "Queijo";   precos[5] = 3.00f;
        itens[6] = "Frango";   precos[6] = 5.00f;
        itens[7] = "Arroz";    precos[7] = 1.00f;
        itens[8] = "Macarrão"; precos[8] = 1.20f;
        itens[9] = "Tomate";   precos[9] = 0.80f;

        Scanner leitor = new Scanner(System.in);

        // Loop externo para vários usuários
        while (true) {

            float totalDaConta = 0.0f;

            // Loop interno para compras de um usuário
            while (true) {
                try {
                    System.out.println("Digite o nome do item (ou 'finalizar' para encerrar a compra):");
                    String itemDigitado = leitor.nextLine();

                    // Finalizar compra
                    if (itemDigitado.equalsIgnoreCase("finalizar")) {
                        System.out.println("Total da compra: R$ " + totalDaConta);
                        System.out.println("Obrigado por comprar conosco!");
                        break;
                    }

                    // Procurar o item no vetor
                    int indiceDoItem = -1;
                    for (int i = 0; i < itens.length; i++) {
                        if (itens[i].equalsIgnoreCase(itemDigitado)) {
                            indiceDoItem = i;
                            break;
                        }
                    }

                    // Se não encontrar o item, lança exceção
                    if (indiceDoItem == -1) {
                        throw new ItemNaoEncontradoException(
                            "Item '" + itemDigitado + "' não encontrado. Tente novamente."
                        );
                    }

                    System.out.println("Digite a quantidade de " + itens[indiceDoItem] + ":");
                    int quantidade = leitor.nextInt();
                    leitor.nextLine(); // Limpa o buffer

                    // Calcular custo
                    float custoDoItem = precos[indiceDoItem] * quantidade;
                    totalDaConta += custoDoItem;

                    System.out.println(
                        "Adicionado: " + quantidade + " x " + itens[indiceDoItem] +
                        "Total atual: R$ " + totalDaConta
                    );

                } catch (ItemNaoEncontradoException erro) {
                    System.out.println(erro.getMessage());

                } catch (Exception erro) {
                    System.out.println("Entrada inválida. Tente novamente.");
                    leitor.nextLine(); // Limpa entrada inválida
                }
            }

            // Opção para sair do programa
            System.out.println("Digite 'sair' para encerrar o programa ou pressione ENTER para novo usuário:");
            String entradaUsuario = leitor.nextLine();

            if (entradaUsuario.equalsIgnoreCase("sair")) {
                System.out.println("Programa encerrado. Até logo!");
                break;
            }
        }

        leitor.close();
    }
}