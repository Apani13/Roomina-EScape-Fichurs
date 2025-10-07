package cat.itacademy.console;

import cat.itacademy.dto.completeInventory.*;

public class CompleteInventoryPrinter {

    public String printCompleteInventory(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTARIO COMPLETO ===\n");
        sb.append("Listado de todos los elementos en stock:\n\n");
        sb.append(printAllEscapeRoms(inventory));
        sb.append("\n");
        sb.append(printAllRooms(inventory));
        sb.append("\n");
        sb.append(printAllItems(inventory));
        sb.append("\n");
        sb.append(printAllClues(inventory));
        sb.append("\n");
        sb.append(printInventorySummary(inventory));
        return sb.toString();
    }

    public String printAllEscapeRoms(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ESCAPE ROOMS CREADOS (").append(inventory.getAvailableEscapeRooms().size()).append(") ---\n");
        for (EntityEscapeRoomDTO escapeRoom : inventory.getAvailableEscapeRooms()) {
            sb.append("• ").append("Id: ").append( escapeRoom.getId()).append("| Nombre: ").append(escapeRoom.getName()).append("\n");
        }
        return sb.toString();
    }

    public String printEconomicValue(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== VALOR ECONÓMICO DEL INVENTARIO ===\n");
        sb.append("Valor económico de los elementos en stock:\n\n");
        sb.append(printRoomsValue(inventory));
        sb.append("\n");
        sb.append(printItemsValue(inventory));
        sb.append("\n");
        sb.append(printCluesValue(inventory));
        sb.append("\n");
        sb.append(printEconomicSummary(inventory));

        return sb.toString();
    }

    public String printAllRooms(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- SALAS (").append(inventory.getAllRoms().size()).append(") ---\n");
        for (EntityRoomDTO room : inventory.getAllRoms()) {
            sb.append("• ").append("Id: ").append(room.getId()).append(" | ")
                    .append(room.getName()).append(" | Temática: ").append(room.getTheme()).append("\n");
        }
        return sb.toString();
    }

    public String printAllItems(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        int totalItemUnits = inventory.getAllItems().stream()
                .mapToInt(EntityItemDTO::getQuantity)
                .sum();

        sb.append("--- OBJETOS (").append(totalItemUnits).append(" unidades) ---\n");
        for (EntityItemDTO item : inventory.getAllItems()) {
            sb.append("• ").append("Id: ").append(item.getId()).append(" | ")
                    .append(item.getName()).append(" | Cantidad: ").append(item.getQuantity()).append("\n");
        }
        return sb.toString();
    }

    public String printAllClues(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- PISTAS (").append(inventory.getAllClues().size()).append(") ---\n");
        for (EntityClueDTO clue : inventory.getAllClues()) {
            sb.append("• ").append("Id: ").append(clue.getId()).append(" | ")
                    .append(clue.getName()).append(" | Temática: ").append(clue.getTheme()).append("\n");
        }
        return sb.toString();
    }

    public String printRoomsValue(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- SALAS (").append(inventory.getAllRoms().size()).append(") ---\n");
        for (EntityRoomDTO room : inventory.getAllRoms()) {
            sb.append("• ").append(room.getName())
                    .append(" | Valor: ").append(room.getPrice()).append("€\n");
        }
        return sb.toString();
    }

    public String printItemsValue(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        int totalItemUnits = inventory.getAllItems().stream()
                .mapToInt(EntityItemDTO::getQuantity)
                .sum();

        sb.append("--- OBJETOS (").append(totalItemUnits).append(" unidades) ---\n");
        for (EntityItemDTO item : inventory.getAllItems()) {
            double totalItemPrice = item.getPrice() * item.getQuantity();
            sb.append("• ").append(item.getName())
                    .append(" | Cantidad: ").append(item.getQuantity())
                    .append(" | Valor unidad: ").append(item.getPrice()).append("€")
                    .append(" | Total: ").append(totalItemPrice).append("€\n");
        }
        return sb.toString();
    }

    public String printCluesValue(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("--- PISTAS (").append(inventory.getAllClues().size()).append(") ---\n");
        for (EntityClueDTO clue : inventory.getAllClues()) {
            sb.append("• ").append(clue.getName())
                    .append(" | Valor: ").append(clue.getPrice()).append("€\n");
        }
        return sb.toString();
    }

    public String printInventorySummary(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        int totalItemUnits = inventory.getAllItems().stream()
                .mapToInt(EntityItemDTO::getQuantity)
                .sum();

        sb.append("=== RESUMEN INVENTARIO ===\n");
        sb.append("• Salas: ").append(inventory.getAllRoms().size()).append(" unidades\n");
        sb.append("• Objetos: ").append(totalItemUnits).append(" unidades\n");
        sb.append("• Pistas: ").append(inventory.getAllClues().size()).append(" unidades\n");
        sb.append("• Total elementos: ").append(inventory.getAllRoms().size() + totalItemUnits + inventory.getAllClues().size()).append(" unidades\n");

        return sb.toString();
    }

    public String printEconomicSummary(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();

        int totalItemUnits = inventory.getAllItems().stream()
                .mapToInt(EntityItemDTO::getQuantity)
                .sum();

        sb.append("=== RESUMEN VALOR INVENTARIO ===\n");
        sb.append("• Salas: ").append(inventory.getAllRoms().size()).append(" unidades\n");
        sb.append("• Objetos: ").append(totalItemUnits).append(" unidades\n");
        sb.append("• Pistas: ").append(inventory.getAllClues().size()).append(" unidades\n");
        sb.append("• VALOR TOTAL INVENTARIO: ").append(inventory.getTotalInventoryPrice()).append(" €\n");

        return sb.toString();
    }
}

