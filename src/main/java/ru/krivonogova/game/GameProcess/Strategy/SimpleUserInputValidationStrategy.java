package ru.krivonogova.game.GameProcess.Strategy;

public class SimpleUserInputValidationStrategy implements UserInputValidationStrategy {
    @Override
    public boolean isValidInput(String input) {
    	String regex = "^[A-H][1-8] [A-H][1-8]$";
        return input.matches(regex) || input.equals("0");
    }
}