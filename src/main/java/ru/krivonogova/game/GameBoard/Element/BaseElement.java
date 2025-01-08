package ru.krivonogova.game.GameBoard.Element;

public class BaseElement extends Element {
	private BaseElementType type;
	
	public BaseElement(BaseElementType type) {
        this.type = type;
    }

	@Override
	public String getType() {
		return type.name();
	}

}
