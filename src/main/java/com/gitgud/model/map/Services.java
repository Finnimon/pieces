package com.gitgud.model.map;


public record Services()
{
    //    public static int calculateDistance(Tile tile1, Tile tile2)
    //    {
    //        int xDistance = Math.abs(tile1.getXPosition() - tile2.getXPosition());
    //        int yDistance = Math.abs(tile1.getYPosition() - tile2.getYPosition());
    //
    //        return xDistance + yDistance;
    //    }
    //
    //
    //    public boolean[][] getReachableTiles(Tile tiles[][], Tile start, int range)
    //    {
    //        boolean[][] reachableTiles = new boolean[tiles.length][tiles[0].length];
    //        Arrays.stream(Arrays.stream(tiles).flatMap(Arrays::stream)
    //                        .toArray(Tile[]::new))
    //                .filter(tile -> calculateDistance(tile, start) <= range)
    //                .forEach(tile -> reachableTiles[tile.getXPosition()][tile.getYPosition()] = true);
    //
    //
    //        return reachableTiles;
    //    }
}
