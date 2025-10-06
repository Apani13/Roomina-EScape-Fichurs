package cat.itacademy.console;

import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.availableInventory.AvailableItemDTO;

public class AvailableInventoryPrinter {

    public static String printAvailableInventory(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO ACTUALIZADO ===\n");
        sb.append("Cantidad disponible de cada elemento:\n\n");

        sb.append("--- SALAS DISPONIBLES (").append(inventory.getAvailableRooms().size()).append(") ---\n");
        for (AvailableRoomDTO room : inventory.getAvailableRooms()) {
            sb.append("• ").append(room.getName()).append(" | Temática: ").append(room.getTheme()).append("\n");
        }

        sb.append("\n--- PISTAS DISPONIBLES (").append(inventory.getAvailableClues().size()).append(") ---\n");
        for (AvailableClueDTO clue : inventory.getAvailableClues()) {
            sb.append("• ").append(clue.getName()).append(" | Temática: ").append(clue.getTheme()).append("\n");
        }

        sb.append("\n--- OBJETOS DISPONIBLES (").append(inventory.getTotalItemsUnits()).append(" unidades) ---\n");
        for (AvailableItemDTO item : inventory.getAvailableItems()) {
            sb.append("• ").append(item.getName())
                    .append(" | Cantidad: ").append(item.getQuantity()).append("\n");
        }
        return sb.toString();
    }
}