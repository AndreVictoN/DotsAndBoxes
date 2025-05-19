package io.github.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import io.github.dotsandboxes.utils.Drawables;

public class Lines implements Drawables
{
    final private Texture lineTexture;
    final private ArrayList<Sprite> sprites;
    final private ArrayList<Polygon> polygons;
    private ArrayList<Integer> firstRow;
    private ArrayList<Integer> secondRow;
    private ArrayList<Integer> thirdRow;
    private ArrayList<Integer> fourthRow;
    private ArrayList<Integer> fifthRow;
    private ArrayList<Integer> firstColumn;
    private ArrayList<Integer> secondColumn;
    private ArrayList<Integer> thirdColumn;
    private ArrayList<Integer> fourthColumn;
    private ArrayList<Integer> fifthColumn;
    private ArrayList<Integer> sixthColumn;
    private ArrayList<Square> squares;
    private boolean[] isHovering;
    private boolean[] isClicked;

    public Lines()
    {
        this.sprites = new ArrayList<>();
        this.polygons = new ArrayList<>();
        this.squares = new ArrayList<>();
        this.isHovering = new boolean[60];
        this.isClicked = new boolean[60];
        this.lineTexture = new Texture("line.png");

        for(int i = 0; i < 60; i++)
        {
            Sprite sprite = new Sprite(lineTexture);

            sprite.setSize(149, 12);
            sprite.setOrigin(74.5f, 6);
            sprite.getBoundingRectangle().setSize(sprite.getScaleX(), sprite.getScaleY());
            sprite.getBoundingRectangle().setCenter(sprite.getOriginX(), sprite.getOriginY());

            checkIfIsUp(sprite, i);

            //changeColor(sprite);
            isClicked[i] = false;
            isHovering[i] = false;
            sprites.add(sprite);
        }

        addRowsAndColumnsToArrays();
    }

    private void addRowsAndColumnsToArrays()
    {
        this.firstRow = new ArrayList<>(){{add(0); add(6); add(12); add(18); add(24); add(30);}};
        this.secondRow = new ArrayList<>(){{add(1); add(7); add(13); add(19); add(25); add(31);}};
        this.thirdRow = new ArrayList<>(){{add(2); add(8); add(14); add(20); add(26); add(32);}};
        this.fourthRow = new ArrayList<>(){{add(3); add(9); add(15); add(21); add(27); add(33);}};
        this.fifthRow = new ArrayList<>(){{add(4); add(10); add(16); add(22); add(28); add(34);}};
        this.sixthColumn = new ArrayList<>(){{add(5); add(11); add(17); add(23); add(29);}};
        this.firstColumn = new ArrayList<>(){{add(35); add(36); add(37); add(38); add(39);}};
        this.secondColumn = new ArrayList<>(){{add(40); add(41); add(42); add(43); add(44);}};
        this.thirdColumn = new ArrayList<>(){{add(45); add(46); add(47); add(48); add(49);}};
        this.fourthColumn = new ArrayList<>(){{add(50); add(51); add(52); add(53); add(54);}};
        this.fifthColumn = new ArrayList<>(){{add(55); add(56); add(57); add(58); add(59);}};
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for(Sprite sprite : sprites)
        {
            sprite.draw(batch);
        }
    }

    private void checkIfIsUp(Sprite sprite, int i)
    {
        if(i == 5 || i == 11 || i == 17 || i == 23 || i == 29){
            manageSpritesThatRotate(sprite, i);
            sprite.setPosition(((i % 6) + 3.5f) * 150, ((i / 6) + 0.9f) * 150);
        }else if(i >= 35 && i < 40){
            manageSpritesThatRotate(sprite, i);
            if(i == 35) sprite.setPosition(((0 % 6) + 3.41f) * 150, ((0 / 6) + 0.9f) * 150);
            else { sprite.setPosition(sprites.get(i - 1).getX(), sprites.get(i - 1).getY() + sprite.getHeight() + 137.5f); }
        }else if(i >= 40 && i < 45){
            manageSpritesThatRotate(sprite, i);
            if(i == 40) sprite.setPosition(((0 % 6) + 4.54f) * 150, ((0 / 6) + 0.87f) * 150);
            else { sprite.setPosition(sprites.get(i - 1).getX(), sprites.get(i - 1).getY() + sprite.getHeight() + 138f); }
        }else if(i >= 45 && i < 50){
            manageSpritesThatRotate(sprite, i);
            if(i == 45) sprite.setPosition(((0 % 6) + 5.54f) * 150, ((0 / 6) + 0.87f) * 150);
            else { sprite.setPosition(sprites.get(i - 1).getX(), sprites.get(i - 1).getY() + sprite.getHeight() + 137.5f); }
        }else if(i >= 50 && i < 55){
            manageSpritesThatRotate(sprite, i);
            if(i == 50) sprite.setPosition(((0 % 6) + 6.54f) * 150, ((0 / 6) + 0.87f) * 150);
            else { sprite.setPosition(sprites.get(i - 1).getX(), sprites.get(i - 1).getY() + sprite.getHeight() + 137.5f); }
        }else if(i >= 55){
            manageSpritesThatRotate(sprite, i);
            if(i == 55) sprite.setPosition(((0 % 6) + 7.54f) * 150, ((0 / 6) + 0.87f) * 150);
            else { sprite.setPosition(sprites.get(i - 1).getX(), sprites.get(i - 1).getY() + sprite.getHeight() + 137.5f); }
        }else{
            sprite.setPosition(((i % 6) + 3.95f) * 150, ((i / 6) + 0.4f) * 150);
        }
        sprite.setColor(0f, 0f, 0f, 0.5f);

        generatePolygon(sprite);
    }

    private void manageSpritesThatRotate(Sprite sprite, int i)
    {
        if(i >= 40)
        {
            sprite.setSize(135, 12);
            sprite.setOrigin(62.5f, 6);
        }

        sprite.setRotation(90);
    }

    private void generatePolygon(Sprite sprite)
    {
        Polygon polygon = new Polygon(new float[] {0, 0, sprite.getWidth(), 0, sprite.getWidth(), sprite.getHeight(), 0, sprite.getHeight()});

        polygon.setOrigin(sprite.getOriginX(), sprite.getOriginY());
        polygon.setPosition(sprite.getX(), sprite.getY());
        polygon.setRotation(sprite.getRotation());

        polygons.add(polygon);
    }

    public void checkIfMouseIsHovering(Vector2 mousePos)
    {
        for(int i = 0; i < polygons.size(); i++)
        {
            Polygon polygon = polygons.get(i);
            Sprite sprite = sprites.get(i);

            if(polygon.contains(mousePos.x, mousePos.y))
            {
                isHovering[i] = true;
                sprite.setColor(0f, 0f, 0f, 1f);

                if(Gdx.input.isTouched())
                {
                    if(!isClicked[i]) changeColor(sprite, i);
                }
            }else
            {
                isHovering[i] = false;
                if(!isClicked[i]) sprite.setColor(0f, 0f, 0f, 0.5f);
            }
        }
    }

    private void changeColor(Sprite sprite, int i)
    {
        if(isHovering[i] && !isClicked[i]) sprite.setColor(0f, 0f, 0f, 1f);
        isClicked[i] = true;

        checkSquare(i);
    }

    private void checkSquare(int i)
    {   
        if(thirdRow.contains(i))
        {
            checkSquaresWithRowLines(i, 3);
        }else if(firstRow.contains(i))
        {
            checkSquaresWithRowLines(i, 1);
        }else if(secondRow.contains(i))
        {
            checkSquaresWithRowLines(i, 2);
        }else if(fourthRow.contains(i))
        {
            checkSquaresWithRowLines(i, 4);
        }else if(fifthRow.contains(i))
        {
            checkSquaresWithRowLines(i, 5);
        }else if(firstColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 1);
        }else if(secondColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 2);
        }else if(thirdColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 3);
        }else if(fourthColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 4);
        }else if(fifthColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 5);
        }else if(sixthColumn.contains(i))
        {
            checkSquaresWithColumnLines(i, 6);
        }
    }

    private void checkSquaresWithColumnLines(int i, int ColumnNumber)
    {
        int numberToStart = 0;
        int numberToMultiply = 0;

        switch(ColumnNumber){
            case 1 -> {
                if(i == 35) numberToMultiply = 0; else if(i == 36) numberToMultiply = 1;
                else if(i == 37) numberToMultiply = 2; else if(i == 38) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 0;
            }
            case 2 -> {
                if(i == 40) numberToMultiply = 0; else if(i == 41) numberToMultiply = 1;
                else if(i == 42) numberToMultiply = 2; else if(i == 43) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 1;
            }
            case 3 -> {
                if(i == 45) numberToMultiply = 0; else if(i == 46) numberToMultiply = 1;
                else if(i == 47) numberToMultiply = 2; else if(i == 48) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 2;
            }
            case 4 -> {
                if(i == 50) numberToMultiply = 0; else if(i == 51) numberToMultiply = 1;
                else if(i == 52) numberToMultiply = 2; else if(i == 53) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 3;
            }
            case 5 -> {
                if(i == 55) numberToMultiply = 0; else if(i == 56) numberToMultiply = 1;
                else if(i == 57) numberToMultiply = 2; else if(i == 58) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 4;
            }
            case 6 -> {
                if(i == 5) numberToMultiply = 0; else if(i == 11) numberToMultiply = 1;
                else if(i == 17) numberToMultiply = 2; else if(i == 23) numberToMultiply = 3;
                else numberToMultiply = 4;

                numberToStart = 5;
            }
        }

        switchBoxCheckCasesColumn(i, numberToStart, numberToMultiply);
    }

    private void switchBoxCheckCasesColumn(int i, int numberToStart, int numberToMultiply)
    {
        switch(i) {
            case 35, 36, 37, 38, 39 -> {
                if((isClicked[i] && isClicked[i + 5] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]))
                {
                    System.out.println("Square completed! " + i);
                    Square square = new Square();
                    squares.add(square); square.setPosition(sprites.get(i + 5).getX(), sprites.get(i + 5).getY() - sprites.get(i + 5).getHeight());
                }
            }
            case 55, 56, 57, 58, 59 -> {
                if((isClicked[i] && isClicked[sixthColumn.get(fifthColumn.indexOf(i))] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]) || (isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)]))
                {
                    System.out.println("Square completed! " + i);
                    if(isClicked[sixthColumn.get(fifthColumn.indexOf(i))]) {
                        Square square = new Square();
                        squares.add(square); square.setPosition(sprites.get(sixthColumn.get(fifthColumn.indexOf(i))).getX() + 10, sprites.get(sixthColumn.get(fifthColumn.indexOf(i))).getY() - 16);
                    }else if(isClicked[i-5]){
                        Square square = new Square();
                        squares.add(square); square.setPosition(sprites.get(i).getX() + 5, sprites.get(i ).getY() - sprites.get(i).getHeight());
                    }
                }
            }
            case 5, 11, 17, 23, 29 -> {
                if((isClicked[i] && isClicked[fifthColumn.get(sixthColumn.indexOf(i))] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)]))
                {
                    System.out.println("Square completed! " + i);
                    Square square = new Square();
                    squares.add(square); square.setPosition(sprites.get(i).getX() + 12, sprites.get(i).getY() - sprites.get(i).getHeight() - 5);
                }
            }
            default -> {
                if((isClicked[i] && isClicked[i + 5] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]) || (isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)]))
                {
                    System.out.println("Square completed! " + i);
                    if(isClicked[i+5]){
                        Square square = new Square();
                        squares.add(square); square.setPosition(sprites.get(i + 5).getX() + 5, sprites.get(i + 5).getY() - sprites.get(i + 5).getHeight());
                    }else if(isClicked[i-5]){
                        Square square = new Square();
                        squares.add(square); square.setPosition(sprites.get(i).getX() + 4, sprites.get(i).getY() - sprites.get(i).getHeight());
                    }
                }
            }
        }
    }

    private void checkSquaresWithRowLines(int i, int RowNumber)
    {
        int numberToSum = 0;
        int numberToStartLeft = 0;
        int numberToStartRight = 0;

        switch(RowNumber){
            case 1 -> {
                if(i == 0) numberToSum = 0; else if(i == 6) numberToSum = 1;
                else if(i == 12) numberToSum = 2; else if(i == 18) numberToSum = 3;
                else if(i == 24) numberToSum = 4; else numberToSum = 5;

                numberToStartLeft = 35;
                numberToStartRight = 40;
            }
            case 2 -> {
                if(i == 1) numberToSum = 0; else if(i == 7) numberToSum = 1;
                else if(i == 13) numberToSum = 2; else if(i == 19) numberToSum = 3;
                else if(i == 25) numberToSum = 4; else numberToSum = 5;

                numberToStartLeft = 40;
                numberToStartRight = 45;
            }
            case 3 -> {
                if(i == 2) numberToSum = 0; else if(i == 8) numberToSum = 1;
                else if(i == 14) numberToSum = 2; else if(i == 20) numberToSum = 3;
                else if(i == 26) numberToSum = 4; else numberToSum = 5;

                numberToStartLeft = 45;
                numberToStartRight = 50;
            }
            case 4 -> {
                if(i == 3) numberToSum = 0; else if(i == 9) numberToSum = 1;
                else if(i == 15) numberToSum = 2; else if(i == 21) numberToSum = 3;
                else if(i == 27) numberToSum = 4; else numberToSum = 5;

                numberToStartLeft = 50;
                numberToStartRight = 55;
            }
            case 5 -> {
                if(i == 4) numberToSum = 0; else if(i == 10) numberToSum = 1;
                else if(i == 16) numberToSum = 2; else if(i == 22) numberToSum = 3;
                else if(i == 28) numberToSum = 4; else numberToSum = 5;

                numberToStartLeft = 55;
                numberToStartRight = 5;
            }
        }

        switchBoxCheckCasesRow(i, numberToSum, numberToStartLeft, numberToStartRight);
    }

    private void switchBoxCheckCasesRow(int i, int numberToSum, int numberToStartLeft, int numberToStartRight)
    {
        switch (i) {
            case 30, 31, 32, 33 -> {
                if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + numberToSum - 1])
                {
                    System.out.println("Square completed! " + i);
                }
            }
            case 34 -> {
                if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + (6 * numberToSum) - 1])
                {
                    System.out.println("Square completed! " + i);
                }
            }
            case 0, 1, 2, 3 -> {
                if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[numberToSum + numberToStartRight])
                {
                    System.out.println("Square completed! " + i);
                }
            }
            case 4 -> {
                if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[(6 * numberToSum) + numberToStartRight])
                {
                    System.out.println("Square completed! " + i);
                }
            }
            case 10, 16, 22, 28 -> {
                if((isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[(6 * numberToSum) + numberToStartRight]) || (isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + (6 * numberToSum) - 6]))
                {
                    System.out.println("Square completed! " + i);
                }
            }
            default -> {
                if((isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[numberToStartRight + numberToSum]) || (isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + numberToSum - 1]))
                {
                    System.out.println("Square completed! " + i);
                }
            }
        }
    }

    @Override
    public void dispose()
    {
        for(Sprite sprite : sprites)
        {
            sprite.getTexture().dispose();
        }
    }

    public void disposeSquares()
    {
        for(Square square : squares)
        {
            square.dispose();
        }
    }

    public void drawSquares(SpriteBatch batch)
    {
        for(Square square : squares)
        {
            square.draw(batch);
        }
    }
}
