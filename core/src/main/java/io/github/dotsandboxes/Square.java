package io.github.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.dotsandboxes.utils.Drawables;

public class Square implements Drawables
{
    final private Texture square;
    final private Sprite sprite;

    public Square()
    {
        System.out.println(Gdx.files.internal("squarePlayer.png").exists());
        this.square = new Texture(Gdx.files.internal("squareEnemy.jpg"));
        this.sprite = new Sprite(this.square);
        this.sprite.setSize(150, 140);
    }

    public void setPosition(float x, float y)
    {
        sprite.setPosition(x - 92, y - 47);
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }

    @Override
    public void dispose()
    {
        square.dispose();
    }
}
