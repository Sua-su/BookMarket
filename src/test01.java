
import java.io.File;
import java.io.FileOutputStream;

class Book {
    private String A, B, C, D, F, G;
    private int H;

    public Book(String isbn, String title, int price) {
        this.A = isbn;
        this.B = title;
        this.H = price;
    }
    public void setAuthor(String author) { this.C = author; }
    public void setDescription(String description) { this.D = description; }
    public void setCategory(String category) { this.F = category; }
    public void setReleatseDate(String releatseDate) { this.G = releatseDate; }
    public String toString() {
        return A + "\n" + B + "\n" + H + "\n" + C + "\n" + D + "\n" + F + "\n" + G;
    }
}

public class test01 {

    public static void main(String[] args) {
        Book[] bookList = new Book[5];

        bookList[0] = new Book("ISBN1234", "쉽게 배우는 웹 프로그래밍", 27000);
        bookList[0].setAuthor("송미영");
        bookList[0].setDescription("자바 프로그래밍");
        bookList[0].setCategory("IT 전문서");
        bookList[0].setReleatseDate("2023.01.15");

        bookList[1] = new Book("ISBN2345", "노트르담의 곱추", 33000);
        bookList[1].setAuthor("컴퓨터 사고");
        bookList[1].setDescription("컴퓨터 입문서");
        bookList[1].setCategory("실무 네트워크");
        bookList[1].setReleatseDate("2022.05.20");

        bookList[2] = new Book("ISBN3456", "스크래쳐", 31000);
        bookList[2].setAuthor("김프로그래머");
        bookList[2].setDescription("그날의 기억");
        bookList[2].setCategory("데이터 분석");
        bookList[2].setReleatseDate("2022.10.18");

        bookList[3] = new Book("ISBN4567", "자바의 정석", 30000);
        bookList[3].setAuthor("고프로그래머");
        bookList[3].setDescription("자바 완전 정복");
        bookList[3].setCategory("프로그래밍 언어");
        bookList[3].setReleatseDate("2021.12.10");

        bookList[4] = new Book("ISBN5678", "파이썬 기초", 26000);
        bookList[4].setAuthor("박데이터");
        bookList[4].setDescription("쉽게 배우는 파이썬");
        bookList[4].setCategory("프로그래밍 언어");
        bookList[4].setReleatseDate("2022.03.13");

        try {
            File file = new File("book.txt");
            if (!file.exists())
                file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);

            for (Book book : bookList) {
                String line = book.toString() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();

            System.out.println("파일 쓰기 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}