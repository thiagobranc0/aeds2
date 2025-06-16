public class No<K, V> {
    private K key;
    private V value;
    private No<K, V> esquerda;
    private No<K, V> direita;
    private int altura;

    public No(K key, V value) {
        this.setKey(key);
        this.setValue(value);
        this.setEsquerda(null);
        this.setDireita(null);
        this.altura = 0;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public No<K, V> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<K, V> esquerda) {
        this.esquerda = esquerda;
    }

    public No<K, V> getDireita() {
        return direita;
    }

    public void setDireita(No<K, V> direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura() {
        this.altura = Math.max(this.esquerda.getAltura(), this.direita.getAltura()) + 1;
    }


}
