package io.github.dotsandboxes.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Square implements Drawables
{
    private ArrayList<Sprite> squares;
    protected Texture squareTexture;
    private boolean[] isActive;

    public Square(String texturePath)
    {
        this.squareTexture = new Texture(Gdx.files.internal(texturePath));
        this.isActive = new boolean[25];
        this.squares = new ArrayList<>();
        
        for(int i = 0; i < 25; i++)
        {
            Sprite square = new Sprite(squareTexture);
            square.setSize(130, 130);
            square.setOrigin(65, 65);

            for(boolean active : isActive)
            {
                active = false;
            }

            if(i % 5 == 0)
            {
                square.setPosition(((i % 5) + 4f) * 150, ((i / 5) + 0.52f) * 150);
            }else
            {
                square.setPosition(((i % 5) + 4.03f) * 150, ((i / 5) + 0.52f) * 150);
            }

            square.setColor(square.getColor().r, square.getColor().g, square.getColor().b, 0f);
            
            squares.add(square);
        }
    }

    public void resetGame()
    {
        for(int i = 0; i < squares.size(); i++)
        {
            squares.get(i).setColor(squares.get(i).getColor().r, squares.get(i).getColor().g, squares.get(i).getColor().b, 0f);
            isActive[i] = false;
        }
    }

    public boolean[] getIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean[] isActive)
    {
        for(int i = 0; i < this.isActive.length; i++)
        {
            if(this.isActive[i])
            {
                isActive[i] = this.isActive[i];
            }else if(isActive[i])
            {
                this.isActive[i] = isActive[i];
            }
        }
    }

    public ArrayList<Sprite> getSquares()
    {
        return squares;
    }

    public void changeColor(int index)
    {
        squares.get(index).setColor(squares.get(index).getColor().r, squares.get(index).getColor().g, squares.get(index).getColor().b, 0.5f);
        isActive[index] = true;
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for(Sprite square : squares)
        {
            square.draw(batch);
        }
    }

    @Override
    public void dispose()
    {
        for(Sprite square : squares)
        {
            square.getTexture().dispose();
        }
    }
}
