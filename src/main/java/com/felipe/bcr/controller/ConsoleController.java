package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import com.felipe.bcr.cases.us04.*;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.UserStory;

import java.util.Scanner;

public class ConsoleController {
    private Scanner scanner;

    public void startConsoleMenu(){
        scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Bienvenido al sistema de pruebas de Mercado Libre, " +
                "antes de comenzar le pedimos que ingrese sus credenciales para las pruebas que lo necesiten:");
        System.out.println("Ingrese su email de Mercado Libre: ");
        Main.setEmail(scanner.next());

        System.out.println("Ingrese su contraseña de Mercado Libre: ");
        Main.setPassword(scanner.next());

        while (isRunning){
            try {
                int option = chooseOption();
                switch (option) {
                    case 1 -> {
                        int userStory = chooseUserStory();

                        try {
                            switch (userStory) {
                                case 1 -> chooseCaseFromUserStory01();
                                case 2 -> chooseCaseFromUserStory02();
                                case 3 -> chooseCaseFromUserStory03();
                                case 4 -> System.out.println("Volviendo para atrás...");

                                default -> {
                                    System.out.println("Ingrese un valor entre 1 y 4");
                                    scanner.next();
                                }
                            }
                        } catch (Exception ex){
                            System.out.println("Ingrese un valor entre 1 y 4");
                            scanner.next();
                        }
                    }
                    case 2 -> {
                        System.out.println("Bye!");
                        isRunning = false;
                    }
                }
            } catch (Exception ex){
                System.out.println("Ingrese un valor valor entre 1 y 2");
                scanner.next();
            }
        }
    }

    private int chooseOption(){
        System.out.println("Ingresa una opcion: ");
        System.out.println("1. Correr un caso de prueba de manera individual");
        System.out.println("2. Cerrar la aplicacion");

        return scanner.nextInt();
    };

    private int chooseUserStory(){
        System.out.println("Ingresa la historia de usuario para ver sus casos: ");
        System.out.println("1. US01");
        System.out.println("2. US02");
        System.out.println("3. US04");
        System.out.println("4. Volver para atrás");

        return scanner.nextInt();
    };

    private void chooseCaseFromUserStory01(){
        System.out.println("Ingresa el caso de prueba que deseas correr: ");

    };

    private void chooseCaseFromUserStory02(){
        System.out.println("Ingresa el caso de prueba que deseas correr: ");

    };

    private void chooseCaseFromUserStory03(){
        System.out.println("Ingresa el caso de prueba que deseas correr: ");

        System.out.println("1. US04Case01");
        System.out.println("2. US04Case02");
        System.out.println("3. US04Case03");
        System.out.println("4. US04Case04");
        System.out.println("5. US04Case05");
        System.out.println("6. US04Case06");
        System.out.println("7. US04Case07");
        System.out.println("8. US04Case08");
        System.out.println("9. US04Case09");
        System.out.println("10. US04Case10");

        try{
            int caseSelected = scanner.nextInt();
            switch (caseSelected){
                case 1 -> US04Case01.run();
                case 2 -> US04Case02.run();
                case 3, 6 -> System.out.println("Este caso no se encuentra disponible");
                case 4 -> US04Case04.run();
                case 5 -> US04Case05.run();
                case 7 -> US04Case07.run();
                case 8 -> US04Case08.run();
                case 9 -> US04Case09.run();
                case 10 -> US04Case10.run();

                default -> {
                    System.out.println("Ingrese un valor entre 1 y 10");
                    scanner.next();
                }
            }

            if (caseSelected >= 1 && caseSelected <= 10){
                if (caseSelected != 3 && caseSelected != 6){
                    System.out.println("Caso de prueba finalizado correctamente");

                    Case caseToTest = Case.getCaseByUserStoryAndID(UserStory.US04, caseSelected);
                    caseToTest.end();
                    System.out.println(caseToTest.getLogsAsString());
                }
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
    };
}
