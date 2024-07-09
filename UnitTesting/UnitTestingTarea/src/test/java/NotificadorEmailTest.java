import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.EmailCliente;
import org.example.NotificadorEmail;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificadorEmailTest {

    @Mock
    private EmailCliente emailClienteMock;

    @Test
    public void testNotificar() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("naomidl@test.com", "Bienvenido");

        verify(emailClienteMock).enviar("naomidl@test.com", "Bienvenido");
    }

    @Test
    public void testNotificarConDireccionVacia() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("", "Mensaje");
        verify(emailClienteMock, times(0)).enviar(anyString(), anyString());
    }

    @Test
    public void testNotificarConMensajeNulo() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar("administrador@gerencia.com", null);
        verify(emailClienteMock, times(0)).enviar(anyString(), anyString());
    }

    @Test
    public void testNotificadorMensajeLargo() {
        String direccionLarga = "usuario.con.nombre.muy.largo@example.com";
        String mensajeLargo = "El correo es muy largo, intentelo por favor." +
                "Envielo nuevamente con las correcciones";
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificar(direccionLarga, mensajeLargo);

        // Verificar que emailClienteMock.enviar se llamó con los argumentos correctos
        verify(emailClienteMock).enviar(direccionLarga, mensajeLargo);
    }

    @Test
    public void testNotificarUrgente() {
        NotificadorEmail notificador = new NotificadorEmail(emailClienteMock);
        notificador.notificarUrgente("naomidl@test.com.com", "Contestar lo antes posible");

        verify(emailClienteMock).enviar("naomidl@test.com.com", "[URGENTE] Contestar lo antes posible");
    }
}
