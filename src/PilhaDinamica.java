public class PilhaDinamica<E> {
    private Celula<E> topo;
    private Celula<E> fundo;

    public PilhaDinamica() {
        Celula<E> sentinela = new Celula<>();
        topo = fundo = sentinela;
    }

    public boolean isEmpty() {
        return topo == fundo;
    }

    public void push(E elemento) {
        topo = new Celula<>(elemento, topo);
    }

    public E peek(){
        if(isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
        return topo.getItem();
    }

    public E pop(){
        E poppedElement = peek();
        topo = topo.getProxima();
        return poppedElement;
    }

    public void concatenar(PilhaDinamica<E> pilha) {
        pilha.inverter();
        while(!pilha.isEmpty()) {
            this.push(pilha.pop());
        }
    }

    public void inverter() {
        PilhaDinamica<E> pilhaInvertida = new PilhaDinamica<>();
        while(!this.isEmpty()) {
            pilhaInvertida.push(this.pop());
        }
        this.topo = pilhaInvertida.topo;
        this.fundo = pilhaInvertida.fundo;
    }

    public int obterNumeroItens() {
        Celula<E> aux = topo;
        int contador = 0;
        while(aux != fundo) {
            contador++;
            aux = aux.getProxima();
        }
        return contador;
    }

    public boolean isPalindrome(String palavra) {
        PilhaDinamica<Character> pilhaChar = new PilhaDinamica<>();
        PilhaDinamica<Character> pilhaCharInvertida = new PilhaDinamica<>();

        palavra = palavra.toLowerCase();

        for(int i = 0; i < palavra.length(); i++) {
            pilhaChar.push(palavra.charAt(i));
            pilhaCharInvertida.push(palavra.charAt(i));
        }

        pilhaCharInvertida.inverter();

        while(!pilhaChar.isEmpty()) {
            if(pilhaChar.pop() != pilhaCharInvertida.pop()) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindromeRaw(String palavra) {
        PilhaDinamica<Character> pilhaChar = new PilhaDinamica<>();

        palavra = palavra.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for(int i = 0; i < palavra.length(); i++) {
            pilhaChar.push(palavra.charAt(i));
        }

        for(int i = 0; i < palavra.length(); i++) {
            if(palavra.charAt(i) != pilhaChar.pop()) {
                return false;
            }
        }

        return true;
    }

    public void cancelaCaractere(String palavra, char cancelaCaractere) {
        PilhaDinamica<Character> pilhaChar = new PilhaDinamica<>();
        PilhaDinamica<Character> pilhaCharInvertida = new PilhaDinamica<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < palavra.length(); i++) {
            if(palavra.charAt(i) != cancelaCaractere) {
                pilhaChar.push(palavra.charAt(i));
            } else {
                pilhaChar.pop();
            }
        }
        while(!pilhaChar.isEmpty()) {
            pilhaCharInvertida.push(pilhaChar.pop());
        }
        while(!pilhaCharInvertida.isEmpty()) {
            sb.append(pilhaCharInvertida.pop());
        }
        System.out.println(sb.toString());
    }


}
