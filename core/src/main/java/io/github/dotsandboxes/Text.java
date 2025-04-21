package io.github.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    BitmapFont font;
    CharSequence text;

    public Text()
    {
        font = new BitmapFont(Gdx.files.internal("dotline/Dotline.fnt"), Gdx.files.internal("dotline/Dotline.png"), false);
        text = "Dots & Boxes";
        font.getData().setScale(2f);
    }

    public void drawText(SpriteBatch batch)
    {
        font.draw(batch, text, 600, 950);
    }

    public void dispose()
    {
        font.dispose();
    }
}
