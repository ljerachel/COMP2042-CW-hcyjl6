package test.Model;

/**
 * class that contains the individual scores together with the name
 */
public class IndividualScores implements Comparable{


    private String name;
    private int highscore;


    public IndividualScores( String name , int highscore)
    {

        this.name = name;
        this.highscore = highscore;

    }

    public int getHighscore() {
        return highscore;
    }

    public int compareTo(Object b) {
        int comparescore = ((IndividualScores)b).getHighscore();
        //return this.highscore - comparescore;
        return -(this.highscore -comparescore) ;
    }


    public String getName() {
        return name;
    }



}
