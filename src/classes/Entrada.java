package classes;

public class Entrada {

    public String chave;
    public StopWord palavra;
    public boolean valido;

    public Entrada(){
        this.chave = "";
        this.palavra = null;
        this.unsetValido();
    }
    public Entrada(String palavra, StopWord termoDaPalavra){
        this.chave = palavra;
        this.palavra = termoDaPalavra;
        this.setValido();
    }


    public boolean isValido() {
        return valido;
    }

    public void setValido(){
        this.valido = true;
    }
    public void unsetValido(){
        this.valido = false;
    }

}
