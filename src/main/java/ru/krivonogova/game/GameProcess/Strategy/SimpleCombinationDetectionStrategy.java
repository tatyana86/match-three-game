package ru.krivonogova.game.GameProcess.Strategy;

import ru.krivonogova.game.GameBoard.Element.Element;
import ru.krivonogova.game.GameBoard.Field.Field;

public class SimpleCombinationDetectionStrategy implements CombinationDetectionStrategy {

	@Override
    public boolean isCombinationDetect(Field field, int fromRow, int fromCol, int toRow, int toCol) {
        // проверка выходов за пределы поля
        if (fromRow < 0 || fromRow >= field.getHeight() || fromCol < 0 || fromCol >= field.getWidth() ||
            toRow < 0 || toRow >= field.getHeight() || toCol < 0 || toCol >= field.getWidth()) {
            return false;
        }

        // сохраняем элементы из начальной и конечной позиций
        Element fromElement = field.getElement(fromRow, fromCol);
        Element toElement = field.getElement(toRow, toCol);

        // меняем местами элементы, чтобы проверить комбинации
        field.setElement(fromRow, fromCol, toElement);
        field.setElement(toRow, toCol, fromElement);

        boolean hasCombination = checkForCombinations(field);
        if (hasCombination) {
        	return hasCombination;
        }
        
        // возвращаем элементы, если комбинаций не найдено
        field.setElement(fromRow, fromCol, fromElement);
        field.setElement(toRow, toCol, toElement);
        
        return hasCombination;
    }

    private boolean checkForCombinations(Field field) {
    	boolean foundCombination = false;
        
        // проверка по горизонтали
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth() - 2; j++) {
                if (field.getElement(i, j) != null && field.getElement(i, j + 1) != null && field.getElement(i, j + 2) != null &&
                	field.getElement(i, j).getType() == field.getElement(i, j + 1).getType() &&
                    field.getElement(i, j).getType() == field.getElement(i, j + 2).getType()) {
                    foundCombination = true;

                    field.setElement(i, j, null);
                    field.setElement(i, j + 1, null);
                    field.setElement(i, j + 2, null);
                }
            }
        }

        // проверка по вертикали
        for (int j = 0; j < field.getWidth(); j++) {
            for (int i = 0; i < field.getHeight() - 2; i++) {
                if (field.getElement(i, j) != null && field.getElement(i + 1, j) != null && field.getElement(i + 2, j) != null &&
                	field.getElement(i, j).getType() == field.getElement(i + 1, j).getType() &&
                    field.getElement(i, j).getType() == field.getElement(i + 2, j).getType()) {
                    foundCombination = true;

                    field.setElement(i, j, null);
                    field.setElement(i + 1, j, null);
                    field.setElement(i + 2, j, null);
                }
            }
        }

        // падение элементов
        if (foundCombination) {
        	field.collapseElements();
        }
        
        return foundCombination;
    }

}
