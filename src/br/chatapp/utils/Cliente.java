package br.chatapp.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import br.chatapp.dao.Mensagem;
import javafx.application.Platform;

public class Cliente {
	private static Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	public static boolean conectar() {
		try {
			socket = new Socket("104.131.147.102", 5000);
			output = new ObjectOutputStream (socket.getOutputStream());
			return true;
		} catch (IOException e) {
			System.out.println("metodo Cliente.conectar()" + e.getMessage());
		}
		return false;
	}

	public static void clienteBackground() {
		try {
			input = new ObjectInputStream(socket.getInputStream());
			try{
				@SuppressWarnings("unchecked")
				ArrayList<Mensagem> listaServer = (ArrayList<Mensagem>) input.readObject();
				Platform.runLater(()-> Mensagem.adicionarTodasLista(listaServer));
			}catch (ClassNotFoundException e) {
				System.out.println("Cliente.Mensagem.adicionarTodasLista()" + e.getMessage());
			}
			
			while (true) {
				try{
					final Mensagem mensagemRecebida = (Mensagem) input.readObject();
					System.out.println(mensagemRecebida);
					Platform.runLater(()-> Mensagem.getLista().add(mensagemRecebida));
				}catch (ClassNotFoundException e) {
					System.out.println("metodo Cliente.clienteBackground()" + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("metodo Cliente.clienteBackground()" + e.getMessage());
		}

	}
	public static boolean enviarMensagemSocket(Mensagem mensagem) {
		try {
			output.writeObject(mensagem);
			output.reset();
			return true;
		} catch (IOException e) {
			System.out.println("metodo Cliente.enviarMensagemSocket()" + e.getMessage());
		}
		return false;
	}
	
	public static boolean enviarLoginUsuarioServidor(Mensagem mensagem) {
		try{
			output.writeObject(mensagem);
			return true;
		}catch (IOException e) {
			System.out.println("metodo Cliente.enviarUsuarioServidor()" + e.getMessage());
		}
		return false;
	}

	public static void deslogarClienteServidor(Mensagem mensagem){
		try{
			output.writeObject(mensagem);
		}catch (IOException e) {
			System.out.println("metodo Cliente.deslogarClienteServidor()" + e.getMessage());
		}
	}
	
	public static void fecharCliente() {
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			System.out.println("metodo Cliente.close()" + e.getMessage());
		}
	}
}
