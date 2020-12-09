public class CandidateExistsException extends Exception{

    private String candidateName;

    public CandidateExistsException(String candidateName) {
        this.candidateName = candidateName;
    }

}
