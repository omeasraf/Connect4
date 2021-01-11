package com.amiasraf.connect4;

public class Connect4WinningCombo {
    public final int w1, w2,w3, w4;

    public Connect4WinningCombo(int w1, int w2, int w3, int w4) {
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
    }

    @Override
    public String toString() {
        return "TicTacToeWinningCombo{" +
                "w1=" + w1 +
                ", w2=" + w2 +
                ", w3=" + w3 +
                ", w4=" + w4 +
                '}';
    }

}
