package br.chatapp.controllers;

import br.chatapp.dao.Mensagem;
import br.chatapp.dao.Usuario;
import javafx.collections.ObservableList;

public class ChatController {

    public boolean enviarMensagem(String texto, Usuario usuario) {
        //TODO
    	Mensagem mensagem = new Mensagem(texto,usuario);
    	boolean enviado = mensagem.enviar();
    	return enviado;
    }

    public ObservableList<Mensagem> carregarMensagens() {
        // TODO
        // return new ArrayList<Mensagem>();
        return Mensagem.getMensagens();
    }

}
