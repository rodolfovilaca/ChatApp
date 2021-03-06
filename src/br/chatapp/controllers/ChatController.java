package br.chatapp.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;

public class ChatController {
    public boolean enviarMensagem(String texto, Usuario usuario) {
    	Mensagem mensagem = new Mensagem(texto, usuario);
    	mensagem.setHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    	boolean enviado = mensagem.enviar();
    	return enviado;
    }
}
