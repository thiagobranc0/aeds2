public class App {
    static void printaVetorAoContraririo(int[] vetor, int posicao) {
        if(posicao < vetor.length - 1) {
            printaVetorAoContraririo(vetor, posicao + 1);
        }
        System.out.println(vetor[posicao]);
    }

    static int numeroDeVogais(String string, int posicaoString) {
        if (posicaoString >= string.length()) {
            return 0;
        }

        char c = string.charAt(posicaoString);
        int atual = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;

        return atual + numeroDeVogais(string, posicaoString + 1);
    }


    public static void main(String[] args) {
//        int [] vetor = {3, 56, 7, 12};


//        printaVetorAoContraririo(vetor, 0);

//        String string = "ABBA";
//
//        System.out.println(numeroDeVogais(string.toLowerCase(), 0));
//        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(10);
//        pilha.push(1);
//        pilha.push(2);
//        pilha.push(3);
//        pilha.push(4);
//        pilha.pop();
//        System.out.println(pilha.peek());
//        PilhaDinamica<Integer> pilhaDinamica = new PilhaDinamica<>();
//        System.out.println(pilhaDinamica.isPalindromeRaw("Socorram-me em Marrocos"));
//        System.out.println(5%4);

//        ListaDinamica<String> lista = new ListaDinamica<>();
//        lista.insertLast("a");
//        lista.insertLast("b");
//        lista.insertLast("d");
//        System.out.println(lista.localizarByItem("d"));
//        lista.insertSorted("c", new ComparadorAlfabetico());
//        lista.insertSorted("z", new ComparadorAlfabetico());
//        lista.insertSorted("t", new ComparadorAlfabetico());
//        lista.insertSorted("Th", new ComparadorAlfabetico());
//        lista.insertSorted("Ta", new ComparadorAlfabetico());
//        System.out.println("Aqui está: " + lista.localizar(7));
//        lista.trocar("a", "c");
//        System.out.println(lista.removeFirst());
//        System.out.println(lista.removeFirst());
//        System.out.println(lista.removeFirst());
//        System.out.println(fila.copiar().verificarExistencia("a"));
//        FilaDinamica<String> filaCopia = fila.dividir();
        //lista.imprimir();
//        System.out.println(fila.obterNumeroItens());
//        fila.concatenar(filaCopia);
//        fila.imprimir();
//        PilhaDinamica<String> pilha = new PilhaDinamica<>();
//        pilha.cancelaCaractere("PO#UCTE##-MIB#NADR##S", '#');

//        FilaDinamica<String> fila = new FilaDinamica<>();
//        System.out.println(fila.xCx("HunterCHunter"));
//        fila.queue("a");
//        fila.queue("b");
//        fila.queue("c");
//        System.out.println(fila.frente("c"));

//        ListaEstatica lista = new ListaEstatica(7);
//        lista.insertLast(2);
//        lista.insertLast(32);
//        lista.insertLast(47);
//        lista.insertLast(54);
//        lista.insertLast(68);
//        lista.insertLast(88);
//        lista.insertLast(92);
//
//        System.out.println(lista);
//
//        System.out.println(lista.buscaBinaria(88));

        ABB<Integer, Integer> arvore = new ABB<>();

        arvore.inserir(16, 16);
        arvore.inserir(8, 8);
        arvore.inserir(23, 23);
        arvore.inserir(4, 4);
        arvore.inserir(11, 11);
        arvore.inserir(19, 19);
        arvore.inserir(27, 27);
        arvore.inserir(5, 5);

        System.out.println(arvore.caminhamentoEmOrdem());

        System.out.println(arvore.caminhamentoPreOrdem());

        System.out.println(arvore.caminhamentoPosOrdem());
    }
}
