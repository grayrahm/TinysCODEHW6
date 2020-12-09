import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


class ElectionData {
  private LinkedList<String> ballot = new LinkedList<String>();
  private LinkedList<String> votes = new LinkedList<String>();
  private HashMap<String, Integer> firstVotes = new HashMap<String, Integer>();
  private HashMap<String, Integer> secondVotes = new HashMap<String, Integer>();
  private HashMap<String, Integer> thirdVotes = new HashMap<String, Integer>();

  
  ElectionData() {
    this.ballot.add("Gompei");
    this.ballot.add("Husky");
  }

  public LinkedList<String> getBallot() {
    return ballot;
  }

  public LinkedList<String> getVotes() {
    return votes;
  }

  public HashMap<String, Integer> getFirstVotes() {
    return firstVotes;
  }

  public HashMap<String, Integer> getSecondVotes() {
    return secondVotes;
  }

  public HashMap<String, Integer> getThirdVotes() {
    return thirdVotes;
  }

  public void processVote(String f, String s, String t)
          throws UnknownCandidateException, DuplicateVotesException {

    int x = 0;
    for(String st: ballot){
      if(st.equals(f)){
        x++;
      }
    }

    if(x!= 1){
      throw new UnknownCandidateException(f);
    }
    //
    int y = 0;
    for(String st: ballot){
      if(st.equals(s)){
        y++;
      }
    }

    if(y!= 1){
      throw new UnknownCandidateException(s);
    }

    //

    int z = 0;
    for(String st: ballot){
      if(st.equals(t)){
        z++;
      }
    }

    if(z!= 1){
      throw new UnknownCandidateException(t);
    }

    if(f.equals(s))
      throw new DuplicateVotesException(f);

    if(f.equals(t))
      throw new DuplicateVotesException(f);

    if(s.equals(t))
      throw new DuplicateVotesException(s);

    this.firstVotes.put(f, this.firstVotes.get(f)+1);
    this.secondVotes.put(s, this.secondVotes.get(s)+1);
    this.thirdVotes.put(t, this.thirdVotes.get(t)+1);
  }



  public void addCandidate(String candidate) throws CandidateExistsException{

    for(String s: ballot){
      if(s.equals(candidate)){
        throw new CandidateExistsException(candidate);
      }
    }

    this.firstVotes.put(candidate, 0);
    this.secondVotes.put(candidate, 0);
    this.thirdVotes.put(candidate, 0);

    this.ballot.add(candidate);
  }

  public int countVotes(String forCand) {
    int numvotes = 0;
    for (String s : votes) {
      if (s.equals(forCand))
        numvotes = numvotes+1;
    }
    return numvotes;
    }

    public String findWinnerMostFirstVotes(){

    double totalVotes = 0.0;
    for(Map.Entry<String, Integer> map : firstVotes.entrySet()){
      totalVotes = totalVotes + map.getValue();
    }

    for(Map.Entry<String, Integer> map : firstVotes.entrySet()){
      if(map.getValue()/totalVotes >= 0.5){
        return map.getKey();
      }
    }

    return "Runoff required";
    }

    public String findWinnerMostPoints(){

    HashMap<String, Integer> hash = new HashMap<String, Integer>();

    for(Map.Entry<String, Integer> map : thirdVotes.entrySet()){
      hash.put(map.getKey(),map.getValue());
    }

      for(Map.Entry<String, Integer> map : hash.entrySet()){
        hash.put(map.getKey(), map.getValue() + firstVotes.get(map.getKey())*3 + secondVotes.get(map.getKey())*2);
      }

      int max = 0;
      String winner = "";

      for(Map.Entry<String, Integer> map : hash.entrySet()){
        if(map.getValue() > max){
          max = map.getValue();
          winner = map.getKey();
        }
      }

      return winner;
    }

  }
