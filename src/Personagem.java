class Personagem {
    private String nome;
    private int energia;

    public Personagem(String nome, int energia) {
        this.nome = nome;
        this.energia = energia;
    }

    public void diminuirEnergia(int valor) {
        energia -= valor;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void morrer() {
        energia = 0;
    }

    public int mostrarEnergia() {
        return energia;
    }

    public String getNome() {
        return nome;
    }

    public int getEnergia() {
        return energia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEnergia(int energia) {
        if (energia < 0) {
            this.energia = 0;
        } else {
            this.energia = energia;
        }
    }
}