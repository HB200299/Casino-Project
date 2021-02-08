package casino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Casino {

    public static int chips;
    private static ArrayList<Members> MemberList = new ArrayList<>();
    public static String name;
    public static int pass;
    private static String folderDirectory = System.getProperty("user.dir") + "\\MemberList.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MemberList = readFile();

        System.out.println("Have you been to this casino before?");
        System.out.println("1-yes");
        System.out.println("2-no");
        int hereBefore = input.nextInt();
        System.out.println("Please enter your name");
        name = input.next();
        if (hereBefore == 1) {
            getMember();
            if (chips == 0) {
                System.out.println("Sorry, you dont have any chips! please come again!");
                //end
            }
        }
        if (hereBefore == 2) {
            System.out.println("Welcome to the casino! Here you start out with only 100 chips, but through the many games you can earn your way up the ranks but purchasing passes,");
            System.out.println("giving you access to more games and higher bets! To start off, you will only have access to blackjack and a bet of up to 50 chips!");
            chips = 100;
            pass = 1;
        }

        while (true) {
            while (pass == 1) {
                if (chips == 0) {
                    System.out.println("Sorry, looks like you've run out of chips! Please come again another time!");
                    end(hereBefore);
                }
                System.out.println("What would you like to do?");
                System.out.println("1-Play Blackjack");
                System.out.println("2-buy the Second Pass (500 chips, you currently have " + chips + " chips)");
                System.out.println("3-Leave the casino");
                int temp = input.nextInt();
                switch (temp) {
                    case 1:
                        Blackjack();
                        break;
                    case 2:
                        if (chips > 499) {
                            chips = chips - 500;
                            pass = 2;
                            System.out.println("For reaching the second pass, here is another 100 chips on the house!");
                            chips = chips + 100;
                            System.out.println("Well done, you can now access Higher or Lower, and your betting limit has increased to 100 chips!");
                        } else {
                            System.out.println("Oh no, looks like you dont have enough chips! Play some games and come back later!");
                        }
                        break;
                    case 3:
                        end(hereBefore);
                        writeFile(MemberList);
                        System.exit(0);
                        break;
                }
            }
            while (pass == 2) {
                if (chips == 0) {
                    System.out.println("Sorry, looks like you've run out of chips! You have been demoted to pass 1!You have been given a consultation of 250 chips.");
                    chips = 250;
                    pass = 1;
                    break;
                }
                System.out.println("What would you like to do?");
                System.out.println("1-Play Blackjack");
                System.out.println("2-Play Higher or Lower");
                System.out.println("3-buy the Third Pass (750 chips, you currently have " + chips + " chips)");
                System.out.println("4-Leave the casino");
                int temp = input.nextInt();
                switch (temp) {
                    case 1:
                        Blackjack();
                        break;
                    case 2:
                        HigherOrLower();
                        break;
                    case 3:
                        if (chips > 749) {
                            chips = chips - 750;
                            pass = 3;
                            System.out.println("For reaching the third pass, here is another 300 chips on the house!");
                            chips = chips + 300;
                            System.out.println("Well done, you can now access the slot machines and can bet any number of your chips!");
                        } else {
                            System.out.println("Oh no, looks like you dont have enough chips! Play some games and come back later!");
                        }
                        break;
                    case 4:
                        end(hereBefore);
                        writeFile(MemberList);
                        System.exit(0);
                        break;
                }
            }
            while (pass == 3) {
                if (chips == 0) {
                    System.out.println("Sorry, looks like you've run out of chips! You have been demoted to pass 2!You have been given a consultation of 500 chips.");
                    chips = 500;
                    pass = 2;
                    break;
                }
                System.out.println("What would you like to do?");
                System.out.println("1-Play Blackjack");
                System.out.println("2-Play Higher or Lower");
                System.out.println("3-Play the Slot Machines");
                System.out.println("4-Leave the casino");
                int temp = input.nextInt();
                switch (temp) {
                    case 1:
                        Blackjack();
                        break;
                    case 2:
                        HigherOrLower();
                        break;
                    case 3:
                        SlotMachine();
                        break;
                    case 4:
                        end(hereBefore);
                        writeFile(MemberList);
                        System.exit(0);
                        break;
                }
            }
        }
    }

    public static void Blackjack() {
        Scanner input = new Scanner(System.in);
        int betChips = chips;

        if (pass == 1) {
            System.out.println("How much are you going to bet? you currently have " + chips + " chips. you can bet up to 50 chips.");
            boolean temp1 = false;
            while (temp1 == false) {
                int temp = input.nextInt();
                if (temp < 51) {
                    betChips = temp;
                    temp1 = true;
                } else {
                    System.out.println("Plese enter a number equal to or below 50");
                }
            }
        }
        if (pass == 2) {
            System.out.println("How much are you going to bet? you currently have " + chips + "chips. you can bet up to 100 chips.");
            boolean temp1 = false;
            while (temp1 == false) {
                int temp = input.nextInt();
                if (temp < 101) {
                    betChips = temp;
                    temp1 = true;
                } else {
                    System.out.println("Plese enter a number equal to or below 100");
                }
            }
        }
        if (pass == 3) {
            System.out.println("How much are you going to bet? you currently have " + chips + "chips.");
            boolean temp1 = false;
            while (temp1 == false) {
                int temp = input.nextInt();
                if (temp < (chips + 1)) {
                    betChips = temp;
                    temp1 = true;
                } else {
                    System.out.println("Plese enter a number equal to or below your total number of chips");
                }
            }
        }

        int round = BlackjackRound();
        int comround = BlackjackComRound();
        if (round > 21) {
            System.out.println("You lost, too bad. You lose all the chips you bet.");
            chips = chips - betChips;
        } else {
            if ((comround > round) && (comround < 22)) {
                System.out.println("You lost, too bad. You lose all the chips you bet.");
                chips = chips - betChips;
            } else {
                if (comround < round) {
                    System.out.println("Good job, you won! your bet chips have been doubled!");
                    chips = chips + betChips;
                }
            }
        }
    }

    public static int BlackjackRound() {
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        int total = 0;
        boolean finnished = false;
        while (finnished == false) {
            int temp = random.nextInt(13) + 1;
            System.out.println("The card you got was ");
            CardCheck(temp);
            total = total + temp;
            if (total > 21) {
                System.out.println("Oh no, your total is " + total + ", thats over 21! you lose this round!");
                finnished = true;
                break;
            }
            System.out.println("Do you want to draw another card?");
            if ((input.next()).equalsIgnoreCase("no")) {
                finnished = true;
                break;
            }
        }
        return total;
    }

    public static int BlackjackComRound() {
        Random random = new Random();
        int total = 0;
        boolean finnished = false;
        while (finnished == false) {
            int temp = random.nextInt(13) + 1;
            System.out.println("The dealer got ");
            CardCheck(temp);
            total=total+temp;
            System.out.println("Their total so far is " + total);
            if ((total > 17)) {
                finnished = true;
            } else {
                System.out.println("They are drawing another card");
            }
        }
        return total;
    }

    public static void CardCheck(int a) {
        if ((a > 1) && (a < 11)) {
            System.out.println("a " + a);
        }
        if ((a == 1)) {
            System.out.println("an Ace");
        }
        if ((a == 11)) {
            System.out.println("a Jack");
        }
        if ((a == 12)) {
            System.out.println("a Queen");
        }
        if ((a == 13)) {
            System.out.println("a King");
        }
    }

    public static void HigherOrLower() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int betChips = chips;

        if (pass == 2) {
            System.out.println("How much are you going to bet? you currently have " + chips + " chips. you can bet up to 100 chips.");
            boolean temp1 = false;
            while (temp1 == false) {
                int temp = input.nextInt();
                if (temp < 101) {
                    betChips = temp;
                    temp1 = true;
                } else {
                    System.out.println("Plese enter a number equal to or below 100");
                }
            }
        }
        if (pass == 3) {
            System.out.println("How much are you going to bet? you currently have " + chips + " chips.");
            boolean temp1 = false;
            while (temp1 == false) {
                int temp = input.nextInt();
                if (temp < (chips + 1)) {
                    betChips = temp;
                    temp1 = true;
                } else {
                    System.out.println("Plese enter a number equal to or below your total number of chips");
                }
            }
        }

        int temp = random.nextInt(13) + 1;
        System.out.println("The first card pulled was ");
        CardCheck(temp);

        System.out.println("Will the next card be higher or lower?");
        System.out.println("1-higher");
        System.out.println("2-lower");
        int ans = input.nextInt();

        int temp2 = random.nextInt(13) + 1;
        System.out.println("The next card is ");
        CardCheck(temp2);

        if (temp2 == temp) {
            System.out.println("The cards are the same! your chips have been returned.");
        }
        switch (ans) {
            case 1:
                if (temp2 > temp) {
                    System.out.println("Good job, you won! your bet chips have been doubled!");
                    chips = chips + betChips;
                }
                if (temp2 < temp) {
                    System.out.println("You lost, too bad. You lose all the chips you bet.");
                    chips = chips - betChips;
                }
                break;
            case 2:
                if (temp2 > temp) {
                    System.out.println("You lost, too bad. You lose all the chips you bet.");
                    chips = chips - betChips;
                }
                if (temp2 < temp) {
                    System.out.println("Good job, you won! your bet chips have been doubled!");
                    chips = chips + betChips;
                }
                break;
        }
    }

    public static void SlotMachine() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int betChips = chips;

        System.out.println("Which slot machine would you like to play on?");
        System.out.println("1-10 chips");
        System.out.println("2-50 chips");
        System.out.println("3-100 chips");
        System.out.println("4-custom chips");
        int ans = input.nextInt();
        System.out.println("The prize is the number shown (if all three are rolled) times the chips bet, or half the number shown (if only two are rolled) times the bet chips (rounded up)");
        switch (ans) {
            case 1:
                betChips = 10;
                break;
            case 2:
                betChips = 50;
                break;
            case 3:
                betChips = 100;
                break;
            case 4:
                System.out.println("How much are you going to bet? you currently have " + chips + "chips.");
                boolean temp1 = false;
                while (temp1 == false) {
                    int temp = input.nextInt();
                    if (temp < (chips + 1)) {
                        betChips = temp;
                        temp1 = true;
                    } else {
                        System.out.println("Plese enter a number equal to or below your total number of chips");
                    }
                }
                break;
        }
        int slot1 = random.nextInt(10) + 1;
        System.out.println("Slot 1 - " + slot1);
        int slot2 = random.nextInt(10) + 1;
        System.out.println("Slot 2 - " + slot2);
        int slot3 = random.nextInt(10) + 1;
        System.out.println("Slot 3 - " + slot3);
        if ((slot1 == slot2) && (slot2 == slot3)) {
            int tempChips = betChips * slot1;
            System.out.println("well done, you've won " + tempChips + "Chips! Jackpot!");
            chips = chips + (tempChips - betChips);
        }
        if ((slot1 != slot2) || (slot2 != slot3) || (slot1 != slot3)) {
            System.out.println("oh no, you've won nothing! Try again next time!");
            chips = chips - betChips;
        }
        if ((slot1 == slot2) && (slot1 != slot3)) {
            if (slot1 == 1) {
                slot1 = 2;
            }
            if (slot1 == 3) {
                slot1 = 4;
            }
            if (slot1 == 5) {
                slot1 = 6;
            }
            if (slot1 == 7) {
                slot1 = 8;
            }
            if (slot1 == 9) {
                slot1 = 10;
            }

            int tempChips = betChips * (slot1 / 2);
            System.out.println("Well done, you've won " + tempChips + "Chips!");
            chips = chips + (betChips - tempChips);
        }
        if ((slot2 == slot3) && (slot1 != slot1)) {
            if (slot2 == 1) {
                slot2 = 2;
            }
            if (slot2 == 3) {
                slot2 = 4;
            }
            if (slot2 == 5) {
                slot1 = 6;
            }
            if (slot2 == 7) {
                slot1 = 8;
            }
            if (slot2 == 9) {
                slot2 = 10;
            }

            int tempChips = betChips * (slot2 / 2);
            System.out.println("Well done, you've won " + tempChips + "Chips!");
            chips = chips + (betChips - tempChips);
        }
        if ((slot1 == slot3) && (slot1 != slot2)) {
            if (slot1 == 1) {
                slot1 = 2;
            }
            if (slot1 == 3) {
                slot1 = 4;
            }
            if (slot1 == 5) {
                slot1 = 6;
            }
            if (slot1 == 7) {
                slot1 = 8;
            }
            if (slot1 == 9) {
                slot1 = 10;
            }

            int tempChips = betChips * (slot1 / 2);
            System.out.println("Well done, you've won " + tempChips + "Chips!");
            chips = chips + (betChips - tempChips);
        }
    }

    public static void writeFile(ArrayList<Members> Member) {
        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < Member.size(); i++) {
                printToFile.println(Member.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    public static ArrayList<Members> readFile() {
        ArrayList<Members> Members = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] MembersDetails = lineFromFile.split(", ");
                Members newMembers = new Members(MembersDetails[0],Integer.parseInt(MembersDetails[1]),Integer.parseInt(MembersDetails[2]));
                Members.add(newMembers);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return Members;
    }

    public static void getMember() {
        Scanner input = new Scanner(System.in);
        if (MemberList.isEmpty()) {
            System.out.println("Sorry, we have no members at the moment.");
        } else {
            for (int i = 0; i < MemberList.size(); i++) {
                if (name.equals(MemberList.get(i).getName())) {
                    chips = MemberList.get(i).getChips();
                    pass = MemberList.get(i).getPass();
                    break;
                }
            }
        }
    }

    public static void end(int a) {
        if (a == 1) {
            for (int i = 0; i < MemberList.size(); i++) {
                if (name.equals(MemberList.get(i).getName())) {
                    MemberList.get(i).setChips(chips);
                    MemberList.get(i).setPass(pass);
                    break;
                }
            }
            
        }
        if (a == 2) {
            Members newMember = new Members(name, pass, chips);
            MemberList.add(newMember);
        }
    }

}