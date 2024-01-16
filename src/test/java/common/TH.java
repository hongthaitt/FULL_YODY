package common;

public class TH {
    public static void main(String[] args) {
        String  key ="     ht    hf     ";
        String[] splitKey = key.split("\\s+");
        for(int i=0; i <splitKey.length; i++){
            System.out.println("splitkey size: " +splitKey.length);
            System.out.println("test: " +splitKey[i]);
        }
    }
}
