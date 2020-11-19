# WonderDiscOverWrite
______________________

This project is inspired by Exapunk's wonderdisc level, set in a fictional console which region locks games with randomly dispersed region codes in the game's start up file. In this case, we are generating a file meant to represent this disc file, region locked to Europe. We read through data on the disc, saving numbers to an array list and the string region codes as a -1. We then overwrite the file, maintaining the position of valid data and replacing any string instances with the US region code of NA.   

## Generating our source file
_____________

In order to overwrite a file, we need need a file to read! In this project, we generate the file with a method named write. In order to ensure our overwrite method is robust, we will randomly generate an interval to insert the "EU" region code and generate a random number for any other value. this is done with the line below.   

>int rand = (int) (Math.random() * (25 - 2) + 2);    
>...   
for(int i = 0; i < 100; i++){   
                &nbsp;&nbsp;&nbsp;&nbsp;if(i % rand == 0) {   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;myWriter.write("EU");      
                &nbsp;&nbsp;&nbsp;&nbsp;}else{   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String in;   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Integer num = (int) (Math.random() * (999 - 1) + 1);   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;in = num.toString();   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;myWriter.write(in);   
                &nbsp;&nbsp;&nbsp;&nbsp;}      
...   
   
## Reading our file
___________

Now that we have generated our source file we can begin our main logic. Before overwriting our file, we call a method named read. The goal of this method is to create an array list of integers, we save any numeric values to our array, however we cannot save our region code to this array! How do we get around this? In this case I encapsulated the input into a try catch, we try to parse the input string as a numeric value, and if it fails, then we throw a boolean, named numeric, as false and instead write -1 to the array, as seen below.   

>while (scanner.hasNext()){   
                &nbsp;&nbsp;&nbsp;&nbsp;boolean numeric = true;   
                &nbsp;&nbsp;&nbsp;&nbsp;String data = scanner.next();   
                &nbsp;&nbsp;&nbsp;&nbsp;try{                                                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Double num = Double.parseDouble(data);                  
                &nbsp;&nbsp;&nbsp;&nbsp;}catch(NumberFormatException e){                            
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;numeric = false;                                       
                &nbsp;&nbsp;&nbsp;&nbsp;}   
                &nbsp;&nbsp;&nbsp;&nbsp;if(numeric == true){   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;out.add(Integer.parseInt(data));   
                &nbsp;&nbsp;&nbsp;&nbsp;}else{   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;out.add(-1);   
                &nbsp;&nbsp;&nbsp;&nbsp;}   
            }   

## Overwriting 
________________

Now that we have an array populated with the source file's numeric value's and -1 where we had a string rather than a number, we are ready to overwrite our file. This is done by overwriting our file with our array list, however when the value in the list is -1, we instead write our region code of NA. This code can be seen below.   

>for(int i = 0; i < list.size(); i++){                       
                &nbsp;&nbsp;&nbsp;&nbsp;if(list.get(i) == -1){                                     
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;writer.write("NA");   
                &nbsp;&nbsp;&nbsp;&nbsp;}else{   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;String out = list.get(i).toString();   
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;writer.write(out);   
                &nbsp;&nbsp;&nbsp;&nbsp;}   
                &nbsp;&nbsp;&nbsp;&nbsp;writer.write(" ");   
            }   
            
## Result
_______
Our end result here allows us to transition between the first and second files below. This was a fun way to apply a puzzle from a game to a real world system. However, there is maybe only one lesson from this project that I can apply to other projects. That would be the ability to test if a value is a number or string via a try catch block.   

>EU 296 489 104 696 503 978 756 158 481   
619 EU 619 688 404 998 796 27 722 553   
20 210 EU 474 273 754 448 689 599 812   
908 643 295 EU 912 319 294 272 633 524  
755 862 596 155 EU 328 766 756 282 168   
970 178 589 402 258 EU 734 854 176 524   
768 702 800 58 844 496 EU 28 20 403 902   
144 933 278 167 516 620 EU 782 336 979   
104 272 773 412 274 906 41 EU 689 693   
623 870 243 775 147 333 322 713 EU    
   
   
>NA 296 489 104 696 503 978 756 158 481   
619 NA 619 688 404 998 796 27 722 553   
20 210 NA 474 273 754 448 689 599 812   
908 643 295 NA 912 319 294 272 633 524   
755 862 596 155 NA 328 766 756 282 168   
970 178 589 402 258 NA 734 854 176 524   
768 702 800 58 844 496 NA 28 20 403 902   
144 933 278 167 516 620 NA 782 336 979   
104 272 773 412 274 906 41 NA 689 693   
623 870 243 775 147 333 322 713 NA 
