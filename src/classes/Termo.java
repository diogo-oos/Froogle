package classes;

public class Termo {
    public String palavra = null;
    public int repeticao = 1, id = 0;
    public Documento documento = null;

    public Termo(int idTermo, String palavra, int rep, Documento doc) {
        this.id = idTermo;
        this.palavra = palavra;
        this.repeticao = rep;
        this.documento = doc;
    }
}