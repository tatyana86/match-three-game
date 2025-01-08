package ru.krivonogova.game.GameProcess;

import java.util.Scanner;

import ru.krivonogova.game.GameBoard.Field.Grid;
import ru.krivonogova.game.GameProcess.Observer.SimpleObserver;
import ru.krivonogova.game.GameProcess.Strategy.CombinationDetectionStrategy;
import ru.krivonogova.game.GameProcess.Strategy.MoveAvailabilityStrategy;
import ru.krivonogova.game.GameProcess.Strategy.SimpleCombinationDetectionStrategy;
import ru.krivonogova.game.GameProcess.Strategy.SimpleMoveAvailabilityStrategy;
import ru.krivonogova.game.GameProcess.Strategy.SimpleUserInputValidationStrategy;
import ru.krivonogova.game.GameProcess.Strategy.UserInputValidationStrategy;

public class Game {
	private Grid grid;
	private MoveAvailabilityStrategy moveAvailabilityStrategy;
    private UserInputValidationStrategy inputValidationStrategy;
    private CombinationDetectionStrategy combinationDetectionStrategy;
    private SimpleObserver simpleObserver;
    private int moveCount;
	
    public Game() {
        this.grid = new Grid();
        this.moveAvailabilityStrategy = new SimpleMoveAvailabilityStrategy();
        this.inputValidationStrategy = new SimpleUserInputValidationStrategy();
        this.combinationDetectionStrategy = new SimpleCombinationDetectionStrategy();
        this.simpleObserver = new SimpleObserver();
        this.moveCount = 0;
    }
	
    public void start() {
        System.out.println("Игра началась. Для выхода введите '0'. Инициируем поле...");
        grid.displayField();
        System.out.println("Сделайте ход (например 'D3 D4'), чтобы получилось 3 элемента в ряд:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        while (! input.equals("0")) {
            // проверка валидности ввода (что можно распарсить)
            if (inputValidationStrategy.isValidInput(input)) {
                String[] positions = input.split(" ");
                int fromRow = getRow(positions[0]);
                int fromCol = getCol(positions[0]);
                int toRow = getRow(positions[1]);
                int toCol = getCol(positions[1]);
                
                // проверка доступности хода (что перемещаются соседние элементы)
                if (moveAvailabilityStrategy.isMoveAvailable(grid, fromRow, fromCol, toRow, toCol)) {
                	// проверка, удалось ли выполнить ход (обнаружение комбинации)
                	if (combinationDetectionStrategy.isCombinationDetect(grid, fromRow, fromCol, toRow, toCol)) { 
                		moveCount++;
                		System.out.println("Комбинация найдена!" + "\n" + "Текущее поле:");
                        grid.displayField();                                              
                                           
                        simpleObserver.updateStatistics(moveCount, input);
                        
                    	System.out.println("Сделайте новый ход:");
                        input = scanner.nextLine();
                    } else {
                        System.out.println("Ход не образует три в ряд, попробуйте снова:");
                        input = scanner.nextLine();
                    }
                } else {
                    System.out.println("Такой ход невозможен, введите еще раз:");
                    input = scanner.nextLine();
                }
            } else {
                System.out.println("Недопустимый ввод, введите еще раз:");
                input = scanner.nextLine();
            }
        }
        scanner.close();
        simpleObserver.sendReport();
        System.out.println("Вы вышли из игры.");
    }
    
    private int getRow(String position) {
        return Integer.parseInt(position.substring(1)) - 1; // вычитаем 1, чтобы получить индекс
    }

    private int getCol(String position) {
        return position.charAt(0) - 'A'; // A -> 0, B -> 1, ...
    }
    
}

    
    

