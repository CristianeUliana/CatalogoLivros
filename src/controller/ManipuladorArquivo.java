package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.Livro;



public class ManipuladorArquivo {

	private String filePath;
	private BufferedWriter buffWrite;
	private BufferedReader buffRead;
	
	public ManipuladorArquivo(String path) throws IOException {
		this.filePath = path;
	}
	
	private void generateFileManagement(boolean keepFile) {
		try {
			buffWrite = new BufferedWriter(new FileWriter(filePath, keepFile));
			buffRead = new BufferedReader(new FileReader(filePath));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void closeFileManagement() {
		try {
			buffWrite.close();
			buffRead.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("resource")
	public Livro escreverArquivo () {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o nome do livro: ");
		String nome = scanner.nextLine();
		System.out.println("Insira o autor: ");
		String autor = scanner.nextLine();
		System.out.println("Insira o ano da publicação: ");
		Integer ano = Integer.parseInt(scanner.nextLine());
		System.out.println("Insira a editora: ");
		String editora = scanner.nextLine();
		Livro livro = new Livro(nome, autor, ano, editora);
		generateFileManagement(true);
		try {
			buffWrite.append(nome + ";" + autor + ";" +  ano + ";" + editora);
			buffWrite.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				buffWrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				closeFileManagement();					
			}						
		}
		return livro;
	}
	
	
	
	
	public void atualizarArquivo(ArrayList<Livro> listaLivros) {
		generateFileManagement(false);
		try {
			for (int i=0; i<listaLivros.size(); i++) {
				String nome = listaLivros.get(i).getNome();
				String autor = listaLivros.get(i).getAutor();
				Integer ano = listaLivros.get(i).getAno();
				String editora = listaLivros.get(i).getEditora();
				String novoRegistro = String.format("%s;%s;%d;%s", nome, autor, ano, editora);
				buffWrite.append(novoRegistro);
				buffWrite.newLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			closeFileManagement();
		}
	}
	
	
	public ArrayList<Livro> lerArquivo() {
		String[] linha;
		String dados = "";
		ArrayList<Livro> listaLivros = new ArrayList<>();
		generateFileManagement(true);
		try { 
			while (true) {
				if (dados != null) {
					linha = dados.split(";");
					if(dados.length() > 1) {
						String nome = linha[0];
						String autor = linha[1];
						Integer ano = Integer.parseInt(linha[2]);
						String editora = linha[3];
						Livro livro = new Livro(nome, autor, ano, editora);
						listaLivros.add(livro);						
					}
				} else
					break;
				dados = buffRead.readLine();
			}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				closeFileManagement();
			}
		return listaLivros;
	}
}
