package classes;

public class Termo {
    public String Palavra = null;
    public int NumeroDeOcorrencias = 1, IdTermo = 0;
    public ListaDoc listaDoc = new ListaDoc();

    public Termo(int IdTermo, String Palavra, int NumeroDeOcorrencias) {
        this.IdTermo = IdTermo;
        this.Palavra = Palavra;
        this.NumeroDeOcorrencias = NumeroDeOcorrencias;
    }
}