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
    int playerPoints;
    int enemyPoints;
    boolean canFade = false;

    public Text()
    {
        font = new BitmapFont(Gdx.files.internal("dotline/Dotline.fnt"), Gdx.files.internal("dotline/Dotline.png"), false);
        text = "Dots & Boxes";
        font.getData().setScale(1.5f);
    }

    public Text(Screen screen)
    {
        font = new BitmapFont(Gdx.files.internal("dotline/Dotline.fnt"), Gdx.files.internal("dotline/Dotline.png"), false);

        if(screen == Screen.MAIN_MENU) 
        {
            text = "Press ENTER to Start";
            font.getData().setScale(1);
        }else if(screen == Screen.GAME)
        {
            text = "Dots & Boxes";
            font.getData().setScale(1.5f);
        }else if(screen == Screen.RESULTS_MENU) 
        {
            text = "Press ENTER to Restart";
            font.getData().setScale(1);
        }
    }

    public void setResults(Screen screen, int playerPoints, int enemyPoints)
    {
        if(screen == Screen.RESULTS_MENU && (playerPoints > enemyPoints)) 
        {
            text = "         You Won! \nPress ENTER to Restart";
            font.getData().setScale(1);
        }
        else if(screen == Screen.RESULTS_MENU && (playerPoints < enemyPoints)) 
        {
            text = "         You Lost... \nPress ENTER to Restart";
            font.getData().setScale(1);
        }else if(screen == Screen.GAME)
        {
            text = "Player points: " + playerPoints + "\nEnemy points: " + enemyPoints;
            font.getData().setScale(0.4f);
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        if(this.text.equals("Dots & Boxes"))
        {
            font.draw(batch, text, 600, 950);
        }else if(this.text.equals("Press ENTER to Start"))
        {
            font.draw(batch, text, 530, 540);
        }else if(this.text.equals("         You Won! \nPress ENTER to Restart") || this.text.equals("         You Lost... \nPress ENTER to Restart"))
        {
            font.draw(batch, text, 526, 600);
        }else if(((String) this.text).contains("Player points: "))
        {
            font.draw(batch, text, 100, 850);
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
