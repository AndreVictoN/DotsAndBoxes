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
    private boolean[] isHovering;
    private boolean[] isClicked;

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
                    changeColor(sprite, i);
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
