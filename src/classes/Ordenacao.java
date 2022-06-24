package classes;

public class Ordenacao {
    public void OrdenarTermos(Termo[] vetor, int posicaoInicial, int posicaoFinal) {
        quicksortTermo(vetor, posicaoInicial, posicaoFinal);
    }

    public void OrdenarDocumentos(Documentos[] lista, int posicaoInicial, int posicaoFinal) {
        quicksortDocs(lista, posicaoInicial, posicaoFinal);
    }

    /**
     * Método de ordenação quicksort
     * 
     * @param aTermos[] -> vetor da classe termo
     * @param start     -> indice inicial
     * @param end       -> indice final (ultima posição em que se ainda existem
     *                  termos)
     * @return void
     */
    public void quicksortTermo(Termo[] aTermos, int start, int end) {
        /*
         * base da recursividade: quando o fim for maior ou igual ao início
         * quando a particão resultar em apenas um elemento
         */
        if (start < end) {
            // p é o novo pivô (essa posição já está em seu lugar)
            int p = particionarTermo(aTermos, start, end);

            if (p - start <= end-p) {
                // ou seja, iremos tentar ordenar as partições à esquesda
                quicksortTermo(aTermos, start, p - 1);
                start = p;
            }
            else {
                // e à diretia dela
                quicksortTermo(aTermos, p + 1, end);
                end = p;
            }
        }
    }

    /**
     * Método de ordenação quicksort
     * 
     * @param aTermos[] -> vetor da classe termo
     * @param start     -> indice inicial
     * @param end       -> indice final (ultima posição em que se ainda existem
     *                  termos)
     * @return void
     */
    public void quicksortDocs(Documentos[] aTermos, int start, int end) {
        /*
         * base da recursividade: quando o fim for maior ou igual ao início
         * quando a particão resultar em apenas um elemento
         */
        if (start < end) {
            // p é o novo pivô (essa posição já está em seu lugar)
            int p = particionarDocs(aTermos, start, end);

            // ou seja, iremos tentar ordenar as partições à esquesda
            quicksortDocs(aTermos, start, p - 1);

            // e à diretia dela
            quicksortDocs(aTermos, p + 1, end);
        }
    }

    /**
     * Método para particionar o vetor (implementação do quicksort)
     * 
     * @param aTermos -> Vetor da classe termo
     * @param start   -> início do vetor ou da partição
     * @param end     -> término do vetor ou da partição
     * @return -> int sendo o novo pivô da partição seguinte
     */
    public int particionarTermo(Termo aTermos[], int start, int end) {
        // representam, respectivamente, o início e o final do vetor ou da partição dele
        int esq, dir;
        Termo aux, pivot;

        // variavel pivo recebe a posição do início do vetor ou partição
        pivot = aTermos[start];
        esq = start;
        dir = end;
        while (esq < dir) { // enquanto o início for menor que o final
            while (esq < end && !compararOcorrencias(aTermos[esq].NumeroDeOcorrencias, pivot.NumeroDeOcorrencias)) {
                /*
                 * avanço a posição da esquerda enquanto a propriedade NumeroDeOcorrencias do
                 * termo da
                 * esquerda for maior ou igual ao pivô
                 */
                esq++;
            }
            while (compararOcorrencias(aTermos[dir].NumeroDeOcorrencias, pivot.NumeroDeOcorrencias)) {
                /*
                 * avanço a posição da direita enquanto a propriedade NumeroDeOcorrencias do
                 * termo da
                 * direita for menor que o pivô
                 */
                dir--;
            }

            /*
             * se a esquerda(início) ainda for menor que a direita(final) depois disso,
             * troco uma pela outra
             */
            if (esq < dir) {
                aux = aTermos[esq];
                aTermos[esq] = aTermos[dir];
                aTermos[dir] = aux;
            }
        }
        // depois disso trocamos o termo no início pelo termo da direita
        aTermos[start] = aTermos[dir];

        // o termo da direita recebe o termo do início, que era o pivô
        aTermos[dir] = pivot;

        // o pivõ passa a ser a posição da direita, aonde ela parar, essa posição já
        // está ordenada
        return dir;
        // à esquerda dessa posição, todo mundo é maior. Já à direita, todo mundo é
        // menor
    }

    /**
     * Método para particionar o vetor (implementação do quicksort)
     * 
     * @param aTermos -> Vetor da classe termo
     * @param start   -> início do vetor ou da partição
     * @param end     -> término do vetor ou da partição
     * @return -> int sendo o novo pivô da partição seguinte
     */
    public int particionarDocs(Documentos docs[], int start, int end) {
        // representam, respectivamente, o início e o final do vetor ou da partição dele
        int esq, dir;
        Documentos aux, pivot;

        // variavel pivo recebe a posição do início do vetor ou partição
        pivot = docs[start];
        esq = start;
        dir = end;
        while (esq < dir) { // enquanto o início for menor que o final
            while (esq < end && !compararOcorrencias(docs[esq].ocorrenciasNesteDocOuValorDoDoc, pivot.ocorrenciasNesteDocOuValorDoDoc)) {
                /*
                 * avanço a posição da esquerda enquanto a propriedade NumeroDeOcorrencias do
                 * termo da
                 * esquerda for maior ou igual ao pivô
                 */
                esq++;
            }
            while (compararOcorrencias(docs[dir].ocorrenciasNesteDocOuValorDoDoc, pivot.ocorrenciasNesteDocOuValorDoDoc)) {
                /*
                 * avanço a posição da direita enquanto a propriedade NumeroDeOcorrencias do
                 * termo da
                 * direita for menor que o pivô
                 */
                dir--;
            }

            /*
             * se a esquerda(início) ainda for menor que a direita(final) depois disso,
             * troco uma pela outra
             */
            if (esq < dir) {
                aux = docs[esq];
                docs[esq] = docs[dir];
                docs[dir] = aux;
            }
        }
        // depois disso trocamos o termo no início pelo termo da direita
        docs[start] = docs[dir];

        // o termo da direita recebe o termo do início, que era o pivô
        docs[dir] = pivot;

        // o pivõ passa a ser a posição da direita, aonde ela parar, essa posição já
        // está ordenada
        return dir;
        // à esquerda dessa posição, todo mundo é maior. Já à direita, todo mundo é
        // menor
    }

    /**
     * Método de comparação das ocorrências dos termos da esquesda e direita com o
     * pivo
     * Usado no método de particionar do quicksort
     * (Essa método é excelente para alternar entre ordenação crescente ou
     * decrescente, bastando apenas inverter o sinal da comparação dele)
     * @param ocorrencias -> número de ocorrências dos termos da esquesda ou direita
     * @param pivo
     * @return -> boolean
     */
    public boolean compararOcorrencias(int ocorrencias, int pivo) {
        return ocorrencias < pivo;
    }
}
