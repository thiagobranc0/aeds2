import java.util.Comparator;
import java.util.NoSuchElementException;

public class ABB<K, V> {
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

    public ABB() {
        init(null);
    }

    public ABB(Comparator<K> comparador) {
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
        this.raiz = inserir(this.raiz, key, valor);
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

    public V remover(K key) {
         V removido = this.pesquisar(key);

         this.raiz = remover(this.raiz, key);

         this.tamanho--;
         return removido;
    }

    protected No<K, V> remover(No<K, V> raizArvore, K chaveARemover) {
        int comparacao;

        if (raizArvore == null) {
            throw new NoSuchElementException("Elemento não encontrado na árvore!");
        }

        comparacao = comparador.compare(chaveARemover, raizArvore.getKey());

        if (comparacao == 0) {
            if (raizArvore.getDireita() == null) {
                raizArvore = raizArvore.getEsquerda();
            } else if(raizArvore.getEsquerda() == null) {
                raizArvore = raizArvore.getDireita();
            } else {
                raizArvore.setEsquerda(removeAntecessor(raizArvore, raizArvore.getEsquerda()));
            }
        } else if (comparacao > 0) {
            raizArvore.setDireita(remover(raizArvore.getDireita(), chaveARemover));
        } else {
            raizArvore.setEsquerda(remover(raizArvore.getEsquerda(), chaveARemover));
        }

        return raizArvore;
    }

    protected No<K,V> removeAntecessor(No<K,V> raizArvoreARemover, No<K,V> raizArvoreEsquerda) {
        if (raizArvoreEsquerda.getDireita() != null) {
            raizArvoreEsquerda.setDireita(removeAntecessor(raizArvoreARemover, raizArvoreEsquerda.getDireita()));
        } else {
            raizArvoreARemover.setKey(raizArvoreEsquerda.getKey());
            raizArvoreARemover.setValue(raizArvoreEsquerda.getValue());

            raizArvoreEsquerda = raizArvoreEsquerda.getEsquerda();
        }

        return raizArvoreEsquerda;
    }

    public String caminhamentoEmOrdem() {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        return caminhamentoEmOrdem(raiz);
    }

    protected String caminhamentoEmOrdem(No<K, V> raizArvore) {
        if(raizArvore != null) {
            String conteudo = caminhamentoEmOrdem(raizArvore.getEsquerda());
            conteudo += raizArvore.getValue() + "\n";
            conteudo += caminhamentoEmOrdem(raizArvore.getDireita());

            return conteudo;
        } else {
            return "";
        }
    }

    public String caminhamentoPreOrdem() {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        return caminhamentoPreOrdem(raiz);
    }

    protected String caminhamentoPreOrdem(No<K, V> raizArvore) {
        if(raizArvore != null) {
            String conteudo = raizArvore.getValue() + "\n";
            conteudo += caminhamentoPreOrdem(raizArvore.getEsquerda());
            conteudo += caminhamentoPreOrdem(raizArvore.getDireita());

            return conteudo;
        } else {
            return "";
        }
    }

    public String caminhamentoPosOrdem() {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        return caminhamentoPosOrdem(raiz);
    }

    protected String caminhamentoPosOrdem(No<K, V> raizArvore) {
        if (raizArvore != null) {
            String conteudo = caminhamentoPosOrdem(raizArvore.getEsquerda());
            conteudo += caminhamentoPosOrdem(raizArvore.getDireita());
            conteudo += raizArvore.getValue() + "\n";

            return conteudo;
        } else {
            return "";
        }
    }



}
