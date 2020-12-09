public class DuplicateVotesException extends Exception{

    private String candidateName;

    public DuplicateVotesException(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        return candidateName;
    }
}
