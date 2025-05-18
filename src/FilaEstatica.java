import java.util.NoSuchElementException;

public class FilaEstatica<E> {
    private E[] fila;
    private int frente;
    private int tras;

    public FilaEstatica(int tamanho) {
        this.fila = (E[]) new Object[tamanho];
        this.frente = this.tras = 0;
    }

    public int getIndex(int value) {
        System.out.println(value % fila.length);
        return value % fila.length;
    }

    public boolean isEmpty() {
        return frente == tras;
    }

    public boolean isFull() {
        return getIndex(frente) == getIndex(tras + 1);
    }

    public E peek() {
        if(isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }
        return fila[getIndex(frente)];
    }

    public void queue(E element) {
        if(isFull()) {
            throw new IllegalStateException("Fila cheia");
        }
        fila[getIndex(tras++)] = element;
    }

    public E dequeue() {
        E dequeuedElement = peek();
        frente++;
        return dequeuedElement;
    }




}
