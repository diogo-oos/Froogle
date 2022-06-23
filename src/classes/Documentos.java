package classes;

public class Documentos {
    public int IdDoc;
    public String Titulo;
    /* útil para saber quantas vezes o termo aparece nesse determinado documento
     * (se comportará como valor do documento quando esse calculo for realizado)
     */
    public int ocorrenciasNesteDocOuValorDoDoc;

    public Documentos (int IdDoc, String Titulo, int ocorrenciasNesteDocOuValorDoDoc) {
        this.IdDoc = IdDoc;
        this.Titulo = Titulo;
        this.ocorrenciasNesteDocOuValorDoDoc = ocorrenciasNesteDocOuValorDoDoc;
    }

    public String imprimir() {
        return ("\nID do documento: " + IdDoc + "\nNome do Documento: " + Titulo + "\nValor do documento: " + ocorrenciasNesteDocOuValorDoDoc + "\n");      
    }
}
