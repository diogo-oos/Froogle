package classes;

public class HashTableStopWords {
    
    public final int tam;
    public EntradaStopWords[] dados;
    public int pesos[];

    public HashTableStopWords(int n){
        this.tam = n;
        this.dados = new EntradaStopWords[tam];
        this.preencherPesos();

        for(int i = 0; i < this.tam; i++)
            dados[i] = new EntradaStopWords();// preenche a tabela com entradas null
    }


    public int calcularCodigo(String chave){// calcula o codigo da chave
        int codeFinal=0;
        for (int i =0; i<chave.length(); i++) {
            int j=i;
            if(i >= pesos.length){
                j=0;
            }
            int code = chave.charAt(i);

            code *= pesos[j];

            codeFinal += code;
        }
        return codeFinal;
    }

    public void preencherPesos(){
        this.pesos = new  int[5];
        for (int i = 0; i <pesos.length ; i++) {
            this.pesos[i] = (i += 2);
        }
    }
    public  int segundoHash(int code){
        int newcode = code/32;
        return newcode;
    }
    public int mapear(int codigo){
        return codigo % tam;
    }

    public int localizar(String key){
        int calcHash = calcularCodigo(key);
        int pos = mapear(calcHash);// descobre a posicao
        int indiceSondagem = 1;// indice para iniciar a sondagem quadratica
        while(dados[pos].valido && !key.equals(dados[pos].chave)){
            pos = mapear(pos + (indiceSondagem *segundoHash(calcHash)));
            indiceSondagem++;// indice de sondagem soma + 1
        }
        return pos;// quando acha uma posicao vazia ou com a chave igual, retorna essa posicao
    }

    public void inserir(String chave, StopWord novo){
        EntradaStopWords nova = new EntradaStopWords(chave, novo);// cria nova entrada
        int pos = localizar(chave);// localiza a posicao
        dados[pos] = nova;// posiciona a entrada na respectiva posicao
    }

    public StopWord buscar(String chave){
        int pos = localizar(chave);// localiza a posicao da chave
        return dados[pos].palavra;// retorna o dado dentro da entrada
    }
}
