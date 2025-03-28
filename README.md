/*
Street Info Java Program

This program reads user input for a direction (North/South), street number, and street type 
(e.g., Street, Avenue, Lane, Way), and outputs:

- How many blocks the street is from Central Avenue (east or west, based on type)
- Whether the street is north or south of Washington Street
- The name of the preceding street based on a predefined order

---

How to Use

Compile the program:
> javac Main.java

Run with manual input:
> java Main

Run with test input file (PowerShell):
> Get-Content .\test_input_example1.txt | java -cp . Main > .\output_example1.txt

---

Sample Input:
North
12
Avenue

Sample Output:
North 12th Avenue is 12 blocks west of Central Avenue and is north of Washington Street.
The preceding street is North 11th Drive.
*/