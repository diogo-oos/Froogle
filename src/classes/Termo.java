package classes;

public class Termo {
    public String palavra = null;
    public int repeticao = 1, id = 0;
    public Documentos documento = null;

    public Termo(int idTermo, String palavra, int rep, Documentos doc) {
        this.id = idTermo;
        this.palavra = palavra;
        this.repeticao = rep;
        this.documento = doc;
    }
}