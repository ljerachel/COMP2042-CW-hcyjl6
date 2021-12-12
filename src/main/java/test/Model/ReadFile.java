package test.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * read and arrange file in array list of IndividualScores in descending order
 */
public class ReadFile {
    private int ranking = 1  ;
    private String name;
    private int highscore;


    private static ArrayList<IndividualScores> aList = new ArrayList<IndividualScores>();

    String row  = "";

    /**
     * read highscore.csv file into an array list and sort, ranking is added after sorting
     */
    public ReadFile() throws IOException {


        File a = new File("src/main/resources/Misc/Highscore.csv");

        BufferedReader csvread ;

            csvread = new BufferedReader(new FileReader(a));




            for(int i = 0; i < 9  ; i ++)
            {

                    row = csvread.readLine();
                    System.out.println(row);

                    if(row!= null) {
                        String[] data = row.split(",");
                        name = data[0];
                        highscore = Integer.parseInt(data[1]);
                        aList.add(new IndividualScores(name, highscore));


                    }
                }


            Collections.sort(aList);  // sort file

        FileWriter writer = new FileWriter(a);



        for(IndividualScores score: aList ) {
            writer.write( score.getName() + ',' + score.getHighscore() +','+ ranking + '\n');
            ranking += 1;
        }


        writer.close();


    }






}



