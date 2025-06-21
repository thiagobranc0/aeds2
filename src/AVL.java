import java.util.Comparator;

public class AVL<K, V extends Comparable<V>> extends ABB<K, V> {
    public AVL() {
        super();
    }

    public AVL(Comparator<K> comparador) {
        super(comparador);
    }

    private No<K, V> balancear(No<K, V> raizArvore) {
        int fatorDeBalancementoPai = raizArvore.getFatorBalanceamento();

        if(raizArvore != null) {
            if(fatorDeBalancementoPai == 2) {
                if(raizArvore.getEsquerda().getFatorBalanceamento() == -1) {
                    raizArvore.setEsquerda(rotacionarEsquerda(raizArvore.getEsquerda()));
                }

                raizArvore = rotacionarDireita(raizArvore);
            }

            if(fatorDeBalancementoPai == -2) {
                if(raizArvore.getDireita().getFatorBalanceamento() == 1) {
                    raizArvore.setDireita(rotacionarDireita(raizArvore.getDireita()));
                }
                raizArvore = rotacionarEsquerda(raizArvore);
            }
        }

        return raizArvore;
    }

    private No<K, V> rotacionarDireita(No<K, V> noPai) {

        No<K, V> filhoEsquerda = noPai.getEsquerda();

        noPai.setEsquerda(filhoEsquerda.getDireita());

        filhoEsquerda.setDireita(noPai);

        noPai.setAltura();
        filhoEsquerda.setAltura();

        return filhoEsquerda;
    }

    private No<K, V> rotacionarEsquerda(No<K, V> noPai) {

        No<K, V> filhoDireita = noPai.getDireita();


        noPai.setDireita(filhoDireita.getEsquerda());

        filhoDireita.setEsquerda(noPai);

        noPai.setAltura();
        filhoDireita.setAltura();

        return filhoDireita;
    }

    @Override
    protected No<K, V> inserir(No<K, V> raizArvore, K chave, V valor) {
        return balancear(super.inserir(raizArvore, chave, valor));
    }

    @Override
    protected No<K, V> remover(No<K, V> raizArvore, K chaveARemover) {
        return balancear(super.remover(raizArvore, chaveARemover)) ;
    }

    @Override
    protected No<K, V> removeAntecessor(No<K, V> raizArvoreARemover, No<K, V> raizArvoreEsquerda) {
        return balancear(super.removeAntecessor(raizArvoreARemover, raizArvoreEsquerda));
    }
}
