import java.util.NoSuchElementException;

public class FilaDinamica<E> {
    private Celula<E> frente;
    private Celula<E> tras;

    public FilaDinamica() {
        Celula<E> sentinela = new Celula<>();
        frente = tras = sentinela;
    }

    public boolean isEmpty() {
        return frente == tras;
    }

    public E peek() {
        if(isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

        return frente.getProxima().getItem();
    }

    public void queue(E element) {
//        Celula<E> qeuedElement = new Celula<>(element);
//        tras.setProxima(qeuedElement);
//        tras = qeuedElement;
        tras.setProxima(new Celula<>(element));
        tras = tras.getProxima();
    }

    public E dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

        E dequeuedElement = peek();

        frente.setProxima(frente.getProxima().getProxima());

        if(frente.getProxima() == null) {
            tras = frente;
        }

        return dequeuedElement;
    }

    public void concatenar(FilaDinamica<E> fila) {
        while(!fila.isEmpty()) {
            this.queue(fila.dequeue());
        }
    }

    public int obterNumeroItens() {
        Celula<E> aux = frente.getProxima();
        int contador = 0;
        while(aux != null) {
            contador++;
            aux = aux.getProxima();
        }
        return contador;
    }

    public boolean verificarExistencia(E item) {
        if(this.isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

        Celula<E> aux = frente.getProxima();

        while(aux != null){
            if(aux.getItem().equals(item)) {
                return true;
            }
            aux = aux.getProxima();
        }

        return false;
    }

    public int obterNumItensAFrente(E item) {
        if(this.isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

//        Celula<E> aux = frente;
//        int contador = 0;
//        while(aux != tras) {
//            contador++;
//            aux = aux.getProxima();
//        }
//
//        int contadorItensNaFrente = 0;
//        aux = frente.getProxima();
//
//        for(int i = 0; i < contador; i++) {
//            if(aux.getItem().equals(item)) {
//                return contadorItensNaFrente;
//            }
//            contadorItensNaFrente++;
//            aux = aux.getProxima();
//        }
        Celula<E> aux = frente.getProxima();
        int contador = 0;
        while(aux != null) {
            if(aux.getItem().equals(item)) {
                return contador;
            }
            contador++;
            aux = aux.getProxima();
        }

        throw new NoSuchElementException("Elemento não encontrado");

    }

    public FilaDinamica<E> copiar() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

        Celula<E> aux = frente.getProxima();
        FilaDinamica<E> copiaFila = new FilaDinamica<>();

        while(aux != null) {
            E itemCopiado = aux.getItem();
            copiaFila.queue(itemCopiado);
            aux = aux.getProxima();
        }

        return copiaFila;
    }

    public FilaDinamica<E> dividir() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }

        FilaDinamica<E> filaImpar = new FilaDinamica<>();
        FilaDinamica<E> filaPar = new FilaDinamica<>();
        Celula<E> aux = frente.getProxima();
        int controle = 1;

        while(aux != null) {
            if(controle > 0) {
                E itemCopiado = aux.getItem();
                filaImpar.queue(itemCopiado);
            } else {
                E itemCopiado = aux.getItem();
                filaPar.queue(itemCopiado);
            }
            controle *= -1;
            aux = aux.getProxima();
        }

        this.frente = filaImpar.frente;
        this.tras = filaImpar.tras;

        return filaPar;
    }

    public void imprimir() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }
        Celula<E> aux = frente.getProxima();

        while(aux != null) {
            System.out.println(aux.getItem());
            aux = aux.getProxima();
        }
    }

    public boolean xCx(String palavra) {
        FilaDinamica<Character> f1 = new FilaDinamica<>();
        FilaDinamica<Character> f2 = new FilaDinamica<>();

        palavra = palavra.toLowerCase();

        for(int i = 0; i < palavra.length(); i++) {
           f1.queue(palavra.charAt(i));
        }

        while(!f1.isEmpty()) {
            if(f1.peek() != 'c') {
                f2.queue(f1.dequeue());
            } else {
                f1.dequeue();
                break;
            }
        }

        if(f1.obterNumeroItens() != f2.obterNumeroItens()) {
            return false;
        }

        while(!f2.isEmpty()) {
            if(f1.dequeue() != f2.dequeue()) {
                return false;
            }
        }

        return true;
    }

    private int quantosAFrente(Celula<E> atual, E item) {
        if(atual.getProxima() == null) {
            throw new NoSuchElementException();
        }

        if(atual.getProxima().getItem().equals(item)) {
            return 0;
        }

        return 1 + quantosAFrente(atual.getProxima(), item);
    }

//    private int quantosAFrente(Celula<E> atual, E item) {
//        if(atual == null) {
//            throw new NoSuchElementException();
//        } else if(atual.getItem().equals(item)) {
//            return 0;
//        }
//
//        return 1 + quantosAFrente(atual.getProxima(), item);
//    }

    public int frente(E item) {
        return quantosAFrente(frente, item);
    }
}
