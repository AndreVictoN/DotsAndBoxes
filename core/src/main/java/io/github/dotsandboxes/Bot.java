package io.github.dotsandboxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {
    private Lines lines;
    private SquareEnemy squaresEnemy;
    private SquarePlayer squaresPlayer;
    private Random random;
    public boolean turn;
    public boolean completedSquare = false;

    public Bot(Lines lines, SquareEnemy squaresEnemy, SquarePlayer squaresPlayer) {
        this.lines = lines;
        this.squaresEnemy = squaresEnemy;
        this.squaresPlayer = squaresPlayer;
        this.random = new Random();
        this.turn = false; 
    }

    public void playTurn() {
        List<Integer> availableLines = new ArrayList<>();
        for (int i = 0; i < lines.getIsClicked().length; i++) {
            if (!lines.getIsClicked()[i]) {
                availableLines.add(i);
            }
        }


        if (!availableLines.isEmpty()) {
            int chosenLine = availableLines.get(random.nextInt(availableLines.size()));
            
            lines.getIsClicked()[chosenLine] = true;
            
            lines.getSprites().get(chosenLine).setColor(0f, 0f, 0f, 1f);
            
            checkCompletedSquares(chosenLine);
            
            //System.out.println("Bot jogou na linha: " + chosenLine);
        }

        this.turn = completedSquare;
    }

    private void checkCompletedSquares(int lineIndex) {
        completedSquare = lines.checkSquare(lineIndex, squaresEnemy, this);
        //lines.checkSquare(lineIndex, squaresPlayer);
    }
}