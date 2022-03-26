package com.classJava;

public class Termo {
	
	public String palavra = null;
	public int repeticao=1, id=0;
	
	
	public Termo(String palavras, int repete, int identificador) {
		
		this.palavra= palavras;//atributo palavra recebe palavra referente ao termo
		this.repeticao = repete;//atributo repeticao recebe e atualiza a quantidade de vezes que certo termo aparece
		this.id = identificador;//atribuuto identificador recebe id referente ao termo sendo
	
	}
}