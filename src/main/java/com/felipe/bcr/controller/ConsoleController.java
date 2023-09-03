package com.felipe.bcr.controller;

import com.felipe.bcr.Main;
import com.felipe.bcr.cases.us01.US01Case01;
import com.felipe.bcr.cases.us01.US01Case02;
import com.felipe.bcr.cases.us04.*;
import com.felipe.bcr.cases.us02.*;
import com.felipe.bcr.entitys.Case;
import com.felipe.bcr.entitys.UserStory;

import java.util.Scanner;

public class ConsoleController {
    private Scanner scanner;

    public void startConsoleMenu(){
        scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("""
                Bienvenido al sistema de pruebas de Mercado Libre.\s
                Antes de comenzar le pedimos que ingrese sus credenciales para las pruebas que lo necesiten:\s
                (Si no desea poner sus credenciales, escriba cualquier cosa, pero tenga en cuenta que algunas pruebas no funcionarán)""");
        System.out.println("-> Ingrese su email de Mercado Libre: ");
        Main.setEmail(scanner.next());

        System.out.println("-> Ingrese su contraseña de Mercado Libre: ");
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
                        System.out.println("Estado de los casos:");
                        for (UserStory story : UserStory.values()) {
                            System.out.println("----- Historia de usuario " + story.toString() + " -----");

                            for (int start = (story.getStartCasesID() + 1); start <= (story.getStartCasesID() + story.getMaxCases()); start++) {
                                Case caseToTest = Case.getCaseByUserStoryAndID(story, start);

                                if (caseToTest != null){
                                    System.out.println(caseToTest.toString());
                                }else{
                                    System.out.println("[Caso #" + start + "] No se ha corrido este caso de prueba aún.");
                                }
                            }
                        }
                    }
                    case 3 -> {
                        System.out.println("Bye!");
                        Main.getDriver().quit();
                        isRunning = false;
                    }
                }
            } catch (Exception ex){
                System.out.println("Ingrese un valor valor entre 1 y 3");
                scanner.next();
            }
        }
    }

    private int chooseOption(){
        System.out.println("Ingresa una opcion: ");
        System.out.println("1. Correr un caso de prueba de manera individual");
        System.out.println("2. Ver el estado del casos probados en esta sesión");
        System.out.println("3. Cerrar la aplicacion");

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

        System.out.println("1. US01Case01");
        System.out.println("2. US01Case02");

        try{
            int caseSelected = scanner.nextInt();
            switch (caseSelected){
                case 1 -> US01Case01.run();
                case 2 -> US01Case02.run();

                default -> {
                    System.out.println("Ingrese un valor entre 1 y 2");
                    scanner.next();
                }
            }

            if (caseSelected >= 1 && caseSelected <= 2){
                System.out.println("Caso de prueba finalizado correctamente");

                Case caseToTest = Case.getCaseByUserStoryAndID(UserStory.US01, UserStory.US01.getStartCasesID() + caseSelected);
                System.out.println("Logs del caso de prueba #" + caseToTest.getId() +": ");
                caseToTest.end();
                System.out.println(caseToTest.getLogsAsString());
            }
        }catch (Exception ex){

        }
    };

    private void chooseCaseFromUserStory02(){
        System.out.println("Ingresa el caso de prueba que deseas correr: ");

        System.out.println("1. US02Case01");
        System.out.println("2. US02Case02");
        System.out.println("3. US02Case03");
        System.out.println("4. US02Case04");
        System.out.println("5. US02Case05");
        System.out.println("6. US02Case06");
        System.out.println("7. US02Case07");
        System.out.println("8. US02Case08");
        System.out.println("9. US02Case09");
        System.out.println("10. US02Case10");
        System.out.println("11. US02Case11");
        System.out.println("12. US02Case12");

        try{
            int caseSelected = scanner.nextInt();
            switch (caseSelected){
                case 1 -> US02Case01.run();
                case 2 -> US02Case02.run();
                case 3 -> US02Case03.run();
                case 4 -> US02Case04.run();
                case 5 -> US02Case05.run();
                case 6 -> US02Case06.run();
                case 7 -> US02Case07.run();
                case 8 -> US02Case08.run();
                case 9 -> US02Case09.run();
                case 10 -> US02Case10.run();
                case 11 -> US02Case11.run();
                case 12 -> US02Case12.run();

                default -> {
                    System.out.println("Ingrese un valor entre 1 y 12");
                    scanner.next();
                }
            }

            if (caseSelected >= 1 && caseSelected <= 12){
                System.out.println("Caso de prueba finalizado correctamente");

                Case caseToTest = Case.getCaseByUserStoryAndID(UserStory.US02, UserStory.US02.getStartCasesID() + caseSelected);
                System.out.println("Logs del caso de prueba #" + caseToTest.getId() +": ");
                caseToTest.end();
                System.out.println(caseToTest.getLogsAsString());
            }


        }catch (Exception ex){

        }
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

                    Case caseToTest = Case.getCaseByUserStoryAndID(UserStory.US04, UserStory.US04.getStartCasesID() + caseSelected);
                    caseToTest.end();
                    System.out.println(caseToTest.getLogsAsString());
                }
            }


        }catch (Exception ex){

        }
    };
}
