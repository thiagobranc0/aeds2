import java.util.NoSuchElementException;

public class HashIndireto<K, V> {

    private ListaDinamica<Entrada<K, V>>[] tabelaHash;
    private int capacidade;


    @SuppressWarnings("unchecked")
    public HashIndireto(int capacidade) {
        if (capacidade < 1) {
            throw new IllegalArgumentException("A tabela não pode ter tamanho menor que 1");
        }

        this.tabelaHash = (ListaDinamica<Entrada<K,V>>[]) new ListaDinamica[capacidade];

        for (int i = 0; i < tabelaHash.length; i++) {
            tabelaHash[i] = new ListaDinamica<>();
        }

        this.capacidade = capacidade;
    }

    private int funcaoHash(K chave) {
        return Math.abs(chave.hashCode() % capacidade);
    }

    public boolean vazia() {
        for (ListaDinamica<Entrada<K, V>> lista : tabelaHash) {
            if(!lista.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean contemValor(V valor) {
//        for (int i = 0; i < capacidade; i++) {
//            ListaDinamica<Entrada<K, V>> lista = tabelaHash[i];
//            int tamanhoLista = lista.obterNumeroItens();
//            for (int j = 0; j < tamanhoLista; j++) {
//                if(lista.localizar(j).getValor().equals(valor)) {
//                    return true;
//                };
//            }
//        }
        for (ListaDinamica<Entrada<K, V>> lista : tabelaHash) {
            if(!lista.isEmpty()) {
                for(int i = 0; i < lista.obterNumeroItens(); i++) {
                    if(lista.localizar(i).getValor().equals(valor)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ListaDinamica<Entrada<K, V>> obterTodasEntradas() {
        ListaDinamica<Entrada<K, V>> todasEntradas = new ListaDinamica<>();
        for (ListaDinamica<Entrada<K, V>> lista : tabelaHash) {
            if(!lista.isEmpty()) {
                for(int i = 0; i < lista.obterNumeroItens(); i++) {
                    todasEntradas.insertLast(lista.localizar(i));
                }
            }
        }
        return todasEntradas;
    }

    public ListaDinamica<K> obterTodasChaves() {
        ListaDinamica<K> todasChaves = new ListaDinamica<>();
        for(ListaDinamica<Entrada<K, V>> lista : tabelaHash) {
            if(!lista.isEmpty()) {
                for(int i = 0; i < lista.obterNumeroItens(); i++) {
                    todasChaves.insertLast(lista.localizar(i).getChave());
                }
            }
        }
        return todasChaves;
    }

    public ListaDinamica<V> obterTodosValores() {
        ListaDinamica<V> todosValores = new ListaDinamica<>();
        for(ListaDinamica<Entrada<K, V>> lista : tabelaHash) {
            if(!lista.isEmpty()) {
                for(int i = 0; i < lista.obterNumeroItens(); i++) {
                    todosValores.insertLast(lista.localizar(i).getValor());
                }
            }
        }
        return todosValores;
    }

    public int inserir(K chave, V valor) {
        int posicao = funcaoHash(chave);

        Entrada<K, V> entrada = new Entrada<>(chave, valor);

        try{
            tabelaHash[posicao].localizarByItem(entrada);
            throw new IllegalArgumentException("Item já inserido na tabela");
        } catch(NoSuchElementException e) {
            tabelaHash[posicao].insertLast(entrada);
            return posicao;
        }
    }

    public V pesquisar(K chave) {
        int posicao = funcaoHash(chave);

        Entrada<K, V> procurado = new Entrada<>(chave, null);

        return tabelaHash[posicao].localizarByItem(procurado).getValor();
    }

    public V remover(K chave) {
        int posicao = funcaoHash(chave);

        Entrada<K, V> procurado = new Entrada<>(chave, null);
        V entrada;

        try {
           entrada = tabelaHash[posicao].removeByItem(procurado).getValor();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("Item não existe");
        }

        return entrada;
    }

    public void substituir(K chave, V valor) {
        int posicao = funcaoHash(chave);

        Entrada<K, V> procurado = tabelaHash[posicao].localizarByItem(new Entrada<>(chave, null));

        procurado.setValor(valor);
    }

    public int tamanho() {
        int tamanho = 0;
        for(ListaDinamica<Entrada<K,V>> lista : tabelaHash) {
            tamanho += lista.obterNumeroItens();
        }
        return tamanho;
    }

    @Override
    public String toString() {
        return percorrer();
    }

    public String percorrer() {
        String conteudo = "Tabela com " + capacidade + " posições e " + tamanho() + " itens \n";
        for (int i = 0; i < capacidade; i++) {
            conteudo += "Posição [" + i + "]: ";
            if (tabelaHash[i].isEmpty()) {
                conteudo += "Lista vazia\n";
            } else {
                conteudo += tabelaHash[i] + "\n";
            }
        }

        return conteudo;
    }


}
