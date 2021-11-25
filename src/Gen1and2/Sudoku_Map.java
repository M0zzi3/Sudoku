package Gen1and2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sudoku_Map {
    int map_Size;
    int[][] data;
    int sqSize;
    ArrayList<Integer> numbs;
    ArrayList<int[]> startPointL;

    Sudoku_Map(int size) {
        long startTime = System.nanoTime();

        this.map_Size = size;
        this.sqSize = this.map_Size * this.map_Size;
        this.data = new int[sqSize][sqSize];
        this.numbs = new ArrayList<Integer>();
        for (int i = 1; i <= sqSize; i++) {
            this.numbs.add(i);
        }


        //Find all box position
        ArrayList<int[]> diagonalBoxesStartPointList = new ArrayList<int[]>();
        ArrayList<int[]> verticalBoxesStartPointList = new ArrayList<int[]>();
        int dx = 0;
        int vx = this.map_Size;

        for (int BoxY = 0; BoxY < this.sqSize; BoxY += this.map_Size) {
            for (int BoxX = 0; BoxX < this.sqSize; BoxX += this.map_Size * 2) {
                int finaldiagonalX = BoxX + dx;
                int finalverticalX = BoxX + vx;

                if (finaldiagonalX < this.sqSize) {
                    diagonalBoxesStartPointList.add(new int[]{BoxY, finaldiagonalX});
                }
                if (finalverticalX < this.sqSize) {
                    verticalBoxesStartPointList.add(new int[]{BoxY, finalverticalX});
                }

            }
            //switch dx and vx
            if (dx == 0) {
                dx = this.map_Size;
                vx = 0;
            } else {
                dx = 0;
                vx = this.map_Size;
            }
        }


        //Fill Diagonal boxes
        for (int[] boxStartPoint : diagonalBoxesStartPointList) {
            this.Fill_Diagonal_Box(boxStartPoint);
        }

        //Fill Vertical boxes
        for (int[] boxStartPoint : verticalBoxesStartPointList){
            this.Fill_Vertical_Box(boxStartPoint);
        }


        long elapsedTime = System.nanoTime() - startTime;
        double time = Math.round(elapsedTime * 0.0000001) / 100.0;
        System.out.println("Map generated in: " + time + "s");
    }

    public void Show_map() {

        for (int[] row : this.data) {
            for (int field : row) {
                System.out.print(" " + field + " ");
                if (field < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            System.out.println("");
        }

    }

    public void Fill_Vertical_Box(int[] startPoint) {

        //Make dictionary of all candidates for places
        Map<int[], ArrayList<Integer>> desiredPlaces  = new HashMap<int[], ArrayList<Integer>>();

        //Make dictionary of number prefer for places
        Map <Integer, ArrayList<int[]>> numbsPreferences = new HashMap<Integer, ArrayList<int[]>>();
        for (int numb : this.numbs){
            numbsPreferences.put(numb, new ArrayList<>());
        }

        for (int bY = 0; bY < this.map_Size; bY++) {
            for (int bX = 0; bX < this.map_Size; bX++) {
                int numbYpos = startPoint[0] + bY;
                int numbXpos = startPoint[1] + bX;
                int[] key = {numbYpos, numbXpos};

                ArrayList<Integer> candidates = new ArrayList<>();

                for(int numb : this.numbs){
                    if (this.Test_numb(numb, key)){
                        candidates.add(numb);
                        numbsPreferences.get(numb).add(key);
                    }
                }
                desiredPlaces .put(key, candidates);
            }
        }
        //Show candidates
        System.out.println("Candidates: ");

        for (Map.Entry<int[], ArrayList<Integer>> entry : desiredPlaces .entrySet()) {
            System.out.println(entry.getKey()[0]+" "+entry.getKey()[1]+" : "+entry.getValue());
        }System.out.println("  ");

        //Show nubs pref
        System.out.println("Numbs pref: ");

        for (Map.Entry<Integer, ArrayList<int[]>> entry : numbsPreferences . entrySet()){
            System.out.print(entry.getKey()+" : | ");
            for (int[] val : entry.getValue()){
                System.out.print(val[0]+" "+val[1]+" | ");
            }System.out.println("");
        }System.out.println(" ");


        ;
        //Sort by number of candidates for place
        ArrayList<ArrayList<Integer>> sortedCandidatesList = new ArrayList<>();
        ArrayList<int[]> sortedKeyPlace = new ArrayList<>();

        for (Map.Entry<int[], ArrayList<Integer>> entry : desiredPlaces .entrySet()) {
            sortedKeyPlace.add(entry.getKey());
            sortedCandidatesList.add(entry.getValue());
        }

        for(int i = 0; i < sortedCandidatesList.size()-1; i++){
            boolean pass = true;
            for(int j = 0; j < sortedCandidatesList.size()-1-i; j++){
                if(sortedCandidatesList.get(j).size() > sortedCandidatesList.get(j+1).size()){


                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.addAll(sortedCandidatesList.get(j));
                    sortedCandidatesList.set(j, sortedCandidatesList.get(j+1));
                    sortedCandidatesList.set(j+1, temp);

                    int[] temp2 = sortedKeyPlace.get(j);
                    sortedKeyPlace.set(j, sortedKeyPlace.get(j+1));
                    sortedKeyPlace.set(j+1, temp2);

                    pass = false;
                }
            }
            if (pass){
                break;
            }
        }
        System.out.println("---");

        //Show sorted places and candidates
        for(int k = 0; k< sortedCandidatesList.size(); k++){
            System.out.println(sortedKeyPlace.get(k)[0]+" "+sortedKeyPlace.get(k)[1]+" : "+sortedCandidatesList.get(k));
        }


        //Go by very place in correct order
        ArrayList<Integer> usedNubs = new ArrayList<>();
        ArrayList<int[]> susEdPlaces = new ArrayList<>();//SuSued                    //⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀
        for (int[] keyPlace : sortedKeyPlace){                                       //⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀
            System.out.println(" { "+keyPlace[0]+" "+keyPlace[1]+" } : ");           //⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀
            ArrayList <Integer> candidateForPlace = desiredPlaces .get(keyPlace);    //⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀
            System.out.println("All candidates for this place: "+candidateForPlace); //⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀
            System.out.println("Already usednumbers: "+usedNubs);                    //⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀
                                                                                     //⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀
            Collections.shuffle(candidateForPlace);                                  //⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀
                                                                                     //⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀
            susEdPlaces.add(keyPlace);                                               //⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀
                                                                                     //⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀
                                                                                     //⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀
            //Test all candidates                                                    //⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀
                                                                                     //⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀
            for (int candidate : candidateForPlace) {                                //⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀
                System.out.println("Candidate: "+candidate);                         //⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀
                                                                                     //⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀
                //Test all places list                                               //⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀
                boolean pass = true;                                                 //⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀

                //test all other places that this candidate prefer
                TestOtherPlaces:
                for (int[] testplace : numbsPreferences.get(candidate)){

                    boolean testpalcepass = true;
                    for (int[] usedplace : susEdPlaces){
                        if((testplace[0] == usedplace[0])&&(testplace[1] == usedplace[1])){
                            testpalcepass = false;
                        }
                    }

                    if(testpalcepass){
                        System.out.println("testing place: ("+testplace[0]+" "+testplace[1]+") : "+desiredPlaces.get(testplace));

                        if(desiredPlaces.get(testplace).size() < 2){
                            System.out.println("Candidate needed in other place");
                            pass = false;
                            break TestOtherPlaces;
                        }else {
                            //Find candiate indx
                            int tcIndx = 0;
                            for (int tc : desiredPlaces.get(testplace)){
                                if (desiredPlaces.get(testplace).get(tcIndx) == candidate){
                                    desiredPlaces.get(testplace).remove(tcIndx);
                                    break TestOtherPlaces;
                                }

                                tcIndx++;
                            }

                        }

                    }

                }


                if (pass){
                    System.out.println("Found! : "+candidate);
                    this.data[keyPlace[0]][keyPlace[1]] = candidate;
                    usedNubs.add(candidate);
                    break;
                }

            }
        }
    }

    public void Fill_Diagonal_Box(int[] startPoint) {

        while (true) {

            //Copy and mix number list
            ArrayList<Integer> boxNumbs = new ArrayList<>();
            for (int n : this.numbs) {
                boxNumbs.add(n);
            }
            Collections.shuffle(boxNumbs);



            //Goes by every place
            System.out.println("Fillbox");
            int filledplaces = 0;

            for (int bY = 0; bY < this.map_Size; bY++) {
                for (int bX = 0; bX < this.map_Size; bX++) {
                    int numbYpos = startPoint[0] + bY;
                    int numbXpos = startPoint[1] + bX;

                    System.out.println("Pos: " + numbYpos + " " + numbXpos);
                    //Test all numbs


                    for (int numbIndx = 0; numbIndx < boxNumbs.size(); numbIndx++) {
                        int numb = boxNumbs.get(numbIndx);
                        System.out.println(numb);

                        if (this.Test_numb(numb, new int[] {numbYpos, numbXpos})) {
                            this.data[numbYpos][numbXpos] = numb;
                            boxNumbs.remove(numbIndx);
                            filledplaces++;
                            break;
                        }
                    }


                }
            }
            if (filledplaces == this.sqSize){
                break;
            }
        }
    }





//            int randindx = get_random(boxnumbs.size());
//            int randNumb = boxnumbs.get(randindx);

//            System.out.println(randNumb);


    public boolean Test_numb (int numb, int[] pos){
        boolean pass = true;
        //Test row
        for (int ty : this.data[pos[0]]){
            if (ty == numb){
                pass = false;
            }
        }

        //Test column
        for (int iy = 0; iy < this.sqSize; iy++){
            if (this.data[iy][pos[1]] == numb){
                pass = false;
            }
        }

        return pass;
    }



    public int get_random(int range){
        return (int) Math.floor((Math.random()*range));
    }

    public boolean Check_in_Array(ArrayList<Integer> testlist, int look4){
        for (int t : testlist){
            if (t == look4){
                return true;
            }
        }
        return false;
    }




}
