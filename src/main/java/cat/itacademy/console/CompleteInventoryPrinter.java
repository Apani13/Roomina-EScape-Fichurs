package cat.itacademy.console;

import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.dto.completeInventory.EntityItemDTO;
import cat.itacademy.dto.completeInventory.EntityClueDTO;

public class CompleteInventoryPrinter {

    public String printCompleteInventory(CompleteInventoryDTO inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== VALOR DEL INVENTARIO ===\n");
        sb.append("Valor económico de los elementos en stock:\n");

        sb.append("\n--- SALAS (").append(inventory.getAllRoms().size()).append(") ---\n");
        for (EntityRoomDTO room : inventory.getAllRoms()) {
            sb.append("• ").append(room.getName())
                    .append(" | Valor: ").append(room.getPrice()).append("€\n");
        }

        int totalItemUnits = inventory.getAllItems().stream()
                .mapToInt(EntityItemDTO::getQuantity)
                .sum();

        sb.append("\n--- OBJETOS (").append(totalItemUnits).append(" unidades) ---\n");
        for (EntityItemDTO item : inventory.getAllItems()) {
            double totalItemPrice = item.getPrice() * item.getQuantity();
            sb.append("• ").append(item.getName())
                    .append(" | Cantidad: ").append(item.getQuantity())
                    .append(" | Valor unidad: ").append(item.getPrice()).append("€")
                    .append(" | Total: ").append(totalItemPrice).append("€\n");
        }

        sb.append("\n--- PISTAS (").append(inventory.getAllClues().size()).append(") ---\n");
        for (EntityClueDTO clue : inventory.getAllClues()) {
            sb.append("• ").append(clue.getName())
                    .append(" | Valor: ").append(clue.getPrice()).append("€\n");
        }

        sb.append("\n=== RESUMEN VALOR INVENTARIO ===\n");
        sb.append("• Salas: ").append(inventory.getAllRoms().size()).append(" unidades\n");
        sb.append("• Objetos: ").append(totalItemUnits).append(" unidades\n");
        sb.append("• Pistas: ").append(inventory.getAllClues().size()).append(" unidades\n");
        sb.append("• VALOR TOTAL INVENTARIO: ").append(inventory.getTotalInventoryPrice()).append(" €\n");

        return sb.toString();
    }
}
