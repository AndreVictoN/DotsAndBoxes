package io.github.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.dotsandboxes.utils.Drawables;
import io.github.dotsandboxes.utils.Screen;

public class Text implements Drawables
{
    BitmapFont font;
    CharSequence text;
    boolean canFade = false;

    public Text()
    {
        font = new BitmapFont(Gdx.files.internal("dotline/Dotline.fnt"), Gdx.files.internal("dotline/Dotline.png"), false);
        text = "Dots & Boxes";
        font.getData().setScale(1.5f);
    }

    public Text(Screen screen, int playerPoints, int enemyPoints)
    {
        font = new BitmapFont(Gdx.files.internal("dotline/Dotline.fnt"), Gdx.files.internal("dotline/Dotline.png"), false);

        if(screen == Screen.MAIN_MENU) 
        {
            text = "Press ENTER to Start";
            font.getData().setScale(1);
        }
        else if(screen == Screen.RESULTS_MENU && (playerPoints > enemyPoints)) {
            text = "You Won! \nPress ENTER to Start";
            font.getData().setScale(1);
        }
        if(screen == Screen.RESULTS_MENU && (playerPoints < enemyPoints)) 
        {
            text = "You Lost... \nPress ENTER to Start";
            font.getData().setScale(1);
        }
        else
        {
            text = "Dots & Boxes";
            font.getData().setScale(1.5f);
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        if(this.text == "Dots & Boxes")
        {
            font.draw(batch, text, 600, 950);
        }else if(this.text == "Press ENTER to Start")
        {
            font.draw(batch, text, 530, 540);
        }
    }

    @Override
    public void dispose()
    {
        font.dispose();
    }

    public void animateText()
    {
        if(this.font.getColor().a == 1)
        {
            canFade = true;
            this.font.setColor(this.font.getColor().r, this.font.getColor().g, this.font.getColor().b, this.font.getColor().a - 0.01f);
        }else if(this.font.getColor().a > 0 && canFade)
        {
            this.font.setColor(this.font.getColor().r, this.font.getColor().g, this.font.getColor().b, this.font.getColor().a - 0.01f);
        }else if(this.font.getColor().a > 0 && !canFade)
        {
            this.font.setColor(this.font.getColor().r, this.font.getColor().g, this.font.getColor().b, this.font.getColor().a + 0.01f);
        }else if(this.font.getColor().a == 0)
        {
            this.font.setColor(this.font.getColor().r, this.font.getColor().g, this.font.getColor().b, this.font.getColor().a + 0.01f);
            canFade = false;
        }
    }
}
