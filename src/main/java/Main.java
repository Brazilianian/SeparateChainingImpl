public class Main {
    public static void main(String[] args) {
        SeparateChaining<String, String> separateChaining = new SeparateChaining<>();
        separateChaining.put("Oslo", "Norway");
        separateChaining.put("Oslo", "Norway");
        separateChaining.put("Oslo", "Norway");
        separateChaining.put("Oslo", "Norway");

        separateChaining.put("Paris", "France");
        separateChaining.put("Paris", "France");
        separateChaining.put("Paris", "France");

        separateChaining.put("Kyiv", "Ukraine");
        separateChaining.put("Kyiv", "Ukraine");
        separateChaining.put("Kyiv", "Ukraine");

        separateChaining.delete("Kyiv");
        separateChaining.delete("Kyiv");
        separateChaining.delete("Kyiv");


        separateChaining.print();
    }
}
