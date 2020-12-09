public class UnknownCandidateException extends Exception{

    private String candidateName;

    public UnknownCandidateException(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        return candidateName;
    }
}
