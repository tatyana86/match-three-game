package ru.krivonogova.game.GameProcess.Strategy;

import ru.krivonogova.game.GameBoard.Field.Field;

public class SimpleMoveAvailabilityStrategy implements MoveAvailabilityStrategy {
	@Override
    public boolean isMoveAvailable(Field field, int fromRow, int fromCol, int toRow, int toCol) {
        // проверка на выход за пределы поля
        if (fromRow < 0 || fromRow >= field.getHeight() || fromCol < 0 || fromCol >= field.getWidth() ||
            toRow < 0 || toRow >= field.getHeight() || toCol < 0 || toCol >= field.getWidth()) {
            return false;
        }

        // проверка на соседство
        boolean isAdjacent = (Math.abs(fromRow - toRow) == 1 && fromCol == toCol) || 
                             (Math.abs(fromCol - toCol) == 1 && fromRow == toRow);
        
        return isAdjacent;
    }
}