package cat.itacademy.presentation;

import cat.itacademy.console.AvailableInventoryPrinter;
import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.console.ConsoleUtils;
import cat.itacademy.console.UsedInventoryPrinter;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.dto.usedInventory.UsedEntityDTO;
import cat.itacademy.dto.usedInventory.UsedInventoryDTO;
import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EmptyListException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;
import cat.itacademy.model.*;
import cat.itacademy.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class EscapeRoomMenu {
    private Scanner sc;
    private InventoryService inventoryService;
    private RoomService roomService;
    private EscapeRoomService escapeRoomService;
    private ClueService clueService;
    private ItemService itemService;
    private AvailableInventoryDTO availableInventory;
    private CompleteInventoryDTO completeInvetory;
    private AvailableInventoryPrinter availablePrinter;
    private CompleteInventoryPrinter completePrinter;
    private RoomItemService roomItemService;
    private UsedInventoryPrinter usedInventoryPrinter;
    private UsedInventoryDTO usedInventory;



    public EscapeRoomMenu(Scanner sc) throws SQLException {
        this.sc = sc;
        this.inventoryService = new InventoryService();
        this.roomService = new RoomService();
        this.clueService = new ClueService();
        this.itemService = new ItemService();
        this.escapeRoomService = new EscapeRoomService();
        this.availablePrinter = new AvailableInventoryPrinter();
        this.completePrinter = new CompleteInventoryPrinter();
        this.roomItemService = new RoomItemService();
        this.usedInventoryPrinter = new UsedInventoryPrinter();
        refreshInventory();
    }
        private void refreshInventory() throws SQLException {
            this.availableInventory = inventoryService.getAvailableInventory();
            this.completeInvetory = inventoryService.getCompleteInventory();
            this.usedInventory = inventoryService.getUsedInventory();
    }

    public void showEscapeRoomMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            try{
                refreshInventory();
                System.out.println("🎪 GESTIÓN DE ESCAPE ROOMS");
                System.out.println("1. Mostrar Escape Rooms, Salas, Pistas y Objetos creados");
                System.out.println("2. Crear un nuevo Escape Room");
                System.out.println("3. Crear una nueva Sala");
                System.out.println("4. Crear una nueva Pista");
                System.out.println("5. Crear un nuevo Objeto de Decoración");
                System.out.println("6. Añadir Sala a Escape Room");
                System.out.println("7. Añadir Pista a Sala");
                System.out.println("8. Añadir Objeto a Sala");
                System.out.println("9. Retirar Sala de Escape Room");
                System.out.println("10. Retirar Pista de Sala");
                System.out.println("11. Retirar Objeto de Sala");
                System.out.println("0. Volver al Menú Principal");
                System.out.print("Selecciona una opción: ");

                int option = readOption();
                backToMain = processEscapeRoomOption(option);

            } catch (SQLException e) {
                System.out.println("Error en la base de dades: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                waitForUserInput();
            }
        }
    }

    private boolean processEscapeRoomOption(int option) throws SQLException {
        try {
            switch (option) {
                case 1:
                    showAllElements();
                    break;
                case 2:
                    createEscapeRoom();
                    break;
                case 3:
                    createRoom();
                    break;
                case 4:
                    createClue();
                    break;
                case 5:
                    createItem();
                    break;
                case 6:
                    addRoomToEscapeRoom();
                    break;
                case 7:
                    addClueToRoom();
                    break;
                case 8:
                    addItemToRoom();
                    break;
                case 9:
                    removeRoomFromEscapeRoom();
                    break;
                case 10:
                    removeClueFromRoom();
                    break;
                case 11:
                    removeItemFromRoom();
                    break;
                case 0:
                    return true;
                default:
                    System.out.println("❌ Opción no válida");
            }
    } catch (DuplicateException e) {
        System.out.println("❌ " + e.getMessage());
    } catch (InvalidAttributeException e) {
        System.out.println("❌ " + e.getMessage());
    } catch (NullObjectException e) {
        System.out.println("❌ " + e.getMessage());
    } catch (EmptyListException e) {
        System.out.println("❌ " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("❌ Error de base de datos: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("❌ Error: Debe ingresar un número válido");
    } catch (Exception e) {
        System.out.println("❌ Error inesperado: " + e.getMessage());
    }
        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void showAllElements() throws SQLException {
        System.out.println("\n📋 MOSTRANDO TODOS LOS ELEMENTOS CREADOS");
        System.out.printf(completePrinter.printCompleteInventory(completeInvetory));
    }

    private void createEscapeRoom() throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException  {
        System.out.println("\n🎪 CREAR NUEVO ESCAPE ROOM");
        System.out.print("Nombre del Escape Room: ");
        String name = sc.nextLine();
        EscapeRoom escapeRoom = new EscapeRoom(name);
        escapeRoomService.addEscapeRoom(escapeRoom);
    }

    private void createRoom() throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException{
        System.out.println("\n🚪 CREAR NUEVA SALA");
        System.out.print("Nombre de la sala: ");
        String name = sc.nextLine();
        System.out.print("Temática: ");
        String theme = sc.nextLine();
        System.out.print("Nivel de dificultad: ");
        int level = Integer.parseInt(sc.nextLine());

        Room room = new Room(name, theme, level);
        roomService.addRoom(room);
    }

    private void createClue() throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException{
        System.out.println("\n🔍 CREAR NUEVA PISTA");
        System.out.print("Nombre de la pista: ");
        String name = sc.nextLine();
        System.out.print("Temática: ");
        String theme = sc.nextLine();
        System.out.print("Descripción de la pista: ");
        String description = sc.nextLine();
        Clue clue = new Clue(name,theme, description );
        clueService.addClue(clue);
    }

    private void createItem() throws DuplicateException, InvalidAttributeException, NullObjectException, SQLException {
        System.out.println("\n🎨 CREAR NUEVO OBJETO DE DECORACIÓN");
        System.out.print("Nombre del objeto: ");
        String name = sc.nextLine();
        System.out.print("Material: ");
        String material = sc.nextLine();
        System.out.print("Cantidad: ");
        int quantity = Integer.parseInt(sc.nextLine());

        Item item  = new Item(name, material, quantity);
        itemService.addItem(item);
    }

    private void addRoomToEscapeRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➕ AÑADIR SALA A ESCAPE ROOM");
        System.out.println("\n Listado de Escape rooms disponibles");
        System.out.println(availablePrinter.printAvailableEscapeRooms(availableInventory));
        System.out.println("Inserte el número identificador del escape room al que quiere añadir una sala: ");
        int escapeRoomId = sc.nextInt();
        sc.nextLine();

        System.out.println("\n Listado de salas disponibles:");
        System.out.println(availablePrinter.printAvailableRooms(availableInventory));
        System.out.print("Inserte el número identificador de la sala que quiere añadir: ");
        int roomId = sc.nextInt();
        sc.nextLine();

        escapeRoomService.addRoomToEscapeRoom(escapeRoomId, roomId);
    }

    private void addClueToRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➕ AÑADIR PISTA A SALA");
        System.out.println("\n Listado de salas disponibles:");
        System.out.println(availablePrinter.printAvailableRooms(availableInventory));
        System.out.println("Inserte el número identificador de la sala a la que quiere añadir una pista: ");
        int roomID = sc.nextInt();
        sc.nextLine();

        System.out.println(" Listado de pistas disponibles");
        System.out.println(availablePrinter.printAvailableClues(availableInventory));
        System.out.println("Inserte el número identificador de la pista a añadir:");
        int clueID = sc.nextInt();
        sc.nextLine();

        roomService.addClueToRoom(roomID, clueID);
    }

    private void addItemToRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➕ AÑADIR OBJETO DE DECORACIÓN A SALA");
        System.out.println("\n Listado de salas disponibles:");
        System.out.println(availablePrinter.printAvailableRooms(availableInventory));
        System.out.println("Inserte el número identificador de la sala a la que quiere añadir un objeto: ");
        int roomID = sc.nextInt();
        sc.nextLine();

        System.out.println("\n Listado de obtetos de decoracion: ");
        System.out.println(availablePrinter.printAvailableItems(availableInventory));
        System.out.println("Insert el número identificador del objeto a añadir:");
        int itemId = sc.nextInt();
        sc.nextLine();

        System.out.println("Quantes unitats vols afegir? ");
        int quantity = sc.nextInt();
        sc.nextLine();

        RoomItem roomItem = new RoomItem(roomID, itemId, quantity);
        roomItemService.addItemToRoom(roomItem);
    }

    private void removeRoomFromEscapeRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➖ RETIRAR SALA DE ESCAPE ROOM");
        System.out.println("\n Listado de escape rooms i salas:");
        System.out.println("Listado de salas del escape room seleccionado:");
        System.out.println(usedInventoryPrinter.printUsedRooms(usedInventory));
        System.out.println("Inserte el número identificador del escape room: ");
        int escapeRoomId = sc.nextInt();
        sc.nextLine();
        System.out.println("Inserte el número identificador de la sala a eliminar: ");
        int roomId = sc.nextInt();
        sc.nextLine();

        escapeRoomService.removeRoomFromEscapeRoom(escapeRoomId, roomId);
    }

    private void removeClueFromRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➖ RETIRAR PISTA DE SALA");
        System.out.println("\n Listado de salas i pistas:");
        System.out.println(usedInventoryPrinter.printUsedClues(usedInventory));
        System.out.println("Inserte el número identificador de la sala: ");
        int roomID = sc.nextInt();
        sc.nextLine();
        System.out.println("Inserte el número identificador de la pista que desea retirar:");
        int clueId = sc.nextInt();
        sc.nextLine();

        roomService.removeClueFromRoom(roomID, clueId);
    }

    private void removeItemFromRoom() throws SQLException, EmptyListException, InvalidAttributeException {
        System.out.println("\n➖ RETIRAR OBJETO DE SALA");
        System.out.println("\n Listado de salas i objetos:");
        System.out.println(usedInventoryPrinter.printUsedItems(usedInventory));
        System.out.println("Inserta el número identificador de la sala:");
        int roomID = Integer.parseInt(sc.nextLine());

        System.out.println("Inserta el número identificador del objeto a retirar:");
        int itemID = Integer.parseInt(sc.nextLine());

        roomService.removeItemFromRoom(roomID, itemID);
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void waitForUserInput() {
        System.out.print("⏎ Presiona ENTER para continuar...");
        try {
            sc.nextLine();
        } catch (Exception e) {
        }
    }
}

