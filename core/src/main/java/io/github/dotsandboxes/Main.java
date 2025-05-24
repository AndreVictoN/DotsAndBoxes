package io.github.dotsandboxes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.dotsandboxes.utils.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Screen currentScreen = Screen.MAIN_MENU;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    Dots dots;
    Text text;
    Text enterText;
    Lines lines;
    SquarePlayer squaresPlayer;
    SquareEnemy squaresEnemy;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        dots = new Dots();
        text = new Text();
        enterText = new Text(Screen.MAIN_MENU);
        lines = new Lines();
        squaresPlayer = new SquarePlayer();
        squaresEnemy = new SquareEnemy();

        Gdx.input.setInputProcessor(new InputAdapter() 
        {
            @Override
            public boolean keyDown(int keyCode)
            {
                if(currentScreen == Screen.MAIN_MENU && keyCode == Input.Keys.ENTER)
                {
                    currentScreen = Screen.GAME;
                }

                return true;
            }
        });
    }

    @Override
    public void render() {
        batch.begin();

        if(currentScreen == Screen.GAME)
        {
            ScreenUtils.clear(233f, 233f, 233f, 1f);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
    
            dots.draw(batch);
            text.draw(batch);
            lines.draw(batch);
            squaresPlayer.draw(batch);
            squaresEnemy.draw(batch);
    
            Vector3 mousePos3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos3);
    
            Vector2 mousePos = new Vector2(mousePos3.x, mousePos3.y);
            lines.checkIfMouseIsHovering(mousePos, squaresPlayer);
        }else if(currentScreen == Screen.MAIN_MENU)
        {
            ScreenUtils.clear(233f, 233f, 233f, 1f);
            text.draw(batch);
            enterText.draw(batch);
            enterText.animateText();
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        dots.dispose();
        text.dispose();
        enterText.dispose();
        lines.dispose();
        squaresPlayer.dispose();
        squaresEnemy.dispose();
    }
}
