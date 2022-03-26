package com.froogleApp;

import com.classJava.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FroogleApp {
	// DECLARANDO VARIAVEIS GLOBAISaaa->>

	static final String nomesDeArquivos = "nomesArquivos.txt";// Nome do arquivo que tem os nomes dos arquivos usados
																// para execução

	static final String arquivoTermos = "termos.txt";// Nome do arquivo que contem todos os termos catalogados

	static int posicao = 0;// variavel para controlar a posição do vetor de termos

	static int idTermo = 0;// varaivel para atribuição de id(identificador) aos Termos

	// vetor de termos (ideal trocar por arrayList posteriormente)
	static Termo[] aTermos = new Termo[1000];

	// ------------------------------------------------------

	// Método de execução do programa->>

	// #region Main
	public static void main(String[] args) throws IOException {

		// carregar termos catalogados no arquivo texto(termos.txt) para o vetor de
		// Termos.
		carregandoTermosDoArq();

		// chamar Menu do usuario
		menu();

	}
	// #endregion Main

	// Métodos auxiliares do programa ->>>

	// #region Menu
	public static void menu() throws IOException {
		// Scanner para leitura da opção
		Scanner sc = new Scanner(System.in);

		// variavel de controle para switch
		int opt = 0;

		// Op��es do menu para o usuario.
		while (opt != 4) {
			System.out.println("\n=================== FROOGLE ===================\n");

			System.out.println("============= MENU =============");

			System.out.println("\nOPÇÃO (1) - CARREGAR TERMOS CATALOGADOS PARA ARQUIVO\n"
					+ "OPÇÃO (2) - CONSULTAR TERMOS\nOPÇÃO (3) - INSERIR NOVO TERMO\n" + "\nOPÇÃO (4) - SAIR!\n"
					+ "\nOPÇÃO (5) - CARREGAR NOVOS ARQUIVOS E CATALOGAR TERMOS\n");

			System.out.println("Entre com sua opção:");

			opt = sc.nextInt();// opt recebe a opção escolida

			switch (opt) {// Switch na opção do usuario

				case 1:
					// carregar termos para arquivo de termos formatado
					carregarTermosParaArq();
					break;

				case 2:
					// CONSULTA DE TERMOS ->

					// Sub-Menu para consulta de Termos!
					System.out.print("Consultar um termo especifico(1) ou ver todos os termos(2)");

					int subop = 0;// variavel para controle da opção do sub-menu do usuario

					subop = sc.nextInt();

					switch (subop) {// Switch na opção do usuario

						case 1:
							// BUSCA ESPECIFICA DE TERMOS ->
							String buscaTermo = null;// buscaTermo recebe o termo que o usuario deseja procurar

							System.out.print("Entre com o termo que deseja buscar: ");
							buscaTermo = sc.next();// buscaTermo recebe o termo procurado

							System.out.println(buscarTermo(buscaTermo));// Procura e retorna o termo e seus atributos

							break;

						case 2:
							mostrarTermos(aTermos);// mostrar todos os termos
							break;

						default:
							System.out.println("Por favor, entre com uma opção valida.");
					}

					break;
				// FIM SUB-MENU

				case 3:
					/*-> O ARQUIVO CONTENDO OS TERMOS SÓ DEVE SER
					ATUALIZADO SE FOR ADD UM NOVO TERMO OU TODA VEZ QUE EXECUTAR O PROGRAMA?
					*/

					String novoTermo = null;
					System.out.print("Insira o termo que deseja adicionar> ");
					novoTermo = sc.next();

					inserirTermo(novoTermo);
					break;

				case 4:
					System.out.println("== OBRIGADO POR USAR O FROOGLE!!! == \n VOLTE SEMPRE -_-");
					break;

				case 5:
					// ler e carregar arquivos catalogando os termos:
					limparVetor();
					carregarArquivos(carregarNomesDeArquivos());
					break;

				default:
					System.out.println("OPÇÃO INVALIDA!!");
					break;
			}

		} // FIM WHILE

		sc.close();// fecha scanner do Menu
	}
	// #endregion Menu.

	// #region Termos&Relacionados
	/**
	 * Método que utiliza uma classe externa chamada Termo,
	 * lê um arquivo de texto, cria um termo novo para cada palavra
	 * nova lida e atualiza a quantidade em palavras que se repetem.
	 * 
	 * @param leitor -> variável scanner para leitura do arquivo
	 * @return vetor de termos com todos os termos criados
	 */
	public static Termo[] criarTermos(String nomesArquivos) throws FileNotFoundException {

		Scanner leitor = new Scanner(new File(nomesArquivos));

		// variavel para receber as posições das palavras que se repetirem, quandos as
		// mesmas forem encontradas
		int iPosicaoPalavraRepete = 0;

		while (leitor.hasNext()) {// enquanto existir linhas para ler...

			// vetor para receber as palavras de cada linha do arquivo, separadas por um
			// espaço em branco
			String[] sPalavras = leitor.nextLine().split(" ");

			// la�o para percorrer todas as palavras da linha
			for (int x = 0; x < sPalavras.length; x++) {
				boolean bPalavraRepete = false;// variavel de controle

				int y = 0;// variavel para controlar a leitura do vetor de termos (ideal remover ao
							// colocar arrayList)

				// laço para percorrer o vetor de termos enquanto ainda existirem termos salvos
				while (aTermos[y] != null) {

					if (aTermos[y].palavra.equals(sPalavras[x])) {

						bPalavraRepete = true;
						iPosicaoPalavraRepete = y;
						break;// para a verificação quando é encontrada uma palavra igual, depois passa pra
								// próxima

					}
					y++;
				}

				if (bPalavraRepete == false) {
					Termo novoTermo = new Termo(sPalavras[x], 1, idTermo);// criando objeto termo
					aTermos[posicao] = novoTermo;// vetor de Termos recebe termo criado
					idTermo += 1;// Acresenta o id do termo em um conforme é criado.
					// a variável de controle de posição só é atualizada após ser criado um novo
					// termo
					posicao++;
				}

				else {
					aTermos[iPosicaoPalavraRepete].repeticao++;
				}
			}
		}
		leitor.close();// fechamento do scanner de arquivo
		return aTermos;// retorna array de objetos Termos criados / catalogado
	}

	/**
	 * Percorre o vetor de Termos criados e mostra na tela as propriedades
	 * palavra e qtda, da classe Termo.
	 * 
	 * @param aTermos -> vetor de termos criados
	 */
	public static void mostrarTermos(Termo[] aTermos) {
		// esse tipo de repetição deverá mudar com arrayList
		for (Termo termos : aTermos) {

			if (termos != null) {
				System.out.println("==================================");
				System.out.println("\nID: " + termos.id + "\nTermo: " + termos.palavra + "\n" + "Repetições: "
						+ termos.repeticao + " ");
				System.out.println("==================================");
			}
		}
	}

	/**
	 * 
	 * @return nomeArquivos -> array de Strings com o nome de cada arquivo a ser
	 *         lido.
	 * @throws FileNotFoundException
	 */
	public static String buscarTermo(String termoBuscador) {

		for (Termo objto : aTermos) {// para cada objeto no vetor de termos

			if (objto != null && objto.palavra.equals(termoBuscador)) {// se o objto for diferente de null e seu
																		// atributo palavra for igual o termo procurado.

				// formatando os dados do termo encontrado para poder retorna-lo para o usario.
				String resultBusca = "=============" + "\n Termo: " + objto.palavra + "\n Repete-se: "
						+ objto.repeticao;

				// retorna informações do termo:
				return resultBusca;
			}
		}

		// se não encontrar o termo -> retorna uma msg de aviso!
		return "Termo não encontrado.";
	}

	/**
	 * Cria um novo termo com a palavra digitada pelo usuário
	 * 
	 * @param palavra
	 */
	public static void inserirTermo(String palavra) {

		if (buscarTermo(palavra).equals("Termo não encontrado!")) {

			Termo novoTermo = new Termo(palavra, 1, idTermo);
			idTermo++;
			aTermos[posicao] = novoTermo;
			System.out.print("Termo add com sucesso!");
			System.out.print("\nPalavra: " + aTermos[posicao].palavra + "\nID:" + aTermos[posicao].id);
			posicao++;
		}

		else {
			System.out.print("Termo já existente. Por favor, insira um novo termo.");
		}
	}
	// #endregion Termos&Relacionados.

	// #region Arquivos

	/**
	 * 
	 * @return nomeArquivos -> vetor de Strings contendo os nomes de arquivos a
	 *         serem lidos pelo programa
	 * @throws FileNotFoundException
	 */
	public static String[] carregarNomesDeArquivos() throws FileNotFoundException {

		Scanner lerNomes = new Scanner(new File(nomesDeArquivos));// declara scanner para leitura de arquivos

		int qntArquivos = 0;// variavel que armazena a quantidade de arquivos a serem lidos: tal informação
							// encontra-se na primeira linha do arquivo que contem os nomes dos arquivos.

		qntArquivos = Integer.parseInt(lerNomes.nextLine());// variavel recebe quantidade, primeira linha do arquivo

		String[] nomesArquivos = new String[qntArquivos];// declarando vetor de string para armazenar o nome dos
															// arquivos.

		int cont = 0;// variavel de controle para se movimentar pelo vetor de String "nomeArquivos"

		// Laço while para cada linha do arquivo depois da primeira linha, com a
		// quantidade de arquivos, ser lida
		while (lerNomes.hasNext()) {
			nomesArquivos[cont] = lerNomes.nextLine();
			cont++;// vetor String recebe nome do arquivo
		}

		lerNomes.close();

		// retorna vetor com os nomes dos arquivos a serem carregados
		return nomesArquivos;

	}

	/**
	 * Método que chama outro método para criar termos no vetor aTermos, os termos
	 * vêm
	 * das palavras dos arquivos cujo nomes estão no vetor recebido como parâmetro
	 * 
	 * @param nomesArquivos recebe vetor de String que contem os nomes referentes
	 *                      aos arquivos a serem carregados.
	 * @throws FileNotFoundException
	 */
	public static void carregarArquivos(String[] nomesArquivos) throws FileNotFoundException {

		// laço for repete para cada String com nome de arquivo no vetor
		// "nomesArquivos".
		for (String arq : nomesArquivos) {

			criarTermos(arq);// catalogar e criar termos para cada arquivo passado como par�metro

		}
	}

	/**
	 * Método que carrega todos os termos encontrados, formata seus atributos e
	 * coloca em um arquivo
	 * 
	 * @throws IOException
	 */
	public static void carregarTermosParaArq() throws IOException {

		File arqTermos = new File(arquivoTermos);// declarando arquivo termos.txt no java

		FileWriter sc = new FileWriter(arqTermos);// declarando fileWriter para o arquivo de termos

		for (Termo objt : aTermos) {// Para cada termo no vetor de termos
			if (objt != null)// se o termo for diferente de null

				sc.write(objt.id + ";" + objt.palavra + ";" + objt.repeticao + "\n");// gravar informações dos termos no
																						// arquivo
		}

		sc.close();

	}

	/**
	 * Método que lê o arquivo de termos e cria um termo para cada linha lida
	 */
	public static void carregandoTermosDoArq() throws IOException {
		File arqTermos = new File(arquivoTermos);// declarando arquivo termos.txt no java
		Scanner lerTermos = new Scanner(arqTermos);// Scanner para leitura do arquivo de Termos
		String[] dataTermos;

		while (lerTermos.hasNext()) {
			int i = 0;

			dataTermos = lerTermos.nextLine().split(";");

			// Organização no vetor dataTermos
			// palavra -> posição (1)
			// repetição -> posição (2)
			// id do termo -> posição (0)
			Termo termos = new Termo(dataTermos[i + 1], Integer.parseInt(dataTermos[i + 2]),
					Integer.parseInt(dataTermos[i]));

			aTermos[posicao] = termos;
			posicao++;
			idTermo++;
		}

		lerTermos.close();
	}
	// endregion Arquivos

	/**
	 * Método que limpa o vetor de termos (aTermos) percorrendo-o e atribuindo o
	 * valor
	 * null para cada posição onde antes havia um termo.
	 * No final, zeramos tanto a variável que controla a posição do vetor de termos
	 * quanto a que controla o id do termo (idTermo)
	 */
	public static void limparVetor() {

		for (int i = 0; i < aTermos.length; i++) {
			if (aTermos[i] != null)
				aTermos[i] = null;
		}
		posicao = 0;
		idTermo = 0;
	}

}// fim classe Main