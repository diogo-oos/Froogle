package classes;

public class Documentos {
    public int IdDoc;
    public String Titulo;
    public int ocorrenciasNesteDocOuValorDoDoc;// útil para saber quantas vezes o termo aparece nesse determinado documento
    //public String urlDoc; implementar caso se decidar utilizar documentos da web

    public Documentos (int IdDoc, String Titulo, int ocorrenciasNesteDocOuValorDoDoc) {
        this.IdDoc = IdDoc;
        this.Titulo = Titulo;
        this.ocorrenciasNesteDocOuValorDoDoc = ocorrenciasNesteDocOuValorDoDoc;
    }

    public String imprimir() {
        return ("\nID do documento: " + IdDoc + "\nNome do Documento: " + Titulo + "\nValor do documento: " + ocorrenciasNesteDocOuValorDoDoc + "\n");      
    }
}
