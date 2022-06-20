package classes;

public class Termo {
    public String palavra = null;
    public int repeticao = 1, id = 0;
    public ListaDoc listaDoc = new ListaDoc();

    public Termo(int idTermo, String palavra, int rep) {
        this.id = idTermo;
        this.palavra = palavra;
        this.repeticao = rep;
    }
}