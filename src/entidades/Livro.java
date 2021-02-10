package entidades;

public class Livro {
	private String nome;
	private String autor;
	private Integer ano;
	private String editora;
	
	public Livro(String nome, String autor, Integer ano, String editora) {
		this.nome = nome;
		this.autor = autor;
		this.ano = ano;
		this.editora = editora;
	}
	
	public String toString() {
		return String.format("\nLivro: %s - Autor: %s - Ano: %d - Editora: %s", nome, autor, ano, editora);
		
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	
	
	
}
