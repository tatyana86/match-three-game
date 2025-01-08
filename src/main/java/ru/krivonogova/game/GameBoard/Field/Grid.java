package ru.krivonogova.game.GameBoard.Field;

import ru.krivonogova.game.GameBoard.Element.BaseElement;
import ru.krivonogova.game.GameBoard.Element.BaseElementType;
import ru.krivonogova.game.GameBoard.Element.Element;

public class Grid extends Field {
	private Element[][] grid;
    private final int width = 8;
    private final int height = 8;

    public Grid() {
        this.grid = new Element[width][height];
        initialize();
    }    
    
    private void initialize() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            	BaseElementType randomType = getRandomElementType(i, j);
                grid[i][j] = new BaseElement(randomType);
            }
        }
    }
    
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
    
    public void displayField() {
        // нумерация столбцов
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + getWidth(); c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        // разделительная линия
        System.out.print("   ");
        for (int j = 0; j < getWidth(); j++) {
            System.out.print("__");
        }
        System.out.println();

        // нумерация строк и вывод элементов
        for (int i = 0; i < getHeight(); i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < getWidth(); j++) {
                System.out.print(getElement(i, j).getType() + " ");
            }
            System.out.println();
        }
    }
    
    private BaseElementType getRandomElementType(int x, int y) {
        BaseElementType randomType;
        
        do {
            randomType = BaseElementType.values()[(int) (Math.random() * BaseElementType.values().length)];
        } while (isThreeInARow(x, y, randomType));
        
        return randomType;
    }

    private boolean isThreeInARow(int x, int y, BaseElementType type) {
        String targetType = type.name();

        // Проверка по горизонтали
        if (x >= 2 && grid[x - 1][y].getType().equals(targetType) && grid[x - 2][y].getType().equals(targetType)) {
            return true;
        }
        // Проверка по вертикали
        if (y >= 2 && grid[x][y - 1].getType().equals(targetType) && grid[x][y - 2].getType().equals(targetType)) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public Element getElement(int x, int y) {
        if (! isInBounds(x, y)) {
            throw new IndexOutOfBoundsException("Координаты выходят за пределы поля!");
        }
                
        return grid[x][y];
    }

    @Override
    public void setElement(int x, int y, Element element) {
        if (! isInBounds(x, y)) {
            throw new IndexOutOfBoundsException("Координаты выходят за пределы поля!");
        }
        grid[x][y] = element;
    }
    
	@Override
	public void collapseElements() {
		for (int j = 0; j < getWidth(); j++) {
	        for (int i = getHeight() - 1; i >= 0; i--) {
	            // если элемент пуст (null), начинаем перемещение
	            if (getElement(i, j) == null) {
	                for (int k = i - 1; k >= 0; k--) {
	                    if (getElement(k, j) != null) {
	                        setElement(i, j, getElement(k, j));
	                        setElement(k, j, null);
	                        break;
	                    }
	                }
	            }
	        }

	        // генерируем новые элементы
	        for (int i = 0; i < getHeight(); i++) {
	            if (getElement(i, j) == null) {
	                BaseElementType randomType = getRandomElementType(i, j);
	                setElement(i, j, new BaseElement(randomType));
	            }
	        }
	    }
	}

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
