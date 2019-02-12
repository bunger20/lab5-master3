package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private String first;
    private String last;
    private int uni;
    private String team;

    public HashMap<String,SoccerPlayer> htable = new HashMap();




    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        String key = firstName + " ## " + lastName;
        if(htable.containsKey(key) == true) {
            return false;
        }

        SoccerPlayer newGuy = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        htable.put(key, newGuy);
         return true;

	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if(htable.containsKey(key) == true){
            htable.remove(key);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if(htable.containsKey(key) == true){
            return htable.get(key);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpGoals();
            return true;
        }

        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpAssists();
            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpShots();
            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpSaves();
            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpFouls();
            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer result = getPlayer(firstName, lastName);

        if(result != null){
            result.bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
       if(teamName == null){
            return htable.size();
       }
        int count = 0;
        Collection<SoccerPlayer> collection = htable.values();
        for(SoccerPlayer i: collection){
           if(i.getTeamName().equals(teamName)){
               count++;
           }
        }

        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
       int count = 0;
        Collection<SoccerPlayer> collection = htable.values();
        if(teamName == null){
            for(SoccerPlayer i: collection){

                if(count == idx){
                    return i;
                }
                count++;
            }
        }

    else {
            for (SoccerPlayer i : collection) {
                if (teamName.equals(i.getTeamName())) {

                    if (count == idx) {
                        return i;
                    }
                    count++;
                }
            }


        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {

        try {
            PrintWriter f = new PrintWriter(file);
            Collection<SoccerPlayer> collection = htable.values();
            for(SoccerPlayer i: collection) {
                f.write(logString(i.getFirstName()));
                f.write(logString(i.getLastName()));
                f.write(logString("" + i.getUniform()));
                f.write(logString(i.getTeamName()));
                f.write(logString("" + i.getGoals()));
                f.write(logString("" + i.getAssists()));
                f.write(logString("" + i.getYellowCards()));
                f.write(logString("" + i.getRedCards()));
                f.write(logString("" + i.getFouls()));
                f.write(logString("" + i.getSaves()));
                f.write(logString("" + i.getShots()));
                f.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
