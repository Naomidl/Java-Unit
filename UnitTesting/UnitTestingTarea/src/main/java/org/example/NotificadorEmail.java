package org.example;

public class NotificadorEmail {
    private EmailCliente emailCliente;

    public NotificadorEmail(EmailCliente emailCliente) {
        this.emailCliente = emailCliente;
    }

    public void notificar(String direccion, String mensaje) {
        if (direccion == null || direccion.isEmpty() || mensaje == null || mensaje.isEmpty()) {
            return;
        }
        emailCliente.enviar(direccion, mensaje);
    }

    public void notificarUrgente(String direccion, String mensaje) {
        if (direccion == null || direccion.isEmpty() || mensaje == null || mensaje.isEmpty()) {
            return;
        }
        String mensajeUrgente = "[URGENTE] " + mensaje;
        emailCliente.enviar(direccion, mensajeUrgente);
    }
}
