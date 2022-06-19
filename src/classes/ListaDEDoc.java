package classes;

/**
 * Classe Lista Duplamente Encadeada
 * fonte: https://www.youtube.com/watch?v=gx8LtrOC278
 * https://www.youtube.com/watch?v=oDAfNY_duZQ
 */
public class ListaDEDoc {
    public ElementoDoc inicio = null;
    public ElementoDoc fim = null;
    public int tamanho = 0;// controlador de quantidae de elementos existentes na lista, apenas informativo

    public boolean verificarVazio() {
        if (tamanho == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** inserir dado (Documento) no inicio da lista */
    public void inserirDocNoInicio(Documentos doc) {
        /**
         * se a verificação retornar falso, então quer dizer que a lista não etá vazia
         */
        ElementoDoc novo = new ElementoDoc();// instanciando novo elemento (espaço)
        novo.dado = doc; // informando que agora tenho um dado que precisa ser guardado
        novo.anterior = null;// informando que não tem ninguém antes dele
        novo.proximo = inicio;// informando que o proximo elemento é o inicio, ou seja, estamos colocando um
                              // dado no elemento

         /**
         * se minha lista tiver mais de um elemento então vou apontar para o anterior
         * lembre: a entrada de elementos ocorre da esquerda para a direita, então o
         * ponteiro "anterior" está apontando para a esquerda nesse caso.
         */

        if (!verificarVazio()) {
            inicio.anterior = novo;// meu novo dado será colocado elemento criado no inicio da lista
        }

        inicio = novo;// meu elemento no inicio vai receber o novo elemento (espaço ue será colocado o
                      // dado)

        if(verificarVazio()) {
            fim = inicio;
        }
        tamanho++;
    }

    public Documentos retirarDocDoInicio() {
        /** verifico se a lista não está vazia */
        if (verificarVazio()) {
            return null;
        }
        Documentos aux = inicio.dado;// atribuo o elemento no inicio da lista a um novo obj que será retornado
        inicio = inicio.proximo;// aponto o inicio para o proximo elemento da lista
        /** checo para saber se esse elemento que estou apontando não é nulo */
        if (inicio != null) { //essa verificação é necessário caso tenhamos apenas um elemento na lista
            inicio.anterior = null;
                                    /*
                                    * se o proximo elemento existir eu posso apagar o elemento que estou retirnando
                                    * ou seja, se ele existir eu posso parar de apontar para o espaço onde meu dado
                                    * (obj Doc) estava
                                    */
        } 
        else {
            fim = null; //caso o inicio for nulo, o fim também será. Esse caso ocorerá caso estejamos removendo o último item da lista
        }
        tamanho--;// reduzo o controlador de qtd
        return aux;
    }

    /** inserir dado (Doc) no fim da lista */
    public void inserirDocNoFim(Documentos doc) {
        ElementoDoc novo = new ElementoDoc();// criando espaço para armazenar informação
        novo.dado = doc;// inserindo informação no espaço gerado
        novo.proximo = null;// flexando o ponteiro para o vazio
        novo.anterior = fim;// informando o novo elemento estará no fim d a lista (isso é mais proximo da
                            // explicação do Caram)
        /**
         * verificando se o fim não é um espao vzio, evitar erro de estouro de memoria
         */
        if (fim != null) {
            fim.proximo = novo;// apontando o novo espaço (elemento novo) para o proximo
        }
        
        fim = novo;// informando que o fim não está mais vazio, agora ele tem um elemento com um
                   // dado
        if (verificarVazio()) {
            inicio = fim; 
        }
        tamanho++;
    }

    public Documentos retirarDocDoFim() {
        /** verificando se o fim não aponta para o vazio (evitando erro) */
        if (verificarVazio()) {
            return null;
        }
        Documentos aux  = fim.dado;// caso exsista dado (Termo) ele será passado para a variavel "procurado"
        fim = fim.anterior;// aponto para o dado que estava antes do dado que está sendo retrado
        /**
         * verificando se o proximo para onde vou apontar o fim é o vazio ou contem um
         * elemento
         */
        if (!verificarVazio()) { 
            fim.proximo = null;// caso o proximo não exista (esteja vazio) então meu proximo elemento seá o
                               // vazio
        } else { //isso ocorrerá ao retirarmos o último elemento da lista
            inicio = null;
        }
        tamanho--;// reduzo minha variavel de informação de qtd de elementos, se retirei ele vai
                  // sir da lista
        return aux;// retornando o Termo procurado, se ele existir receberei um termo, se não,
                              // receberei um null

    }

    /**
     * metodo serve para inserir um lemento entre demais elementos existente (no
     * meio da lista),
     * para isso usamos uma varivel indice, onde iremos passar uma referencia de
     * posicao e comparar
     * com a variavel "tamanho", agora essa variavl passa a funcionar como uma
     * variável de controle
     */
    public void inserirNoMeio(Documentos doc, int indice) {
        /**
         * se o indice for menor que 0 quer dizer que quero inserir um elemento no
         * inicio da lista
         * então chamarei o método de inserir no inicio
         */
        if (indice <= 0) {
            inserirDocNoInicio(doc);
        } else if (indice >= tamanho) {
                                        /**
                                        * se o indice for maior que o tamanho de elementos já existentes na minha lista
                                        * vou precisar inserir ele na ultima posicao, então chamarei a funcao de
                                        * inserir no final
                                        */
            inserirDocNoFim(doc);
        } else { 
                /**
                  * e se nenhum dos dois casos forem usados, então quer dizer que o elemento será
                  * inserido no meio da lista
                  */
            ElementoDoc aux = inicio; 
            /**
             * criando um auxiliar que vai ajudar a fazer as conexões, ele começará no
             * inicio da lista
             * ele seria nosso sentinela que passa de caixa em caixa (elemento) vrificando
             * qual a posicao
             * baseando no contador "tamanho"
             */
            /** esse for vai de 0 até a ultima posicao desejada */
            for (int i = 0; i < indice - 1; i++) {
                aux = aux.proximo;// apontando o aux para o proximo lemento na lista
            }
            ElementoDoc novo = new ElementoDoc();// instanciando o novo elemento
            novo.dado = doc;// informo que esse novo elemento tera as infos do termo que estou passando como
                              // parametro
            novo.anterior = aux;// vou falar que ponteiro que aponta para o elemento anterior agora vai pontar
                                     // para o aux
            novo.proximo = aux.proximo;// informo que meu ponteiro que flecha o proximo vai apontar para o novo
                                            // elemento
            aux.proximo = novo;// aqui informo que o proximo elemento vai receber o meu novo elemento
                                    // instanciado
            novo.proximo.anterior = novo;// aponto o meu anterior para o novo elemento instanciado
            tamanho++;
        }
    }

    /** metodo de retirada de elemento com seus dados do meio da lista */
    public Documentos retirarDocNoMeio(int indice) {
        /**
         * verificando se a lista não está vazia, verificando se a posicao de procura
         * (indice) onde vou procurar o elemento existe, verificando se o indice passado
         * não extrapola o tamanho de elementos que tenho na fila
         */
        //todo
        if (verificarVazio() || indice < 0 || indice >= tamanho) {
            return null;
            /**
             * se meu indice for 0, quer dizer que quero retirar um elemento com os dados que estão
             * no inicio da lista
             */
        } else if (indice == 0) {
            return retirarDocDoInicio();
            /**
             * se o indice for o valor do total de tamanho, ou seja, o ultimo item
             * adicionado, eu deverei remover o ultimo item na lista
             */
        } else if (indice == tamanho - 1) {
           return retirarDocDoFim();
        }
        /**
         * agora, se o valor do indice não for nem o valor do tamanho nem o valor do
         * inicio, então quer dizer que está no meio. Portando vou "correr" minha lista
         * até alcançar a posicao e então vou pegar o elemento nessa posicao e retornar
         * os dados existentes nele. Lembrando: lista não é vetor, essa busca por
         * posicao é feita utilizando
         * uma variavel de controle chamada de "tamanho"
         */
        ElementoDoc procurado = inicio;
        for (int i = 0; i < indice; i++) {
            procurado = procurado.proximo;// apontando meu Elemento aux de procura para o proximo, ele está procurando
                                          // até achar a posicao desejada
        }
        /** verificando se o ponteiro para o anterior não está nulo */
        if (procurado.anterior != null) {
            procurado.anterior.proximo = procurado.proximo;// reapontando o ponteiro anterior elemento que está à frente
                                                           // do elemento que estou retirando da lista
            /**
             * verificando se o ponteiro para o proximo (elemento que vem depois do elemento
             * procurado) aponta para algum elemento (para evitar erros, mas se a primeira
             * condição acima foi aceita ele com certeza existe)
             */
            if (procurado.proximo != null) {
                procurado.proximo.anterior = procurado.anterior;// existindo um proximo elemento passo a apontar o
                                                                // ponteiro anterior para ele, isso quer dizer que estou
                                                                // fazendo o elemento da frante apontar para o elementto
                                                                // que estava atras (à esquerda) do meu elemento que
                                                                // estou retirando.
            }
        }
        tamanho--;
        return procurado.dado;
    }

    public String imprimir() {
        StringBuilder imprime = new StringBuilder("==============/ Aparece nos seguintes documentos /==============\n");
        if(verificarVazio()){ 
            imprime.append("Nenhum documento");
        }
        else {
            ElementoDoc aux = inicio;
            while (aux != null) {
                imprime.append("ID do documento: " + aux.dado.Id + "\nNome do Documento: " + aux.dado.titulo + "\n");
                    
                aux = aux.proximo;
            }
        }
        return imprime.toString();
    }

    public boolean verificarSeExisteDoc (String doc) {
        ElementoDoc aux = inicio; 
            /**
             * criando um auxiliar que vai ajudar a fazer as conexões, ele começará no
             * inicio da lista
             * ele seria nosso sentinela que passa de caixa em caixa (elemento) vrificando
             * qual a posicao
             * baseando no contador "tamanho"
             */
            /** esse for vai de 0 até a ultima posicao desejada */
            for (int i = 0; i < tamanho; i++) {
                if (aux.dado.titulo == doc) return true;
                else
                    aux = aux.proximo;// apontando o aux para o proximo lemento na lista
            }
        return false;
    }

    public String toString() {
        StringBuilder imprime = new StringBuilder();
        ElementoDoc aux = inicio;
        while (aux != null) {
            imprime.append(";" + aux.dado.Id + ";" + aux.dado.titulo + ";" + aux.dado.repeticoesNesteDocumento); 
                    
            aux = aux.proximo;
        }
        return imprime.toString();
    }
    
}
