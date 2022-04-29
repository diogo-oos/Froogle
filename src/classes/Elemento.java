package classes;

/**
 * Nó que auxilia as verificações da lista,
 * ele gerencia os ponteiros
 * 
 * fonte: https://www.youtube.com/watch?v=gx8LtrOC278
 * https://www.youtube.com/watch?v=oDAfNY_duZQ
 */
public class Elemento {
    public Elemento proximo = null;// ponteiro para o elemento anterior
    public Elemento anterior = null; // ponteiro para o proximo elemento
    public Termo dado = null; // dado que será armazenado dentro do elemento (espaço na memoria)

}
