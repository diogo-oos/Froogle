package classes;

public class HashTableTermos {
    public final int tam;
    public EntradaTermos[] dados;
    public int pesos[];

    public HashTableTermos(int n) {
        this.tam = n;
        this.dados = new EntradaTermos[tam];
        this.preencherPesos();

        for(int i = 0; i < this.tam; i++)
            dados[i] = new EntradaTermos();// preenche a tabela com entradas null
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

    public int localizar(String key) {
        int calcHash = calcularCodigo(key.toLowerCase());
        int pos = mapear(calcHash);// descobre a posicao
        int indiceSondagem = 1;// indice para iniciar a sondagem quadratica
        while(dados[pos].valido && !key.toLowerCase().equals(dados[pos].chave.toLowerCase())){
            pos = mapear(pos + (indiceSondagem *segundoHash(calcHash)));
            indiceSondagem++;// indice de sondagem soma + 1
            System.out.println("\n" + pos);
        }
        System.out.println("\n" + "saiu");
        // quando acha uma posicao vazia ou com a chave igual, retorna essa posicao
        return pos;
    }

    public void inserir(String chave, Termo novo){
        EntradaTermos nova = new EntradaTermos(chave, novo);// cria nova entrada
        int pos = localizar(chave);// localiza a posicao
        dados[pos] = nova;// posiciona a entrada na respectiva posicao
    }

    public Termo buscar(String chave){
        int pos = localizar(chave);// localiza a posicao da chave
        return dados[pos].palavra;// retorna o dado dentro da entrada
    }

    public void atualizarOcorrencias(String chave){
        int pos = localizar(chave);
        dados[pos].palavra.NumeroDeOcorrencias++;
    }

    public void inserirDocumento(String chave, Documentos doc){
        int pos = localizar(chave);
        dados[pos].palavra.listaDoc.inserirDocNoFim(doc);
    }

    public void atualizarOcorrenciasNoDoc(String chave){
        int pos = localizar(chave);
        dados[pos].palavra.listaDoc.fim.dado.ocorrenciasNesteDocOuValorDoDoc++;
    }

    public EntradaTermos[] pegarTabela() {
        return dados;
    }

    public void limparTabela() {
        for(int i = 0; i < this.tam; i++)
            dados[i] = new EntradaTermos();// preenche a tabela com entradas null
    }
}
