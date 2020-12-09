import org.junit.Test;


public class Examples {

    public Examples() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {};


    Exception e1 = new UnknownCandidateException("gompei");
    Exception e2 = new CandidateExistsException("gompei");
    Exception e3 = new DuplicateVotesException("gompei");
    ElectionData ED = new ElectionData();

    @Test
    public void test1() {
        try {
            ED.addCandidate("a");
            ED.addCandidate("b");
            ED.addCandidate("c");
        } catch (CandidateExistsException e) {
        }
        try {
            ED.processVote("a", "b", "c");
            ED.processVote("b", "a", "c");
            ED.processVote("b", "c", "a");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }
        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();
        

    }



}
