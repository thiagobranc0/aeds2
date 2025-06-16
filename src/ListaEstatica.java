import java.util.NoSuchElementException;

public class ListaEstatica<E extends Comparable<E>> {
    private int primeiro;
    private int ultimo;
    private E[] lista;

    public ListaEstatica(int tamanho) {
        this.lista = (E[]) new Comparable[tamanho];
        this.primeiro = this.ultimo = 0;
    }

    public boolean isEmpty() {
        return this.primeiro == this.ultimo;
    }

    public boolean isFull() {
        return this.ultimo == lista.length;
    }

    public void insert(E element, int index) {
        if(isFull()) {
            throw new IllegalStateException("Lista cheia");
        }

        if(index < 0 || index > this.ultimo) {
            throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
                    + "a posição informada é inválida!");
        }

        for(int i = this.ultimo; i > index; i--) {
            lista[i] = lista[i - 1];
        }
        lista[index] = element;
        this.ultimo++;
    }

    public void insertFirst(E element) {
        if(isFull()) {
            throw new IllegalStateException("Lista cheia");
        }

        for(int i = this.ultimo; i > 0; i--) {
            lista[i] = lista[i - 1];
        }

        lista[0] = element;
        this.ultimo++;
    }

    public void insertLast(E element) {
        if(isFull()) {
            throw new IllegalStateException("Lista cheia");
        }
        lista[this.ultimo++] = element;
    }

    public E remove(int index) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        if(index < 0 || index >= this.ultimo) {
            throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
                    + "a posição informada é inválida!");
        }

        E removedElement = lista[index];
        this.ultimo--;

        for(int i = index; i < this.ultimo; i++) {
            lista[i] = lista[i + 1];
        }

        return removedElement;
    }

    public E removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        E removedElement = lista[0];
        this.ultimo--;

        for(int i = 0; i < this.ultimo; i++) {
            lista[i] = lista[i + 1];
        }

        return removedElement;
    }

    public E removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        E removedElement = lista[--this.ultimo];
        return removedElement;
    }

    public E buscaBinaria(E procurado) {
        return buscadorBinario(this.primeiro, this.ultimo - 1, procurado);
    }

    private E buscadorBinario(int inicio, int fim, E procurado) {
        int meio, comparacao;

        if(inicio > fim) {
            throw new NoSuchElementException("Elemento não encontrado!");
        }

        meio = (fim + inicio) / 2;
        comparacao = procurado.compareTo(lista[meio]);

        if(comparacao == 0) {
            return lista[meio];
        } else if(comparacao > 0) {
            return buscadorBinario(meio + 1, fim, procurado);
        } else {
            return buscadorBinario(inicio, meio - 1, procurado);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < this.ultimo; i++) {
            str.append(lista[i] + "\n");
        }
        return str.toString();
    }
}
