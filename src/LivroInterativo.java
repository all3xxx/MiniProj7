import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class LivroInterativo {
    public static void main(String[] args) {
        // Crie um dicionário de personagens
        HashMap<String, Personagem> personagens = new HashMap<>();

        // Carregue os dados dos personagens do arquivo personagens.txt
        try (BufferedReader br = new BufferedReader(new FileReader("personagens.txt"))) {
            String line;
            String nome = null;
            int energia = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nome: ")) {
                    nome = line.substring(6);
                } else if (line.startsWith("Energia: ")) {
                    energia = Integer.parseInt(line.substring(9));
                    personagens.put(nome, new Personagem(nome, energia));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crie um dicionário de capítulos
        HashMap<String, Capitulo> capitulos = new HashMap<>();

        // Carregue os dados dos capítulos do arquivo capitulos.txt
        try (BufferedReader br = new BufferedReader(new FileReader("capitulos.txt"))) {
            String line;
            String nomeCapitulo = null;
            String texto = null;
            String[] escolhas = null;
            String nomePersonagem = null;
            int alteracaoEnergia = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Capítulo: ")) {
                    nomeCapitulo = line.substring(10);
                } else if (line.startsWith("Texto: ")) {
                    texto = line.substring(7);
                } else if (line.startsWith("Escolhas: ")) {
                    escolhas = line.substring(10).split(", ");
                } else if (line.startsWith("Personagem: ")) {
                    nomePersonagem = line.substring(12);
                } else if (line.startsWith("AlteracaoEnergia: ")) {
                    alteracaoEnergia = Integer.parseInt(line.substring(18));
                    Personagem personagem = personagens.get(nomePersonagem);
                    Capitulo capitulo = new Capitulo(nomeCapitulo, texto, escolhas, personagem, alteracaoEnergia);
                    capitulos.put(nomeCapitulo, capitulo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inicia o scanner para detectar o que foi digitado no console
        Scanner input = new Scanner(System.in);
        System.out.println("Bem-vindo ao livro interativo. Quando estiver preparado para iniciar, digite 1.");
        int iniciar = input.nextInt();

        if (iniciar == 1) {
            // Comece com o primeiro capítulo
            String nomeCapituloAtual = "CAPÍTULO 1 - A resiliência de Diana";
            Capitulo capituloAtual = capitulos.get(nomeCapituloAtual);

            while (capituloAtual != null) {
                capituloAtual.mostrar();
                int escolha = capituloAtual.escolher();
                Personagem personagem = capituloAtual.getPersonagem();
                personagem.diminuirEnergia(capituloAtual.getAlteracaoEnergia());

                // Verifique se o personagem está esgotado
                if (personagem.mostrarEnergia() <= 0) {
                    System.out.println(personagem.getNome() + " ficou esgotada. Fim do programa.");
                    break;
                }

                // Avance para o próximo capítulo
                nomeCapituloAtual = capituloAtual.getEscolhas()[escolha];
                capituloAtual = capitulos.get(nomeCapituloAtual);
            }

            System.out.println("\n\nObrigado por participar do nosso livro interativo.");
        } else {
            System.out.println("Fim do programa.");
        }
    }
}