package com.froogleApp;

import com.classJava.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FroogleApp {
	/*
	 * DECLARANDO VARIAVEIS GLOBAIS->>
	 * Nome do arquivo que tem os nomes dos
	 * arquivos usados para execução
	 */
	static final String nomesDeArquivos = "nomesArquivos.txt";
	static final String arquivoTermos = "termos.txt";// Nome do arquivo que contem todos os termos catalogados
	static int posicao = 0;// variavel para controlar a posição do vetor de termos
	static int idTermo = 0;// varaivel para atribuição de id(identificador) aos Termos
	// vetor de termos (ideal trocar por arrayList posteriormente)
	static Termo[] aTermos = new Termo[1000];

	// ------------------------------------------------------
	// Método de execução do programa->>
	// #region Main
	/** Metodo para limpar tela antes de aparecer primeiro menu */
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * Metodo carregarTermosParaArq
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
				/** gravar informações dos termos no arquivo */
				sc.write(objt.id + ";" + objt.palavra + ";" + objt.repeticao + "\n");
		}
		sc.close();
	}

	/**
	 * 
	 * @return nomeArquivos -> array de Strings com o nome de cada arquivo a ser
	 *         lido.
	 * @throws FileNotFoundException
	 */
	public static String buscarTermo(String termoBuscador) {

		for (Termo objto : aTermos) {// para cada objeto no vetor de termos
			/*
			 * se o objeto for diferente de null e seu atributo palavra for igual o termo
			 * procurado.
			 */
			if (objto != null && objto.palavra.equals(termoBuscador)) {
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
	 * Percorre o vetor de Termos criados e mostra na tela as propriedades
	 * palavra e qtda, da classe Termo.
	 * 
	 * @param aTermos -> vetor de termos criados
	 */
	public static void mostrarTermos(Termo[] aTermos) {
		// chamando a ordenacao dos termos
		
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
		} else {
			System.out.print("Termo já existente. Por favor, insira um novo termo.");
		}
	}

	/**
	 * Metodo limpaVetor
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

	/**
	 * Metodo carregarArquivos
	 * Método que chama outro método para criar termos no vetor aTermos, os termos
	 * vêm
	 * das palavras dos arquivos cujo nomes estão no vetor recebido como parâmetro
	 * 
	 * @param nomesArquivos recebe vetor de String que contem os nomes referentes
	 *                      aos arquivos a serem carregados.
	 * @throws FileNotFoundException
	 */
	public static void carregarArquivos(String[] nomesArquivos) throws FileNotFoundException {
		/*
		 * O que Ocorre aqui?
		 * laço for repete para cada String com nome de arquivo no vetor
		 * "nomesArquivos".
		 */
		for (String arq : nomesArquivos) {
			criarTermos(arq);// catalogar e criar termos para cada arquivo passado como par�metro
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
		/*
		 * variavel que armazena a quantidade de arquivos a serem lidos: tal informação
		 * encontra-se na primeira linha do arquivo que contem os nomes dos arquivos.
		 */
		int qntArquivos = 0;
		qntArquivos = Integer.parseInt(lerNomes.nextLine());// variavel recebe quantidade, primeira linha do arquivo
		/** declarando vetor de string para armazenar o nome dosarquivos. */
		String[] nomesArquivos = new String[qntArquivos];
		/**
		 * variavel de controle para se movimentar pelo vetor de String "nomeArquivos"
		 */
		int cont = 0;
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
			// laco para percorrer todas as palavras da linha
			for (int x = 0; x < sPalavras.length; x++) {
				boolean bPalavraRepete = false;// variavel de controle
				int y = 0;/*
							 * variavel para controlar a leitura do vetor de termos (ideal remover ao
							 * colocar arrayList)
							 */
				/*
				 * laço para percorrer o vetor de termos enquanto ainda existirem termos salvos
				 */
				while (aTermos[y] != null) {
					if (aTermos[y].palavra.equals(sPalavras[x])) {
						bPalavraRepete = true;
						iPosicaoPalavraRepete = y;
						break; /*
								 * para a verificação quando é encontrada uma palavra igual, depois passa pra
								 * próxima
								 */
					}
					y++;
				}
				if (bPalavraRepete == false) {
					Termo novoTermo = new Termo(sPalavras[x], 1, idTermo);// criando objeto termo
					aTermos[posicao] = novoTermo;// vetor de Termos recebe termo criado
					idTermo += 1;// Acresenta o id do termo em um conforme é criado.
					/*
					 * a variável de controle de posição só é atualizada após ser criado um novo
					 * termo
					 */
					posicao++;
				} else {
					aTermos[iPosicaoPalavraRepete].repeticao++;
				}
			}
		}
		leitor.close();// fechamento do scanner de arquivo
		return aTermos;// retorna array de objetos Termos criados / catalogado
	}

	/**
	 * metodo carregandoTermosDoArq
	 * 
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

	// #region Menu
	public static void menu() throws IOException {
		// Métodos auxiliares do programa ->>>
		// #region Termos&Relacionados
		// Scanner para leitura da opção
		Scanner sc = new Scanner(System.in);
		// variavel de controle para switch
		int opt = 0;
		// Opcoes do menu para o usuario.
		while (opt != 5) {
			System.out.println(
					"\n======================================\n		FROOGLE \n======================================\n");
			System.out.println("==> MENU PRINCIPAL <==");
			System.out.println("\n1. CARREGAR TERMOS CATALOGADOS PARA ARQUIVO\n"
					+ "2. CONSULTAR TERMOS\n3. INSERIR NOVO TERMO\n"
					+ "4. CARREGAR NOVOS ARQUIVOS E CATALOGAR TERMOS\n" + "5. SAIR");

			System.out.print("Digite o numero da opção desejada:	");
			opt = sc.nextInt();// opt recebe a opção escolida
			switch (opt) {// Switch na opção do usuario
				case 1:
					// carregar termos para arquivo de termos formatado
					carregarTermosParaArq();
					break;
				case 2:
					// CONSULTA DE TERMOS ->
					// Sub-Menu para consulta de Termos!
					System.out.println("\n==> ESCOLHA UMA OPCAO <==");
					System.out.print("1. Consultar um termo especifico\n2. Ver todos os termos:	");
					int subop = 0;// variavel para controle da opção do sub-menu do usuario
					subop = sc.nextInt();
					switch (subop) {// Switch na opção do usuario
						case 1:
							// BUSCA ESPECIFICA DE TERMOS ->
							String buscaTermo = null;// buscaTermo recebe o termo que o usuario deseja procurar
							System.out.print("\n=> Entre com o termo que deseja buscar:	");
							buscaTermo = sc.next();// buscaTermo recebe o termo procurado
							System.out.println(buscarTermo(buscaTermo));// Procura e retorna o termo e seus atributos
							break;
						case 2:
							ordenacao(aTermos, 0, posicao-1);
							mostrarTermos(aTermos);// mostrar todos os termos
							break;

						default:
							System.out.println("\nPor favor, entre com uma opção valida.");
					}
					break;
				// FIM SUB-MENU
				case 3:
					/*-> O ARQUIVO CONTENDO OS TERMOS SÓ DEVE SER
					ATUALIZADO SE FOR ADD UM NOVO TERMO OU TODA VEZ QUE EXECUTAR O PROGRAMA?
					*/
					String novoTermo = null;
					System.out.print("\nInsira o termo que deseja adicionar:	");
					novoTermo = sc.next();
					novoTermo.toLowerCase();
					inserirTermo(novoTermo);
					break;
				case 4:
					// ler e carregar arquivos catalogando os termos:
					limparVetor();
					carregarArquivos(carregarNomesDeArquivos());
					break;
				case 5:
					limparTela();
					System.out.println("\n\n==== OBRIGADO POR USAR O FROOGLE! ==== > ==== VOLTE SEMPRE ====\n\n");
					break;
				default:
					System.out.println("OPÇÃO INVALIDA!!\n\n--\n\n");
			}
		} // FIM WHILE
		sc.close();// fecha scanner do Menu
	}
	// #endregion Menu.

	public static int particionar(Termo aTermos[], int start, int end) {

		int esq, dir;
		Termo aux, pivot;
		pivot = aTermos[start]; // variavel pivo
		esq = start;
		dir = end;
		while(esq < dir) {
			while(aTermos[esq].repeticao <= pivot.repeticao){
				esq++;
			}
			while(aTermos[dir].repeticao > pivot.repeticao){
				dir--;
			}
			if(esq < dir){
				aux = aTermos[esq];
				aTermos[esq] = aTermos[dir];
				aTermos[dir] = aux;
			}
		}
		aTermos[start] = aTermos[dir];
		aTermos[dir] = pivot;
		return dir;



	}
 
	/**Implementando a funcao quicksort
	 * aTermos[] = vetor que esta sendo ordenado, start = indice inicial, end =
	 * indice
	 * final
	 */
	public static void ordenacao(Termo[] aTermos, int start, int end) {
		if (start < end) {
			int p = particionar(aTermos, start, end); // p é o indice de particao
			ordenacao(aTermos, start, p - 1);
			ordenacao(aTermos, p + 1, end);
		}
	}

	/*public static int verTamanhoVetor(Termo[] aTermos) {
		int cont = 0;
		for (int i = 0; i < aTermos.length; i++) {
			if (aTermos[i] == null) {
				break;
			}
			cont = cont + 1;
		}
		return cont;
	}
 */
	public static void main(String[] args) throws IOException {
		limparTela();
		// carregar termos catalogados no arquivo texto(termos.txt) para o vetor de
		// Termos.
		carregandoTermosDoArq();
		// chamar Menu do usuario
		menu();

	} // #endregion Main

}
