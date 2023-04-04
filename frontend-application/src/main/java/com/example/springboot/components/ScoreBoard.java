package com.example.springboot.components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {
    private List<Game> games;
    protected static int uniqueIndex=0;

    public ScoreBoard() {
        games = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void updateScore(int gameIndex, int homeScore, int awayScore) {
        Game game = games.get(gameIndex);
        game.setScore(homeScore, awayScore);
    }

    public void finishGame(int gameIndex) {
        for (Game game:games){
            if(game.getGameIndex()==gameIndex){
                games.remove(game);
                break;
            }
        }
    }

    public List<Game> getSummary() {
        List<Game> sortedGames = new ArrayList<>(games);
        sortedGames.sort(Comparator.comparing(Game::getTotalScore).reversed()
                .thenComparing(Game::getStartTime).reversed());
        return sortedGames;
    }

    private static class Game {
        private int gameIndex;
        private String homeTeam;
        private String awayTeam;
        private int homeScore;
        private int awayScore;
        private long startTime;

        public Game(String homeTeam, String awayTeam) {
            this.gameIndex = uniqueIndex;
            uniqueIndex++;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = 0;
            this.awayScore = 0;
            this.startTime = System.currentTimeMillis();
        }

        public int getTotalScore() {
            return homeScore + awayScore;
        }

        public int getGameIndex() {
            return gameIndex;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setScore(int homeScore, int awayScore) {
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }

        @Override
        public String toString() {
            return homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam;
        }
    }
}
