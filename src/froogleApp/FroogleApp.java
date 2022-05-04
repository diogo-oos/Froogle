package froogleApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import classes.Termo;
import classes.Documentos;
import classes.HashTable;
import classes.StopWord;

public class FroogleApp {
	// DECLARANDO VARIAVEIS GLOBAIS->>

	static final String arqStopWords = "stopWords.txt";

	static HashTable table = new HashTable(97);

	static final String nomesDeArquivos = "nomesArquivos.txt";// Nome do arquivo que tem os nomes dos arquivos usados
																// para execução

	static final String arquivoTermos = "termos.txt";// Nome do arquivo que contem todos os termos catalogados

	static int posicao = 0;// variavel para controlar a posição do vetor de termos

	static int idTermo = 0;// varaivel para atribuição de id(identificador) aos Termos

	// vetor de termos (ideal trocar por arrayList posteriormente)
	static Termo[] aTermos = new Termo[1000];

	// ------------------------------------------------------

	// Métodos auxiliares do programa ->>>

	// #region Termos&Relacionados
	/**
	 * Método que utiliza uma classe externa chamada Termo,
	 * lê um arquivo de texto, cria um termo novo para cada palavra
	 * nova lida e atualiza a quantidade em palavras que se repetem.
	 * 
	 * @param leitor -> variável scanner para leitura do arquivo
	 * @return vetor de termos com todos os termos criados
	 */
	public static Termo[] criarTermos(Documentos nomesArquivos) throws FileNotFoundException {

		Scanner leitor = new Scanner(new File(nomesArquivos.titulo));

		// variavel para receber as posições das palavras que se repetirem, quando as
		// mesmas forem encontradas
		int iPosicaoPalavraRepete = 0;

		while (leitor.hasNext()) {// enquanto existir linhas para ler...

			// vetor para receber as palavras de cada linha do arquivo, separadas por um
			// espaço em branco
			String[] sPalavras = leitor.nextLine().split(" ");

			// laço para percorrer todas as palavras da linha
			for (int x = 0; x < sPalavras.length; x++) {
				if (table.buscar(sPalavras[x]) == null) {
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
						Termo novoTermo = new Termo(idTermo, sPalavras[x], 1);// criando objeto termo
						novoTermo.listaDoc.inserirDocNoFim(nomesArquivos);
						aTermos[posicao] = novoTermo;// vetor de Termos recebe termo criado
						idTermo += 1;// Acresenta o id do termo em um conforme é criado.
						// a variável de controle de posição só é atualizada após ser criado um novo
						// termo
						posicao++;
					}

					else {
						aTermos[iPosicaoPalavraRepete].repeticao++;
						if (!aTermos[iPosicaoPalavraRepete].listaDoc.verificarSeExisteDoc(nomesArquivos.titulo)) {
							aTermos[iPosicaoPalavraRepete].listaDoc.inserirDocNoFim(nomesArquivos);
						}
					}
				}
			}
		}
		leitor.close();// fechamento do scanner de arquivo
		return aTermos;// retorna array de objetos Termos criados / catalogado
	}

	/**
	 * Percorre o vetor de Termos criados e mostra na tela as propriedades ID,
	 * palavra e quantidade de vezes que ele se repete, da classe Termo.
	 * 
	 * @param aTermos -> vetor de termos criados
	 */
	public static void mostrarTermos(Termo[] aTermos) {
		// esse tipo de repetição deverá mudar com arrayList
		for (Termo termos : aTermos) {
			// verifica se existe termo na posição
			if (termos != null) {
				System.out.println("==================================");
				System.out.println(
						"\nID: " + termos.id + "\nTermo: " + termos.palavra + "\nRepetições: " + termos.repeticao + "\n"
								+ termos.listaDoc.imprimir());
				System.out.println("==================================");
			}
		}
	}

	/**
	 * Método que formata uma string com os dados do termo da posição passada como
	 * parâmetro
	 * no seguinte formato:
	 * -> (Id do termo)
	 * -> (Palavra (próprio termo))
	 * -> (Quantidade de vezes que esse termo se repete)
	 * 
	 * @param pos (Posição do termo no vetor de termos (aTermos))
	 * @return resultBusca (String com os dados do termo naquela posição)
	 */
	public static String exibirTermo(int pos) {

		// formatando os dados do termo encontrado para poder retorna-lo para o usario.
		StringBuilder resultBusca = new StringBuilder("=============\n");
		resultBusca.append("ID: " + aTermos[pos].id + "\n");
		resultBusca.append("Termo: " + aTermos[pos].palavra + "\n");
		resultBusca.append("Repete-se: " + aTermos[pos].repeticao + " vezes" + "\n");
		resultBusca.append(aTermos[pos].listaDoc.imprimir());

		return resultBusca.toString();

	}

	/**
	 * 
	 * @return nomeArquivos -> array de Strings com o nome de cada arquivo a ser
	 *         lido.
	 * @throws FileNotFoundException
	 */
	public static int buscarTermo(String termoDigitado) {
		int pos = 0;

		for (Termo objto : aTermos) {// para cada objeto no vetor de termos

			if (objto != null && objto.palavra.equals(termoDigitado)) {// se o objto for diferente de null e seu
																		// atributo palavra for igual o termo procurado.
				// retorna posição do TERMO:
				return pos;
			}
			pos++;
		}

		// se não encontrar o termo -> retorna uma msg de aviso!
		return -1;
	}

	/**
	 * Cria um novo termo com a palavra digitada pelo usuário
	 * 
	 * @param palavra
	 */
	public static void inserirTermo(String palavra) {
		// verifica se o Termo cadastrado já existe
		if (buscarTermo(palavra) == -1) {

			Termo novoTermo = new Termo(idTermo, palavra, 1);
			idTermo++;// ADD 1 NA VARIVEL idTermo para ser atribuida no proximo.

			aTermos[posicao] = novoTermo;// acrescenta novo termo criado no vetor Global aTermos.
			System.out.print("Termo inserido com sucesso!");
			System.out.print("\nPalavra: " + aTermos[posicao].palavra + "\nID:" + aTermos[posicao].id);
			posicao++;// acrescenta 1 em posição para controle do proximo termo cadastrado!.
		}

		else {
			System.out.print("Termo já existente. Por favor, insira um novo termo.");
		}
	}

	/**
	 * Método que limpa o vetor de termos (aTermos) percorrendo-o e atribuindo o
	 * valor
	 * null para cada posição onde antes havia um termo.
	 * No final, zeramos tanto a variável que controla a posição do vetor de termos
	 * quanto a que controla o id do termo (idTermo)
	 */
	public static void limparVetor() {

		int i = 0;
		while (aTermos[i] != null) {
			aTermos[i] = null;
			i++;
		}
		posicao = 0;
		idTermo = 0;
	}
	// #endregion Termos&Relacionados.

	// #region Arquivos

	/**
	 * Método que percorre o arquivo nomesArquivos.txt que está formatado da
	 * seguinte forma:
	 * -> linha 1: número com a quantidade de nomes de aquivos
	 * -> a partir da linha 2: 1 nome de arquivo em cada linha
	 * 
	 * @return nomeArquivos -> vetor de Strings contendo os nomes de arquivos a
	 *         serem lidos pelo programa
	 * @throws FileNotFoundException
	 */
	public static Documentos[] carregarNomesDeArquivos() throws FileNotFoundException {

		Scanner lerNomes = new Scanner(new File(nomesDeArquivos));// declara scanner para leitura de arquivos

		int qntArquivos = 0;// variavel que armazena a quantidade de arquivos a serem lidos: tal informação
							// encontra-se na primeira linha do arquivo que contem os nomes dos arquivos.

		qntArquivos = Integer.parseInt(lerNomes.nextLine());// variavel recebe quantidade, primeira linha do arquivo

		Documentos[] nomesArquivos = new Documentos[qntArquivos];// declarando vetor de string para armazenar o nome dos
		// arquivos.

		int cont = 0;// variavel de controle para se movimentar pelo vetor de String "nomeArquivos"

		// Laço while para cada linha do arquivo depois da primeira linha, com a
		// quantidade de arquivos, ser lida
		while (lerNomes.hasNext()) {
			Documentos novoDoc = new Documentos(cont, lerNomes.nextLine());
			nomesArquivos[cont] = novoDoc;
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
	public static void carregarArquivos(Documentos[] nomesArquivos) throws FileNotFoundException {

		// laço for repete para cada String com nome de arquivo no vetor
		// "nomesArquivos".
		for (Documentos arq : nomesArquivos) {

			criarTermos(arq);// catalogar e criar termos para cada arquivo passado como parâmetro
		}
	}

	/**
	 * Método que carrega todos os termos encontrados, formata seus atributos e
	 * coloca em um arquivo
	 * 
	 * @throws IOException
	 */
	public static void escreverTermosNoArquivo() throws IOException {

		File arqTermos = new File(arquivoTermos);// declarando arquivo termos.txt no java

		FileWriter sc = new FileWriter(arqTermos);// declarando fileWriter para o arquivo de termos
		quicksort(aTermos, 0, posicao - 1);// ordena de forma crescente os termos antes de salvar

		for (Termo objt : aTermos) {// Para cada termo no vetor de termos
			if (objt != null)// se o termo for diferente de null

				sc.write(objt.id + ";" + objt.palavra + ";" + objt.repeticao + objt.listaDoc.toString() + "\n");// gravar
																												// informações
																												// dos
																												// termos
																												// no
			// arquivo
		}

		sc.close();

	}

	/**
	 * Método que lê o arquivo de termos e cria um termo para cada linha lida
	 */
	public static void carregarTermosDoArq() throws IOException {
		File arqTermos = new File(arquivoTermos);// declarando arquivo termos.txt no java
		Scanner lerTermos = new Scanner(arqTermos);// Scanner para leitura do arquivo de Termos
		String[] dataTermos;

		while (lerTermos.hasNext()) {
			dataTermos = lerTermos.nextLine().split(";");

			Termo termos = new Termo(Integer.parseInt(dataTermos[0]), dataTermos[1], Integer.parseInt(dataTermos[2]));

			for (int i = 3; i <= dataTermos.length - 2; i += 2) {
				termos.listaDoc.inserirDocNoFim(new Documentos(Integer.parseInt(dataTermos[i]), dataTermos[i + 1]));
			}

			aTermos[posicao] = termos;
			posicao++;
			idTermo++;
		}

		lerTermos.close();
	}
	// endregion Arquivos

	/**
	 * Método "ordenarVetor" responsavél pela ordenação do array de termos para
	 * exbir de forma decrescente.
	 * OBS: ALGORITMO DE ORDENAÇÃO (BUBBLE) SERÁ SUBSTITUIDO POR UM MAIS EFICIENTE.
	 * return void;
	 */
	/*
	 * public static void ordenarVetor() {
	 * int trocas = 0;
	 * Termo aux = null;
	 * 
	 * for (int refe = aTermos.length - 1; refe >= 0; refe--) {
	 * 
	 * for (int comp = 0; comp < aTermos.length - 1; comp++) {
	 * 
	 * if (aTermos[comp + 1] != null && aTermos[comp] != null) {
	 * 
	 * if (aTermos[comp + 1].repeticao > aTermos[comp].repeticao) {
	 * 
	 * aux = aTermos[comp + 1];
	 * aTermos[comp + 1] = aTermos[comp];
	 * aTermos[comp] = aux;
	 * trocas++;
	 * }
	 * }
	 * }
	 * 
	 * if (trocas == 0)
	 * break;
	 * 
	 * }
	 * 
	 * }
	 */

	/**
	 * Método de comparação das repetições dos termos da esquesda e direita com o
	 * pivo
	 * Usado no método de particionar do quicksort
	 * (Essa método é excelente para alternar entre ordenação crescente ou
	 * decrescente, bastando apenas inverter o sinal da comparação dele)
	 * 
	 * @param rep  -> número de repetições dos termos da esquesda ou direita
	 * @param pivo
	 * @return -> true ou false
	 */
	public static boolean compRep(int rep, int pivo) {
		return rep < pivo;
		/*
		 * Ordenação decrescente:
		 * 
		 * Posição da ESQUERDA:
		 * retornará o valor negado, ou seja, caso seja maior ou igual ao pivo,
		 * retornará false, que negado se torna true
		 * o que acaba fazendo que a posição avance, sem que haja trocas, deixando assim
		 * as maiores posições à esquerda do pivo.
		 * caso seja menor, retornará true, que negado se torna false, fazendo assim com
		 * que essa determinada posição não seja
		 * atualizada, preparando-a para ser trocada com a posição da direita, quando
		 * houver a troca, essa posição, que é menor
		 * que o pivo, seja jogada para a direita dele.
		 * 
		 * Posição da Direita:
		 * Não há negação, ou seja, se for menor, a posição será deixada aonde está, na
		 * direita do pivo,
		 * se for maior, ela será preparada para a troca com a posição da esquerda ou
		 * com o próprio pivo,
		 * caso esse já esteja em seu devido lugar (isso só ocorre quando a posição da
		 * direita se torna igual ou menor que a posição da esquerda)
		 */
	}

	/**
	 * Método para particionar o vetor (implementação do quicksort)
	 * 
	 * @param aTermos (Vetor que se quer ordenar)
	 * @param start   (início do vetor ou da partição)
	 * @param end     (término do vetor ou da partição)
	 * @return inteiro sendo o novo pivô da partição seguinte
	 */
	public static int particionar(Termo aTermos[], int start, int end) {

		int esq, dir; // representam, respectivamente, o início e o final do vetor ou da partição dele
		Termo aux, pivot;
		pivot = aTermos[start]; // variavel pivo recebe a posição do início do vetor ou partição
		esq = start;
		dir = end;
		while (esq < dir) { // enquanto o início for menor que o final
			while (esq < end && !compRep(aTermos[esq].repeticao, pivot.repeticao)) {
				// avanço a posição da esquerda enquanto a propriedade repetiçao do termo da
				// esquerda for maior ou igual ao pivô
				esq++;
			}
			while (compRep(aTermos[dir].repeticao, pivot.repeticao)) {
				// avanço a posição da direita enquanto a propriedade repetiçao do termo da
				// direita for menor que o pivô
				dir--;
			}
			// se a esquerda(início) ainda for menor que a direita(final) depois disso,
			// troco uma pela outra
			if (esq < dir) {
				aux = aTermos[esq];
				aTermos[esq] = aTermos[dir];
				aTermos[dir] = aux;
			}
		}
		// quando a direita for maior ou igual a esquerda, termina o while
		aTermos[start] = aTermos[dir];// depois disso trocamos o termo no início pelo termo da direita
		aTermos[dir] = pivot;// o termo da direita recebe o termo do início, que era o pivô
		return dir;// o pivõ passa a ser a posição da direita, aonde ela parar, essa posição já
					// está ordenada
		// à esquerda dessa posição, todo mundo é maior. Já à direita, todo mundo é
		// menor
	}

	/**
	 * Implementando a funcao quicksort
	 * aTermos[] = vetor que esta sendo ordenado
	 * start = indice inicial
	 * end = indice final (ultima posição em que se ainda existem termos)
	 */
	public static void quicksort(Termo[] aTermos, int start, int end) {
		/*
		 * base da recursividade: quando o fim for maior ou igual ao início
		 * quando a particão resultar em apenas um elemento
		 */
		if (start < end) {
			int p = particionar(aTermos, start, end); // p é o novo pivô (essa posição já está em seu lugar)
			quicksort(aTermos, start, p - 1); // ou seja, iremos tentar ordenar as partições à esquesda
			quicksort(aTermos, p + 1, end); // e à diretia dela
		}
	}

	// #region StopWords

	public static void carregarStopWords(HashTable table) throws IOException {
		Scanner readder = new Scanner(new File(arqStopWords));
		String stopWord = "";

		while (readder.hasNextLine()) {
			stopWord = readder.nextLine();
			StopWord novoTermoPalavra = new StopWord(stopWord);

			table.inserir(stopWord, novoTermoPalavra);
		}

		readder.close();
	}

	// #endregion StopWords

	// #region Menu
	/**
	 * Metodo para limpar tela antes de aparecer primeiro menu
	 */
	public static void limparTela() {
		System.out.println("\n");
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void menu() throws IOException {
		// Scanner para leitura da opção
		Scanner sc = new Scanner(System.in);

		// variavel de controle para switch
		int opt = 0;

		// Opções do menu para o usuario.
		while (opt != 6) {
			System.out.println(
					"\n======================================\n		FROOGLE \n======================================\n");

			System.out.println("==> MENU PRINCIPAL <==");

			System.out.println("\n1. SALVAR TERMOS CATALOGADOS\n"
					+ "2. CONSULTAR TERMOS\n3. INSERIR NOVO TERMO\n"
					+ "4. CARREGAR NOVOS ARQUIVOS E CATALOGAR TERMOS\n" + "5. AJUDA\n" + "6. SAIR");

			System.out.print("Digite o numero da opção desejada:	");

			opt = sc.nextInt();// opt recebe a opção escolida

			switch (opt) {// Switch na opção do usuario

				case 1:
					// escrever os termos no vetor aTermos no arquivo de termos formatado
					escreverTermosNoArquivo();
					System.out.print("Termos salvos com sucesso!");
					break;

				case 2:
					// CONSULTA DE TERMOS ->

					// Sub-Menu para consulta de Termos ->
					System.out.println("\n==> ESCOLHA UMA OPCAO <==");
					System.out.println("1. Consultar um termo especifico\n2. Ver todos os termos:	");

					int subop = 0;// variavel para controle da opção do sub-menu do usuario

					subop = sc.nextInt();

					switch (subop) {// Switch na opção do usuario

						case 1:
							// BUSCA ESPECIFICA DE TERMOS ->
							String termoDigitado = null;// buscaTermo recebe o termo que o usuario deseja procurar

							System.out.print("\n=> Entre com o termo que deseja buscar:	");
							termoDigitado = sc.next();// buscaTermo recebe o termo procurado

							int termoPos = 0;
							termoPos = buscarTermo(termoDigitado);
							if (termoPos != -1) {// Procura e retorna o termo e seus atributos
								System.out.println(exibirTermo(termoPos));
							}

							else {
								System.out.print("\nTermo não cadastrado.");
							}

							break;

						case 2:
							mostrarTermos(aTermos);// mostrar todos os termos
							break;

						default:
							System.out.println("Por favor, entre com uma opção valida");
					}

					break;
				// FIM SUB-MENU

				case 3:
					/*-> O ARQUIVO CONTENDO OS TERMOS SÓ DEVE SER
					ATUALIZADO SE FOR ADD UM NOVO TERMO OU TODA VEZ QUE EXECUTAR O PROGRAMA?
					*/

					String novoTermo = null;
					System.out.print("Insira o termo que deseja adicionar:	");
					novoTermo = sc.next();

					inserirTermo(novoTermo);
					break;

				case 4:
					// ler e carregar arquivos catalogando os termos:
					limparVetor();
					carregarArquivos(carregarNomesDeArquivos());
					System.out.print("Termos catalogados!");
					break;

				case 5:
					System.out.print("Na opção 1, você pode salvar os termos catalogados em um arquivo de texto.\n\n");
					System.out.print("Na opção 2, você pode pesquisar algum termo que desejar.\n\n");
					System.out.print(
							"Na opçao 3, você pode inserir o novo termo. Lembre-se sempre de salvar, na opção 1, anstes de sair.\n\n");
					System.out.print(
							"Na opção 4, você pode catalogar termos de arquivos, você não precisa fazer isso após salvar os termos.\n"
									+ "Essa opção é necessária quando você adicionar novos arquivos de texto.");
					break;

				case 6:
					limparTela();
					System.out.println("\n\n==== OBRIGADO POR USAR O FROOGLE ==== > ==== VOLTE SEMPRE ====\n\n");
					break;

				default:
					System.out.println("Por favor, entre com uma opção valida.\n\n--\n\n");
					break;
			}

		} // FIM WHILE

		sc.close();// fecha scanner do Menu
	}
	// #endregion Menu.

	// Método de execução do programa->>

	// #region Main
	public static void main(String[] args) throws IOException {
		limparTela();
		// carregar termos catalogados no arquivo texto(termos.txt) para o vetor de
		// Termos.

		carregarStopWords(table);

		carregarTermosDoArq();

		// chamar Menu do usuario
		menu();

	}
	// #endregion Main
}// fim classe Main