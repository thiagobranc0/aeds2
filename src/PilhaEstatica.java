import java.util.NoSuchElementException;

public class PilhaEstatica<E> {
    private int topo;
    private final E[] pilha;

    public PilhaEstatica(int tamanho) {
        this.pilha = (E[]) new Object[tamanho];
        this.topo = 0;
    }

    public boolean isEmpty() {
        return topo == 0;
    }

    public boolean isFull() {
        return topo == pilha.length;
    }

    public void push(E elemento) {
        if(isFull()) {
            throw new IllegalStateException("Pilha cheia");
        }
        pilha[topo++] = elemento;
    }

    public E peek() {
        if(isEmpty()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return pilha[topo - 1];
    }

    public E pop() {
        E poppedElement = peek();
        topo--;
        return poppedElement;
    }
}
