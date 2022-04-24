package com.classJava;

public class ListaDE {
   public Termo termo = null;
    Documento documento = null;

    // ---------------------------


    public boolean listaVazia() {
        if ( == null)
            return true;
        else
            return false;
    }

    public void adicionarTermo(Termo novo) {

        if (listaVazia()) {
            termo = novo.palavra;
        }

        novo.palavra = termo;
        novo.vagaoAnterior = documento;
        termo = novo;
        novo.vagaoPosterior.vagaoAnterior = novo;
        documento.vagaoPosterior = novo;
    }

    public void adicionarFinal(Vagao novo) {

        if (listaVazia()) {
            termo = novo;
            documento = novo;
        }

        novo.vagaoPosterior = termo;
        novo.vagaoAnterior = documento;
        documento = novo;
        novo.vagaoPosterior.vagaoAnterior = novo;
        novo.vagaoAnterior.vagaoPosterior = novo;
    }

    public void imprimir() {
        Vagao aux = termo;

        do {
            System.out.println("Vagao: " + aux.nomeDoVagao);
            if (aux.vagaoAnterior != null)
                System.out.println("Anterior: " + aux.vagaoAnterior.nomeDoVagao);
            if (aux.vagaoPosterior != null)
                System.out.println("Posterior: " + aux.vagaoPosterior.nomeDoVagao);
            System.out.println("------------------------------------");
            aux = aux.vagaoPosterior;
        } while (aux != termo);

    }

}