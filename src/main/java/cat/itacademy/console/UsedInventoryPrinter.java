package cat.itacademy.console;

import cat.itacademy.dto.usedInventory.*;

public class UsedInventoryPrinter {

    public String printUsedInventory(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO EN USO ===\n");
        sb.append("Elementos actualmente en uso:\n\n");
        sb.append(printUsedEscapeRooms(inventory));
        sb.append("\n");
        sb.append(printUsedRooms(inventory));
        sb.append("\n");
        sb.append(printUsedClues(inventory));
        sb.append("\n");
        sb.append(printUsedItems(inventory));
        sb.append("\n");
        sb.append(printUsedSummary(inventory));

        return sb.toString();
    }

    public String printUsedEscapeRooms(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ESCAPE ROOMS EN USO (").append(inventory.getUsedEscapeRooms().size()).append(") ---\n");
        for (UsedEscapeRoomDTO escapeRoom : inventory.getUsedEscapeRooms()) {
            sb.append("• ").append("Id: ").append(escapeRoom.getId()).append(" | ")
                    .append(escapeRoom.getName())
                    .append(" | Salas: ").append(escapeRoom.getTotalRooms())
                    .append(" | Tickets vendidos: ").append(escapeRoom.getTotalTicketsSold()).append("\n");
        }
        return sb.toString();
    }

    public String printUsedRooms(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- SALAS EN USO (").append(inventory.getUsedRooms().size()).append(") ---\n");
        for (UsedRoomDTO room : inventory.getUsedRooms()) {
            sb.append("• ")
                    .append("ER ID: ").append(room.getEscapeRoomId()).append(" | ")
                    .append(room.getEscapeRoomName()).append(" | ")
                    .append("Sala ID: ").append(room.getId()).append(" | ")
                    .append(room.getName())
                    .append("\n");
        }
        return sb.toString();
    }

    public String printUsedClues(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- PISTAS EN USO (").append(inventory.getUsedClues().size()).append(") ---\n");
        for (UsedClueDTO clue : inventory.getUsedClues()) {
            sb.append("• ")
                    .append("Sala ID: ").append(clue.getRoomId()).append(" | ")
                    .append(clue.getRoomName()).append(" | ")
                    .append("Pista ID: ").append(clue.getId()).append(" | ")
                    .append(clue.getName())
                    .append("\n");
        }
        return sb.toString();
    }

    public String printUsedItems(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- OBJETOS EN USO (").append(inventory.getTotalUsedItems()).append(" unidades) ---\n");
        for (UsedItemDTO item : inventory.getUsedItems()) {
            sb.append("• ")
                    .append("Sala ID: ").append(item.getRoomId()).append(" | ")
                    .append(item.getRoomName()).append(" | ")
                    .append("Objeto ID: ").append(item.getId()).append(" | ")
                    .append(item.getName())
                    .append("\n");
        }
        return sb.toString();
    }

    public String printUsedSummary(UsedInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESUMEN INVENTARIO EN USO ===\n");
        sb.append("• Escape Rooms en uso: ").append(inventory.getUsedEscapeRooms().size()).append(" unidades\n");
        sb.append("• Salas en uso: ").append(inventory.getUsedRooms().size()).append(" unidades\n");
        sb.append("• Pistas en uso: ").append(inventory.getUsedClues().size()).append(" unidades\n");
        sb.append("• Objetos en uso: ").append(inventory.getTotalUsedItems()).append(" unidades\n");
        sb.append("• Total elementos en uso: ").append(
                inventory.getUsedEscapeRooms().size() + inventory.getUsedRooms().size() +
                        inventory.getUsedClues().size() + inventory.getTotalUsedItems()
        ).append(" unidades\n");

        return sb.toString();
    }
}


