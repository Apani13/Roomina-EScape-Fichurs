package cat.itacademy.console;

import cat.itacademy.dto.availableInventory.*;

public class AvailableInventoryPrinter {
    StringBuilder sb = new StringBuilder();

    public AvailableInventoryPrinter() {}

    public String printAvailableInventory(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO ACTUALIZADO ===\n");
        sb.append("Cantidad disponible de cada elemento:\n\n");
        sb.append(printAvailableEscapeRooms(inventory));
        sb.append("\n");
        sb.append(printAvailableRooms(inventory));
        sb.append("\n");
        sb.append(printAvailableClues(inventory));
        sb.append("\n");
        sb.append(printAvailableItems(inventory));

        return sb.toString();
    }


    public String printAvailableEscapeRooms(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ESCAPE ROOMS CREADOS (").append(inventory.getAvailableEscapeRooms().size()).append(") ---\n");
        for (AvailableEscapeRoomDTO escapeRoom : inventory.getAvailableEscapeRooms()) {
            sb.append("• ").append("Id: ").append( escapeRoom.getId()).append("| Nombre: ").append(escapeRoom.getName()).append("\n");
        }
        return sb.toString();
    }

    public String printAvailableRooms(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- SALAS DISPONIBLES (").append(inventory.getAvailableRooms().size()).append(") ---\n");
        for (AvailableRoomDTO room : inventory.getAvailableRooms()) {
            sb.append("• ").append("Id: ").append( room.getId()).append("| ").append(room.getName()).append(" | Temática: ").append(room.getTheme()).append("\n");
        }
        return sb.toString();
    }

    public String printAvailableInventoryValue(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== VALOR DEL INVENTARIO DISPONIBLE ===\n\n");

        double totalValue = 0;

        sb.append("Salas disponibles: ").append(inventory.getAvailableRooms().size()).append("\n");
        for (AvailableRoomDTO room : inventory.getAvailableRooms()) {
            sb.append("• ").append(room.getName()).append(" | ").append(room.getPrice()).append("€\n");
            totalValue += room.getPrice();
        }

        sb.append("\nPistas disponibles: ").append(inventory.getAvailableClues().size()).append("\n");
        for (AvailableClueDTO clue : inventory.getAvailableClues()) {
            sb.append("• ").append(clue.getName()).append(" | ").append(clue.getPrice()).append("€\n");
            totalValue += clue.getPrice();
        }

        sb.append("\nObjetos disponibles: ").append(inventory.getAvailableItems().size()).append(" tipos\n");
        for (AvailableItemDTO item : inventory.getAvailableItems()) {
            double itemTotal = item.getPrice() * item.getStock();
            sb.append("• ").append(item.getName()).append(" | ").append(item.getStock()).append(" unidades | ")
                    .append(itemTotal).append("€\n");
            totalValue += itemTotal;
        }

        sb.append("\nVALOR TOTAL INVENTARIO DISPONIBLE: ").append(totalValue).append(" €\n");

        return sb.toString();
    }

    public String printAvailableClues(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- PISTAS DISPONIBLES (").append(inventory.getAvailableClues().size()).append(") ---\n");
        for (AvailableClueDTO clue : inventory.getAvailableClues()) {
            sb.append("• ").append("Id: ").append( clue.getId()).append("| ").append(clue.getName()).append(" | Temática: ").append(clue.getTheme()).append("\n");
        }
        return sb.toString();
    }

    public String printAvailableItems(AvailableInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- OBJETOS DISPONIBLES (").append(inventory.getTotalItemsUnits()).append(" unidades) ---\n");
        for (AvailableItemDTO item : inventory.getAvailableItems()) {
            sb.append("• ").append("Id: ").append( item.getId()).append("| ").append(item.getName())
                    .append(" | Cantidad: ").append(item.getStock()).append("\n");
        }
        return sb.toString();
    }

}