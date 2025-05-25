package io.github.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.dotsandboxes.utils.Drawables;

public class Dots implements Drawables
{
    private final ArrayList<Texture> dots;

    public Dots()
    {
        dots = new ArrayList<>();

        for(int i = 0; i < 36; i++)
        {
            Texture dot = new Texture("dot.png");
            
            dots.add(dot);
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            Sprite sprite = new Sprite(dots.get(i));

            sprite.setSize(90, 90);
            sprite.setOrigin(45, 45);

            if(i % 6 == 0)
            {
                sprite.setPosition(((i % 6) + 3.62f) * 150, ((i / 6) + 0.13f) * 150);
            }else
            {
                sprite.setPosition(((i % 6) + 3.65f) * 150, ((i / 6) + 0.13f) * 150);
            }
            sprite.draw(batch);
        }
    }

    @Override
    public void dispose()
    {
        for(Texture dot : dots)
        {
            dot.dispose();
        }
    }
}
