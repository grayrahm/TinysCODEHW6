import java.rmi.activation.UnknownObjectException;
import java.util.Scanner;

public class VotingMachine {

    private ElectionData election;

    public VotingMachine(ElectionData election) {
        this.election = election;
    }

    Scanner keyboard = new Scanner(System.in);

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : election.getBallot()) {
            System.out.println(s);
        }
    }

    public void addWriteIn(String candidate) throws CandidateExistsException {
        try{
            election.addCandidate(candidate);
            System.out.println("The candidate was successfully added");
        }

        catch(CandidateExistsException e){
            System.out.println("The candidate you input already exists");
        }


    }


    public void screen() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException {
        this.printBallot();
        System.out.println("Who do you want to vote for as your first choice?");
        String candidate1 = keyboard.next();
        System.out.println("Who do you want to vote for as your second choice?");
        String candidate2 = keyboard.next();
        System.out.println("Who do you want to vote for as your third choice?");
        String candidate3 = keyboard.next();
        try {
            election.processVote(candidate1, candidate2, candidate3);
        }

        catch(UnknownCandidateException e){
            System.out.println("You voted for an unknown candidate");
            System.out.println("Type 'y' if you would like to add that candidate to your ballot");
            String yes = keyboard.next();
            if(yes.equals("y") || yes.equals("Y")){
                this.addWriteIn(e.getCandidateName());
            }

            this.screen();
        }

        catch(DuplicateVotesException e){
            System.out.println("You cannot vote for the same candidate twice >:(");
            this.screen();
        }


       // System.out.println("You voted for " + candidate);
    }
}
