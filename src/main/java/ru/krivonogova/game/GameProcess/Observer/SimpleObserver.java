package ru.krivonogova.game.GameProcess.Observer;

import java.util.HashMap;
import java.util.Map;

public class SimpleObserver implements StatisticsObserver, ReportObserver {
	
	static final int SCORE = 30; // кол-во очков по умолчанию при обнаружении комбинации
	
	private Map<Integer, String> moveHistory;
    private int totalScore;
    
    public SimpleObserver() {
        this.moveHistory = new HashMap<>();
        this.totalScore = 0;
    }

	@Override
	public void updateStatistics(int moveCount, String input) {
		moveHistory.put(moveCount, input);
        totalScore += SCORE;		
	}

	@Override
	public void sendReport() {
		System.out.println("Общие очки: " + totalScore);
		System.out.println("История ходов:");
		for (Map.Entry<Integer, String> entry : moveHistory.entrySet()) {
            System.out.println("Ход " + entry.getKey() + ": " + entry.getValue());
        }
        		
	}

}
