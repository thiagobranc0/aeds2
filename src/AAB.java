import org.w3c.dom.Node;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class AAB<K, V> {
    private No<K, V> raiz;
    private Comparator<K> comparador;
    private int tamanho;

    @SuppressWarnings("unchecked")
    private void init(Comparator<K> comparador) {
        this.raiz = null;
        this.tamanho = 0;

        if (comparador == null) {
            comparador = (Comparator<K>) Comparator.naturalOrder();
        }

        this.comparador = comparador;
    }

    public AAB() {
        init(null);
    }

    public AAB(Comparator<K> comparador) {
        init(comparador);
    }

    public Boolean vazia() {
        return raiz == null;
    }

    public V pesquisar(K key) {
        return pesquisar(this.raiz, key);
    }

    private V pesquisar(No<K, V> raizArvore, K procurado) {
        int comparacao;

        if (raizArvore == null) {
            throw new NoSuchElementException("O item não foi localizado na árvore!");
        }

        comparacao = comparador.compare(procurado, raizArvore.getKey());

        if (comparacao == 0) {
            return raizArvore.getValue();
        } else if (comparacao < 0) {
            return pesquisar(raizArvore.getEsquerda(), procurado);
        } else {
            return pesquisar(raizArvore.getDireita(), procurado);
        }
    }

    public int inserir(K key, V valor) {
        inserir(this.raiz, key, valor);
        return ++tamanho;
    }

    protected No<K, V> inserir(No<K, V> raizArvore, K chave, V valor) {
        int comparacao;

        if (raizArvore == null) {
            raizArvore = new No<>(chave, valor);
        } else {
            comparacao = comparador.compare(chave, raizArvore.getKey());
            if(comparacao > 0) {
                raizArvore.setDireita(inserir(raizArvore.getDireita(), chave, valor));
            } else if(comparacao < 0) {
                raizArvore.setEsquerda(inserir(raizArvore.getEsquerda(), chave, valor));
            } else {
                throw new RuntimeException("O item já foi inserido na árvore!");
            }
        }

        return raizArvore;
    }

    //Agora faça o remover


}
