import java.util.Objects;

public class Entrada<K, V> {
    private K chave;
    private V valor;
    private boolean isRemoved;

    public Entrada(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
        this.isRemoved = false;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    @Override
    public boolean equals(Object outroObjeto) {
        Entrada<?, ?> outraEntrada;

        if (this == outroObjeto) {
            return true;
        } else if (outroObjeto == null || this.getClass() != outroObjeto.getClass()) {
            return false;
        } else {
            outraEntrada =  (Entrada<?, ?>) outroObjeto;
            return (this.chave.equals(outraEntrada.chave));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(chave);
    }

    @Override
    public String toString() {
        return "Entrada{" + "chave=" + chave + ", valor=" + valor + "} ";
    }
}
