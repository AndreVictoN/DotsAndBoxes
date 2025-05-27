package io.github.dotsandboxes;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import io.github.dotsandboxes.utils.Drawables;
import io.github.dotsandboxes.utils.Square;

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
    private boolean[] isHovering;
    private boolean[] isClicked;
    
    public int playerPoints;
    public int enemyPoints;

    /*Mapas para checar quadrados correspondentes de linhas*/
    private static final Map<Integer, int[]> squareIndexMapColumn = Map.ofEntries(
        Map.entry(5, new int[]{4, -1}),
        Map.entry(11, new int[]{9, -1}),
        Map.entry(17, new int[]{14, -1}),
        Map.entry(23, new int[]{19, -1}),
        Map.entry(29, new int[]{24, -1}),
        Map.entry(35, new int[]{-1, 0}),
        Map.entry(36, new int[]{-1, 5}),
        Map.entry(37, new int[]{-1, 10}),
        Map.entry(38, new int[]{-1, 15}),
        Map.entry(39, new int[]{-1, 20}),
        Map.entry(40, new int[]{0, 1}),
        Map.entry(41, new int[]{5, 6}),
        Map.entry(42, new int[]{10, 11}),
        Map.entry(43, new int[]{15, 16}),
        Map.entry(44, new int[]{20, 21}),
        Map.entry(45, new int[]{1, 2}),
        Map.entry(46, new int[]{6, 7}),
        Map.entry(47, new int[]{11, 12}),
        Map.entry(48, new int[]{16, 17}),
        Map.entry(49, new int[]{21, 22}),
        Map.entry(50, new int[]{2, 3}),
        Map.entry(51, new int[]{7, 8}),
        Map.entry(52, new int[]{12, 13}),
        Map.entry(53, new int[]{17, 18}),
        Map.entry(54, new int[]{22, 23}),
        Map.entry(55, new int[]{3, 4}),
        Map.entry(56, new int[]{8, 9}),
        Map.entry(57, new int[]{13, 14}),
        Map.entry(58, new int[]{18, 19}),
        Map.entry(59, new int[]{23, 24})
    );
    private static final Map<Integer, int[]> squareIndexMapRow = Map.ofEntries(
        Map.entry(0, new int[]{0, -1}),
        Map.entry(1, new int[]{1, -1}),
        Map.entry(2, new int[]{2, -1}),
        Map.entry(3, new int[]{3, -1}),
        Map.entry(4, new int[]{4, -1}),
        Map.entry(30, new int[]{-1, 20}),
        Map.entry(31, new int[]{-1, 21}),
        Map.entry(32, new int[]{-1, 22}),
        Map.entry(33, new int[]{-1, 23}),
        Map.entry(34, new int[]{-1, 24}),
        Map.entry(6, new int[]{5, 0}),
        Map.entry(7, new int[]{6, 1}),
        Map.entry(8, new int[]{7, 2}),
        Map.entry(9, new int[]{8, 3}),
        Map.entry(10, new int[]{9, 4}),
        Map.entry(12, new int[]{10, 5}),
        Map.entry(13, new int[]{11, 6}),
        Map.entry(14, new int[]{12, 7}),
        Map.entry(15, new int[]{13, 8}),
        Map.entry(16, new int[]{14, 9}),
        Map.entry(18, new int[]{15, 10}),
        Map.entry(19, new int[]{16, 11}),
        Map.entry(20, new int[]{17, 12}),
        Map.entry(21, new int[]{18, 13}),
        Map.entry(22, new int[]{19, 14}),
        Map.entry(24, new int[]{20, 15}),
        Map.entry(25, new int[]{21, 16}),
        Map.entry(26, new int[]{22, 17}),
        Map.entry(27, new int[]{23, 18}),
        Map.entry(28, new int[]{24, 19})
    );

    public Lines()
    {
        this.sprites = new ArrayList<>();
        this.polygons = new ArrayList<>();
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

    public void resetGame()
    {
        for(int i = 0; i < 60; i++)
        {
            isClicked[i] = false;
            isHovering[i] = false;
            sprites.get(i).setColor(0f, 0f, 0f, 0.5f);
        }
        playerPoints = 0;
        enemyPoints = 0;
    }

    public boolean[] getIsClicked()
    {
        return isClicked;
    }

    public ArrayList<Sprite> getSprites()
    {
        return sprites;
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

    public void checkIfMouseIsHovering(Vector2 mousePos, Square squares, Bot bot)
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
                    if(!isClicked[i]) changeColor(sprite, i, squares, bot);
                }
            }else
            {
                isHovering[i] = false;
                if(!isClicked[i]) sprite.setColor(0f, 0f, 0f, 0.5f);
            }
        }
    }

    private void changeColor(Sprite sprite, int i, Square squares, Bot bot)
    {
        if(isHovering[i] && !isClicked[i]) sprite.setColor(0f, 0f, 0f, 1f);
        isClicked[i] = true;
        
        checkSquare(i, squares, bot);
    }

    public boolean checkSquare(int i, Square squares, Bot bot)
    {   
        if(thirdRow.contains(i)){
            return checkSquaresWithRowLines(i, 3, squares, bot);
        }else if(firstRow.contains(i)){
            return checkSquaresWithRowLines(i, 1, squares, bot);
        }else if(secondRow.contains(i)){
            return checkSquaresWithRowLines(i, 2, squares, bot);
        }else if(fourthRow.contains(i)){
            return checkSquaresWithRowLines(i, 4, squares, bot);
        }else if(fifthRow.contains(i)){
            return checkSquaresWithRowLines(i, 5, squares, bot);
        }else if(firstColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 1, squares, bot);
        }else if(secondColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 2, squares, bot);
        }else if(thirdColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 3, squares, bot);
        }else if(fourthColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 4, squares, bot);
        }else if(fifthColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 5, squares, bot);
        }else if(sixthColumn.contains(i)){
            return checkSquaresWithColumnLines(i, 6, squares, bot);
        }else{return false;}
    }

    private boolean checkSquaresWithColumnLines(int i, int ColumnNumber, Square squares, Bot bot)
    {
        int numberToStart = ColumnNumber - 1;
        int numberToMultiply = 0;

        switch(ColumnNumber){
            case 1 -> {
                if(i == 35) numberToMultiply = 0; else if(i == 36) numberToMultiply = 1;
                else if(i == 37) numberToMultiply = 2; else if(i == 38) numberToMultiply = 3;
                else numberToMultiply = 4;
            }case 2 -> {
                if(i == 40) numberToMultiply = 0; else if(i == 41) numberToMultiply = 1;
                else if(i == 42) numberToMultiply = 2; else if(i == 43) numberToMultiply = 3;
                else numberToMultiply = 4;
            }case 3 -> {
                if(i == 45) numberToMultiply = 0; else if(i == 46) numberToMultiply = 1;
                else if(i == 47) numberToMultiply = 2; else if(i == 48) numberToMultiply = 3;
                else numberToMultiply = 4;
            }case 4 -> {
                if(i == 50) numberToMultiply = 0; else if(i == 51) numberToMultiply = 1;
                else if(i == 52) numberToMultiply = 2; else if(i == 53) numberToMultiply = 3;
                else numberToMultiply = 4;
            }case 5 -> {
                if(i == 55) numberToMultiply = 0; else if(i == 56) numberToMultiply = 1;
                else if(i == 57) numberToMultiply = 2; else if(i == 58) numberToMultiply = 3;
                else numberToMultiply = 4;
            }case 6 -> {
                if(i == 5) numberToMultiply = 0; else if(i == 11) numberToMultiply = 1;
                else if(i == 17) numberToMultiply = 2; else if(i == 23) numberToMultiply = 3;
                else numberToMultiply = 4;
            }
        }
        
        boolean squareFormed = switchBoxCheckCasesColumn(i, numberToStart, numberToMultiply, squares);
        if(squares instanceof SquarePlayer) {bot.turn = !squareFormed;}
        return squareFormed;
    }

    private boolean switchBoxCheckCasesColumn(int i, int numberToStart, int numberToMultiply, Square squares)
    {
        switch(i) {
            case 35, 36, 37, 38, 39 -> {
                if((isClicked[i] && isClicked[i + 5] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)])){
                    checkSquareToShowColumn(i, squares, 'R');
                    return true;
                }else{return false;}
            }case 55, 56, 57, 58, 59 -> {
                if((isClicked[i] && isClicked[sixthColumn.get(fifthColumn.indexOf(i))] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]) && (isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)])){
                    checkSquareToShowColumn(i, squares, 'R'); checkSquareToShowColumn(i, squares, 'L');
                    return true;
                }else if(isClicked[i] && isClicked[sixthColumn.get(fifthColumn.indexOf(i))] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]){
                    checkSquareToShowColumn(i, squares, 'R');
                    return true;
                }else if(isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)]){
                    checkSquareToShowColumn(i, squares, 'L');
                    return true;
                }else{return false;}
            }case 5, 11, 17, 23, 29 -> {
                if((isClicked[i] && isClicked[fifthColumn.get(sixthColumn.indexOf(i))] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)])){
                    checkSquareToShowColumn(i, squares, 'L');
                    return true;
                }else{return false;}
            }default -> {
                if ((isClicked[i] && isClicked[i + 5] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]) && (isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)])){
                    checkSquareToShowColumn(i, squares, 'R'); checkSquareToShowColumn(i, squares, 'L');
                    return true;
                }else if(isClicked[i] && isClicked[i + 5] && isClicked[numberToStart + (numberToMultiply * 6)] && isClicked[numberToStart + ((numberToMultiply + 1) * 6)]){
                    checkSquareToShowColumn(i, squares, 'R');
                    return true;
                }else if(isClicked[i] && isClicked[i - 5] && isClicked[(numberToStart - 1) + (numberToMultiply * 6)] && isClicked[(numberToStart - 1) + ((numberToMultiply + 1) * 6)]){
                    checkSquareToShowColumn(i, squares, 'L');
                    return true;
                }else{return false;}
            }
        }
    }

    private void checkSquareToShowColumn(int i, Square squares, char leftOrRight)
    {
        countPoints(squares, i);

        int[] indexes = squareIndexMapColumn.get(i);
        if(indexes == null) return;

        int index = (leftOrRight == 'L') ? indexes[0] : indexes[1];

        if(index == -1) return;

        if(!squares.getIsActive()[index]){
            squares.changeColor(index);
        }
    }

    private void countPoints(Square squares, int i)
    {
        System.out.println("Square completed! " + i);

        if(squares instanceof SquareEnemy){
            enemyPoints++;
        }else if (squares instanceof SquarePlayer){
            playerPoints++;
        }
    }

    private boolean checkSquaresWithRowLines(int i, int RowNumber, Square squares, Bot bot)
    {
        int numberToSum = 0; int numberToStartLeft = 0; int numberToStartRight = 0;

        switch(RowNumber){
            case 1 -> {
                if(i == 0) numberToSum = 0; else if(i == 6) numberToSum = 1;
                else if(i == 12) numberToSum = 2; else if(i == 18) numberToSum = 3;
                else if(i == 24) numberToSum = 4; else numberToSum = 5;
                numberToStartLeft = 35;
                numberToStartRight = 40;
            }case 2 -> {
                if(i == 1) numberToSum = 0; else if(i == 7) numberToSum = 1;
                else if(i == 13) numberToSum = 2; else if(i == 19) numberToSum = 3;
                else if(i == 25) numberToSum = 4; else numberToSum = 5;
                numberToStartLeft = 40;
                numberToStartRight = 45;
            }case 3 -> {
                if(i == 2) numberToSum = 0; else if(i == 8) numberToSum = 1;
                else if(i == 14) numberToSum = 2; else if(i == 20) numberToSum = 3;
                else if(i == 26) numberToSum = 4; else numberToSum = 5;
                numberToStartLeft = 45;
                numberToStartRight = 50;
            }case 4 -> {
                if(i == 3) numberToSum = 0; else if(i == 9) numberToSum = 1;
                else if(i == 15) numberToSum = 2; else if(i == 21) numberToSum = 3;
                else if(i == 27) numberToSum = 4; else numberToSum = 5;
                numberToStartLeft = 50;
                numberToStartRight = 55;
            }case 5 -> {
                if(i == 4) numberToSum = 0; else if(i == 10) numberToSum = 1;
                else if(i == 16) numberToSum = 2; else if(i == 22) numberToSum = 3;
                else if(i == 28) numberToSum = 4; else numberToSum = 5;
                numberToStartLeft = 55;
                numberToStartRight = 5;
            }
        }

        boolean squareFormed = switchBoxCheckCasesRow(i, numberToSum, numberToStartLeft, numberToStartRight, squares);
        if(squares instanceof SquarePlayer) {bot.turn = !squareFormed;}
        return squareFormed;
    }

    private boolean switchBoxCheckCasesRow(int i, int numberToSum, int numberToStartLeft, int numberToStartRight, Square squares)
    {
        switch (i) {
            case 30, 31, 32, 33 -> {
                if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + numberToSum - 1]){
                    checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else{return false;}
            }case 34 -> {
                if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[29]){
                    checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else{return false;}
            }case 0, 1, 2, 3 -> {
                if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[numberToSum + numberToStartRight]){
                    checkSquareToShowRow(i, squares, 'U');
                    return true;
                }else{return false;}
            }case 4 -> {
                if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[(6 * numberToSum) + numberToStartRight]){
                    checkSquareToShowRow(i, squares, 'U');
                    return true;
                }else{return false;}
            }case 10, 16, 22, 28 -> {
                if((isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[(6 * numberToSum) + numberToStartRight]) && (isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + (6 * numberToSum) - 6])){
                    checkSquareToShowRow(i, squares, 'U'); checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[(6 * numberToSum) + numberToStartRight]){
                    checkSquareToShowRow(i, squares, 'U');
                    return true;
                }else if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + (6 * numberToSum) - 6]){
                    checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else{return false;}
            }default -> {
                if((isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[numberToStartRight + numberToSum]) && (isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + numberToSum - 1])){
                    checkSquareToShowRow(i, squares, 'U'); checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else if(isClicked[i] && isClicked[i + 6] && isClicked[numberToStartLeft + numberToSum] && isClicked[numberToStartRight + numberToSum]){
                    checkSquareToShowRow(i, squares, 'U');
                    return true;
                }else if(isClicked[i] && isClicked[i - 6] && isClicked[numberToStartLeft + numberToSum - 1] && isClicked[numberToStartRight + numberToSum - 1]){
                    checkSquareToShowRow(i, squares, 'D');
                    return true;
                }else{return false;}
            }
        }
    }

    private void checkSquareToShowRow(int i, Square squares, char upOrDown)
    {

        countPoints(squares, i);

        int[] indexes = squareIndexMapRow.get(i);
        if(indexes == null) return;

        int index = (upOrDown == 'U') ? indexes[0] : indexes[1];

        if(index == -1) return;

        if(!squares.getIsActive()[index]){
            squares.changeColor(index);
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
}
