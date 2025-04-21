package io.github.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dots {
    private final ArrayList<Texture> dots;

    public Dots()
    {
        dots = new ArrayList<>();

        for(int i = 0; i < 16; i++)
        {
            Texture dot = new Texture("dot.png");
            
            dots.add(dot);
        }
    }

    public void drawDots(SpriteBatch batch)
    {
        for(int i = 0; i < dots.size(); i++)
        {
            Sprite sprite = new Sprite(dots.get(i));

            sprite.setSize(70, 70);
            sprite.setPosition(((i % 4) + 3.05f) * 200, ((i / 4) + 0.4f) * 200);
            sprite.setOrigin(50, 50);

            sprite.draw(batch);

            //batch.draw(sprite, (i % 4) * 100, (i / 4) * 100);
        }
    }

    public void dispose()
    {
        for(Texture dot : dots)
        {
            dot.dispose();
        }
    }
}
