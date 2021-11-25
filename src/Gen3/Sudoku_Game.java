package Gen3;

import java.util.*;

public class Sudoku_Game {
    int sq_size;
    int[][]map_data;
    int map_size;
    Map<int[], ArrayList<Integer>> candidates_D;
    int[] numbs;
    Random rand;


    Sudoku_Game(int size) {
        //Start timer
        long start_time = System.nanoTime();
        System.out.println("Welcome to sudoku game by NM0zzi3");

        //Sets all fields
        this.sq_size = size;
        this.map_size = this.sq_size *this.sq_size;
        this.map_data = new int[this.map_size][this.map_size];
        this.candidates_D = new HashMap<int[], ArrayList<Integer>>();
        this.numbs = new int[this.map_size];
        this.rand = new Random();

        //Generate numbs
        for(int numb = 0; numb < this.map_size; numb++){
            this.numbs[numb] = numb+1;
        }

        //Generate HashMap with all candidates for all places
        for (int y = 0; y < this.map_size; y++){
            for(int x = 0; x < this.map_size; x++){

                int[] place = {y,x};
                this.candidates_D.put(place, new ArrayList<>());
                for(int candidate_Indx = 0; candidate_Indx < this.map_size; candidate_Indx++){
                    this.candidates_D.get(place).add(this.numbs[candidate_Indx]);
                }
            }
        }



//        for (Map.Entry<int[], ArrayList<Integer>> entry : this.candidates_D .entrySet()) {
//            System.out.println(this.PlaceData(entry.getKey())+" : "+entry.getValue());
//        }



        ////Generate random square

        //Find cords for that square
        int middle_of_map = this.sq_size /2;
        int[] randomSquare_startPoint = new int[] {middle_of_map*this.sq_size +1, middle_of_map*this.sq_size +1};

        //Generate random order in that square
        ArrayList<Integer> candidatesIndex_List = new ArrayList<>();
        for(int i = 0 ; i < this.map_size; i++){candidatesIndex_List.add(i);}
        Collections.shuffle(candidatesIndex_List);

        //Go by every place and set random nub to that place
        int candidateIndex = 0;
        for(int randomSquareY = 0; randomSquareY < this.sq_size; randomSquareY++){
            for(int randomSquareX = 0; randomSquareX < this.sq_size; randomSquareX++) {

                int rSqY = randomSquare_startPoint[0]+randomSquareY;
                int rSqX = randomSquare_startPoint[1]+randomSquareX;
                int[] place = {rSqY, rSqX};


//                this.map_data[rSqY][rSqY] = candidate;
//                this.candidates_D.get(place).remove(candidateIndex);

                candidateIndex++;
            }
        }














        long elapsedTime = System.nanoTime() - start_time;
        double time = Math.round(elapsedTime * 0.0000001) / 100.0;
        System.out.println("Map generated in: " + time + "s");
    }

    public void Show_Map(){
        for (int y = 0; y < this.map_size; y++) {
            for (int x = 0; x < this.map_size; x++) {
                System.out.print(this.map_data[y][x]+" ");
            }System.out.println("");
        }
    }


    private String PlaceData(int[] data){
        return data[0]+" "+data[1];
    }

}