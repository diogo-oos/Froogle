package classes;

public class Ordenacao {
    public void OrdenarTermos(Termo[] vetor, int posicaoFinal) {
        shellSortTermos(vetor, posicaoFinal);
    }

    public void OrdenarDocumentos(Documentos[] lista, int posicaoInicial, int posicaoFinal) {
        quicksortDocs(lista, posicaoInicial, posicaoFinal);
    }

    /**
     * Método de ordenação quicksort
     * 
     * @param Docs[] -> vetor da classe Documentos
     * @param start     -> indice inicial
     * @param end       -> indice final (ultima posição em que se ainda existem
     *                  Documentos)
     * @return void
     */
    public void quicksortDocs(Documentos[] Docs, int start, int end) {
        /*
         * base da recursividade: quando o fim for maior ou igual ao início
         * quando a particão resultar em apenas um elemento
         */
        if (start < end) {
            // p é o novo pivô (essa posição já está em seu lugar)
            int p = particionarDocs(Docs, start, end);

            // ou seja, iremos tentar ordenar as partições à esquesda
            quicksortDocs(Docs, start, p - 1);

            // e à diretia dela
            quicksortDocs(Docs, p + 1, end);
        }
    }

    /**
     * Método para particionar o vetor (implementação do quicksort)
     * @param docs -> Vetor da classe termo
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
     * Método de comparação das ocorrências dos documentos da esquesda e direita com o
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

    /**
     * Método de ordenação shellSort em ordem decrescente
     * (Necessário passar o comprimento do vetor para evitar posições iguais
     * a null)
     * @param aTermos -> vetor da classe Termo
     * @param comprimentoVetor -> comprimento do vetor
     */
    private void shellSortTermos(Termo[] aTermos, int comprimentoVetor) {
        int posicao , j , pulos = 1;
        Termo termo;

        do {
            // sequências dos pulos proposta por Knuth
            pulos = 3 * pulos + 1;
        } while (pulos < comprimentoVetor);

        do {
            pulos = pulos / 3;// atualização do valor dos pulos
            for (posicao = pulos; posicao < comprimentoVetor; posicao++) {
                termo = aTermos[posicao];
                j = posicao - pulos;
                // enquanto for maior que o termo da posição, para ocorrer ordenação decrescente
                while (j >= 0 && termo.NumeroDeOcorrencias > aTermos[j].NumeroDeOcorrencias) {
                    aTermos[j + pulos] = aTermos[j];
                    j = j - pulos;
                }
                aTermos [j + pulos] = termo;
            }
        } while (pulos > 1);
    }
}
