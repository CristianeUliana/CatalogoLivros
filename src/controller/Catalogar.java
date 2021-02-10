package controller;

import java.io.IOException;
import java.util.*;

import entidades.Livro;


public class Catalogar {

	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Livro> listaLivros = new ArrayList<>();
	private ManipuladorArquivo manipuladorArquivo;
	

	public Catalogar() {
		try {
			this.manipuladorArquivo = new ManipuladorArquivo("D:\\Projetos_Pessoais\\CatalogoLivros\\ListaLivros.txt");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String mostrarMenu() {
					
		StringBuilder sb = new StringBuilder();
		sb.append("Digite o comando desejado: \n");
		sb.append("1 - Lista de livros \n");
		sb.append("2 - Inserir novo livro \n");
		sb.append("3 - Remover livro \n");
		sb.append("4 - Pesquisar livro \n");
		sb.append("0 - Sair \n");
				
		System.out.println(sb.toString());
		return scanner.nextLine();
	}

	
	
	public void iniciar() {
		listaLivros.addAll(manipuladorArquivo.lerArquivo());	
		String opcao = mostrarMenu();
		
		while (!opcao.equals("0")) {
			switch (opcao) {
			case "1":
				System.out.println(listaLivros);
				opcao = mostrarMenu();
				break;
			case "2":
				Livro novoLivro = manipuladorArquivo.escreverArquivo();
				if (novoLivro != null) {
					listaLivros.add(novoLivro);
				}
				opcao = mostrarMenu();
				break;
			case "3":
				System.out.println("Insira o nome do livro que deseja excluir: ");
				String nomeExcluir = scanner.nextLine();
				Optional<Livro> selectedExcluir = listaLivros.stream().filter(livro -> livro.getNome().equals(nomeExcluir)).findFirst();
				listaLivros.remove(selectedExcluir.get());
				manipuladorArquivo.atualizarArquivo(listaLivros);
				opcao = mostrarMenu();
				break;
			case "4":
				System.out.println("Insira o nome do livro que deseja procurar: ");
				String nomeProcurar = scanner.nextLine();
				Optional<Livro> selectedPesquisar = listaLivros.stream().filter(livro -> livro.getNome().equals(nomeProcurar)).findFirst();
				Integer indiceLivroPesquisar = listaLivros.indexOf(selectedPesquisar.get());
				System.out.println(listaLivros.get(indiceLivroPesquisar));
				opcao = mostrarMenu();
				break;
			}
		}
	
		System.out.println("Sistema finalizado");
	}
}
