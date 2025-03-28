

/*
 * Explanation Video: https://www.loom.com/share/384b2544f0fd43ba9963e279a1a6a263?sid=381747ee-b0d9-4cb8-b494-e15e03c699ff
 * Execution Video: https://www.loom.com/share/fa9b8858c6314861aee60d55dd3dfd69
 */



import java.util.Scanner;

public class Main {

    // Method to return the number with its ordinal suffix, e.g., 1 -> "1st", 12 -> "12th"
    public static String ordinalSuffix(int num) {
        String numStr = String.valueOf(num);
        // Handle special cases for 11, 12, 13
        if (numStr.endsWith("11") || numStr.endsWith("12") || numStr.endsWith("13")) {
            return numStr + "th";
        }
        // Determine suffix based on the last digit
        char lastChar = numStr.charAt(numStr.length() - 1);
        switch (lastChar) {
            case '1': return numStr + "st";
            case '2': return numStr + "nd";
            case '3': return numStr + "rd";
            default:  return numStr + "th";
        }
    }
    
    // Helper method to determine if the street is east or west based on its type.
    public static String getSide(String streetType) {
        String[] eastTypes = {"street", "place", "way"};
        String[] westTypes = {"avenue", "drive", "lane"};
        
        for (String t : eastTypes) {
            if (t.equalsIgnoreCase(streetType)) {
                return "east";
            }
        }
        for (String t : westTypes) {
            if (t.equalsIgnoreCase(streetType)) {
                return "west";
            }
        }
        return "unknown";
    }

    // Method to compute the preceding street based on the updated assignment logic.
    public static String getPrecedingStreet(String direction, int streetNum, String streetType) {
        // Define the ordering arrays for east and west sides.
        String[] eastTypes = {"street", "place", "way"};
        String[] westTypes = {"avenue", "drive", "lane"};
        int precedingNum = streetNum;
        String precedingType = "";
        
        // Determine if the current street type is east or west.
        boolean isEast = false;
        boolean isWest = false;
        for (String t : eastTypes) {
            if (t.equalsIgnoreCase(streetType)) { 
                isEast = true; 
                break; 
            }
        }
        for (String t : westTypes) {
            if (t.equalsIgnoreCase(streetType)) { 
                isWest = true; 
                break; 
            }
        }
        
        if (isEast) {
            // Find the index in the east array.
            int index = -1;
            for (int i = 0; i < eastTypes.length; i++) {
                if (eastTypes[i].equalsIgnoreCase(streetType)) { 
                    index = i; 
                    break; 
                }
            }
            // If it's the first element, wrap around and decrement the number.
            if (index == 0) {
                precedingNum = streetNum - 1;
                precedingType = eastTypes[eastTypes.length - 1];
            } else {
                precedingType = eastTypes[index - 1];
            }
        } else if (isWest) {
            // Find the index in the west array.
            int index = -1;
            for (int i = 0; i < westTypes.length; i++) {
                if (westTypes[i].equalsIgnoreCase(streetType)) { 
                    index = i; 
                    break; 
                }
            }
            if (index == 0) {
                precedingNum = streetNum - 1;
                precedingType = westTypes[westTypes.length - 1];
            } else {
                precedingType = westTypes[index - 1];
            }
        } else {
            // Fallback for an unrecognized type
            precedingNum = streetNum - 1;
            precedingType = streetType;
        }
        
        // Capitalize the first letter of the preceding street type.
        precedingType = precedingType.substring(0, 1).toUpperCase() + precedingType.substring(1);
        
        return direction + " " + ordinalSuffix(precedingNum) + " " + precedingType;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Get user inputs.
        System.out.print("Enter direction (North/South): ");
        String direction = scanner.nextLine();
        
        System.out.print("Enter street number (e.g., 12): ");
        int streetNum = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter street type (Street, Avenue, Lane, Way, etc.): ");
        String streetType = scanner.nextLine();
        
        // 2. Determine if the street is east or west of Central Avenue based on its type.
        String side = getSide(streetType);
        
        // 3. Determine whether the street is north or south of Washington Street (from user input).
        String northOrSouthOfWashington = direction.equalsIgnoreCase("North") ? "north" : "south";
        
        // 4. Construct the full street name with the ordinal suffix.
        String fullStreetName = direction + " " + ordinalSuffix(streetNum) + " " + streetType;
        
        // 5. Get the preceding street.
        String precedingStreet = getPrecedingStreet(direction, streetNum, streetType);
        
        // 6. Print the output.
        System.out.println(fullStreetName + " is " + streetNum + " blocks " 
                           + side + " of Central Avenue and is " 
                           + northOrSouthOfWashington + " of Washington Street.");
        System.out.println("The preceding street is " + precedingStreet + ".");
        
        scanner.close();
    }
}
