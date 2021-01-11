package com.amiasraf.connect4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Connect4Service {

    static List<Connect4WinningCombo> list = new ArrayList<>();
    static {
        loadTest();
    }

    public static Path copyToTempFile(URL url, String suffix) throws IOException {
        Path tempFile = Files.createTempFile(null, suffix);
        try (InputStream in = url.openStream();
             OutputStream out = Files.newOutputStream(tempFile)) {
            in.transferTo(out);
        }
        return tempFile;
    }

    private static void loadTest(){
        try{
            Path file = copyToTempFile(Connect4Service.class.getResource("/Connect4WinningCombo"),"");
            String[] lines = Files.readAllLines(file).toArray(String[]::new);
            for (String row: lines){
                int w1,w2,w3, w4;
                String columns[] = row.split(",");
                w1 = Integer.parseInt(columns[0]);
                w2 = Integer.parseInt(columns[1]);
                w3 = Integer.parseInt(columns[2]);
                w4 = Integer.parseInt(columns[3]);
                Connect4WinningCombo status = new Connect4WinningCombo(w1,w2,w3, w4);
                list.add(status);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connect4Status getStatus(String moves){
        char[] allMoves = moves.toCharArray();

        Connect4Status status = new Connect4Status(false, new int[0], "?");


        for (Connect4WinningCombo wc:list){
            if (allMoves[wc.w1] == allMoves[wc.w2] && allMoves[wc.w2] == allMoves[wc.w3] && allMoves[wc.w3] == allMoves[wc.w4] && allMoves[wc.w1] != '?'){
                status = new Connect4Status(true, new int[] {wc.w1,wc.w2,wc.w3, wc.w4}, allMoves[wc.w1] + "");

                break;
            }
        }
        return status;
    }
}
