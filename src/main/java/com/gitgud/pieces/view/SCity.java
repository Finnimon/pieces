package com.gitgud.pieces.view;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 02.06.2024
 */

public class SCity
{
    
    
    private static int playerX = 11;
    
    
    private static int playerY = 5;
    
    
    public static Scene createCityScene(Stage stage)
    {
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid,
                                Constants.DEFAULT_WIDTH * Constants.MAP_DIMENSION,
                                Constants.DEFAULT_WIDTH * Constants.MAP_DIMENSION);
        int[][] map = new int[Constants.MAP_DIMENSION][Constants.MAP_DIMENSION];
        
        try (CSVReader reader = new CSVReader(new FileReader(Constants.MAP_PATH)))
        {
            List<String[]> lines = reader.readAll();
            for (int i = 0; i < Constants.MAP_DIMENSION; i++)
            {
                for (int j = 0; j < Constants.MAP_DIMENSION; j++)
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
        
        Rectangle rect = new Rectangle(5, 5, Constants.DEFAULT_WIDTH - 10, Constants.DEFAULT_WIDTH - 10);
        
        
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                Pane pane = new Pane();
                pane.setMinHeight(Constants.DEFAULT_WIDTH);
                pane.setMinWidth(Constants.DEFAULT_WIDTH);
                int finalJ = j;
                int finalI = i;
                switch (map[i][j])
                {
                    case 0 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                    }
                    case 1 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e ->
                                               {
                                                   if ((Math.abs(finalJ - playerY) == 1 &&
                                                        Math.abs(finalI - playerX) == 0) ||
                                                       (Math.abs(finalJ - playerY) == 0 &&
                                                        Math.abs(finalI - playerX) == 1))
                                                   {
                                                       grid.setCursor(Cursor.HAND);
                                                   }
                                               });
                        pane.setOnMouseExited(e ->
                                              {
                                                  grid.setCursor(Cursor.DEFAULT);
                                              });
                        pane.setOnMouseClicked(e ->
                                               {
                                                   if ((Math.abs(finalJ - playerY) == 1 &&
                                                        Math.abs(finalI - playerX) == 0) ||
                                                       (Math.abs(finalJ - playerY) == 0 &&
                                                        Math.abs(finalI - playerX) == 1))
                                                   {
                                                       grid.getChildren().removeAll(rect);
                                                       try
                                                       {
                                                           getGridCell(grid, playerX + 1, playerY).setBorder(null);
                                                           getGridCell(grid, playerX - 1, playerY).setBorder(null);
                                                           getGridCell(grid, playerX, playerY + 1).setBorder(null);
                                                           getGridCell(grid, playerX, playerY - 1).setBorder(null);
                                                       }
                                                       catch (NullPointerException ex)
                                                       {
                                                           System.out.println(ex.getMessage());
                                                           System.exit(2);
                                                       }
                                                       rect.setX(5);
                                                       rect.setY(5);
                                                       playerY = finalJ;
                                                       playerX = finalI;
                                                       try
                                                       {
                                                           if (playerX < Constants.MAP_DIMENSION - 1 &&
                                                               map[playerX + 1][playerY] == 1)
                                                           {
                                                               getGridCell(grid,
                                                                           playerX + 1,
                                                                           playerY).setBorder(new Border(new BorderStroke(
                                                                       Color.LIGHTGREEN,
                                                                       BorderStrokeStyle.SOLID,
                                                                       new CornerRadii(4),
                                                                       new BorderWidths(3))));
                                                           }
                                                           if (playerX > 0 && map[playerX - 1][playerY] == 1)
                                                           {
                                                               getGridCell(grid,
                                                                           playerX - 1,
                                                                           playerY).setBorder(new Border(new BorderStroke(
                                                                       Color.LIGHTGREEN,
                                                                       BorderStrokeStyle.SOLID,
                                                                       new CornerRadii(4),
                                                                       new BorderWidths(3))));
                                                           }
                                                           if (playerY < Constants.MAP_DIMENSION - 1 &&
                                                               map[playerX][playerY + 1] == 1)
                                                           {
                                                               getGridCell(grid,
                                                                           playerX,
                                                                           playerY +
                                                                           1).setBorder(new Border(new BorderStroke(
                                                                       Color.LIGHTGREEN,
                                                                       BorderStrokeStyle.SOLID,
                                                                       new CornerRadii(4),
                                                                       new BorderWidths(3))));
                                                           }
                                                           if (playerY > 0 && map[playerX][playerY - 1] == 1)
                                                           {
                                                               getGridCell(grid,
                                                                           playerX,
                                                                           playerY -
                                                                           1).setBorder(new Border(new BorderStroke(
                                                                       Color.LIGHTGREEN,
                                                                       BorderStrokeStyle.SOLID,
                                                                       new CornerRadii(4),
                                                                       new BorderWidths(3))));
                                                           }
                                                       }
                                                       catch (NullPointerException ex)
                                                       {
                                                           System.out.println(ex.getMessage());
                                                           System.exit(2);
                                                       }
                                                       pane.getChildren().add(rect);
                                                   }
                                               });
                    }
                    case 2 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(SWrought.createWroughtScene(stage)));
                        Tooltip.install(pane, new Tooltip("Schmiede"));
                    }
                    case 3 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(STale.createTaleScene(stage)));
                        Tooltip.install(pane, new Tooltip("Hauptquatier"));
                    }
                    case 4 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(STavern.createTavernScene(stage)));
                        Tooltip.install(pane, new Tooltip("Schenke"));
                    }
                    case 5 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(SMagicAmplifier.createMAScene(stage)));
                        Tooltip.install(pane, new Tooltip("Magieverstärker"));
                    }
                    case 6 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.TURQUOISE,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(SBasisCamp.createBasiscampScene(stage)));
                        Tooltip.install(pane, new Tooltip("Basiscamp"));
                    }
                    case 7 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.TOMATO,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(SFactionCamps.createFactionCampsScene(stage)));
                        Tooltip.install(pane, new Tooltip("Fraktionscamp"));
                    }
                    case 8 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(SMarketplace.createMarketplaceScene(stage)));
                        Tooltip.install(pane, new Tooltip("Marktplatz"));
                    }
                    case 9 ->
                    {
                        pane.setBackground(new Background(new BackgroundFill(Color.DEEPPINK,
                                                                             new CornerRadii(0),
                                                                             new Insets(0))));
                        pane.setOnMouseEntered(e -> scene.setCursor(Cursor.HAND));
                        pane.setOnMouseExited(e -> scene.setCursor(Cursor.DEFAULT));
                        pane.setOnMouseClicked(e -> stage.setScene(STrainingCamp.createTrainingCampScene(stage)));
                        Tooltip.install(pane, new Tooltip("Trainingscamp"));
                    }
                }
                grid.add(pane, j, i);
                if (j == playerY && i == playerX)
                {
                    rect.setFill(Color.ORANGE);
                    pane.getChildren().add(rect);
                }
            }
        }
        
        getGridCell(grid, playerX - 1, playerY).setBorder(new Border(new BorderStroke(Color.LIGHTGREEN,
                                                                                      BorderStrokeStyle.SOLID,
                                                                                      new CornerRadii(4),
                                                                                      new BorderWidths(3))));
        grid.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        return scene;
    }
    
    
    private static Pane getGridCell(GridPane pane, int x, int y)
    {
        for (Node node : pane.getChildren())
        {
            if (GridPane.getRowIndex(node) != null &&
                GridPane.getRowIndex(node) == x &&
                GridPane.getColumnIndex(node) != null &&
                GridPane.getColumnIndex(node) == y)
            {
                return (Pane) node;
            }
        }
        return new Pane();
    }
}