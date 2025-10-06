package cat.itacademy.presentation;

import cat.itacademy.console.CompleteInventoryPrinter;
import cat.itacademy.console.ConsoleUtils;
import cat.itacademy.dto.availableInventory.AvailableInventoryDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.model.Item;
import cat.itacademy.model.Room;
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



    public EscapeRoomMenu(Scanner sc) throws SQLException {
        this.sc = sc;
        this.inventoryService = new InventoryService();
        this.roomService = new RoomService();
        this.clueService = new ClueService();
        this.itemService = new ItemService();
        this.escapeRoomService = new EscapeRoomService();
        this.availableInventory = inventoryService.getAvailableInventory();
        this.completeInvetory = inventoryService.getCompleteInventory();

    }

    public void showEscapeRoomMenu() throws SQLException {
        boolean backToMain = false;

        while (!backToMain) {
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
        }
    }

    private boolean processEscapeRoomOption(int option) throws SQLException {
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
        ConsoleUtils.pressEnterToContinue(sc);
        return false;
    }

    private void showAllElements() throws SQLException {
        System.out.println("\n📋 MOSTRANDO TODOS LOS ELEMENTOS CREADOS");
        CompleteInventoryDTO inventory = inventoryService.getCompleteInventory();
        CompleteInventoryPrinter printer = new CompleteInventoryPrinter();
        System.out.printf(printer.printCompleteInventory(inventory));
    }

    private void createEscapeRoom() {
        System.out.println("\n🎪 CREAR NUEVO ESCAPE ROOM");
        System.out.print("Nombre del Escape Room: ");
        String name = sc.nextLine();
        EscapeRoom escapeRoom = new EscapeRoom(name);
        escapeRoomService.addEscapeRoom(escapeRoom);
    }

    private void createRoom() {
        System.out.println("\n🚪 CREAR NUEVA SALA");
        System.out.print("Nombre de la sala: ");
        String name = sc.nextLine();
        System.out.print("Temática: ");
        String theme = sc.nextLine();
        System.out.print("Nivel de dificultad: ");
        int level = Integer.parseInt(sc.nextLine());
        sc.nextLine();

        Room room = new Room(name, theme, level);
        roomService.addRoom(room);
    }

    private void createClue() {
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

    private void createItem() {
        System.out.println("\n🎨 CREAR NUEVO OBJETO DE DECORACIÓN");
        System.out.print("Nombre del objeto: ");
        String name = sc.nextLine();
        System.out.print("Material: ");
        String material = sc.nextLine();
        System.out.print("Cantidad: ");
        int quantity = Integer.parseInt(sc.nextLine());
        sc.nextLine();

        Item item  = new Item(name, material, quantity);
        itemService.addItem(item);
    }

    private void addRoomToEscapeRoom() throws SQLException {
        System.out.println("\n➕ AÑADIR SALA A ESCAPE ROOM");
        System.out.println("\n Listado de Escape rooms disponibles");
        System.out.println(escapeRoomService.getAllEscapeRooms());
        System.out.println("Inserte el número identificador del escaperoom al que quiere añadir una sala: ");
        int escapeRoomId = sc.nextInt();
        sc.nextLine();

        System.out.println("\n Listado de salas disponibles:");
        System.out.println(clueService);
        System.out.print("Inserte el número identificador de la sala que quiere añadir ");
        int roomId = sc.nextInt();
        sc.nextLine();

        escapeRoomService.addRoomToEscapeRoom(escapeRoomId, roomId);
    }

    private void addClueToRoom() throws SQLException {
        System.out.println("\n➕ AÑADIR PISTA A SALA");
        System.out.println("\n Listado de salas disponibles:");
        System.out.println(availableInventory.getAvailableClues());
        System.out.println("Inserte el número identificador de la sala a la que quiere añadir una pista: ");
        int roomID = sc.nextInt();
        sc.nextLine();

        System.out.println(" Listado de pistas diposnibles");
        System.out.println(availableInventory.getAvailableClues());
        System.out.println("Inserte el número identificador de la pista a añadir:");
        int clueID = sc.nextInt();
        sc.nextLine();

        roomService.addClueToRoom(roomID, clueID);
    }

    private void addItemToRoom() throws SQLException {
        System.out.println("\n➕ AÑADIR OBJETO DE DECORACIÓN A SALA");
        System.out.println("\n Listado de salas disponibles:");
        System.out.println(completeInvetory.getAllRoms());
        System.out.println("Inserte el número identificador de la sala a la que quiere añadir un objeto: ");
        int roomID = sc.nextInt();
        sc.nextLine();

        System.out.println("\n Listado de obtetos de decoracion: ");
        System.out.println(completeInvetory.getAllClues());
        System.out.println("Insert el número identificador del objeto a añadir:");
        int itemId = sc.nextInt();
        sc.nextLine();
    }

    private void removeRoomFromEscapeRoom() throws SQLException {
        System.out.println("\n➖ RETIRAR SALA DE ESCAPE ROOM");
        System.out.println("\n Listado de escape rooms:");
        System.out.println(escapeRoomService.getAllEscapeRooms());
        System.out.println("Inserte el número identificador del escape room: ");
        int escapeRoomId = sc.nextInt();
        sc.nextLine();
        System.out.println("Listado de salas del escape room selecionado:");
        System.out.println(escapeRoomService.getRoomsByEscapeRoom(escapeRoomId));
        System.out.println("Inserte el número identificador de la sala a eliminar: ");
        int roomId = sc.nextInt();
        sc.nextLine();
        escapeRoomService.removeRoomFromEscapeRoom(roomId);
    }

    private void removeClueFromRoom() throws SQLException {
        System.out.println("\n➖ RETIRAR PISTA DE SALA");
        System.out.println("\n Listado de salas:");
        System.out.println(roomService.getRoomsWithClues());
        System.out.println("Inserte el número identificador de la sala: ");
        int roomID = sc.nextInt();
        sc.nextLine();

        System.out.println("\nListado de pistas a eliminar de la sala");
        System.out.println(roomService.getCluesByRoomId(roomID));
        System.out.println("Inserte el número identificador de la pista que desea retirar:");
        int clueId = sc.nextInt();
        sc.nextLine();

        roomService.removeClueFromRoom(clueId);
    }

    private void removeItemFromRoom() throws SQLException {
        System.out.println("\n➖ RETIRAR OBJETO DE SALA");
        System.out.println("\n Listado de salas:");
        System.out.println(roomService.getRoomsWithClues());
        System.out.println("\nInserte el número identificador de la sala: ");
        int roomID = sc.nextInt();
        sc.nextLine();
        System.out.println("\nListado de objetos en la sala:");
        System.out.println(roomService.getItemByRoomId(roomID));
        System.out.println("Inserta el número identificador del objeto a retirar:");
        int itemID = sc.nextInt();
        roomService.removeItemFromRoom(itemID);
    }

    private int readOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

