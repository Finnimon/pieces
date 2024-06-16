package com.gitgud.pieces.view;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class SCity
{
    
    private static final short DEFAULT_WIDTH = 50;
    
    
    private static final short MAP_DIMENSION = 12;
    
    
    private static final String MAP_PATH = "src/main/java/com/gitgud/csv/Map.csv";
    
    
    public static Scene createCityScene(Stage stage)
    {
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, DEFAULT_WIDTH * MAP_DIMENSION, DEFAULT_WIDTH * MAP_DIMENSION);
        int[][] map = new int[MAP_DIMENSION][MAP_DIMENSION];
        
        for (int i = 0; i < 12; i++)
        {
            pane.getRowConstraints().add(new RowConstraints(DEFAULT_WIDTH));
        }
        
        for (int i = 0; i < 12; i++)
        {
            pane.getColumnConstraints().add(new ColumnConstraints(DEFAULT_WIDTH));
        }
        
        try (CSVReader reader = new CSVReader(new FileReader(MAP_PATH)))
        {
            List<String[]> lines = reader.readAll();
            for (int i = 0; i < MAP_DIMENSION; i++)
            {
                for (int j = 0; j < MAP_DIMENSION; j++)
                {
                    map[i][j] = Integer.parseInt(lines.get(i)[j]);
                }
            }
        }
        catch (IOException | CsvException | NumberFormatException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        
        
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                Region region = new Region();
                switch (map[i][j])
                {
                    case 0 -> region.setBackground(
                            new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0))));
                    case 1 -> region.setBackground(
                            new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0))));
                    case 2 ->
                    {
                        region.setBackground(
                                new Background(new BackgroundFill(Color.BROWN, new CornerRadii(0), new Insets(0))));
                        region.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        region.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        region.setOnMouseClicked(e -> stage.setScene(SWrought.createWroughtScene(stage)));
                    }
                }
                pane.add(region, j, i);
            }
        }
        
        pane.setAlignment(Pos.CENTER);
        return scene;
    }
}