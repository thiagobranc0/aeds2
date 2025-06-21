import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class ABB<K, V extends Comparable<V>> {
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

    public String caminhamentoDescrescente() {
        if(vazia()) {
            throw new NoSuchElementException("Árvorervore vazia!");
        }

        return caminhamentoDescrescente(raiz);
    }

    protected String caminhamentoDescrescente(No<K, V> raizArvore) {
        if(raizArvore != null) {
            String conteudo = caminhamentoDescrescente(raizArvore.getDireita());
            conteudo += raizArvore.getValue() + "\n";
            conteudo += caminhamentoDescrescente(raizArvore.getEsquerda());

            return conteudo;
        } else {
            return "";
        }
    }

    public V obterMenor() {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia");
        }
        V menor = obterMenor(raiz);
        return menor;
    }

    protected V obterMenor(No<K, V> raizArvore) {
        if(raizArvore.getEsquerda() != null) {
            return obterMenor(raizArvore.getEsquerda());
        } else {
            return raizArvore.getValue();
        }
    }

    public ABB<K, V> clone() {
        ABB<K,V> clone = new ABB<>(this.comparador);
        clone.raiz = this.clone(this.raiz, clone);
        return clone;
    }


    protected No<K, V> clone(No<K, V> raizArvore, ABB<K,V> arvoreClone) {
        if (raizArvore != null) {
            No<K, V> NovoNo = raizArvore.clone();
            NovoNo.setEsquerda(this.clone(raizArvore.getEsquerda(), arvoreClone));
            NovoNo.setDireita(this.clone(raizArvore.getDireita(), arvoreClone));

            arvoreClone.tamanho++;

            return NovoNo;
        } else {
            return null;
        }
    }

    public boolean ehRaiz(K chave) {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }
        return this.raiz.getKey().equals(chave);
    }

    public ABB<K, V> obterSubconjuntoMaiores(K chave) {
        ABB<K, V> subconjunto = new ABB<>(this.comparador);
        subconjunto.raiz = obterSubconjuntoMaiores(chave, this.raiz);
        return subconjunto.clone();
    }

    protected No<K, V> obterSubconjuntoMaiores(K chave, No<K, V> raizArvore) {
        int comparacao;

        if(raizArvore == null) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        comparacao = this.comparador.compare(chave, raizArvore.getKey());

        if(comparacao == 0) {
            No<K, V> NovoNo = raizArvore.clone();
            NovoNo.setEsquerda(null);
            return NovoNo;
        } else if(comparacao < 0) {
            return obterSubconjuntoMaiores(chave, raizArvore.getEsquerda());
        } else {
            return obterSubconjuntoMaiores(chave, raizArvore.getDireita());
        }
    }

    public int tamanho() {
        return tamanho;
    }

    public int contadorDeNos() {
        return contadorDeNos(this.raiz);
    }

    protected int contadorDeNos(No<K, V> raizArvore) {
        int contador = 0;

        if(raizArvore != null) {
            contador++;
            contador += contadorDeNos(raizArvore.getEsquerda());
            contador += contadorDeNos(raizArvore.getDireita());
        }

        return contador;
    }

    public No<K, V> econtrarNo(K chave) {
        return encontrarNo(this.raiz, chave);
    }

    protected No<K, V> encontrarNo(No<K, V> raizArvore, K chave) {
        int comparacao;

        if(raizArvore == null) {
            throw new NoSuchElementException("Elemento não encontrado!");
        }

        comparacao = this.comparador.compare(chave, raizArvore.getKey());

        if(comparacao == 0) {
            return raizArvore;
        } else if(comparacao < 0) {
            return encontrarNo(raizArvore.getEsquerda(), chave);
        } else {
            return encontrarNo(raizArvore.getDireita(), chave);
        }
    }

    public V obterAntecessor(K chave) {
        No<K, V> procurado = encontrarNo(this.raiz, chave);

        if(procurado.getEsquerda() == null) {
            throw new NoSuchElementException("Elemento não possui antecessor!");
        }

        return obterAntecessor(procurado.getEsquerda()).getValue();
    }

    protected No<K, V> obterAntecessor(No<K, V> antecessor) {
        if(antecessor.getDireita() != null) {
            return obterAntecessor(antecessor.getDireita());
        } else {
            return antecessor;
        }

    }

    public Double calcularValorMedio(Function<V,Double> extrator) {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        return (calcularValorMedio(extrator, this.raiz) / this.contadorDeNos());
    }

    protected Double calcularValorMedio(Function<V,Double> extrator, No<K, V> raizArvore) {
        Double valor = 0.0;

        if(raizArvore != null) {
           valor += extrator.apply(raizArvore.getValue());
           valor += calcularValorMedio(extrator, raizArvore.getEsquerda());
           valor += calcularValorMedio(extrator, raizArvore.getDireita());
        }

        return valor;
    }

    public int contarSe(Predicate<V> condicional) {
        if(vazia()) {
            throw new NoSuchElementException("Árvore vazia!");
        }

        return contarSe(condicional, this.raiz);
    }

    protected int contarSe(Predicate<V> condicional, No<K, V> raizArvore) {
        int contador = 0;

        if(raizArvore != null) {
            if(condicional.test(raizArvore.getValue())) {
                contador++;
            }
            contador += contarSe(condicional, raizArvore.getEsquerda());
            contador += contarSe(condicional, raizArvore.getDireita());
        }

        return contador;
    }

    public V obterSucessor(V valor) {
        return acharValor(this.raiz, valor);
    }

    protected V acharValor(No<K, V> raizArvore, V valor) {
        int comparacao;

        if(raizArvore == null) {
            throw new NoSuchElementException("Elemento não encontrado na árvore");
        }

        comparacao = valor.compareTo(raizArvore.getValue());

        if(comparacao == 0) {
            if(raizArvore.getDireita() == null) {
                throw new NoSuchElementException("O elemento não possui sucessor!");
            }
            return obterSucessor(raizArvore.getDireita());
        } else if(comparacao < 0) {
            return acharValor(raizArvore.getEsquerda(), valor);
        } else {
            return acharValor(raizArvore.getDireita(), valor);
        }
    }

    protected V obterSucessor(No<K, V> raizArvore) {
        if(raizArvore.getEsquerda() == null) {
            return raizArvore.getValue();
        } else {
            return obterSucessor(raizArvore.getEsquerda());
        }
    }

}
