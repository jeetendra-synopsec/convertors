package SynopSec;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CvsToJsonConverter{
    private static final long serialVersionUID = 1L;
    private static File CSVFile;
    private static BufferedReader read;
    private static BufferedWriter write;

    public CvsToJsonConverter()
    {
        
    }

    public static void main(String args[])
    {
    	CSVFile = new File("Sample.csv");
    	
    	CvsToJsonConverter parse = new CvsToJsonConverter();
        parse.convert();

        System.exit(0);
    }

    private void convert(){
        /*Converts a .csv file to .json. Assumes first line is header with columns*/
        try {
            read = new BufferedReader(new FileReader(CSVFile));

            String outputName = CSVFile.toString().substring(0, 
                    CSVFile.toString().lastIndexOf(".")) + ".json"; 
            String jsonValue = "";
            
            write = new BufferedWriter(new FileWriter(new File(outputName)));

            String line;
            String columns[]; //contains column names
            int num_cols;
            String tokens[];

            int progress = 0; //check progress

            //initialize columns
            line = read.readLine(); 
            columns = line.split(",");
            num_cols = columns.length;

            jsonValue = jsonValue + "[";
           // write.write("["); //begin file as array
            line = read.readLine();


            while(true) 
            {
                tokens = line.split(",");

                if (tokens.length == num_cols){ //if number columns equal to number entries
                	jsonValue = jsonValue + "{";
                	//write.write("{");

                    for (int k = 0; k < num_cols; ++k){ //for each column 
                        if (tokens[k].matches("^-?[0-9]*\\.?[0-9]*$")){ //if a number
                        	jsonValue = jsonValue + "\"" + columns[k] + "\": " + tokens[k];
                           // write.write("\"" + columns[k] + "\": " + tokens[k]);
                            if (k < num_cols - 1) write.write(", ");                                                }
                        else { //if a string
                        	jsonValue = jsonValue + "\"" + columns[k] + "\": \"" + tokens[k] + "\"";
                            //write.write("\"" + columns[k] + "\": \"" + tokens[k] + "\"");
                            if (k < num_cols - 1) write.write(", ");
                        }
                    }

                    ++progress; //progress update
                    if (progress % 10000 == 0) System.out.println(progress); //print progress           


                    if((line = read.readLine()) != null){//if not last line
                    	jsonValue = jsonValue + "}";
                    //	write.write("},");
                    	jsonValue = jsonValue + ",\n";
                       // write.newLine();
                    }
                    else{
                    	jsonValue = jsonValue + "}]";
                        //write.write("}]");//if last line
                        jsonValue = jsonValue + "\n";
                        // write.newLine();
                        break;
                    }
                }
                
            }

          System.out.println("CvsToJsonConverter.convert() comverted value -="+jsonValue);

         //   write.close();
            read.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
    }
    }