package com.miniGoogle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.classJava.*;

public class AppMiniGoogle {
	
	static final String NOME_ARQUIVO = "AED_20_MiniGoogle_Etapa1_2022-1.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner leitor = new Scanner(new File(NOME_ARQUIVO));
		
		mostrarTermos(criarTermos(leitor));
		
		leitor.close();
		
	}
	
	/**
	 * Método que utiliza uma classe externa chamada Termo,
	 * lê um arquivo de texto, cria um termo novo para cada palavra
	 * nova lida e atualiza a quantidade em palavras que se repetem.
	 * @param leitor -> variável scanner para leitura do arquivo
	 * @return vetor de termos com todos os termos criados 
	 */
	public static Termo[] criarTermos(Scanner leitor) {
		
		//vetor de termos (ideal trocar por arrayList posteriormente)
		Termo[] aTermos = new Termo[1000];
		
		int posicao = 0;//variavel para controlar a posição do vetor de termos
		
		//variavel para receber as posições das palavras que se repetirem, quandos as mesmas forem encontradas
		int iPosicaoPalavraRepete = 0;
		
		while(leitor.hasNext()) {//enquanto existir linhas para ler...
			
			//vetor para receber as palavras de cada linha do arquivo, separadas por um espaço em branco
			String[] sPalavras = leitor.nextLine().split(" ");
			
			//laço para percorrer todas as palavras da linha
			for(int x = 0; x<sPalavras.length; x++) {
				boolean bPalavraRepete = false;//variavel de controle
				
				int y = 0;//variavel para controlar a leitura do vetor de termos (ideal remover ao colocar arrayList)
				
				//laço para percorrer o vetor de termos enquanto ainda existirem termos salvos
				while(aTermos[y] != null) {
						
					if(aTermos[y].palavra.equals(sPalavras[x])) {
						
						bPalavraRepete = true;
						iPosicaoPalavraRepete = y;
						break;//para a verificação quando é encontrada uma palavra igual, depois passa pra próxima
						
					}
					y++;
				}
				
				if(bPalavraRepete == false) {
					Termo novoTermo = new Termo();
					novoTermo.palavra = sPalavras[x];
					aTermos[posicao] = novoTermo;
					
					//a variável de controle de posição só é atualizada após ser criado um novo termo
					posicao++;
				}
				
				else {
					aTermos[iPosicaoPalavraRepete].qtda++;
				}
						
			}
			
		}
		
		return aTermos;
		
	}
	
	/**
	 * Percorre o vetor de Termos criados e mostra na tela as propriedades 
	 * palavra e qtda, da classe Termo.
	 * @param aTermos -> vetor de termos criados
	 */
	public static void mostrarTermos (Termo[] aTermos) {
		
		int i = 0;
		
		//esse tipo de repetição deverá mudar com arrayList
		while(aTermos[i] != null) {
			
			System.out.println("Termo: " + aTermos[i].palavra + "\n" + "Quantidade de vezes que ocorre: " + aTermos[i].qtda + " ");
			System.out.println("==================================");
			i++;
			
		}
		
	}
	
}


