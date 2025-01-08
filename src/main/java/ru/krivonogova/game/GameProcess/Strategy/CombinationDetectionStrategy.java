package ru.krivonogova.game.GameProcess.Strategy;

import ru.krivonogova.game.GameBoard.Field.Field;

public interface CombinationDetectionStrategy {
	boolean isCombinationDetect(Field field, int fromRow, int fromCol, int toRow, int toCol);
}
