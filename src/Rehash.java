public class Rehash<K, V> {

    private Entrada<K, V>[] tabelaHash;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public Rehash(int capacidade) {
        this.capacidade = capacidade;
        tabelaHash = (Entrada<K, V>[]) new Entrada[capacidade];

        for (int i = 0; i < this.capacidade; i++) {
            tabelaHash[i] = null;
        }
    }

    private int funcaoHash(K chave, int tentativas) {
        return Math.abs((chave.hashCode() + tentativas * tentativas) % capacidade);
    }

    public int inserir(K chave, V valor) {
        int tentativas = 0;
        int posicao = funcaoHash(chave, tentativas);
        boolean inseriu = false;

        while((tentativas < this.capacidade) && !inseriu) {
            if (tabelaHash[posicao] == null || tabelaHash[posicao].isRemoved()) {
                tabelaHash[posicao] = new Entrada<>(chave, valor);
                inseriu = true;
            } else if (tabelaHash[posicao].getChave().equals(chave) && !tabelaHash[posicao].isRemoved()) {
                throw new IllegalArgumentException("Item já inserido na tabela");
            } else {
                posicao = funcaoHash(chave, ++tentativas);
            }
        }

        if(inseriu) {
            return posicao;
        }

        throw new IllegalStateException("Tabela cheia!");
    }

    @Override
    public String toString() {
        return percorrer();
    }

    public String percorrer(){

        String conteudo = "";
        for (int i = 0; i < capacidade; i++) {
            conteudo += ("Posição[" + i + "]: ");
            if ((tabelaHash[i] == null) || (tabelaHash[i].isRemoved())) {
                conteudo += "vazia\n";
            } else {
                conteudo += tabelaHash[i] + "\n";
            }
        }
        return conteudo;
    }
}
