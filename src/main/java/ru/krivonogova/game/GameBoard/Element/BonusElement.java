package ru.krivonogova.game.GameBoard.Element;

public class BonusElement extends Element {
	private BonusElementType type;

    public BonusElement(BonusElementType type) {
        this.type = type;
    }

	@Override
	public String getType() {
		return type.name();
	}

}
