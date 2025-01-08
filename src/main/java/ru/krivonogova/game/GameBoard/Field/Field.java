package ru.krivonogova.game.GameBoard.Field;

import ru.krivonogova.game.GameBoard.Element.Element;

public abstract class Field {
	public abstract Element getElement(int x, int y);;
	public abstract void setElement(int x, int y, Element element);
	public abstract void displayField();
	public abstract void collapseElements();
    public abstract int getWidth();
    public abstract int getHeight();
}
