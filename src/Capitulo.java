import java.util.Scanner;

class Capitulo {
    private String nome;
    private String texto;
    private String[] escolhas;
    private Personagem personagem;
    private int alteracaoEnergia;
    private Scanner escaneador;

    public Capitulo(String nome, String texto, String[] escolhas, Personagem personagem, int alteracaoEnergia) {
        this.nome = nome;
        this.texto = texto;
        this.escolhas = escolhas;
        this.personagem = personagem;
        this.alteracaoEnergia = alteracaoEnergia;
        this.escaneador = new Scanner(System.in);
    }

    public void mostrar() {
        System.out.println("\n" + getNome() + "\n");
        System.out.println(getTexto());
        System.out.println("Escolha a próxima ação:");

        for (int i = 0; i < escolhas.length; i++) {
            System.out.println((i + 1) + ". " + escolhas[i]);
        }
    }

    public int escolher() {
        int opcao;
        do {
            opcao = escaneador.nextInt();
        } while (opcao < 1 || opcao > escolhas.length);

        return opcao - 1;
    }

    public String getNome() {
        return nome;
    }

    public String getTexto() {
        return texto;
    }

    public String[] getEscolhas() {
        return escolhas;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public int getAlteracaoEnergia() {
        return alteracaoEnergia;
    }
}