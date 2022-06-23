# Froogle

Apelidado de "Froogle" este projeto foi desenvolvido em grupos para a disciplina de "Algoritmos e Estruturas de Dados" (AED) cursada na PUC Minas Campus São Gabriel em Belo Horizonte, Minas Gerais, Brasil. 
Os membros do grupo são: 

- Diogo Marques (diogo-oos);

- Leonardo Mamede (xLyMzR);

- Lucas Amaral (lucasamaralgh);

- Lucas Lima (Lucas-San99).

-----------------------

No projeto trabalhamos habilidades como:

- Utilização e atualização de arquivos
- Estruturas de Dados
- Complexidade de algoritmos
- Algoritmos de ordenação

-----------------------

1ª etapa do projeto:

A partir da classe “Termo” já criada, nesta etapa, o grupo de trabalho deve:

    a) Carregar termos de um conjunto maior que 2 arquivos memória principal.
    b) Criar um mini aplicativo que permita consultar ou inserir novos termos.
    c) Salvar os termos em um arquivo, com a estrutura abaixo.
    d) Quando solicitado, exibir os termos ordenados decrescentemente por sua frequência.
    
Estrutura dos arquivos:

    TERMO
    IdTermo;Palavra;NúmeroDeOcorrências
    
-----------------------
    
2ª etapa do projeto:

Para futuramente permitir a busca por documentos que contenham palavras-chave, precisamos 
começar a criar um índice de palavras e documentos e associá-los. O índice consiste, inicialmente, em colocar 
numa lista todos os documentos em que um termo aparece. Observe as tarefas desta etapa:

    a) Criar a classe documento.
    b) Armazenar os termos em um vetor de tamanho muito grande. Cada termo agora terá uma lista de 
    documentos, onde ficarão os documentos em que ele aparece.
    c) Melhorar o procedimento da primeira etapa: o sistema agora deve registrar cada documento em um 
    objeto da classe criada. Ao ler um termo naquele documento, este documento deve ser inserido na lista 
    pertencente a este termo.
    d) Na leitura de cada documento, ignorar pronomes e preposições: eu, tu, ele, nós, vós, eles, que, de, para,
    até, antes, após, depois, com ... (pode incluir na lista o que você achar relevante). 
    e) Melhorar o mini aplicativo para mostrar todos os documentos em que uma palavra ocorre. 
    
Estrutura dos arquivos:

    TERMO
    IdTermo;Palavra;NúmeroDeOcorrências
    DOCUMENTO
    IdDoc;Titulo;URL
    STOPWORDS (palavras para ignorar)
    Palavra (uma por linha)


