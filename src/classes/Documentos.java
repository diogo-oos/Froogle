package classes;

public class Documentos {
    public int Id;
    public String titulo;
    public int repeticoesNesteDocumento;// útil para saber quantas vezes o termo aparece nesse determinado documento
    //public String urlDoc; implementar caso se decidar utilizar documentos da web

    public Documentos (int Id, String titulo, int repeticoesNesteDocumento) {
        this.Id = Id;
        this.titulo = titulo;
        this.repeticoesNesteDocumento = repeticoesNesteDocumento;
    }
}
