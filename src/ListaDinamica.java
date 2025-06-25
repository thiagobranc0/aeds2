import java.util.Comparator;
import java.util.NoSuchElementException;

public class ListaDinamica<E> {
    Celula<E> primeiro;
    Celula<E> ultimo;
    int tamanho;

    public ListaDinamica() {
        Celula<E> sentinela = new Celula<>();
        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return this.primeiro == this.ultimo;
    }

    public void insert(E element, int index) {
        if(index < 0 || index > this.tamanho) {
            throw new IndexOutOfBoundsException("Posição informada inválida");
        }

        Celula<E> anterior = primeiro;
        for(int i = 0; i < index; i++) {
            anterior = anterior.getProxima();
        }

        anterior.setProxima(new Celula<>(element, anterior.getProxima()));

        if(index == tamanho) {
            ultimo = anterior.getProxima();
        }

        tamanho++;
    }

    public void insertSorted(E element, Comparator<E> comparator) {
        Celula<E> anterior = primeiro;

        while(anterior.getProxima() != null) {
            if(!(comparator.compare(element, anterior.getProxima().getItem()) > 0)) {
                anterior.setProxima(new Celula<>(element, anterior.getProxima()));
                tamanho++;
                return;
            }
            anterior = anterior.getProxima();
        }

        anterior.setProxima(new Celula<>(element, anterior.getProxima()));
        ultimo = anterior.getProxima();
        tamanho++;
    }

    public void insertFirst(E element) {
        primeiro.setProxima(new Celula<>(element, primeiro.getProxima()));
        if(primeiro.getProxima().getProxima() == null) {
            ultimo = primeiro.getProxima();
        }
        tamanho++;
    }

    public void insertLast(E element) {
        ultimo.setProxima(new Celula<>(element));
        ultimo = ultimo.getProxima();
        tamanho++;
    }

    public E remove(int index) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora do limite da lista");
        }

        Celula<E> anterior = primeiro;

        for(int i = 0; i < index; i++) {
            anterior = anterior.getProxima();
        }

        E removedElement = anterior.getProxima().getItem();
        anterior.setProxima(anterior.getProxima().getProxima());

        if(anterior.getProxima() == null) {
            ultimo = anterior;
        }

        tamanho--;
        return removedElement;
    }

    public E removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        E removedElement = primeiro.getProxima().getItem();
        primeiro.setProxima(primeiro.getProxima().getProxima());
        if(primeiro.getProxima() == null) {
            ultimo = primeiro;
        }
        tamanho--;
        return removedElement;
    }

    public E removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        Celula<E> anterior = primeiro;
        while(anterior.getProxima() != ultimo) {
            anterior = anterior.getProxima();
        }

        E removedElement = ultimo.getItem();
        anterior.setProxima(null);
        ultimo = anterior;
        tamanho--;
        return removedElement;
    }

    public int obterNumeroItens() {
        return this.tamanho;
    }

    public E removeByItem(E item) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        Celula<E> anterior = primeiro;
        for(int i = 0; i < tamanho; i++) {
            if(anterior.getProxima().getItem().equals(item)) {
                E removedItem = anterior.getProxima().getItem();
                anterior.setProxima(anterior.getProxima().getProxima());
                if(anterior.getProxima() == null) {
                    ultimo = anterior;
                }
                tamanho--;
                return removedItem;
            }
            anterior = anterior.getProxima();
        }

        throw new NoSuchElementException("Item não encontrado");
    }

    public E localizar(int index) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        if(index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        Celula<E> anterior = primeiro;

        for (int i = 0; i < index; i++) {
            anterior = anterior.getProxima();
        }

        return anterior.getProxima().getItem();
    }

    public void trocar(E item1, E item2) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }

        Celula<E> anterior = primeiro;
        Celula<E> troca1 = null;
        Celula<E> troca2 = null;

        for(int i = 0; i < tamanho; i++) {
            if(anterior.getProxima().getItem().equals(item1)) {
                troca1 = anterior.getProxima();
            }
            if(anterior.getProxima().getItem().equals(item2)) {
                troca2 = anterior.getProxima();
            }
            anterior = anterior.getProxima();
        }

        if(troca1 != null && troca2 != null) {
            troca1.setItem(item2);
            troca2.setItem(item1);
        } else {
            throw new NoSuchElementException("Um dos itens não foi encontrado");
        }
    }

    public void imprimir() {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }
        Celula<E> aux = primeiro.getProxima();
        while(aux != null) {
            System.out.println(aux.getItem());
            aux = aux.getProxima();
        }
    }

    public E localizarByItem(E item) {
        if(isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }
//        Celula<E> aux = primeiro;
//
//        while(aux.getProxima() != null) {
//            if(aux.getProxima().getItem().equals(item)) {
//                return aux.getProxima().getItem();
//            }
//            aux = aux.getProxima();
//        }
//        throw new NoSuchElementException();

        Celula<E> auxiliar = primeiro.getProxima();

        for(int i = 0; i < tamanho; i++) {
            if(auxiliar.getItem().equals(item)) {
                return auxiliar.getItem();
            }
            auxiliar = auxiliar.getProxima();
        }

        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Celula<E> aux = primeiro.getProxima();

        for(int i = 0; i < tamanho; i++) {
            builder.append(aux.getItem().toString());
            aux = aux.getProxima();
        }

        return builder.toString();
    }
}
