package classes;

public class EntradaTermos {

    public String chave;
    public Termo palavra;
    public boolean valido;

    public EntradaTermos(){
        this.chave = "";
        this.palavra = null;
        this.unsetValido();
    }
    public EntradaTermos(String palavra, Termo termoDaPalavra){
        this.chave = palavra;
        this.palavra = termoDaPalavra;
        this.setValido();
    }

    public void setValido(){
        this.valido = true;
    }
    public void unsetValido(){
        this.valido = false;
    }

}
