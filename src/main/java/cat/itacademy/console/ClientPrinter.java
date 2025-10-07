package cat.itacademy.console;

import cat.itacademy.model.Client;

import java.util.List;

public class ClientPrinter {

    public String printClients(List<Client> clients) {
        if (clients.isEmpty()) {
            return "No hay clientes registrados.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- CLIENTES REGISTRADOS (").append(clients.size()).append(") ---\n");

        for (Client client : clients) {
            sb.append(printClient(client));
        }

        return sb.toString();
    }

    public String printClient(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("• ID: ").append(client.getId())
                .append(" | Nombre: ").append(client.getUserName())
                .append(" | Email: ").append(client.getEmail())
                .append(" | Teléfono: ").append(client.getPhone())
                .append(" | Notificaciones: ").append(client.isAcceptsNotifications() ? "Sí" : "No")
                .append("\n");
        return sb.toString();
    }

    public String printClientsCompact(List<Client> clients) {
        if (clients.isEmpty()) {
            return "No hay clientes registrados.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- CLIENTES (").append(clients.size()).append(") ---\n");

        for (Client client : clients) {
            sb.append("• ID: ").append(client.getId())
                    .append(" | ").append(client.getUserName())
                    .append(" | ").append(client.getEmail())
                    .append("\n");
        }

        return sb.toString();
    }

    public String printClientDetails(Client client) {
        if (client == null) {
            return "Cliente no encontrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== DETALLES DEL CLIENTE ===\n")
                .append("• ID: ").append(client.getId()).append("\n")
                .append("• Nombre: ").append(client.getUserName()).append("\n")
                .append("• Email: ").append(client.getEmail()).append("\n")
                .append("• Teléfono: ").append(client.getPhone()).append("\n")
                .append("• Acepta notificaciones: ").append(client.isAcceptsNotifications() ? "Sí" : "No").append("\n");

        return sb.toString();
    }
}
