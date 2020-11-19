import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//This project is inspired by Exapunk's wonderdisc
//set in a fictional retro console which uses a string for region lock.
//In this case, we are generating a file meant to represent a disc
//region locked to Europe.
//We read through the data on the disc, saving numbers to an arraylist,
//when we encounter a region code, we instead write -1 to the array.
//After that, we send the array and file to an overwrite method.
//This method will copy correct values on the file, but replace the -1s
//with our region code of NA.

public class Main {

    public static void main(String[] args) {
        File file = new File("src/main/resources/source.txt"); //we start by declaring the file we want to use
        file = write(file);                                             //then we generate data for the file

        ArrayList<Integer> hold = read(file);                           //we can then generate our array list

        file = overWrite(file, hold);                                   //before overwriting our data
    }

    public static File write(File file){                                //This method generates our source file
        try{                                                            //try catch is used to contain our FileWriter
            int rand = (int) (Math.random() * (25 - 2) + 2);            //generates the frequency we will insert our
            FileWriter myWriter = new FileWriter(file);                     //region code
            for(int i = 0; i < 100; i++){
                if(i % rand == 0) {
                    myWriter.write("EU");
                }else{
                    String in;
                    Integer num = (int) (Math.random() * (999 - 1) + 1);
                    in = num.toString();
                    myWriter.write(in);
                }
                myWriter.write(" ");
            }
            myWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }

    public static ArrayList<Integer> read(File file){                   //reads values in file, returns a list
        ArrayList<Integer> out = new ArrayList<>();

        try{                                                            //Try catch statement contains the scanner
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                boolean numeric = true;
                String data = scanner.next();
                try{                                                    //This try catch is used to test if the input is
                    Double num = Double.parseDouble(data);                  //numeric, if it is not, the exception will
                }catch(NumberFormatException e){                            //be thrown and we will write -1 into the
                    numeric = false;                                        //list
                }
                if(numeric == true){
                    out.add(Integer.parseInt(data));
                }else{
                    out.add(-1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static File overWrite(File file, ArrayList<Integer> list){   //overwrite's file with NA region lock data
        try{                                                            //try statement contains FileWriter
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < list.size(); i++){                       //loop will either write list data, or "NA" if
                if(list.get(i) == -1){                                      //numeric value is -1
                    writer.write("NA");
                }else{
                    String out = list.get(i).toString();
                    writer.write(out);
                }
                writer.write(" ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

}
