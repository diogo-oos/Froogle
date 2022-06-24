package classes;

/**
 * Classe Lista Duplamente Encadeada
 * fonte: https://www.youtube.com/watch?v=gx8LtrOC278
 * https://www.youtube.com/watch?v=oDAfNY_duZQ
 */
public class ListaDoc {
    public ElementoDoc inicio = null;
    public ElementoDoc fim = null;
    // controlador de quantidae de elementos existentes na lista, apenas informativo
    public int tamanho = 0;

    public boolean verificarVazio() {
        if (tamanho == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que insere na última posição da lista
     * @return void
     */
    public void inserirDocNoFim(Documentos doc) {
        // criando espaço para armazenar informação
        ElementoDoc novo = new ElementoDoc();
        novo.dado = doc;
        // flexando o ponteiro para o vazio
        novo.proximo = null;
        // novo elemento estará no fim da lista
        novo.anterior = fim; 
        /**
         * verificando se o fim não é um espao vzio, evitar erro de estouro de memoria
         */
        if (fim != null) {
            // apontando o novo espaço (elemento novo) para o proximo
            fim.proximo = novo;
        }
        fim = novo;
        if (verificarVazio()) {
            // se a lista estiver vazia, o início e fim são o mesmo elemento
            inicio = fim; 
        }
        tamanho++;
    }

    /**
     * Método que retira o último item da lista
     * @return o último elemento ou null, caso a lista esteja vazia
     */
    public Documentos retirarDocDoFim() {
        if (verificarVazio()) {
            return null;
        }
        // caso exsista dado (Termo) ele será passado para a variavel "procurado"
        Documentos aux  = fim.dado;

        // aponto para o dado que estava antes do dado que está sendo retrado
        fim = fim.anterior;
        /**
         * verificando se o proximo para onde vou apontar o fim é o vazio ou contem um
         * elemento
         */
        if (verificarVazio()) { 
            // caso o proximo não exista (esteja vazio) então meu proximo elemento será o vazio
            fim.proximo = null;
        } else { //isso ocorrerá ao retirarmos o último elemento da lista
            inicio = null;
        }
        tamanho--;
        return aux;
    }

    /**
     * Método que imprime a lista
     * @return retona uma string formatada para ser imprimida
     */
    public String imprimir() {
        StringBuilder imprime = new StringBuilder("==============/ Aparece nos seguintes documentos /==============\n");
        if(verificarVazio()){ 
            imprime.append("Nenhum documento");
        }
        else {
            ElementoDoc aux = inicio;
            while (aux != null) {
                imprime.append("ID do documento: " + aux.dado.IdDoc + "\nNome do Documento: " + aux.dado.Titulo + "\n");
                    
                aux = aux.proximo;
            }
        }
        return imprime.toString();
    }

    /**
     * Método que verifica se um determindado documento esta na lista
     * @param doc -> documento procurado
     * @param atualizarValor -> boolean true caso seja necessário atualizar o
     * valor do documento procurado caso ele exista (isto é necessário
     * para mesclar os valores dos documentos que contém duas palvras-chave
     * procuradas)
     * @return boolean
     */
    public boolean verificarSeExisteDoc (Documentos doc, boolean atualizarValor) {
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
                if (aux.dado.Titulo.equals(doc.Titulo)) {
                    if (atualizarValor) aux.dado.ocorrenciasNesteDocOuValorDoDoc += doc.ocorrenciasNesteDocOuValorDoDoc;
                    return true;
                }
                else
                    aux = aux.proximo;// apontando o aux para o proximo lemento na lista
            }
        return false;
    }

    /**
     * Método que uma string com os dados dos docuentos
     * @return string
     */
    public String toString() {
        StringBuilder imprime = new StringBuilder();
        ElementoDoc aux = inicio;
        while (aux != null) {
            imprime.append("`" + aux.dado.IdDoc + "`" + aux.dado.Titulo + "`" + aux.dado.ocorrenciasNesteDocOuValorDoDoc); 
                    
            aux = aux.proximo;
        }
        return imprime.toString();
    }

    /**
     * Método que retorna o tamanho da lista
     * @return int (tamanho)
     */
    public int tamanho() {
        int tamanho = 0;
        ElementoDoc aux = inicio;
        while (aux != null) {
            tamanho++; 
            aux = aux.proximo;
        }
        return tamanho;
    }
}
